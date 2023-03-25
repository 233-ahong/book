import request from '@/utils/request.js'

export function getBookList(data) {
  return request({
    url: '/routine/book/list',
    method: 'get',
    params: data
  })
}

export function getBookInfo(id) {
  return request({
    url: '/routine/book/bookInfo/' + id,
    method: 'get'
  })
}

export function sellingPlacard() {
  return request({
    url: '/routine/book/sellingPlacard',
    method: 'get'
  })
}
export function borrowPlacard () {
  return request({
    url: '/routine/book/borrowPlacard',
    method: 'get'
  })
}

export function randomBook() {
  return request({
    url: '/routine/book/randomBook',
    method: 'get'
  })
}
