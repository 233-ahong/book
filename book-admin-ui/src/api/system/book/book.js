import request from '@/utils/request'

// 查询图书信息列表
export function listInfo (query) {
  return request({
    url: '/book/bookInfo/list',
    method: 'get',
    params: query
  })
}

// 查询图书信息详细
export function getInfo (bookId) {
  return request({
    url: '/book/bookInfo/' + bookId,
    method: 'get'
  })
}

// 新增图书信息
export function addInfo (data) {
  return request({
    url: '/book/bookInfo',
    method: 'post',
    data: data
  })
}

// 修改图书信息
export function updateInfo (data) {
  return request({
    url: '/book/bookInfo',
    method: 'put',
    data: data
  })
}

// 删除图书信息
export function delInfo (bookId) {
  return request({
    url: '/book/bookInfo/' + bookId,
    method: 'delete'
  })
}
