import request from '@/utils/request.js'
export function addCart(id,num){
  return request({
    url: '/cart/add/' + id + '/' + num,
    method: 'get'
  })
}

export function getCart() {
  return request({
    url: '/cart/getCart',
    method: 'get'
  })
}

export function updateCart(data) {
  return request({
    url: '/system/cart',
    method: 'put',
    data: data
  })
}

export function delCart(cartId) {
  return request({
    url: '/system/cart/' + cartId,
    method: 'delete'
  })
}
