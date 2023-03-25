import request from '@/utils/request'

// 查询书籍分类列表
export function listSort (query) {
  return request({
    url: '/system/sort/list',
    method: 'get',
    params: query
  })
}

// 查询书籍分类详细
export function getSort (sortId) {
  return request({
    url: '/system/sort/' + sortId,
    method: 'get'
  })
}

// 新增书籍分类
export function addSort (data) {
  return request({
    url: '/system/sort',
    method: 'post',
    data: data
  })
}

// 修改书籍分类
export function updateSort (data) {
  return request({
    url: '/system/sort',
    method: 'put',
    data: data
  })
}

// 删除书籍分类
export function delSort (sortId) {
  return request({
    url: '/system/sort/' + sortId,
    method: 'delete'
  })
}
