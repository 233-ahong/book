<template>
  <div class='div-classify'>
    <el-row :gutter='24'>
      <el-col :span='2' class='el-col-blank'/>
      <el-col :span='20'>
        <div >
          <el-link @click='linkClick(item.categoryCode)' :underline='false' v-for='item in classifyList' :style='linkClass'>{{item.categoryName}}</el-link>
        </div>
      </el-col>
      <el-col :span='2' class='el-col-blank'/>
    </el-row>
  </div>
</template>

<script setup>
import { getClassifyList } from '@/api/routine/classify.js'
import { reactive, ref } from 'vue'
import { useRouter } from 'vue-router'

const router = useRouter()
const classifyList = ref([])
const linkClass = ref('color: #FFFFFF; margin: 10px 20px 0 0;')
const data = reactive({
  queryParams: {
    categoryName: null,
    categoryCode: null,
    parentId: null,
    categoryStatus: null
  }
})

function getList(data) {
  getClassifyList(data).then(response => {
    classifyList.value = response.data
    console.log(response)
  })
}
function linkClick(data) {
  router.push({
    path: '/book',
    query: {
      id: data
    }
  });
}
getList()
</script>

<style lang='scss' scoped>
.div-classify {
  background-color: #F64243;
  padding: 0;
  margin: 0;
  height: 40px;
}
.el-col-blank {
  margin: 0;
  padding: 0;
}
.link-classify {
  color: #FFFFFF;
  margin: 10px 20px 0 0;
}
.link-classify-active{
  color: #1482f0;
}
</style>
