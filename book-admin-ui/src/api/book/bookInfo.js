import request from '@/utils/request'

// 查询图书信息列表
export function listBookInfo (query) {
  return request({
    url: '/book/bookInfo/list',
    method: 'get',
    params: query
  })
}

// 查询图书信息详细
export function getBookInfo (bookId) {
  return request({
    url: '/book/bookInfo/' + bookId,
    method: 'get'
  })
}

// 新增图书信息
export function addBookInfo (data) {
  return request({
    url: '/book/bookInfo',
    method: 'post',
    data: data
  })
}

// 修改图书信息
export function updateBookInfo (data) {
  return request({
    url: '/book/bookInfo',
    method: 'put',
    data: data
  })
}

// 删除图书信息
export function delBookInfo (bookId) {
  return request({
    url: '/book/bookInfo/' + bookId,
    method: 'delete'
  })
}
export function borrowBooks (bookId, data) {
  return request({
    url: '/book/bookInfo/borrow/' + bookId + '/' + data,
    method: 'get'
  })
}
