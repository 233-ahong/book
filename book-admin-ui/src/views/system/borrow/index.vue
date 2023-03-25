<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="图书名称" prop="bookName">
        <el-input
          v-model="queryParams.bookName"
          placeholder="请输入图书名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="归还状态" prop="borrowState">
        <el-select v-model="queryParams.borrowState" placeholder="请选择归还状态" clearable>
          <el-option
            v-for="dict in dict"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
<!--      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
        >归还图书</el-button>
      </el-col>-->
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="borrowList" @selection-change="handleSelectionChange">
      <el-table-column label="借阅人" align="center" prop="userName" />
      <el-table-column label="图书名称" align="center" prop="bookName" />
      <el-table-column label="借阅时长" align="center" prop="borrowingTime" >
        <template v-slot="scope">
          <dict-tag :options="options" :value="scope.row.borrowingTime"/>
        </template>
      </el-table-column>
      <el-table-column label="还书日期" align="center" prop="remandDate" width="180">
        <template v-slot="scope">
          <span>{{ parseTime(scope.row.remandDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="借阅时间" align="center" prop="modifiedTime" width="180">
        <template v-slot="scope">
          <span>{{ parseTime(scope.row.modifiedTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="归还状态" align="center" prop="borrowState">
        <template v-slot="scope">
          <dict-tag :options="dict" :value="scope.row.borrowState"/>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template v-slot="scope">
          <el-button
            v-if="scope.row.borrowState === 3"
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
          >确认归还</el-button>
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
  </div>
</template>

<script>
import { listBorrow, getBorrow, addBorrow, updateBorrow, returnBook } from '@/api/book/borrow'

export default {
  name: 'Borrow',
  data () {
    return {
      options: [{
        value: '1',
        label: '一周',
        raw: {
          listClass: 'primary',
          isDefault: 'Y',
          default: true
        }
      }, {
        value: '2',
        label: '两周',
        raw: {
          listClass: 'primary',
          isDefault: 'Y',
          default: true
        }
      }, {
        value: '3',
        label: '一个月',
        raw: {
          listClass: 'primary',
          isDefault: 'Y',
          default: true
        }
      }, {
        value: '4',
        label: '两个月',
        raw: {
          listClass: 'primary',
          isDefault: 'Y',
          default: true
        }
      }],
      dict: [{
        label: '已归还',
        value: '0',
        raw: {
          listClass: 'success',
          isDefault: 'Y',
          default: true
        }
      }, {
        label: '未归还',
        value: '1',
        raw: {
          listClass: 'primary',
          isDefault: 'Y',
          default: true
        }
      }, {
        label: '逾期未还',
        value: '2',
        raw: {
          listClass: 'danger',
          isDefault: 'Y',
          default: true
        }
      }, {
        label: '归还中',
        value: '3',
        raw: {
          listClass: 'success',
          isDefault: 'Y',
          default: true
        }
      }],
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      bookIds: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 借阅记录详情表格数据
      borrowList: [],
      // 弹出层标题
      title: '',
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        userId: null,
        userName: null,
        bookId: null,
        bookName: null,
        borrowingTime: null,
        remandDate: null,
        modifiedTime: null,
        borrowState: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        userId: [
          { required: true, message: '用户ID不能为空', trigger: 'blur' }
        ],
        userName: [
          { required: true, message: '借阅人不能为空', trigger: 'blur' }
        ],
        bookId: [
          { required: true, message: '图书ID不能为空', trigger: 'blur' }
        ],
        bookName: [
          { required: true, message: '图书名称不能为空', trigger: 'blur' }
        ],
        borrowingTime: [
          { required: true, message: '借阅时长不能为空', trigger: 'blur' }
        ],
        remandDate: [
          { required: true, message: '还书日期不能为空', trigger: 'blur' }
        ],
        modifiedTime: [
          { required: true, message: '借阅时间不能为空', trigger: 'blur' }
        ],
        borrowState: [
          { required: true, message: '归还状态不能为空', trigger: 'blur' }
        ]
      }
    }
  },
  created () {
    this.getList()
  },
  methods: {
    /** 查询借阅记录详情列表 */
    getList () {
      this.loading = true
      listBorrow(this.queryParams).then(response => {
        this.borrowList = response.rows
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
        borrowId: null,
        userId: null,
        userName: null,
        bookId: null,
        bookName: null,
        borrowingTime: null,
        remandDate: null,
        modifiedTime: null,
        borrowState: null
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
      this.ids = selection.map(item => item.borrowId)
      this.bookIds = selection.map(item => item.bookId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd () {
      this.reset()
      this.open = true
      this.title = '添加借阅记录详情'
    },
    /** 修改按钮操作 */
    handleUpdate (row) {
      this.reset()
      const borrowId = row.borrowId || this.ids
      getBorrow(borrowId).then(response => {
        this.form = response.data
        this.open = true
        this.title = '修改借阅记录详情'
      })
    },
    /** 提交按钮 */
    submitForm () {
      this.$refs.form.validate(valid => {
        if (valid) {
          if (this.form.borrowId != null) {
            updateBorrow(this.form).then(response => {
              this.$modal.msgSuccess('修改成功')
              this.open = false
              this.getList()
            })
          } else {
            addBorrow(this.form).then(response => {
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
      const borrowIds = row.borrowId || this.ids
      const bookIds = row.bookId || this.bookIds
      this.$modal.confirm('是否确认归还书名为 《' + row.bookName + '》 的图书？').then(function () {
        return returnBook(borrowIds, bookIds, 'admin')
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess('归还成功')
      }).catch(() => {
      })
    }
  }
}
</script>
