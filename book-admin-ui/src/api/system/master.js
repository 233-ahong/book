import request from '@/utils/request'

// 查询订单主列表
export function listMaster (query) {
  return request({
    url: '/system/master/list',
    method: 'get',
    params: query
  })
}

// 查询订单主详细
export function getMaster (orderId) {
  return request({
    url: '/system/master/' + orderId,
    method: 'get'
  })
}

// 新增订单主
export function addMaster (data) {
  return request({
    url: '/system/master',
    method: 'post',
    data: data
  })
}

// 修改订单主
export function updateMaster (data) {
  return request({
    url: '/system/master',
    method: 'put',
    data: data
  })
}

// 删除订单主
export function delMaster (orderId) {
  return request({
    url: '/system/master/' + orderId,
    method: 'delete'
  })
}
