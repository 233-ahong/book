<template>
  <div>
    <p />
    <el-row :gutter="24">
      <el-col :span="2"/>
      <el-col :span="5">
        <router-link to='/index'>
          <book-logo />
        </router-link>
      </el-col>
      <el-col :span='10' v-if='!name'/>
      <el-col :span='5' v-if='!name'>
        <div class='el-login-div' >
          <el-link  class='link-div-left' href='/register' >
            <svg-icon icon-class="user" class="el-input__icon input-icon" />
            注册
          </el-link>
          |
          <el-link class='link-div-right' href='/login'>
            <svg-icon icon-class="password" class="el-input__icon input-icon" />
            登录
          </el-link>
        </div>
      </el-col>
      <el-col :span='5' v-if='name'/>
      <el-col :span='10' v-if='name'>
        <div class='el-login-div'>
          <el-link  class='link-div-left' href='/borrow' >
            <svg-icon icon-class="log" class="el-input__icon input-icon" />
            借阅记录
          </el-link>
          |
          <el-link class='link-div-right' href='/cart'>
            <svg-icon icon-class="shopping" class="el-input__icon input-icon" />
            购物车
          </el-link>
          |
          <el-link  class='link-div-left' href='/userinfo' >
            <svg-icon icon-class="user" class="el-input__icon input-icon" />
            {{name.userName}}
          </el-link>
          |
          <el-link  class='link-div-right' @click='logout' >
            <el-icon class="el-input__icon input-icon"><SwitchButton /></el-icon>
            登出
          </el-link>
        </div>
      </el-col>
      <el-col :span="2"/>
    </el-row>
  </div>
</template>

<script setup>
import BookLogo from "@/layout/logo";
import { useStorage } from 'vue3-storage'
import { ElMessage, ElMessageBox } from 'element-plus'
import useUserStore from '@/store/modules/user.js'
import { computed } from 'vue'
const storage = useStorage()
const name = storage.getStorageSync("userInfo")

function logout () {
  ElMessageBox.confirm(
    "确定退出登录?",
    "系统提示",
    {
      type: 'warning',
      confirmButtonText: '确定'
    }
  ).then(() => {
    useStorage().setStorageSync("userInfo","")
    useUserStore().logOut().then(() => {
      ElMessage.success("退出登录成功!")
    })
  })
}
</script>

<style lang='scss' scoped>
.input-icon {
  width: 25px;
  height: 15px;
  padding-right: 10px;
}
.link-div-right {
  margin-left: 10px;
}
.link-div-left {
  margin-right: 10px;
}
.el-login-div {
  margin-top: 20px;
}
</style>
