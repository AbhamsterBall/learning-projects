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

// blog search : blog search with type
