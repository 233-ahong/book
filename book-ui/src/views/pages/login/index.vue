<template>
  <div class="login">
    <el-form
      ref="loginRef"
      :model="loginForm"
      :rules="loginRules"
      class="login-form"
    >
      <h3 class="title">云上书城登录</h3>
      <div v-if="isPhone">
        <el-form-item prop="username">
          <el-input
            v-model="loginForm.username"
            type="text"
            auto-complete="off"
            placeholder="手机号"
          >
          </el-input>
        </el-form-item>
        <el-form-item prop="code">
          <el-input
            v-model="loginForm.code"
            auto-complete="off"
            placeholder="验证码"
            style="width: 63%"
            @keyup.enter.native="handleLogin"
          >
          </el-input>
          <el-button class='button-getCode' @click="getCodes" :disabled="codeOff">{{codeString}}</el-button>
        </el-form-item>
      </div>
      <div v-if="!isPhone">
        <el-form-item prop="username">
          <el-input
            v-model="loginForm.username"
            type="text"
            auto-complete="off"
            placeholder="账号"
          >
          </el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input
            v-model="loginForm.password"
            type="password"
            auto-complete="off"
            placeholder="密码"
            @keyup.enter.native="handleLogin"
          >
          </el-input>
        </el-form-item>
        <div>
          <el-checkbox v-model="loginForm.rememberMe" style="margin: 0 0 25px 0">记住密码</el-checkbox>
<!--          <el-link :underline='false' type="warning" style='float: right'>忘记密码</el-link>-->
        </div>
      </div>
      <el-form-item style="width: 100%">
        <el-button
          :loading="loading"
          size="large"
          type="primary"
          style="width: 100%"
          @click.prevent="handleLogin"
        >
          <span v-if="!loading">登 录</span>
          <span v-else>登 录 中...</span>
        </el-button>
        <div style='width: 100%'>
          <router-link class="link-type" :to="'/register'">立即注册</router-link>
          <div style='float: right'>
            <a class="link-type" @click='changeLoginStyle'>{{loginStyle}}</a>
          </div>
        </div>
      </el-form-item>
    </el-form>
  </div>
</template>

<script setup>
import Cookies from "js-cookie";
import { encrypt, decrypt } from "@/utils/jsencrypt";
import useUserStore from "@/store/modules/user";
import { computed, getCurrentInstance, ref } from 'vue'
import { useRouter } from "vue-router";
import { getCode } from '@/api/login.js'
import { ElMessage } from 'element-plus'

const userStore = useUserStore();
const router = useRouter();
const { proxy } = getCurrentInstance();

const loginForm = ref({
  username: "admin123",
  password: "admin123",
  rememberMe: false,
  code: "",
  uuid: "",
});

const loginRules = {
  username: [{ required: true, trigger: "blur", message: "请输入您的账号" }],
  password: [{ required: true, trigger: "blur", message: "请输入您的密码" }],
  code: [{ required: true, trigger: "change", message: "请输入验证码" }],
};

const codeOff = ref(false)
const seconds = ref(60)
const codeString = ref('获取验证码')
const codeUrl = ref("");
const loading = ref(false);
// 验证码开关
const captchaEnabled = ref(false);
// 注册开关
const redirect = ref(true);
const isPhone = ref(false)
const loginStyle = computed(() =>{
  if(isPhone.value){
    return '密码登录'
  } else {
    return '手机号登录'
  }
})
function handleLogin() {
  proxy.$refs.loginRef.validate((valid) => {
    if (valid) {
      loading.value = true;
      // 勾选了需要记住密码设置在 cookie 中设置记住用户名和密码
      if (loginForm.value.rememberMe) {
        Cookies.set("username", loginForm.value.username, { expires: 30 });
        Cookies.set("password", encrypt(loginForm.value.password), {
          expires: 30,
        });
        Cookies.set("rememberMe", loginForm.value.rememberMe, { expires: 30 });
      } else {
        // 否则移除
        Cookies.remove("username");
        Cookies.remove("password");
        Cookies.remove("rememberMe");
      }
      if (!isPhone.value) {
        userStore.loginByPassword(loginForm.value).then(() => {
          userStore.getInfo().then(() => {
            router.push({ path: "/index" });
          })
        }).catch(() => {
          loading.value = false;
        })
      } else {
        userStore.loginByPhone(loginForm.value).then(() => {
          userStore.getInfo().then(() => {
            router.push({ path: "/index" });
          })
        }).catch(() => {
          loading.value = false;
        })
      }
    }
  });
}

function dateDown (i) {
  if (i <= 0) {
    codeOff.value = false
    codeString.value = '获取验证码'
  } else {
    codeOff.value = true
    codeString.value = i + '秒后重新获取'
    i--
    setTimeout(() => {
      dateDown(i)
    }, 1000)
  }
}

function getCodes() {
  if (judgePhoneNo(loginForm.value.username)) {
    getCode(loginForm.value.username).then(res => {
      if (res.code !== 200 ){
        ElMessage.error(res.msg)
      } else {
        ElMessage.success("验证码发送成功")
        loginForm.value.uuid = res.msg
        console.log(res.msg)
        codeOff.value = true
        dateDown(seconds.value)
      }
    })
  } else {
    ElMessage.error('请输入正确的手机号')
  }
}

function getCookie() {
  const username = Cookies.get("username");
  const password = Cookies.get("password");
  const rememberMe = Cookies.get("rememberMe");
  loginForm.value = {
    username: username === undefined ? loginForm.value.username : username,
    password:
      password === undefined ? loginForm.value.password : decrypt(password),
    rememberMe: rememberMe === undefined ? false : Boolean(rememberMe),
  };
}
function changeLoginStyle() {
  isPhone.value = !isPhone.value
}

function judgePhoneNo (phoneNo) {
  var reg = /^1[3-9][0-9]\d{8}$/
  return reg.test(phoneNo)
}

getCookie();
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
