import request from '../utils/request'

const baseUrl = import.meta.env.VITE_API_USER_BASE_URL
const minioBaseUrl = import.meta.env.VITE_API_MINIO_BASE_URL

export function setUserName(data) {
    return request({
        url: baseUrl + "/json/user/setname/" + localStorage.getItem("utoken"),
        method: 'put',
        data: data
    })
}

export function checkMailCode(account, code) {
    return request({
        url: baseUrl + "/json/user/checkmailcode/" + account +　"/" + code,
        method: 'get'
    })
}

export function isNameExist(name) {
    return request({
        url: baseUrl + "/json/user/isexist/" + name,
        method: 'get'
    })
}

export function getEmailCode(account) {
    return request({
        url: baseUrl + "/json/user/getmailcode/" + account,
        method: 'get'
    })
}

export function register(data) {
    return request({
        url: baseUrl + "/json/user/register",
        method: 'post',
        data: data
    })
}

export function userLogin(data) {
    return request({
        url: baseUrl + "/json/user/login",
        method: 'post',
        data: data
    })
}

export function getProfile(name) {
    return request({
        url: minioBaseUrl + "/profile/" + name,
        method: 'get',
        responseType: 'blob'
    })
}

// insecure : delete after alter to backend
export function putProfile(name, blob) {
    const accessKey = 'IEJiPMBh0214n5H8wNPH';
    const secretKey = 'RKcBEB9AXN4zyr02rMG1GpQ5ZDldGJhZEvdHo3nw';
    return request({
        url: minioBaseUrl + "/profile/" + name,
        method: 'put',
        data: blob,
        headers: {
            'Content-Type': 'image/svg+xml', // 指定内容类型为二进制流
            'Authorization': `Bearer ${accessKey}:${secretKey}`
        }
    })
}