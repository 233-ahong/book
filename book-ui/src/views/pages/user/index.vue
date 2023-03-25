<template>
  <div class="app-container" v-if='loading'>
    <el-row :gutter="20">
      <el-col :span="6" :xs="24">
        <el-card class="box-card">
          <template v-slot:header>
            <div class="clearfix">
              <span>个人信息</span>
            </div>
          </template>
          <div>
            <div class="text-center">
              <user-avatar :user="state.user" />
            </div>
            <ul class="list-group list-group-striped">
              <li class="list-group-item">
                <svg-icon icon-class="user" />用户名称
                <div class="pull-right">{{ state.user.userName }}</div>
              </li>
              <li class="list-group-item">
                <svg-icon icon-class="phone" />手机号码
                <div class="pull-right">{{ state.user.mobilePhone }}</div>
              </li>
              <li class="list-group-item">
                <svg-icon icon-class="email" />用户邮箱
                <div class="pull-right">{{ state.user.email }}</div>
              </li>
              <li class="list-group-item">
                <svg-icon icon-class="date" />创建日期
                <div class="pull-right">{{ state.user.registerTime }}</div>
              </li>
            </ul>
          </div>
        </el-card>
      </el-col>
      <el-col :span="18" :xs="24">
        <el-card>
          <template v-slot:header>
            <div class="clearfix">
              <span>基本资料</span>
            </div>
          </template>
          <el-tabs v-model="activeTab">
            <el-tab-pane label="基本资料" name="userinfo">
              <userInfo :user="state.user" />
            </el-tab-pane>
<!--            <el-tab-pane label="修改密码" name="resetPwd">
              <resetPwd />
            </el-tab-pane>-->
            <el-tab-pane label='收货地址' name='calculate'>
              <user-addr />
            </el-tab-pane>
            <el-tab-pane label='订单列表' name='orderInfo'>
              <order-info />
            </el-tab-pane>
          </el-tabs>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script setup name="Profile">
import UserAvatar from "./userAvatar.vue";
import UserInfo from "./userInfo.vue";
import ResetPwd from "./resetPwd.vue";
import UserAddr from './userAddr.vue'
import OrderInfo from './orderInfo.vue'
import { getInfo } from '@/api/login.js'
import { reactive, ref } from 'vue'
const activeTab = ref("userinfo");
const state = reactive({
  user: {},
  roleGroup: {},
  postGroup: {}
});
const loading = ref(false)

function getUser() {
  getInfo().then(response => {
    state.user = response.user;
    state.roleGroup = response.roleGroup;
    state.postGroup = response.postGroup;
    console.log(state.user)
    loading.value = true
  });
}
getUser();
</script>
