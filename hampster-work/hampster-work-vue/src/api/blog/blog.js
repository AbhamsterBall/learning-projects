import request from '../../utils/request.js'

const apiUrl = import.meta.env.VITE_API_BLOG_BASE_URL;

// index page : blog name
export function indexBlogName() {
  return request({
    url : apiUrl + "/index/b_name",
    method : 'get'
  })
  // return $.get(apiUrl +
  //   "/index/b_name");
}

// index page : blog type name
export function indexBtName() {
  return request({
    url : apiUrl + "/index/bt_name",
    method : 'get'
  })
  // return $.get(apiUrl +
  //   "/index/bt_name");
}

// search page : blog name by type
export function searchBlogByType(type, page) {
  return request({
    url : apiUrl + "/search/" + type + "/b_name/" + page,
    method : 'get'
  })
  // return $.get(apiUrl +
  //     "/search/" + type + "/b_name/" + page)
}

// get blog content summary
export function getBlogContentSummary(info) {
  return request({
    url : apiUrl + "/blog/getSummary",
    method : 'post',
    data : info
  })
  // return $.post(apiUrl +
  //   "/blog/getSummary", info);
}

// get blog content
export function getBlogContent(name) {
  return request({
    url : apiUrl + "/blog/" + name,
    method : 'get'
  })
  // return $.get(apiUrl +
  //     "/blog/" + name);
}

// get blog name by type
export function getBlogNameByType(type) {
  return request({
    url : apiUrl + "/blog/" + type + "/b_name",
    method : 'get'
  })
  // return $.get(apiUrl +
  //   "/blog/" + type + "/b_name");
}