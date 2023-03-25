import tab from './tab'
import modal from './modal'
import download from './download'

export default {
  install (Vue) {
    // 页签操作
    Vue.prototype.$tab = tab
    // 认证对象
    // 模态框对象
    Vue.prototype.$modal = modal
    // 下载文件
    Vue.prototype.$download = download
  }
}
