<template>
  <div class="login">
    <el-form
      ref="registerRef"
      :model="registerForm"
      :rules="loginRules"
      class="login-form"
    >
      <h3 class="title">云上书城注册</h3>
      <el-form-item prop="userName">
        <el-input
          v-model="registerForm.userName"
          type="text"
          auto-complete="off"
          placeholder="账号"
        >
        </el-input>
      </el-form-item>
      <el-form-item prop="nickName">
        <el-input
          v-model="registerForm.nickName"
          type="text"
          auto-complete="off"
          placeholder="昵称"
        >
        </el-input>
      </el-form-item>
      <el-form-item prop='mobilePhone'>
        <el-input
          v-model='registerForm.mobilePhone'
          type='text'
          auto-complete="off"
          placeholder="手机号"
        />
      </el-form-item>
      <el-form-item prop="password">
        <el-input
          v-model="registerForm.password"
          type="password"
          auto-complete="off"
          placeholder="密码"
        >
        </el-input>
      </el-form-item>
      <el-form-item prop="confirmPassword">
        <el-input
          v-model="registerForm.confirmPassword"
          type="password"
          auto-complete="off"
          placeholder="确认密码"
        >
        </el-input>
      </el-form-item>
      <el-form-item>
        <el-button
          :loading="loading"
          size="large"
          type="primary"
          style="width: 100%"
          @click.prevent='handleRegister'
        >
          <span v-if="!loading">注 册</span>
          <span v-else>注 册 中...</span>
        </el-button>
        <div style='width: 100%'>
          <router-link class="link-type" :to="'/login'">已有账号?立即登录</router-link>
        </div>
      </el-form-item>
    </el-form>
  </div>
</template>

<script setup>
import { getCurrentInstance, ref } from 'vue'
import { register } from '@/api/login.js'
import { ElMessageBox } from 'element-plus'

const { proxy } = getCurrentInstance();


const loading = ref(false)
const registerForm = ref({
  userName: "",
  password: "",
  confirmPassword: "",
  nickName: "",
  mobilePhone: ""
});
const equalToPassword = (rule, value, callback) => {
  if (registerForm.value.password !== value) {
    callback(new Error('两次输入的密码不一致'))
  } else {
    callback()
  }
}
const loginRules = {
  userName: [
    { required: true, trigger: "blur", message: "请输入您的账号" },
    { min: 8, max: 20, message: '用户账号长度必须介于 8 和 20 之间', trigger: 'blur' }
  ],
  password: [
    { required: true, trigger: "blur", message: "请输入您的密码" },
    { min: 16, max: 32, message: '用户密码长度必须介于 16 和 32 之间', trigger: 'blur' }
  ],
  confirmPassword: [
    { required: true, trigger: 'blur', message: '请再次输入您的密码' },
    { required: true, validator: equalToPassword, trigger: 'blur' }
  ],
  nickName: [
    { required: true, trigger: 'blur', message: '请输入您的昵称' },
    { min: 5, max: 32, message: '用户昵称长度必须介于 5 和 32 之间', trigger: 'blur' }
  ],
  mobilePhone: [
    { required: true, message: "手机号码不能为空", trigger: "blur" },
    { pattern: /^1[3|4|5|6|7|8|9][0-9]\d{8}$/, message: "请输入正确的手机号码", trigger: "blur" }
  ]
};
function handleRegister() {
  proxy.$refs.registerRef.validate((valid) => {
    if (valid) {
      loading.value = true
      register(registerForm.value).then(res => {
        ElMessageBox.confirm(
          "恭喜你: 您的账号 " + registerForm.value.userName + " 注册成功",
          "系统提示",
          {
            confirmButtonText: '确定',
            type: 'success'
          }
        ).then(() => {
          location.href = "/login";
        }).catch(() => {})
      })
    }
  })
}
</script>

<style lang="scss" scoped>
.login {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
  background-image: url("../../../assets/images/login-background.jpg");
  background-size: cover;
}
.title {
  margin: 0 auto 30px auto;
  text-align: center;
  color: #707070;
}

.login-form {
  border-radius: 6px;
  background: #ffffff;
  width: 400px;
  padding: 25px 25px 5px 25px;
  .el-input {
    height: 40px;
    input {
      height: 40px;
    }
  }
  .input-icon {
    height: 39px;
    width: 14px;
    margin-left: 0;
  }
}
.login-tip {
  font-size: 13px;
  text-align: center;
  color: #bfbfbf;
}
.login-code {
  width: 33%;
  height: 40px;
  float: right;
  img {
    cursor: pointer;
    vertical-align: middle;
  }
}
.el-login-footer {
  height: 40px;
  line-height: 40px;
  position: fixed;
  bottom: 0;
  width: 100%;
  text-align: center;
  color: #fff;
  font-family: Arial,serif;
  font-size: 12px;
  letter-spacing: 1px;
}
.login-code-img {
  height: 40px;
  padding-left: 12px;
}
.button-getCode {
  width: 33%;
  height: 40px;
  margin-left: 14px;
}
</style>
