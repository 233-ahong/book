import request from '@/utils/request.js'

export function getClassifyList(query) {
  return request({
    url: '/routine/classify/list',
    method: 'get',
    params: query
  })
}
