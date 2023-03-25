<template>
  <div>
    <el-row :gutter='24'>
      <el-col :span='2'/>
      <el-col :span='20'>
        <div>
          <el-table v-loading="loading" :data="carts" >
            <el-table-column label='图书名称' align='center' prop='bookName'>
              <template #default='scope'>
                <el-link :href='bookInfoUrl(scope.row)'>
                  {{ scope.row.bookName }}
                </el-link>
              </template>
            </el-table-column>
            <el-table-column label="图片" align='center' prop='coverAddress'>
              <template #default="scope">
                <image-preview :src="scope.row.coverAddress" :width="50" :height="50"/>
              </template>
            </el-table-column>
            <el-table-column label="加入购物车数量" align="center" prop="productAmount" >
              <template #default="scope">
                <el-input-number class='el-number' v-model="scope.row.productAmount" :min="1" :max="10" @change="handleChange(scope.row)" />
              </template>
            </el-table-column>
            <el-table-column label="书籍价格" align="center" prop="price" />
            <el-table-column label='图书出版社' align='center' prop='bookPress' />
            <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
              <template #default="scope">
                <el-button
                  type="text"
                  icon="Edit"
                  @click="handleBuy(scope.row)"
                >购买</el-button>
                <el-button
                  type="text"
                  icon="Delete"
                  @click="handleDelete(scope.row)"
                >移出购物车</el-button>
              </template>
            </el-table-column>
          </el-table>

          <pagination
            v-show="total>0"
            :total="total"
            v-model:page="queryParams.pageNum"
            v-model:limit="queryParams.pageSize"
            @pagination="getCarts"
          />

          <el-dialog :title='title' v-model="open" width="680px" append-to-body>
            <div>
              <el-form ref="detailRefs" :model="form" :rules="rules" label-width="80px">
                <el-form-item label='收货人' prop='shippingUser' >
                  <el-input v-model='form.shippingUser' placeholder='请输入收货人姓名'/>
                </el-form-item>
                <el-form-item label='收货地址' prop='customerAddrId' >
                  <el-radio-group v-model="form.customerAddrId" class="ml-4">
                    <el-radio v-for='item in addrList' :label="item.customerAddrId" size="large">
                      {{ item.province + " " + item.city + " " + item.district + " " + item.address }}
                    </el-radio>
                  </el-radio-group>
                </el-form-item>
                <el-form-item label="购买数量" prop="productAmount">
                  <el-input-number :min='1' :max='10' v-model="form.productAmount"/>
                </el-form-item>
                <el-form-item label="总价" prop="price">
                  <div class='dialog-price'>¥ {{price}}</div>
                </el-form-item>
              </el-form>
              <div class="dialog-footer">
                <el-button type="primary" @click="submitForm">确 定</el-button>
                <el-button @click="cancel">取 消</el-button>
              </div>
            </div>
          </el-dialog>
        </div>
      </el-col>
      <el-col :span='2'/>
    </el-row>
  </div>
</template>

<script setup>
import { delCart, getCart, updateCart } from '@/api/cart.js'
import { computed, getCurrentInstance, reactive, ref, toRefs } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { useRouter } from 'vue-router'
import { listAddr } from '@/api/addr.js'
import { useStorage } from 'vue3-storage'
import { addOrder } from '@/api/order.js'

const { proxy } = getCurrentInstance();
const storage = useStorage()
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const total = ref(0);
const title = ref('图书购买')
const router = useRouter()
const cartInfo = ref('')
const addrList = ref([])
const addrId = ref('')
const price = computed(() => {
  return form.value.productAmount * cartInfo.value.price
})
const bookInfoUrl = (row) => {
  return '/bookInfo?id=' + row.bookId
}
const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 30,
  },
  rules: {
    shippingUser: [
      { required: true, message: "收货人姓名不能为空", trigger: "blur" }
    ],
    productAmount: [
      { required: true, message: "购买数量不能为空", trigger: "blur" }
    ],
    customerAddrId: [
      { required: true, message: "收货地址不能为空", trigger: "blur" }
    ]
  }
});

const { queryParams, form, rules } = toRefs(data);

const carts = ref([])

function cancel() {
  form.value = {
  };
  proxy.resetForm("detailRefs");
}
function getList() {
  listAddr(queryParams.value).then(response => {
    addrList.value = response.rows;
  });
}
function getCarts() {
  getCart().then(res => {
    carts.value = res.rows
    total.value = res.total
    loading.value = false
  })
}

function handleDelete(data) {
  ElMessageBox.confirm(
    '是否确认删除购物车编号为"' + data.cartId + '"的数据项？',
    "系统提示",
    {
      type: 'warning',
      confirmButtonText: "确定"
    }
  ).then(function () {
    return delCart(data.cartId)
  }).then(() => {
    getCarts()
    this.$modal.msgSuccess('删除成功')
  }).catch(() => {})
}
function handleBuy(data) {
  cartInfo.value = data
  this.form.shippingUser = storage.getStorageSync("userInfo").nickName
  this.form.productAmount = data.productAmount
  this.form.productId = data.productId
  this.form.cartId = data.cartId
  this.form.price = cartInfo.value.price
  open.value = true
}
function submitForm() {
  proxy.$refs["detailRefs"].validate(valid => {
    if (valid) {
      ElMessageBox.confirm(
        "确定购买 " + cartInfo.value.productAmount + " 本 " + cartInfo.value.bookName + " 吗?",
        "系统提示",
        {
          type: 'warning',
          confirmButtonText: '确定'
        }
      ).then(() => {
        console.log(form.value)
        addOrder(form.value).then(res => {
          ElMessage.success("购买成功!")
        })
        open.value = false
        getCarts()
      })
    }
  })
}
function handleChange(row) {
  const data = {
    cartId: row.cartId,
    productAmount: row.productAmount
  }
  updateCart(data)
}
getCarts()
getList()
</script>

<style lang='scss' scoped>
.el-number {
  width: 100px;
}
.dialog-price {
  font-size: 30px;
  color: #FF383D;
}
</style>
