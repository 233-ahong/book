<template>
  <div class="wrap">
    <book-classify/>
    <el-row :gutter='24' class='div-row-bc'>
      <el-col :span='2'/>
      <el-col :span='4'>
        <div class='front-main-left'>
          <span class='front-left-h3'>畅销榜</span>
          <ul class='book-list selSort'>
            <li v-for='(item,index) in sellingPlacardList' :id='index' @mouseover='cssStyleOver(index)' @mouseleave='cssStyleLeave(index)'>
              <div :class="sortClass(index+1)">{{index+1}}</div>
              <div class="sort-book-sel hide" >
                <a :href="bookInfoHref(item)" target="_Parent" class="img-href img-href-size-4"><img class="bookimg" :src="item.coverAddress" style="display: block;"></a>
                <div class="sort-book-info-1">
                  <p><a :href="bookInfoHref(item)" target="_Parent">{{item.bookName}}</a></p>
                  <p class="color-gray">{{item.bookAuthor}}</p>
                </div>
              </div>
              <div class="sort-normal">
                <span class="sort-book-name">{{item.bookName}}</span>
              </div>
            </li>
          </ul>
        </div>
      </el-col>
      <el-col :span='12'>
        <div class='front-main-center'>
          <el-row :gutter='24'>
            <el-col :span='8'/>
            <el-col :span='8'>
              <el-input
                v-model="bookName"
                placeholder="请输入书名"
                clearable
                style="width: 120px"
                @keyup.enter="handleQuery"
              />
              <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
            </el-col>
            <el-col :span='8'/>
          </el-row>
          <div class="right-column h404" v-if='bookInfo'>
            <div class="slider-first clear">
              <div class="slider-left">
                <a :href="bookHref(bookInfo.bookId)" target="_Parent" class="img-href img-href-size-1">
                  <img id="slider_first_img" :src="bookInfo.coverAddress">
                </a>
              </div>
              <div class="slider-right">
                <!-- js 对其进行内容替换 -->
                <h2 class="book-title"><a :href="bookHref(bookInfo.bookId)" target="_Parent" id="slider_first_title">{{bookInfo.bookName}}</a></h2>
                <div class="book-author" id="slider_first_author">作者 {{bookInfo.bookAuthor}}</div>
                <div class="detail" id="slider_first_detail"><a :href="bookHref(bookInfo.bookId)" target="_Parent">{{bookInfo.descript}}</a></div>
                <div class="read"><div class='dialog-price'>¥ {{bookInfo.price}}</div><a :href="bookHref(bookInfo.bookId)" target="_Parent" id="first_read_btn">立即购买/借阅 </a></div>
                <ul class="slider-list" id="slider_first">
                  <li v-for='(item,index) in bookList' :data-link="bookHref(item,bookId)" class="cur" @mouseover='bookInfoUpdate(index)'>
                    <a :href="bookHref(item.bookId)">
                      <span class="img-href img-href-size-4"><img :src="item.coverAddress"  class="bookimg" style="display: inline;"></span></a>
                  </li>
                </ul>
              </div>
            </div>
          </div>
        </div>
      </el-col>
      <el-col :span='4'>
        <div class='front-main-right'>
          <span class='front-left-h3'>借阅榜</span>
          <ul class='book-list selSort'>
            <li v-for='(item,index) in borrowPlacardList' :id='10*(index+1)' @mouseover='cssStyleOver(10*(index+1))' @mouseleave='cssStyleLeave(10*(index+1))'>
              <div :class="sortClass(index+1)">{{index+1}}</div>
              <div class="sort-book-sel hide" >
                <a :href="bookInfoHref(item)" target="_Parent" class="img-href img-href-size-4"><img class="bookimg" :src="item.coverAddress" style="display: block;"></a>
                <div class="sort-book-info-1">
                  <p><a :href="bookInfoHref(item)" target="_Parent">{{item.bookName}}</a></p>
                  <p class="color-gray">{{item.bookAuthor}}</p>
                </div>
              </div>
              <div class="sort-normal">
                <span class="sort-book-name">{{item.bookName}}</span>
              </div>
            </li>
          </ul>
        </div>
      </el-col>
      <el-col :span='2'></el-col>
    </el-row>
  </div>
</template>

<script setup>
import BookClassify from '@/views/pages/classify'
import { ref, render } from 'vue'
import { borrowPlacard, randomBook, sellingPlacard } from '@/api/routine/book.js'
import { useRouter } from 'vue-router'
const router = useRouter()
const sortClass = (index) => {
  if (index===1) {
    return "first sort"
  } else if (index===2||index===3) {
    return "second sort"
  }else {
    return "third sort"
  }
}
const bookInfo = ref('')
const bookName = ref('')
const bookList = ref([])
const sellingPlacardList = ref([])
const borrowPlacardList = ref([])
const bookInfoHref = (item) => {
  return "/bookInfo?id=" + item.bookId
}
const bookHref = (item) => {
  return "/bookInfo?id=" + item
}
function cssStyleLeave(id) {
  let elementsById1 = document.getElementById(id).children[1]
  let s1 = elementsById1.getAttribute("class").concat("hide")
  elementsById1.setAttribute("class",s1)
  let elementsById = document.getElementById(id).children[0]
  let s = elementsById.getAttribute("class").replace("hide","")
  elementsById.setAttribute("class",s)
}
function cssStyleOver(id) {
  let elementsById = document.getElementById(id).children[1]
  let s = elementsById.getAttribute("class").replace("hide","")
  elementsById.setAttribute("class",s)
}

function getSellingPlacard() {
  sellingPlacard().then(res => {
    sellingPlacardList.value = res.data
  })
}
function getBorrowPlacard() {
  borrowPlacard().then(res => {
    borrowPlacardList.value =res.data
  })
}
function getRandomBook() {
  randomBook().then(res => {
    bookList.value = res.data
    bookInfo.value = res.data[0]
  })
}
function bookInfoUpdate(index) {
  bookInfo.value = bookList.value[index]
}
function handleQuery() {
  router.push({
    path: "/book",
    query: {
      id: 0,
      bookName: bookName.value
    }
  })
}
getSellingPlacard()
getBorrowPlacard()
getRandomBook()
</script>

<style lang='scss' scoped>
@import "@/assets/styles/front.scss";
.wrap {
  padding: 0;
  margin: 0;
}
.front-main-center, .front-main-left, .front-main-right {
  background-color: #FFFFFF;
}
.div-row-bc {
  background-color: #f4f4f4;
  margin: 0;
  padding: 0;
}
.front-left-h3 {
  margin-left: 60px;
  text-align: center;
  font-size: 30px;
}
.dialog-price {
  color: #FF383D;
  float: left;
}
</style>
