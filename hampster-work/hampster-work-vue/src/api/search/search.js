import request from '../../utils/request.js'

const apiUrl = import.meta.env.VITE_API_BLOG_BASE_URL;

// blog search : blog search
export function blogSearch(title, searchText, page) {
    if (!title.includes("HAMPSTER"))
        return request({
            url : apiUrl + "/search/blur/" + title + "/" + searchText + "/" + page,
            method : 'get'
        })
        // return $.get(apiUrl +
    //   "/search/blur/" + title + "/" + searchText + "/" + page);
    else return request({
        url : apiUrl + "/search/blur/" + searchText + "/" + page,
        method : 'get'
    })
    // return $.get(apiUrl +
    // "/search/blur/" + searchText + "/" + page);
}