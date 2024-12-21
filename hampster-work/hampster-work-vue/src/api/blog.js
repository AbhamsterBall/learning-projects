import $ from "jquery";
import request from '../utils/request'

const apiUrl = import.meta.env.VITE_API_BLOG_BASE_URL;

// index page : blog name
export function indexBlogName() {
  return request({
    url : apiUrl + "/json/index/b_name",
    method : 'get'
  })
  // return $.get(apiUrl +
  //   "/json/index/b_name");
}

// index page : blog type name
export function indexBtName() {
  return request({
    url : apiUrl + "/json/index/bt_name",
    method : 'get'
  })
  // return $.get(apiUrl +
  //   "/json/index/bt_name");
}

// search page : blog name by type
export function searchBlogByType(type, page) {
  return request({
    url : apiUrl + "/json/search/" + type + "/b_name/" + page,
    method : 'get'
  })
  // return $.get(apiUrl +
  //     "/json/search/" + type + "/b_name/" + page)
}

// get blog name by type
export function getBlogNameByType(type) {
  return request({
    url : apiUrl + "/json/blog/" + type + "/b_name",
    method : 'get'
  })
  // return $.get(apiUrl +
  //   "/json/blog/" + type + "/b_name");
}

// blog search : blog search
export function blogSearch(title, searchText, page) {
  if (!title.includes("HAMPSTER"))
    return request({
      url : apiUrl + "/json/search/blur/" + title + "/" + searchText + "/" + page,
      method : 'get'
    })
    // return $.get(apiUrl +
    //   "/json/search/blur/" + title + "/" + searchText + "/" + page);
  else return request({
    url : apiUrl + "/json/search/blur/" + searchText + "/" + page,
    method : 'get'
  })
    // return $.get(apiUrl +
    // "/json/search/blur/" + searchText + "/" + page);
}

// get blog content summary
export function getBlogContentSummary(info) {
  return request({
    url : apiUrl + "/json/blog/getSummary",
    method : 'post',
    data : info
  })
  // return $.post(apiUrl +
  //   "/json/blog/getSummary", info);
}

// get blog content
export function getBlogContent(name) {
  return request({
    url : apiUrl + "/json/blog/" + name,
    method : 'get'
  })
  // return $.get(apiUrl +
  //     "/json/blog/" + name);
}