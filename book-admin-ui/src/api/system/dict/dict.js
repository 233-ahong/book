import request from '@/utils/request'

export function getDict () {
  return request({
    url: '/system/sort/dict',
    method: 'post'
  })
}
