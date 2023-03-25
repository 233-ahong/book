import request from '@/utils/request.js'

export function addOrder(data) {
  return request({
    url: '/order/add',
    method: 'put',
    data: data
  })
}

export function listMaster() {
  return request({
    url: '/order/getOrderMaster',
    method: 'get'
  })
}
