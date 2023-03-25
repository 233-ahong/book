<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="图书编码" prop="bookCore">
        <el-input
          v-model="queryParams.bookCore"
          placeholder="请输入图书编码"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
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
            v-for="dict in bookSort"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="图书余量" prop="bookRemaining">
        <el-input
          v-model="queryParams.bookRemaining"
          placeholder="请输入图书余量"
          clearable
          @keyup.enter.native="handleQuery"
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

    <el-table v-loading="loading" :data="infoList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="图书ID" align="center" prop="bookId" />
      <el-table-column label="图书编码" align="center" prop="bookCore" />
      <el-table-column label="图书名称" align="center" prop="bookName" />
      <el-table-column label="国条码" align="center" prop="barCode" />
      <el-table-column label="图书出版社" align="center" prop="bookPress" />
      <el-table-column label="图书作者" align="center" prop="bookAuthor" />
      <el-table-column label="封面" align="center" prop="coverAddress" width="100">
        <template slot-scope="scope">
          <el-image :src="scope.row.coverAddress" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="封面地址" align="center" prop="coverAddress" show-overflow-tooltip/>
      <el-table-column label="分类" align="center" prop="bookCategory">
        <template slot-scope="scope">
          <dict-tag :options="bookSort" :value="scope.row.bookCategory"/>
        </template>
      </el-table-column>
      <el-table-column label="图书销售价格" align="center" prop="price" />
      <el-table-column label="上下架状态" align="center" prop="publishStatus" />
      <el-table-column label="审核状态" align="center" prop="auditStatus" />
      <el-table-column label="图书页数" align="center" prop="bookPage" />
      <el-table-column label="图书描述" align="center" prop="descript"  show-overflow-tooltip/>
      <el-table-column label="图书录入时间" align="center" prop="indate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.indate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="最后修改时间" align="center" prop="modifiedTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.modifiedTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="图书余量" align="center" prop="bookRemaining" />
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

    <!-- 添加或修改图书信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="图书名称" prop="bookName">
          <el-input v-model="form.bookName" placeholder="请输入图书名称" />
        </el-form-item>
        <el-form-item label="图书出版社" prop="bookPress">
          <el-input v-model="form.bookPress" placeholder="请输入图书出版社" />
        </el-form-item>
        <el-form-item label="图书作者" prop="bookAuthor">
          <el-input v-model="form.bookAuthor" placeholder="请输入图书作者" />
        </el-form-item>
        <el-form-item label="封面地址" prop="coverAddress">
          <img-upload @input="getImgUrl"/>
        </el-form-item>
        <el-form-item label="分类" prop="bookCategory">
          <el-select v-model="form.bookCategory" placeholder="请选择分类">
            <el-option
              v-for="dict in bookSort"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="图书销售价格" prop="price">
          <el-input v-model="form.price" placeholder="请输入图书销售价格" />
        </el-form-item>
        <el-form-item label="图书页数" prop="bookPage">
          <el-input v-model="form.bookPage" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="图书描述" prop="descript">
          <el-input v-model="form.descript" type="textarea" placeholder="请输入内容" />
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
import { listInfo, getInfo, delInfo, addInfo, updateInfo } from '@/api/system/book/book'
import { getDict } from '@/api/system/dict/dict'
import ImgUpload from '@/components/ImageUpload'

export default {
  name: 'Info',
  components: { ImgUpload },
  data () {
    return {
      bookSort: [],
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
      infoList: [],
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
        modifiedTime: null,
        bookRemaining: null
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
          { required: true, message: '分类不能为空', trigger: 'blur' }
        ],
        price: [
          { required: true, message: '图书销售价格不能为空', trigger: 'blur' }
        ],
        publishStatus: [
          { required: true, message: '上下架状态：0下架1上架不能为空', trigger: 'blur' }
        ],
        auditStatus: [
          { required: true, message: '审核状态：0未审核，1已审核不能为空', trigger: 'blur' }
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
    this.getDict()
  },
  methods: {
    /** 查询图书信息列表 */
    getList () {
      this.loading = true
      listInfo(this.queryParams).then(response => {
        this.infoList = response.rows
        this.total = response.total
        this.loading = false
      })
    },
    // 获取分类字典
    getDict () {
      getDict().then(res => {
        this.bookSort = res.data
        console.log(res)
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
        modifiedTime: null,
        bookRemaining: null
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
      getInfo(bookId).then(response => {
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
            updateInfo(this.form).then(response => {
              this.$modal.msgSuccess('修改成功')
              this.open = false
              this.getList()
            })
          } else {
            addInfo(this.form).then(response => {
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
        return delInfo(bookIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess('删除成功')
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport () {
      this.download('system/info/export', {
        ...this.queryParams
      }, `info_${new Date().getTime()}.xlsx`)
    },
    getImgUrl (data) {
      this.form.coverAddress = data
    },
    abc () {
      return 'abc'
    }
  }
}
</script>
