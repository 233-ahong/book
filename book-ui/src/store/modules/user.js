import { logout, getInfo, loginByPassword, loginByPhone } from '@/api/login'
import { getToken, setToken, removeToken } from "@/utils/auth";
import defAva from "@/assets/images/profile.jpg";
import { defineStore } from "pinia";
import {useStorage} from 'vue3-storage'

const useUserStore = defineStore("user", {
  state: () => ({
    token: getToken(),
    name: "",
    user: "",
    avatar: "",
    roles: [],
    permissions: [],
  }),
  actions: {
    // 密码登录
    loginByPassword(userInfo) {
      const username = userInfo.username.trim();
      const password = userInfo.password;
      const code = userInfo.code;
      const uuid = userInfo.uuid;
      return new Promise((resolve, reject) => {
        loginByPassword(userInfo)
          .then((res) => {
            setToken(res.msg);
            this.token = res.msg;
            resolve();
          })
          .catch((error) => {
            reject(error);
          });
      });
    },
    loginByPhone(userInfo){
      return new Promise((resolve, reject) => {
        loginByPhone(userInfo)
          .then((res) => {
            setToken(res.msg);
            this.token = res.msg;
            resolve();
          })
          .catch((error) => {
            reject(error);
          });
      });
    },
    // 获取用户信息
    getInfo() {
      return new Promise((resolve, reject) => {
        getInfo()
          .then((res) => {
            const user = res.user
            useStorage().setStorageSync("userInfo",res.user)
            this.user = user;
            const avatar =
              user.avatar == "" || user.avatar == null
                ? defAva
                : import.meta.env.VITE_APP_BASE_API + user.avatar;

            if (res.roles && res.roles.length > 0) {
              // 验证返回的roles是否是一个非空数组
              this.roles = res.roles;
              this.permissions = res.permissions;
            } else {
              this.roles = ["ROLE_DEFAULT"];
            }
            this.name = user.userName;
            this.avatar = avatar;
            resolve(res);
          })
          .catch((error) => {
            reject(error);
          });
      });
    },
    // 退出系统
    logOut() {
      return new Promise((resolve, reject) => {
        logout(this.token)
          .then(() => {
            this.token = "";
            this.roles = [];
            this.permissions = [];
            removeToken();
            resolve();
          })
          .catch((error) => {
            reject(error);
          });
      });
    },
  },
});

export default useUserStore;
