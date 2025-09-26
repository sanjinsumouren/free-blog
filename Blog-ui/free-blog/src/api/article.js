import request from './request'

// 获取文章列表
export function getArticleList(params) {
  return request({
    url: '/articles/list',
    method: 'get',
    params
  })
}

// 获取文章详情
export function getArticleDetail(id) {
  return request({
    url: `/articles/${id}`,
    method: 'get'
  })
}

// 获取文章分类
export function getCategories() {
  return request({
    url: '/categories',
    method: 'get'
  })
}

// 根据分类获取文章
export function getArticlesByCategory(categoryId) {
  return request({
    url: `/categories/${categoryId}/articles`,
    method: 'get'
  })
}

// 搜索文章
export function searchArticles(query) {
  return request({
    url: '/articles/search',
    method: 'get',
    params: { query }
  })
}