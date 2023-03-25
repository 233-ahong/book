<template>
  <div>
    <el-row :gutter="24">
      <el-col :span="2"/>
      <el-col :span='20'>
        <ul>
          <li v-for='item in infoList' class='bookList'>
            <a class='book' @click='openBook(item)'>
              <div class='book-cover'>
                <img :src='item.coverAddress' alt=''/>
              </div>
              <div class='book-detail'>
                <h3>{{item.bookName}}</h3>
                <h4>作者 {{item.bookAuthor}} </h4>
                <h5>{{item.descript}}</h5>
              </div>
            </a>
          </li>
        </ul>
        <pagination
          v-show="total>0"
          :total="total"
          v-model:page="queryParams.pageNum"
          v-model:limit="queryParams.pageSize"
          @pagination="getList"
        />
      </el-col>
      <el-col :span='2'/>
    </el-row>
  </div>
</template>

<script setup>

import { getCurrentInstance, reactive, ref, toRefs } from 'vue'
import { getBookList } from '@/api/routine/book.js'
import { useRouter, useRoute } from 'vue-router'

const { proxy } = getCurrentInstance();
const router = useRouter()
const route = useRoute()
const dateOpen = ref(false)
const bookQuantity = ref(1)
const infoList = ref([]);
const open = ref(false);
const showSearch = ref(true);
const ids = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref('')
const date = ref('1')
const data = reactive({
  bookInfo: null,
  queryParams: {
    pageNum: 1,
    pageSize: 30,
    bookCategory: null,
    bookName: null
  }
});

function getList() {
  if (route.query.bookName !== null && route.query.bookName!== undefined) {
    queryParams.value.bookName = route.query.bookName
  }
  if (route.query.id !== '0'){
    queryParams.value.bookCategory = route.query.id
  }
  getBookList(queryParams.value).then(response => {
    infoList.value = response.rows;
    total.value = response.total;
  });
}
function openBook(item) {
  router.push({
    path: '/bookInfo',
    query: {
      id: item.bookId
    }
  })
}
const { queryParams, bookInfo } = toRefs(data)
getList()
</script>

<style scoped>
.bookList .book .book-detail {
  display: inline;
  width: 263px;
  height: 100px;
  padding: 0 16px 0 16px;
}
.bookList .book .book-cover {
  width: 75px;
  height: 100px;
}
.book {
  display: flex;
  width: 100%;
}
.bookList {
  height: 135px;
  width: 400px;
  padding: 7.5px 15px 7.5px 15px;
}
ul {
  display: flex;
  flex-wrap: wrap;
  list-style: none;
  padding-left: 20px;
}
li {
  flex: none;
  width: 8rem;
  box-sizing: border-box;
  padding: 0.15rem 0.15rem;
  border-radius: 0.05rem;
}
li:hover {
  background-color: #EEEEEE;
}
h3 {
  margin-top: 4px;
  font-weight: 500;
  color: #22293e;
  line-height: 20px;
}
h4 {
  color: rgba(34, 41, 62, 0.8);
  margin-top: -4px;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 1;
  -webkit-box-orient: vertical;
  overflow: hidden;
}
h5 {
  margin-top: -10px;
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 3;
  -webkit-box-orient: vertical;
  color: rgba(34, 41, 62, 0.6);
}
h3 {
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 1;
  -webkit-box-orient: vertical;
  overflow: hidden;
}
img {
  box-shadow: 0 4px 8px 0 rgb(205 210 219 / 90%);
  border-radius: 1px;
  width: 100%;
  height: 100%;
}
a{
  text-decoration: none;
  margin: 10px 0 10px 0;
}
.dialog-image {
  width: 300px;
  height: 400px;
  margin-left: -8px;
}
.dialog-des {
  height: 80px;
}
.dialog-descript {
  height: 96px;
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 5;
  -webkit-box-orient: vertical;
  color: rgba(34, 41, 62, 0.6);
}
.dialog-price {
  font-size: 20px;
  color: #FF383D;
}
.dialog-row {
  margin: 10px 0 0 0;
}
.dialog-number {
  width: 100px;
  height: 40px;
  margin-right: 10px;
}
.dialog-borrow {
  margin-left: 12px;
  margin-top: 10px;
}
</style>
