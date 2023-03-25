<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="订单表ID" prop="orderId">
        <el-input
          v-model="queryParams.orderId"
          placeholder="请输入订单表ID"
          clearable
        />
      </el-form-item>
      <el-form-item label="书籍ID" prop="productId">
        <el-input
          v-model="queryParams.productId"
          placeholder="请输入书籍ID"
          clearable
        />
      </el-form-item>
      <el-form-item label="书名" prop="productName">
        <el-input
          v-model="queryParams.productName"
          placeholder="请输入书名"
          clearable
        />
      </el-form-item>
      <el-form-item label="购买数量" prop="productCnt">
        <el-input
          v-model="queryParams.productCnt"
          placeholder="请输入购买数量"
          clearable
        />
      </el-form-item>
      <el-form-item label="购买单价" prop="productPrice">
        <el-input
          v-model="queryParams.productPrice"
          placeholder="请输入购买单价"
          clearable
        />
      </el-form-item>
      <el-form-item label="商品重量" prop="weight">
        <el-input
          v-model="queryParams.weight"
          placeholder="请输入商品重量"
          clearable
        />
      </el-form-item>
      <el-form-item label="优惠分摊金额" prop="feeMoney">
        <el-input
          v-model="queryParams.feeMoney"
          placeholder="请输入优惠分摊金额"
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

    <el-table v-loading="loading" :data="detailList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="订单详情表ID" align="center" prop="orderDetailId" />
      <el-table-column label="订单表ID" align="center" prop="orderId" />
      <el-table-column label="书籍ID" align="center" prop="productId" />
      <el-table-column label="书名" align="center" prop="productName" />
      <el-table-column label="购买数量" align="center" prop="productCnt" />
      <el-table-column label="购买单价" align="center" prop="productPrice" />
      <el-table-column label="商品重量" align="center" prop="weight" />
      <el-table-column label="优惠分摊金额" align="center" prop="feeMoney" />
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

    <!-- 添加或修改订单详情对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="订单表ID" prop="orderId">
          <el-input v-model="form.orderId" placeholder="请输入订单表ID" />
        </el-form-item>
        <el-form-item label="书籍ID" prop="productId">
          <el-input v-model="form.productId" placeholder="请输入书籍ID" />
        </el-form-item>
        <el-form-item label="书名" prop="productName">
          <el-input v-model="form.productName" placeholder="请输入书名" />
        </el-form-item>
        <el-form-item label="购买数量" prop="productCnt">
          <el-input v-model="form.productCnt" placeholder="请输入购买数量" />
        </el-form-item>
        <el-form-item label="购买单价" prop="productPrice">
          <el-input v-model="form.productPrice" placeholder="请输入购买单价" />
        </el-form-item>
        <el-form-item label="商品重量" prop="weight">
          <el-input v-model="form.weight" placeholder="请输入商品重量" />
        </el-form-item>
        <el-form-item label="优惠分摊金额" prop="feeMoney">
          <el-input v-model="form.feeMoney" placeholder="请输入优惠分摊金额" />
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
import { listDetail, getDetail, delDetail, addDetail, updateDetail } from '@/api/system/detail'

export default {
  name: 'Detail',
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
      // 订单详情表格数据
      detailList: [],
      // 弹出层标题
      title: '',
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        orderId: null,
        productId: null,
        productName: null,
        productCnt: null,
        productPrice: null,
        weight: null,
        feeMoney: null,
        modifiedTime: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        orderId: [
          { required: true, message: '订单表ID不能为空', trigger: 'blur' }
        ],
        productId: [
          { required: true, message: '书籍ID不能为空', trigger: 'blur' }
        ],
        productName: [
          { required: true, message: '书名不能为空', trigger: 'blur' }
        ],
        productCnt: [
          { required: true, message: '购买数量不能为空', trigger: 'blur' }
        ],
        productPrice: [
          { required: true, message: '购买单价不能为空', trigger: 'blur' }
        ],
        feeMoney: [
          { required: true, message: '优惠分摊金额不能为空', trigger: 'blur' }
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
    /** 查询订单详情列表 */
    getList () {
      this.loading = true
      listDetail(this.queryParams).then(response => {
        this.detailList = response.rows
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
        orderDetailId: null,
        orderId: null,
        productId: null,
        productName: null,
        productCnt: null,
        productPrice: null,
        weight: null,
        feeMoney: null,
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
      this.ids = selection.map(item => item.orderDetailId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd () {
      this.reset()
      this.open = true
      this.title = '添加订单详情'
    },
    /** 修改按钮操作 */
    handleUpdate (row) {
      this.reset()
      const orderDetailId = row.orderDetailId || this.ids
      getDetail(orderDetailId).then(response => {
        this.form = response.data
        this.open = true
        this.title = '修改订单详情'
      })
    },
    /** 提交按钮 */
    submitForm () {
      this.$refs.form.validate(valid => {
        if (valid) {
          if (this.form.orderDetailId != null) {
            updateDetail(this.form).then(response => {
              this.$modal.msgSuccess('修改成功')
              this.open = false
              this.getList()
            })
          } else {
            addDetail(this.form).then(response => {
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
      const orderDetailIds = row.orderDetailId || this.ids
      this.$modal.confirm('是否确认删除订单详情编号为"' + orderDetailIds + '"的数据项？').then(function () {
        return delDetail(orderDetailIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess('删除成功')
      }).catch(() => {})
    }
  }
}
</script>
