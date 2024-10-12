import $ from "jquery";

const apiUrl = "/blog-api";

// index page : blog name
export function indexBlogName() {
  return $.get(apiUrl + 
    "/json/index/b_name");
}

// index page : blog type name
export function indexBtName() {
  return $.get(apiUrl +
    "/json/index/bt_name");
}

// search page : blog name by type
export function searchBlogByType(type, page) {
  return $.get(apiUrl +
      "/json/search/" + type + "/b_name/" + page)
}

// get blog name by type
export function getBlogNameByType(type) {
  return $.get(apiUrl +
    "/json/blog/" + type + "/b_name");
}

// blog search : blog search
export function blogSearch(title, searchText, page) {
  if (!title.includes("HAMPSTER"))
    return $.get(apiUrl +
      "/json/search/blur/" + title + "/" + searchText + "/" + page);
  else return $.get(apiUrl +
    "/json/search/blur/" + searchText + "/" + page);
}

// get blog content summary
export function getBlogContentSummary(info) {
  return $.get(apiUrl +
    "/json/blog/" + info + "/getsummary");
}

// get blog content
export function getBlogContent(name) {
  return $.get(apiUrl +
      "json/blog/" + name);
}