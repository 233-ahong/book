<template>
  <div>
    <el-row :gutter='24' v-if='bookInfo'>
      <el-col :span='2'/>
      <el-col :span='8'>
        <div>
          <img class='dialog-image' :src="bookInfo.coverAddress" title="bookInfo.bookName" :alt="bookInfo.bookName" >
        </div>
      </el-col>
      <el-col :span='12'>
        <div class='dialog-des'>
          <h1>{{bookInfo.bookName}}</h1>
          <el-row class='dialog-row' :gutter='24'>
            <el-col :span='6'>
              <h4>作者: {{bookInfo.bookAuthor}}</h4>
            </el-col>
            <el-col :span='8'>
              <h4>出版社: {{bookInfo.bookPress}}</h4>
            </el-col>
            <el-col :span='10'>
              <h4>上架时间:{{bookInfo.indate}}</h4>
            </el-col>
          </el-row>
          <hr/>
          <el-row class='dialog-row' :gutter='24'>
            <el-col :span='24'>
              <div class='dialog-descript'>  {{bookInfo.descript}}</div>
            </el-col>
          </el-row>
          <hr/>
          <el-row class='dialog-row' :gutter='24'>
            <el-col :span='24'>
              <div class='dialog-price'>¥ {{bookInfo.price}}</div>
            </el-col>
          </el-row>
          <el-row class='dialog-row' :gutter='24'>
            <el-col :span='24'>
              <el-input-number
                class='dialog-number'
                v-model='bookQuantity'
                controls-position='right'
                :min='1'
                :max='10'/>
              <el-button size='large' icon='ShoppingCart' color='#FF2832' @click='addCarts'>加入购物车</el-button>
              <el-button size='large' icon='Goods' color='#FF2832' @click='openBuyDialog' plain>立即购买</el-button>
            </el-col>
            <el-button @click='openBorrow' class='dialog-borrow' size='large' icon='WalletFilled'>立即借阅</el-button>
          </el-row>
        </div>
      </el-col>
      <el-col :span='2'/>
    </el-row>

    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <div>
        <label>借阅时长</label>
        <el-select v-model="value" placeholder="请选择借阅时长">
          <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </div>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="borrowBooks">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <el-dialog :title='title' v-model="openBuy" width="680px" append-to-body>
      <div>
        <el-form ref="detailRefss" :model="form" :rules="rules" label-width="80px">
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
            <el-input-number :min='1' :max='10' v-model="form.productAmount" />
          </el-form-item>
          <el-form-item label="总价" prop="price">
            <div class='dialog-price'>¥ {{price}}</div>
          </el-form-item>
        </el-form>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button  @click='cancel'>取 消</el-button>
        </div>
      </div>
    </el-dialog>
  </div>
</template>

<script setup>
import { computed, getCurrentInstance, reactive, ref, toRefs } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { getBookInfo } from '@/api/routine/book.js'
import { useRoute } from 'vue-router'
import { borrowBook } from '@/api/borrow.js'
import { addCart } from '@/api/cart.js'
import { listAddr } from '@/api/addr.js'
import { useStorage } from 'vue3-storage'
import { addOrder } from '@/api/order.js'
const { proxy } = getCurrentInstance();
const storage = useStorage()
const title = ref('')
const router =useRoute()
const bookQuantity = ref(1)
const addrList = ref([])
const open = ref(false)
const openBuy = ref(false)
const value = ref('')
const options = ref( [{
    value: '1',
    label: '一周'
  }, {
    value: '2',
    label: '两周'
  }, {
    value: '3',
    label: '一个月'
  }, {
    value: '4',
    label: '两个月'
  }])
const data = reactive({
  bookInfo: null,
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
})
const { queryParams, form, rules, bookInfo } = toRefs(data);
const price   = computed(() => {
  return form.value.productAmount * bookInfo.value.price
})

function openBuyDialog() {
  getList()
  title.value = '用户信息'
  form.value.shippingUser = storage.getStorageSync("userInfo").nickName
  form.value.price = bookInfo.value.price
  form.value.productId = bookInfo.value.bookId
  form.value.productAmount = bookQuantity.value
  openBuy.value = true
}
function openBorrow() {
  title.value = '请选择借阅时长'
  open.value = true
}
function getList() {
  listAddr().then(response => {
    addrList.value = response.rows;
  });
}
function borrowBooks() {
  if (value.value === null || value.value === '') {
    ElMessage.error('请选择借阅时长')
  } else {
    borrowBook(bookInfo.value.bookId,value.value).then(res => {
      open.value = false
      ElMessage.success('图书 ' + bookInfo.value.bookName + ' 借阅成功')
    })
  }
}
function cancel() {
  open.value = false
  openBuy.value = false
  value.value = ''
}
function getBook() {
  const id = router.query.id
  getBookInfo(id).then(res => {
    bookInfo.value = res.data
  })
}
function addCarts() {
  addCart(bookInfo.value.bookId, bookQuantity.value).then(() => {
    ElMessage.success('加入购物车成功')
  })
}
function submitForm() {
  proxy.$refs["detailRefss"].validate(valid => {
    if (valid) {
      ElMessageBox.confirm(
        "确定购买 " + form.value.productAmount + " 本 " + bookInfo.value.bookName + " 吗?",
        "系统提示",
        {
          type: 'warning',
          confirmButtonText: '确定'
        }
      ).then(() => {
        addOrder(form.value).then(res => {
          ElMessage.success("购买成功!")
          open.value = false
          openBuy.value = false
        })
      })
    }
  })
}
getBook()
</script>

<style lang='scss' scoped>
.dialog-image {
  width: 300px;
  height: 400px;
  margin-left: -8px;
}
.dialog-des {
  height: 80px;
}
.dialog-descript {
  height: 96px;
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 5;
  -webkit-box-orient: vertical;
  color: rgba(34, 41, 62, 0.6);
}
.dialog-price {
  font-size: 20px;
  color: #FF383D;
}
.dialog-row {
  margin: 10px 0 0 0;
}
.dialog-number {
  width: 100px;
  height: 40px;
  margin-right: 10px;
}
.dialog-borrow {
  margin-left: 12px;
  margin-top: 10px;
}
</style>
