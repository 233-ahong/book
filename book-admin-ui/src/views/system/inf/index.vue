<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="用户账号" prop="userName">
        <el-input
          v-model="queryParams.userName"
          placeholder="请输入用户账号"
          clearable
        />
      </el-form-item>
      <el-form-item label="用户昵称" prop="nickName">
        <el-input
          v-model="queryParams.nickName"
          placeholder="请输入用户昵称"
          clearable
        />
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input
          v-model="queryParams.password"
          placeholder="请输入密码"
          clearable
        />
      </el-form-item>
      <el-form-item label="用户状态" prop="userStats">
        <el-input
          v-model="queryParams.userStats"
          placeholder="请输入用户状态"
          clearable
        />
      </el-form-item>
      <el-form-item label="手机号" prop="mobilePhone">
        <el-input
          v-model="queryParams.mobilePhone"
          placeholder="请输入手机号"
          clearable
        />
      </el-form-item>
      <el-form-item label="邮箱" prop="userEmail">
        <el-input
          v-model="queryParams.userEmail"
          placeholder="请输入邮箱"
          clearable
        />
      </el-form-item>
      <el-form-item label="性别" prop="gender">
        <el-input
          v-model="queryParams.gender"
          placeholder="请输入性别"
          clearable
        />
      </el-form-item>
      <el-form-item label="注册时间" prop="registerTime">
        <el-date-picker clearable
          v-model="queryParams.registerTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择注册时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="最后修改时间" prop="modifiedTime">
        <el-date-picker clearable
          v-model="queryParams.modifiedTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择最后修改时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="用户角色" prop="userRole">
        <el-input
          v-model="queryParams.userRole"
          placeholder="请输入用户角色"
          clearable
        />
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

    <el-table v-loading="loading" :data="infList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键ID" align="center" prop="userInfId" />
      <el-table-column label="用户账号" align="center" prop="userName" />
      <el-table-column label="用户昵称" align="center" prop="nickName" />
      <el-table-column label="密码" align="center" prop="password" />
      <el-table-column label="用户状态" align="center" prop="userStats" />
      <el-table-column label="头像地址" align="center" prop="userAvatar" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.userAvatar" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="手机号" align="center" prop="mobilePhone" />
      <el-table-column label="邮箱" align="center" prop="userEmail" />
      <el-table-column label="性别" align="center" prop="gender" />
      <el-table-column label="注册时间" align="center" prop="registerTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.registerTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="最后修改时间" align="center" prop="modifiedTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.modifiedTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="用户角色" align="center" prop="userRole" />
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

    <!-- 添加或修改用户信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="用户账号" prop="userName">
          <el-input v-model="form.userName" placeholder="请输入用户账号" />
        </el-form-item>
        <el-form-item label="用户昵称" prop="nickName">
          <el-input v-model="form.nickName" placeholder="请输入用户昵称" />
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input v-model="form.password" placeholder="请输入密码" />
        </el-form-item>
        <el-form-item label="用户状态" prop="userStats">
          <el-input v-model="form.userStats" placeholder="请输入用户状态" />
        </el-form-item>
        <el-form-item label="头像地址">
          <image-upload v-model="form.userAvatar"/>
        </el-form-item>
        <el-form-item label="手机号" prop="mobilePhone">
          <el-input v-model="form.mobilePhone" placeholder="请输入手机号" />
        </el-form-item>
        <el-form-item label="邮箱" prop="userEmail">
          <el-input v-model="form.userEmail" placeholder="请输入邮箱" />
        </el-form-item>
        <el-form-item label="性别" prop="gender">
          <el-input v-model="form.gender" placeholder="请输入性别" />
        </el-form-item>
        <el-form-item label="注册时间" prop="registerTime">
          <el-date-picker clearable
            v-model="form.registerTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择注册时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="最后修改时间" prop="modifiedTime">
          <el-date-picker clearable
            v-model="form.modifiedTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择最后修改时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="用户角色" prop="userRole">
          <el-input v-model="form.userRole" placeholder="请输入用户角色" />
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
import { listInf, getInf, delInf, addInf, updateInf } from '@/api/system/inf'

export default {
  name: 'Inf',
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
      // 用户信息表格数据
      infList: [],
      // 弹出层标题
      title: '',
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        userName: null,
        nickName: null,
        password: null,
        userStats: null,
        userAvatar: null,
        mobilePhone: null,
        userEmail: null,
        gender: null,
        registerTime: null,
        modifiedTime: null,
        userRole: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        userName: [
          { required: true, message: '用户账号不能为空', trigger: 'blur' }
        ],
        nickName: [
          { required: true, message: '用户昵称不能为空', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '密码不能为空', trigger: 'blur' }
        ],
        mobilePhone: [
          { required: true, message: '手机号不能为空', trigger: 'blur' }
        ]
      }
    }
  },
  created () {
    this.getList()
  },
  methods: {
    /** 查询用户信息列表 */
    getList () {
      this.loading = true
      listInf(this.queryParams).then(response => {
        this.infList = response.rows
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
        userInfId: null,
        userName: null,
        nickName: null,
        password: null,
        userStats: null,
        userAvatar: null,
        mobilePhone: null,
        userEmail: null,
        gender: null,
        registerTime: null,
        modifiedTime: null,
        userRole: null
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
      this.ids = selection.map(item => item.userInfId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd () {
      this.reset()
      this.open = true
      this.title = '添加用户信息'
    },
    /** 修改按钮操作 */
    handleUpdate (row) {
      this.reset()
      const userInfId = row.userInfId || this.ids
      getInf(userInfId).then(response => {
        this.form = response.data
        this.open = true
        this.title = '修改用户信息'
      })
    },
    /** 提交按钮 */
    submitForm () {
      this.$refs.form.validate(valid => {
        if (valid) {
          if (this.form.userInfId != null) {
            updateInf(this.form).then(response => {
              this.$modal.msgSuccess('修改成功')
              this.open = false
              this.getList()
            })
          } else {
            addInf(this.form).then(response => {
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
      const userInfIds = row.userInfId || this.ids
      this.$modal.confirm('是否确认删除用户信息编号为"' + userInfIds + '"的数据项？').then(function () {
        return delInf(userInfIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess('删除成功')
      }).catch(() => {})
    }
  }
}
</script>
