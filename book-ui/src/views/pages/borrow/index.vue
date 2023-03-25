<template>
  <div>
    <el-row :gutter='24'>
      <el-col :span='2'/>
      <el-col :span='20'>
        <div class="app-container">
          <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch">
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
              <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
              <el-button icon="Refresh" @click="resetQuery">重置</el-button>
            </el-form-item>
          </el-form>
          <el-table v-loading="loading" :data="list" >
            <el-table-column label='图书名称' align='center' prop='bookName' >
              <template #default='scope'>
                <el-link :href='bookInfoUrl(scope.row)' >
                  {{ scope.row.bookName }}
                </el-link>
              </template>
            </el-table-column>
            <el-table-column label="图片" align='center' prop='coverAddress'>
              <template #default="scope">
                <image-preview :src="scope.row.coverAddress" :width="50" :height="75"/>
              </template>
            </el-table-column>
            <el-table-column label="借阅时间" align="center" prop="modifiedTime">
              <template #default="scope">
                <span>{{ parseTime(scope.row.modifiedTime,'{y}-{m}-{d}') }}</span>
              </template>
            </el-table-column>
            <el-table-column label='借阅时长' align='center' prop='borrowingTime'>
              <template #default="scope">
                <dict-tag :options="borrowDate" :value="scope.row.borrowingTime"/>
              </template>
            </el-table-column>
            <el-table-column label='剩余归还时间' align='center'>
              <template #default='scope'>
                <el-tag v-if='scope.row.borrowState === 1'>{{remainingTime(scope.row)}} 天</el-tag>
              </template>
            </el-table-column>
            <el-table-column label='归还状态' align='center' prop='borrowState'>
              <template #default="scope">
                <dict-tag :options="dict" :value="scope.row.borrowState"/>
              </template>
            </el-table-column>
            <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
              <template #default="scope">
                <el-button
                  v-if="scope.row.borrowState === 1"
                  type="text"
                  icon="Edit"
                  @click="handleBorrow(scope.row)"
                >归还图书</el-button>
              </template>
            </el-table-column>
          </el-table>

          <pagination
            v-show="total>0"
            :total="total"
            v-model:page="queryParams.pageNum"
            v-model:limit="queryParams.pageSize"
            @pagination="getBorrows"
          />
        </div>
      </el-col>
      <el-col :span='2'/>
    </el-row>
  </div>
</template>

<script setup>
import { getCurrentInstance, ref } from 'vue'
const { proxy } = getCurrentInstance();
import { parseTime } from '@/utils/ruoyi.js'
const loading = ref(true)
const showSearch = ref(true);
const list = ref([])
const borrowDate = ref([{
  value: '1',
  label: '一周',
  elTagType: "primary"
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
}])
const dict = ref([{
  label: '已归还',
  value: '0',
  elTagType: "success"
}, {
  label: '未归还',
  value: '1',
  elTagType: "primary"
}, {
  label: '逾期未还',
  value: '2',
  elTagType: "danger"
}, {
  label: '归还中',
  value: '3',
  elTagType: "default"
}])
const remainingTime = (row) => {
  let date = new Date();
  let until= new Date(row.modifiedTime);
  let days = (date-until)/1000/3600/24;
  let day = Math.floor(days);
  if (row.borrowingTime===1){
    return -day + 7
  }
  if (row.borrowingTime===2){
    return -day + 14
  }
  if (row.borrowingTime===3){
    return -day + 30
  }
  if (row.borrowingTime===4){
    return -day + 60
  }
}
const bookInfoUrl = (row) => {
  return '/bookInfo?id=' + row.bookId
}
const total = ref(0);
const queryParams = ref({
  pageNum: 1,
  pageSize: 30,
  bookName: '',
  borrowState: ''
})
import { getBorrow, returnBook } from '@/api/borrow.js'
import { now } from '@vueuse/core'
import { ElMessage, ElMessageBox } from 'element-plus'
function getBorrows() {
  getBorrow(queryParams.value).then(res => {
    list.value = res.rows
    total.value =res.total
    loading.value = false
  })
}
function handleQuery () {
  queryParams.value.pageNum = 1
  getBorrows()
}
function resetQuery() {
  proxy.resetForm("queryForm")
}
function handleBorrow(data) {
  ElMessageBox.confirm(
    '是否确认归还书名为 《' + data.bookName + '》 的图书？',
    '系统提示',
    {
      confirmButtonText: '确定',
      type: 'warning'
    }
  ).then(function () {
    return returnBook(data.borrowId)
  }).then(() => {
    getBorrows()
    ElMessage.success("提交归还成功，请等待管理员确认归还")
  })
}
getBorrows()
</script>

<style scoped>

</style>
