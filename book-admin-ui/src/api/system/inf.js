import request from '@/utils/request'

// 查询用户信息列表
export function listInf (query) {
  return request({
    url: '/system/inf/list',
    method: 'get',
    params: query
  })
}

// 查询用户信息详细
export function getInf (userInfId) {
  return request({
    url: '/system/inf/' + userInfId,
    method: 'get'
  })
}

// 新增用户信息
export function addInf (data) {
  return request({
    url: '/system/inf',
    method: 'post',
    data: data
  })
}

// 修改用户信息
export function updateInf (data) {
  return request({
    url: '/system/inf',
    method: 'put',
    data: data
  })
}

// 删除用户信息
export function delInf (userInfId) {
  return request({
    url: '/system/inf/' + userInfId,
    method: 'delete'
  })
}
