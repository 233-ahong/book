import request from '@/utils/request'

// 查询借阅记录详情列表
export function listBorrow (query) {
  return request({
    url: '/book/borrow/list',
    method: 'get',
    params: query
  })
}

export function userListBorrow (query) {
  return request({
    url: '/book/borrow/userList',
    method: 'get',
    params: query
  })
}

// 查询借阅记录详情详细
export function getBorrow (borrowId) {
  return request({
    url: '/book/borrow/' + borrowId,
    method: 'get'
  })
}

// 新增借阅记录详情
export function addBorrow (data) {
  return request({
    url: '/book/borrow',
    method: 'post',
    data: data
  })
}

// 修改借阅记录详情
export function updateBorrow (data) {
  return request({
    url: '/book/borrow',
    method: 'put',
    data: data
  })
}

// 删除借阅记录详情
export function delBorrow (borrowId) {
  return request({
    url: '/book/borrow/' + borrowId,
    method: 'delete'
  })
}

export function returnBook (borrowId, bookId, state) {
  return request({
    url: '/book/borrow/return/' + borrowId + '/' + bookId + '/' + state,
    method: 'delete'
  })
}
