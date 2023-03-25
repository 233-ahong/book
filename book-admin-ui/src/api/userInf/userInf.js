import request from '@/utils/request'

// 查询用户信息列表
export function listUserInf (query) {
  return request({
    url: '/book/userInf/list',
    method: 'get',
    params: query
  })
}

// 查询用户信息详细
export function getUserInf (userId) {
  return request({
    url: '/book/userInf/' + userId,
    method: 'get'
  })
}

// 新增用户信息
export function addUserInf (data) {
  return request({
    url: '/book/userInf',
    method: 'post',
    data: data
  })
}

// 修改用户信息
export function updateUserInf (data) {
  return request({
    url: '/book/userInf',
    method: 'put',
    data: data
  })
}

// 删除用户信息
export function delUserInf (userId) {
  return request({
    url: '/book/userInf/' + userId,
    method: 'delete'
  })
}
