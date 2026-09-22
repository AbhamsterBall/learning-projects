import request from '../../utils/request.js'

const apiUrl = import.meta.env.VITE_API_BLOG_BASE_URL;

export function processInfo(info) {
    return request({
        url : apiUrl + "/ai/process",
        method : 'post',
        data : info,
        headers: { "Content-Type": "multipart/form-data" }
    })
}