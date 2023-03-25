<template>
  <div class="app-container">
    <el-table v-loading="loading" :data="masterList">
      <el-table-column label="书名" align="center">
        <template #default='scope'>
          <a :href='hrefUrl(scope.row)'>{{scope.row.orderDetailList.productName}}</a>
        </template>
      </el-table-column>
      <el-table-column label='图片' align='center'>
        <template #default='scope'>
          <image-preview :src="scope.row.coverAddress" :width="50" :height="75"/>
        </template>
      </el-table-column>
      <el-table-column label="金额" align="center" prop="orderMoney" />
      <el-table-column label="收货时间" align="center" prop="receiveTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.receiveTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="订单状态" align="center" prop="orderStatus" >
        <template #default='scope'>
          <dict-tag :options="orderStatusDict" :value="scope.row.orderStatus" />
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button
            v-if='scope.row.orderStatus ===1'
            type="text"
            icon="Edit"
            @click='updateOrderStatus(scope.row)'
          >确认收货</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      v-model:page="queryParams.pageNum"
      v-model:limit="queryParams.pageSize"
      @pagination="getList"
    />
  </div>
</template>

<script setup>
import { listMaster, updateOrder } from '@/api/master'
import { getCurrentInstance, reactive, ref } from 'vue'
import { parseTime } from '@/utils/ruoyi.js'
import { ElMessage, ElMessageBox } from 'element-plus'

const { proxy } = getCurrentInstance();
const hrefUrl = (scope) => {
  return "/bookInfo?id=" + scope.orderDetailList.productId
}
const orderStatusDict = ref( [
  {
    value: "0",
    label: "待发货",
    raw: {
      listClass: "primary",
      isDefault: "Y",
      default: true,
    },
  },
  {
    value: "1",
    label: "待收货",
    raw: {
      listClass: "primary",
      isDefault: "Y",
      default: true,
    },
  },
  {
    value: "2",
    label: "已收货",
    raw: {
      listClass: "primary",
      isDefault: "Y",
      default: true,
    },
  },
])
const masterList = ref([]);
const orderDetailList = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const checkedOrderDetail = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref("");
const userAddr = (scope) => {
  return scope
}
const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    orderSn: null,
    customerId: null,
    shippingUser: null,
    province: null,
    city: null,
    district: null,
    address: null,
    orderMoney: null,
    districtMoney: null,
    shippingMoney: null,
    paymentMoney: null,
    shippingCompName: null,
    shippingSn: null,
    shippingTime: null,
    payTime: null,
    receiveTime: null,
    orderStatus: null,
    invoiceTime: null,
    modifiedTime: null
  }
});

const { queryParams, form } = toRefs(data);

/** 查询订单主列表 */
function getList() {
  loading.value = true;
  listMaster(queryParams.value).then(response => {
    masterList.value = response.rows;
    total.value = response.total;
    loading.value = false;
  });
}
function updateOrderStatus(scope) {
  ElMessageBox.confirm(
    "请确认以收到货物",
    "系统提示",
    {
      type: 'warning',
      confirmButtonText: "确认"
    }
  ).then(() => {
    updateOrder(scope.orderId).then(() => {
      ElMessage.success("确认收货成功")
    })
  })
}
getList()
</script>
