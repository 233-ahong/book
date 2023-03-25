<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="订单编号" prop="orderSn">
        <el-input
          v-model="queryParams.orderSn"
          placeholder="请输入订单编号"
          clearable
        />
      </el-form-item>
      <el-form-item label="下单人ID" prop="customerId">
        <el-input
          v-model="queryParams.customerId"
          placeholder="请输入下单人ID"
          clearable
        />
      </el-form-item>
      <el-form-item label="收货人姓名" prop="shippingUser">
        <el-input
          v-model="queryParams.shippingUser"
          placeholder="请输入收货人姓名"
          clearable
        />
      </el-form-item>
      <el-form-item label="省" prop="province">
        <el-input
          v-model="queryParams.province"
          placeholder="请输入省"
          clearable
        />
      </el-form-item>
      <el-form-item label="市" prop="city">
        <el-input
          v-model="queryParams.city"
          placeholder="请输入市"
          clearable
        />
      </el-form-item>
      <el-form-item label="区" prop="district">
        <el-input
          v-model="queryParams.district"
          placeholder="请输入区"
          clearable
        />
      </el-form-item>
      <el-form-item label="地址" prop="address">
        <el-input
          v-model="queryParams.address"
          placeholder="请输入地址"
          clearable
        />
      </el-form-item>
      <el-form-item label="订单金额" prop="orderMoney">
        <el-input
          v-model="queryParams.orderMoney"
          placeholder="请输入订单金额"
          clearable
        />
      </el-form-item>
      <el-form-item label="优惠金额" prop="districtMoney">
        <el-input
          v-model="queryParams.districtMoney"
          placeholder="请输入优惠金额"
          clearable
        />
      </el-form-item>
      <el-form-item label="运费金额" prop="shippingMoney">
        <el-input
          v-model="queryParams.shippingMoney"
          placeholder="请输入运费金额"
          clearable
        />
      </el-form-item>
      <el-form-item label="支付金额" prop="paymentMoney">
        <el-input
          v-model="queryParams.paymentMoney"
          placeholder="请输入支付金额"
          clearable
        />
      </el-form-item>
      <el-form-item label="快递公司名称" prop="shippingCompName">
        <el-input
          v-model="queryParams.shippingCompName"
          placeholder="请输入快递公司名称"
          clearable
        />
      </el-form-item>
      <el-form-item label="快递单号" prop="shippingSn">
        <el-input
          v-model="queryParams.shippingSn"
          placeholder="请输入快递单号"
          clearable
        />
      </el-form-item>
      <el-form-item label="发货时间" prop="shippingTime">
        <el-date-picker clearable
          v-model="queryParams.shippingTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择发货时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="支付时间" prop="payTime">
        <el-date-picker clearable
          v-model="queryParams.payTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择支付时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="收货时间" prop="receiveTime">
        <el-date-picker clearable
          v-model="queryParams.receiveTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择收货时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="发票抬头" prop="invoiceTime">
        <el-input
          v-model="queryParams.invoiceTime"
          placeholder="请输入发票抬头"
          clearable
        />
      </el-form-item>
      <el-form-item label="最后修改时间" prop="modifiedTime">
        <el-date-picker clearable
          v-model="queryParams.modifiedTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择最后修改时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
        >删除</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="masterList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="订单ID" align="center" prop="orderId" />
      <el-table-column label="订单编号" align="center" prop="orderSn" />
      <el-table-column label="下单人ID" align="center" prop="customerId" />
      <el-table-column label="收货人姓名" align="center" prop="shippingUser" />
      <el-table-column label="省" align="center" prop="province" />
      <el-table-column label="市" align="center" prop="city" />
      <el-table-column label="区" align="center" prop="district" />
      <el-table-column label="地址" align="center" prop="address" />
      <el-table-column label="订单金额" align="center" prop="orderMoney" />
      <el-table-column label="优惠金额" align="center" prop="districtMoney" />
      <el-table-column label="运费金额" align="center" prop="shippingMoney" />
      <el-table-column label="支付金额" align="center" prop="paymentMoney" />
      <el-table-column label="快递公司名称" align="center" prop="shippingCompName" />
      <el-table-column label="快递单号" align="center" prop="shippingSn" />
      <el-table-column label="发货时间" align="center" prop="shippingTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.shippingTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="支付时间" align="center" prop="payTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.payTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="收货时间" align="center" prop="receiveTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.receiveTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="订单状态" align="center" prop="orderStatus" />
      <el-table-column label="发票抬头" align="center" prop="invoiceTime" />
      <el-table-column label="最后修改时间" align="center" prop="modifiedTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.modifiedTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改订单主对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="订单编号 yyyymmddnnnnnnnn" prop="orderSn">
          <el-input v-model="form.orderSn" placeholder="请输入订单编号 yyyymmddnnnnnnnn" />
        </el-form-item>
        <el-form-item label="下单人ID" prop="customerId">
          <el-input v-model="form.customerId" placeholder="请输入下单人ID" />
        </el-form-item>
        <el-form-item label="收货人姓名" prop="shippingUser">
          <el-input v-model="form.shippingUser" placeholder="请输入收货人姓名" />
        </el-form-item>
        <el-form-item label="省" prop="province">
          <el-input v-model="form.province" placeholder="请输入省" />
        </el-form-item>
        <el-form-item label="市" prop="city">
          <el-input v-model="form.city" placeholder="请输入市" />
        </el-form-item>
        <el-form-item label="区" prop="district">
          <el-input v-model="form.district" placeholder="请输入区" />
        </el-form-item>
        <el-form-item label="地址" prop="address">
          <el-input v-model="form.address" placeholder="请输入地址" />
        </el-form-item>
        <el-form-item label="订单金额" prop="orderMoney">
          <el-input v-model="form.orderMoney" placeholder="请输入订单金额" />
        </el-form-item>
        <el-form-item label="优惠金额" prop="districtMoney">
          <el-input v-model="form.districtMoney" placeholder="请输入优惠金额" />
        </el-form-item>
        <el-form-item label="运费金额" prop="shippingMoney">
          <el-input v-model="form.shippingMoney" placeholder="请输入运费金额" />
        </el-form-item>
        <el-form-item label="支付金额" prop="paymentMoney">
          <el-input v-model="form.paymentMoney" placeholder="请输入支付金额" />
        </el-form-item>
        <el-form-item label="快递公司名称" prop="shippingCompName">
          <el-input v-model="form.shippingCompName" placeholder="请输入快递公司名称" />
        </el-form-item>
        <el-form-item label="快递单号" prop="shippingSn">
          <el-input v-model="form.shippingSn" placeholder="请输入快递单号" />
        </el-form-item>
        <el-form-item label="发货时间" prop="shippingTime">
          <el-date-picker clearable
            v-model="form.shippingTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择发货时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="支付时间" prop="payTime">
          <el-date-picker clearable
            v-model="form.payTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择支付时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="收货时间" prop="receiveTime">
          <el-date-picker clearable
            v-model="form.receiveTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择收货时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="发票抬头" prop="invoiceTime">
          <el-input v-model="form.invoiceTime" placeholder="请输入发票抬头" />
        </el-form-item>
        <el-form-item label="最后修改时间" prop="modifiedTime">
          <el-date-picker clearable
            v-model="form.modifiedTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择最后修改时间">
          </el-date-picker>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listMaster, getMaster, delMaster, addMaster, updateMaster } from '@/api/system/master'

export default {
  name: 'Master',
  data () {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 订单主表格数据
      masterList: [],
      // 弹出层标题
      title: '',
      // 是否显示弹出层
      open: false,
      // 查询参数
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
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        orderSn: [
          { required: true, message: '订单编号 yyyymmddnnnnnnnn不能为空', trigger: 'blur' }
        ],
        customerId: [
          { required: true, message: '下单人ID不能为空', trigger: 'blur' }
        ],
        shippingUser: [
          { required: true, message: '收货人姓名不能为空', trigger: 'blur' }
        ],
        province: [
          { required: true, message: '省不能为空', trigger: 'blur' }
        ],
        city: [
          { required: true, message: '市不能为空', trigger: 'blur' }
        ],
        district: [
          { required: true, message: '区不能为空', trigger: 'blur' }
        ],
        address: [
          { required: true, message: '地址不能为空', trigger: 'blur' }
        ],
        orderMoney: [
          { required: true, message: '订单金额不能为空', trigger: 'blur' }
        ],
        districtMoney: [
          { required: true, message: '优惠金额不能为空', trigger: 'blur' }
        ],
        shippingMoney: [
          { required: true, message: '运费金额不能为空', trigger: 'blur' }
        ],
        paymentMoney: [
          { required: true, message: '支付金额不能为空', trigger: 'blur' }
        ],
        createTime: [
          { required: true, message: '下单时间不能为空', trigger: 'blur' }
        ],
        orderStatus: [
          { required: true, message: '订单状态不能为空', trigger: 'blur' }
        ],
        modifiedTime: [
          { required: true, message: '最后修改时间不能为空', trigger: 'blur' }
        ]
      }
    }
  },
  created () {
    this.getList()
  },
  methods: {
    /** 查询订单主列表 */
    getList () {
      this.loading = true
      listMaster(this.queryParams).then(response => {
        this.masterList = response.rows
        this.total = response.total
        this.loading = false
      })
    },
    // 取消按钮
    cancel () {
      this.open = false
      this.reset()
    },
    // 表单重置
    reset () {
      this.form = {
        orderId: null,
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
        createTime: null,
        shippingTime: null,
        payTime: null,
        receiveTime: null,
        orderStatus: 0,
        invoiceTime: null,
        modifiedTime: null
      }
      this.resetForm('form')
    },
    /** 搜索按钮操作 */
    handleQuery () {
      this.queryParams.pageNum = 1
      this.getList()
    },
    /** 重置按钮操作 */
    resetQuery () {
      this.resetForm('queryForm')
      this.handleQuery()
    },
    // 多选框选中数据
    handleSelectionChange (selection) {
      this.ids = selection.map(item => item.orderId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd () {
      this.reset()
      this.open = true
      this.title = '添加订单主'
    },
    /** 修改按钮操作 */
    handleUpdate (row) {
      this.reset()
      const orderId = row.orderId || this.ids
      getMaster(orderId).then(response => {
        this.form = response.data
        this.open = true
        this.title = '修改订单主'
      })
    },
    /** 提交按钮 */
    submitForm () {
      this.$refs.form.validate(valid => {
        if (valid) {
          if (this.form.orderId != null) {
            updateMaster(this.form).then(response => {
              this.$modal.msgSuccess('修改成功')
              this.open = false
              this.getList()
            })
          } else {
            addMaster(this.form).then(response => {
              this.$modal.msgSuccess('新增成功')
              this.open = false
              this.getList()
            })
          }
        }
      })
    },
    /** 删除按钮操作 */
    handleDelete (row) {
      const orderIds = row.orderId || this.ids
      this.$modal.confirm('是否确认删除订单主编号为"' + orderIds + '"的数据项？').then(function () {
        return delMaster(orderIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess('删除成功')
      }).catch(() => {})
    }
  }
}
</script>
