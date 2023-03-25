import request from '@/utils/request'

// 查询订单主列表
export function listMaster(query) {
  return request({
    url: '/system/masterView/list',
    method: 'get',
    params: query
  })
}
 export function updateOrder(id) {
  return request({
    url: '/system/masterView/' + id,
    method: 'put',
  })
 }
