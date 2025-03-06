import request from '../../utils/request.js'
import {fingerprint, store} from "../../main.js";

const baseUrl = import.meta.env.VITE_API_USER_BASE_URL
const minioBaseUrl = import.meta.env.VITE_API_MINIO_BASE_URL

export function setUserName(data) {
    return request({
        url: baseUrl + "/user/setname/" + localStorage.getItem("utoken"),
        method: 'put',
        data: data
    })
}

export function checkMailCode(account, code) {
    return request({
        url: baseUrl + "/user/checkmailcode/" + account +　"/" + code,
        method: 'get'
    })
}

export function isNameExist(name) {
    return request({
        url: baseUrl + "/user/isexist/" + name,
        method: 'get'
    })
}

export function getEmailCode(account) {
    return request({
        url: baseUrl + "/user/getmailcode/" + account,
        method: 'get'
    })
}

export function register(data) {
    return request({
        url: baseUrl + "/user/register",
        method: 'post',
        data: data
    })
}

export function userLogin(data) {
    return request({
        url: baseUrl + "/user/login",
        method: 'post',
        data: data
    })
}

export async function getUserInfo() {
    const fpId =  fingerprint.visitorId;
    return request({
        url: baseUrl + "/user/info",
        // params: {
        //     token: store.state.token,
        //     fingerprint: fpId
        // },
        method: 'get',
        headers: {
            'Authorization': `Bearer ${store.state.token}`,
            'Fingerprint': fpId
        }
    })
}