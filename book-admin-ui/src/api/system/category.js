import request from '@/utils/request'

// 查询书籍分类列表
export function listCategory (query) {
  return request({
    url: '/system/category/list',
    method: 'get',
    params: query
  })
}

// 查询书籍分类详细
export function getCategory (categoryId) {
  return request({
    url: '/system/category/' + categoryId,
    method: 'get'
  })
}

// 新增书籍分类
export function addCategory (data) {
  return request({
    url: '/system/category',
    method: 'post',
    data: data
  })
}

// 修改书籍分类
export function updateCategory (data) {
  return request({
    url: '/system/category',
    method: 'put',
    data: data
  })
}

// 删除书籍分类
export function delCategory (categoryId) {
  return request({
    url: '/system/category/' + categoryId,
    method: 'delete'
  })
}
