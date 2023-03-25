import { createApp } from "vue";
import ElementPlus from "element-plus";
import "element-plus/dist/index.css";
import * as ElementPlusIconsVue from "@element-plus/icons-vue";
import locale from "element-plus/lib/locale/lang/zh-cn";
import App from "./App.vue";
import router from "./router";
import Cookies from "js-cookie";
import "@/assets/styles/index.scss"; // global css
import Vue3Storage, { StorageType } from 'vue3-storage'

// svg图标
import "virtual:svg-icons-register";
import SvgIcon from "@/components/SvgIcon";
import elementIcons from "@/components/SvgIcon/svgicon";
import Pagination from '@/components/Pagination'
import RightToolbar from '@/components/RightToolbar'
import ImagePreview from "@/components/ImagePreview"
import DictTag from '@/components/DictTag'
import {resetForm} from '@/utils/ruoyi.js'

import plugins from "./plugins"; // plugins
import store from "./store";

const app = createApp(App);
// 使用element-plus 并且设置全局的大小
app.use(ElementPlus, {
  locale: locale,
  // 支持 large、default、small
  size: Cookies.get("size") || "default",
});
app.use(router);
app.use(plugins);
app.use(store);
app.use(elementIcons);
app.config.globalProperties.resetForm = resetForm
app.use(Vue3Storage, { namespace: "pro_", storage: StorageType.Session })
app.component("svg-icon", SvgIcon);
app.component('RightToolbar', RightToolbar)
app.component('ImagePreview', ImagePreview)
app.component('DictTag', DictTag)
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
  app.component(key, component);
}

app.component('Pagination', Pagination)

app.mount("#app");
