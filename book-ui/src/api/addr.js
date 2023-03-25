import request from '@/utils/request'

// 查询用户地址列表
export function listAddr(query) {
  return request({
    url: '/system/addr/list',
    method: 'get',
    params: query
  })
}

// 查询用户地址详细
export function getAddr(customerAddrId) {
  return request({
    url: '/system/addr/' + customerAddrId,
    method: 'get'
  })
}

// 新增用户地址
export function addAddr(data) {
  return request({
    url: '/system/addr',
    method: 'post',
    data: data
  })
}

// 修改用户地址
export function updateAddr(data) {
  return request({
    url: '/system/addr',
    method: 'put',
    data: data
  })
}

// 删除用户地址
export function delAddr(customerAddrId) {
  return request({
    url: '/system/addr/' + customerAddrId,
    method: 'delete'
  })
}
