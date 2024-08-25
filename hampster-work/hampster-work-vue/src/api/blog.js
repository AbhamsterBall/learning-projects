import $ from "jquery";

const apiUrl = "/blog-api";

// index blog name
export function indexBlogName() {
  return $.get(apiUrl + 
    "/json/index/b_name");
}

