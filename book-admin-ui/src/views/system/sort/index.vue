<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择状态" clearable>
          <el-option
            v-for="dict in dictStatus"
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

    <el-table v-loading="loading" :data="sortList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="分类ID" align="center" prop="sortId" />
      <el-table-column label="分类标签" align="center" prop="sortLabel" />
      <el-table-column label="分类键值" align="center" prop="sortValue" />
      <el-table-column label="样式属性" align="center" prop="cssClass" />
      <el-table-column label="表格回显样式" align="center" prop="listClass">
        <template v-slot="scope">
          <dict-tag :options="listClassOptions" :value="scope.row.listClass"/>
        </template>
      </el-table-column>
      <el-table-column label="状态" align="center" prop="status">
        <template slot-scope="scope">
          <dict-tag :options="dictStatus" :value="scope.row.status"/>
        </template>
      </el-table-column>
      <el-table-column label="备注" align="center" prop="remark" />
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
            @click="handleDelete(scope.row)"
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

    <!-- 添加或修改书籍分类对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="分类标签" prop="sortLabel">
          <el-input v-model="form.sortLabel" placeholder="请输入分类标签" />
        </el-form-item>
        <el-form-item label="分类键值" prop="sortValue">
          <el-input v-model="form.sortValue" placeholder="请输入分类键值" />
        </el-form-item>
        <el-form-item label="样式属性" prop="cssClass">
          <el-input v-model="form.cssClass" placeholder="请输入样式属性" />
        </el-form-item>
        <el-form-item label="表格回显样式" prop="listClass">
          <el-select v-model="form.listClass" placeholder="请选择表格回显样式">
            <el-option
              v-for="item in listClassOptions"
              :key="item.value"
              :label="item.label + '(' + item.value + ')'"
              :value="item.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="状态">
          <el-radio-group v-model="form.status">
            <el-radio
              v-for="dict in dictStatus"
              :key="dict.value"
              :label="dict.value"
            >{{dict.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
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
import { listSort, getSort, delSort, addSort, updateSort } from '@/api/system/sort'

export default {
  name: 'Sort',
  data () {
    return {
      dictStatus: [
        {
          label: '正常',
          value: '0',
          raw:
            {
              dictLabel: '正常',
              dictValue: '0',
              listClass: 'primary',
              isDefault: 'Y',
              status: '0',
              default: true
            }
        }, {
        label: '停用',
        value: '1',
        raw:
          {
            dictLabel: '停用',
            dictValue: '1',
            listClass: 'danger',
            isDefault: 'N',
            status: '0',
            default: false
          }
      }],
      listClassOptions: [
        {
          value: 'default',
          label: '默认',
          raw: {
            listClass: 'default',
            isDefault: 'Y',
            default: true
          }
        },
        {
          value: 'primary',
          label: '主要',
          raw: {
            listClass: 'primary'
          }
        },
        {
          value: 'success',
          label: '成功',
          raw: {
            listClass: 'success',
            isDefault: 'N',
            default: false
          }
        },
        {
          value: 'info',
          label: '信息',
          raw: {
            listClass: 'info',
            isDefault: 'N',
            default: false
          }
        },
        {
          value: 'warning',
          label: '警告',
          raw: {
            listClass: 'warning',
            isDefault: 'N',
            default: false
          }
        },
        {
          value: 'danger',
          label: '危险',
          raw: {
            listClass: 'danger',
            isDefault: 'N',
            default: false
          }
        }
      ],
      dictHide: {},
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
      // 书籍分类表格数据
      sortList: [],
      // 弹出层标题
      title: '',
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        sortLabel: null,
        sortValue: null,
        cssClass: null,
        listClass: null,
        isDefault: null,
        status: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        sortLabel: [
          { required: true, message: '分类标签不能为空', trigger: 'blur' }
        ],
        sortValue: [
          { required: true, message: '分类键值不能为空', trigger: 'blur' }
        ]
      }
    }
  },
  created () {
    this.getList()
  },
  methods: {
    /** 查询书籍分类列表 */
    getList () {
      this.loading = true
      listSort(this.queryParams).then(response => {
        this.sortList = response.rows
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
        sortId: null,
        sortLabel: null,
        sortValue: null,
        cssClass: null,
        listClass: null,
        isDefault: '0',
        status: '0',
        remark: null
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
      this.ids = selection.map(item => item.sortId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd () {
      this.reset()
      this.open = true
      this.title = '添加书籍分类'
    },
    /** 修改按钮操作 */
    handleUpdate (row) {
      this.reset()
      const sortId = row.sortId || this.ids
      getSort(sortId).then(response => {
        this.form = response.data
        this.open = true
        this.title = '修改书籍分类'
      })
    },
    /** 提交按钮 */
    submitForm () {
      this.$refs.form.validate(valid => {
        if (valid) {
          if (this.form.sortId != null) {
            updateSort(this.form).then(response => {
              this.$modal.msgSuccess('修改成功')
              this.open = false
              this.getList()
            })
          } else {
            addSort(this.form).then(response => {
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
      const sortIds = row.sortId || this.ids
      this.$modal.confirm('是否确认删除书籍分类编号为"' + sortIds + '"的数据项？').then(function () {
        return delSort(sortIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess('删除成功')
      }).catch(() => {})
    }
  }
}
</script>
