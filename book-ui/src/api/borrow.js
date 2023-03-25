import request from '@/utils/request.js'

export function borrowBook(id,date) {
  return request({
    url: '/book/bookInfo/borrow/' + id + '/' + date,
    method: 'get'
  })
}

export function getBorrow(query) {
  return request({
      url: '/borrowView/get',
      method: 'get',
      params: query
  })
}

export function returnBook (borrowId) {
  return request({
    url: '/book/borrow/return/' + borrowId  ,
    method: 'delete'
  })
}
