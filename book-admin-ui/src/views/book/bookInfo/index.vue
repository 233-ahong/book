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
      <el-form-item label="图书出版社" prop="bookPress">
        <el-input
          v-model="queryParams.bookPress"
          placeholder="请输入图书出版社"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="图书作者" prop="bookAuthor">
        <el-input
          v-model="queryParams.bookAuthor"
          placeholder="请输入图书作者"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="分类" prop="bookCategory">
        <el-select v-model="queryParams.bookCategory" placeholder="请选择分类" clearable>
          <el-option
            v-for="dict in bookCategory"
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
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"/>
    </el-row>

    <el-row :gutter="20">
      <el-col
        :span="6"
        v-for="(item,index) in bookInfoList"
        :key="index"
      >
        <el-card>
          <el-tooltip class="item" effect="dark"  placement="top-start">
            <div slot="content" class="div-descript">简介：{{item.descript}}</div>
            <div>
              <div>
                <el-image :src = item.coverAddress  class="el-image-add"/>
              </div>
              <el-descriptions :column = 2>
                <el-descriptions-item label="图书名称">{{item.bookName}}</el-descriptions-item>
                <el-descriptions-item label="图书出版社">{{item.bookPress}}</el-descriptions-item>
                <el-descriptions-item label="图书售格">{{ item.price }}</el-descriptions-item>
                <el-descriptions-item label="图书分类">
                  <dict-tag :options="bookCategory" :value="item.bookCategory"/>
                </el-descriptions-item>
                <el-descriptions-item label="图书余量">{{item.bookRemaining}}</el-descriptions-item>
              </el-descriptions>
              <el-button type="primary" plain class="el-button-div" @click="openDialog(item.bookId,item.bookName)">借阅</el-button>
            </div>
          </el-tooltip>
        </el-card>
      </el-col>
    </el-row>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
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
  </div>
</template>

<script>
import { listBookInfo, getBookInfo, delBookInfo, addBookInfo, updateBookInfo, borrowBooks } from '@/api/book/bookInfo'

export default {
  name: 'BookInfo',
  data () {
    return {
      id: '',
      bookName: '',
      options: [{
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
      }],
      value: '',
      bookCategory: [
        {
          label: '科幻',
          value: '0',
          raw: {
            listClass: 'primary',
            isDefault: 'Y',
            default: true
          }
        }],
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
      // 图书信息表格数据
      bookInfoList: [],
      // 弹出层标题
      title: '',
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        bookCore: null,
        bookName: null,
        barCode: null,
        bookPress: null,
        bookAuthor: null,
        coverAddress: null,
        bookCategory: null,
        price: null,
        publishStatus: null,
        auditStatus: null,
        bookPage: null,
        descript: null,
        indate: null,
        modifiedTime: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        bookCore: [
          { required: true, message: '图书编码不能为空', trigger: 'blur' }
        ],
        bookName: [
          { required: true, message: '图书名称不能为空', trigger: 'blur' }
        ],
        barCode: [
          { required: true, message: '国条码不能为空', trigger: 'blur' }
        ],
        bookPress: [
          { required: true, message: '图书出版社不能为空', trigger: 'blur' }
        ],
        bookAuthor: [
          { required: true, message: '图书作者不能为空', trigger: 'blur' }
        ],
        coverAddress: [
          { required: true, message: '封面地址不能为空', trigger: 'blur' }
        ],
        bookCategory: [
          { required: true, message: '分类不能为空', trigger: 'change' }
        ],
        price: [
          { required: true, message: '图书销售价格不能为空', trigger: 'blur' }
        ],
        publishStatus: [
          { required: true, message: '上下架状态不能为空', trigger: 'blur' }
        ],
        auditStatus: [
          { required: true, message: '审核状态不能为空', trigger: 'blur' }
        ],
        bookPage: [
          { required: true, message: '图书页数不能为空', trigger: 'blur' }
        ],
        descript: [
          { required: true, message: '图书描述不能为空', trigger: 'blur' }
        ],
        indate: [
          { required: true, message: '图书录入时间不能为空', trigger: 'blur' }
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
    /** 查询图书信息列表 */
    getList () {
      this.loading = true
      listBookInfo(this.queryParams).then(response => {
        this.bookInfoList = response.rows
        this.total = response.total
        this.loading = false
      })
    },
    // 取消按钮
    cancel () {
      this.open = false
      this.value = ''
      this.reset()
    },
    // 表单重置
    reset () {
      this.form = {
        bookId: null,
        bookCore: null,
        bookName: null,
        barCode: null,
        bookPress: null,
        bookAuthor: null,
        coverAddress: null,
        bookCategory: null,
        price: null,
        publishStatus: 0,
        auditStatus: 0,
        bookPage: null,
        descript: null,
        indate: null,
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
      this.ids = selection.map(item => item.bookId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd () {
      this.reset()
      this.open = true
      this.title = '添加图书信息'
    },
    /** 修改按钮操作 */
    handleUpdate (row) {
      this.reset()
      const bookId = row.bookId || this.ids
      getBookInfo(bookId).then(response => {
        this.form = response.data
        this.open = true
        this.title = '修改图书信息'
      })
    },
    /** 提交按钮 */
    submitForm () {
      this.$refs.form.validate(valid => {
        if (valid) {
          if (this.form.bookId != null) {
            updateBookInfo(this.form).then(response => {
              this.$modal.msgSuccess('修改成功')
              this.open = false
              this.getList()
            })
          } else {
            addBookInfo(this.form).then(response => {
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
      const bookIds = row.bookId || this.ids
      this.$modal.confirm('是否确认删除图书信息编号为"' + bookIds + '"的数据项？').then(function () {
        return delBookInfo(bookIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess('删除成功')
      }).catch(() => {})
    },
    openDialog (id, bookName) {
      this.bookName = bookName
      this.title = '是否借阅 ' + bookName + ' 这本书'
      this.id = id
      this.open = true
    },
    borrowBooks () {
      if (this.value === null || this.value === '') {
        this.$modal.msg('请选择借阅时长')
      } else {
        borrowBooks(this.id, this.value).then(res => {
          this.open = false
          this.$modal.msgSuccess('图书 ' + this.bookName + ' 借阅成功')
          this.bookName = ''
        })
      }
    }
  }
}
</script>

<style lang="scss" scoped>
.el-image-add{
  width: 250px;
  height: 250px;
}
.el-button-div {
margin-left: 100px
}
.div-descript {
  width: 300px;
}
</style>
