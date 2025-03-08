<script setup>
import {ref, toRefs} from 'vue'
import { inputResponse, inputTest, loadMoving, loadStop, hideWrong } from "./Login.vue";
import smallWarning from '../svg/SmallWarning.vue'
import $ from "jquery";
import axios from "axios";
import bcrypt from 'bcryptjs';
import SetName from "./SetName.vue";
import {register, userLogin} from "../../api/search/user.js";
import router from "../../router/index.js";
import { publicKey } from "../../utils/request.js";

let buttonColor = ref("#a9acba")
let buttonPointer = ref("none")

let props = defineProps({
  account: String,
})

let { account } = toRefs(props) // 解构为单独的变量

$(() => {
  // console.log(isLogin.value)
  // encryptData("123456", publicKey)

  inputResponse('.login-signup-pass', "••••••••••••")

  inputTest('.login-signup-pass', buttonColor, buttonPointer)

})

function showOrHide() {
  let x = $(".login-signup-pass")[0];
  if (x.type === "password") {
    x.type = "text";
  } else {
    x.type = "password";
  }
}

function addAccount() {
  loadMoving()
  // const ur = "http://localhost:8082/json/user/register"
  // axios.post(ur, {
  //   uId: 0,
  //   uName: "Registered Hamster",
  //   uMail: account._object.account,
  //   uPass: bcrypt.hashSync($('.login-signup-pass').val(), bcrypt.genSaltSync())
  // })
  register({
    uId: 0,
    uName: "Registered Hamster",
    uMail: account._object.account,
    uPass: bcrypt.hashSync($('.login-signup-pass').val(), bcrypt.genSaltSync())
  }).then(data => {
    if (data.status) {
      // localStorage.setItem("utoken", data.user_token)
      // // const accessKey = 'IEJiPMBh0214n5H8wNPH';
      // // const secretKey = 'RKcBEB9AXN4zyr02rMG1GpQ5ZDldGJhZEvdHo3nw';
      // getProfile('default.svg')
      // // axios.get("http://47.109.149.213:9000/profile/default.svg", {responseType: 'blob'})
      //   .then(response => {
      //     const blob = new Blob([response]);
      //     // axios.put("http://47.109.149.213:9000/profile/" + data.data.user_token + ".svg",
      //     //     blob,  {
      //     //   headers: {
      //     //     'Content-Type': 'image/svg+xml', // 指定内容类型为二进制流
      //     //     'Authorization': `Bearer ${accessKey}:${secretKey}`
      //     //   }
      //     // })
      //     putProfile(data.user_token + ".svg", blob)
      //         .then(response => {
      //           loadStop()
      //           moveLeft()
      //           // toSetName()
      //         })
      //         .catch(error => {
      //           console.error(error);
      //         });
      //   });
          loadStop()
          moveLeft()
       }
    })
    .catch(error => {
      console.error(error);
    }
  );
}

import { displayWrong } from "./Login.vue";
import {ElMessage} from "element-plus";
import FingerprintJS from '@fingerprintjs/fingerprintjs';
import { store } from '../../main.js'

async function login() {
  loadMoving()

  // 初始化 FingerprintJS
  const fp = await FingerprintJS.load();
  // 生成指纹
  const result = await fp.get();
  const fpId =  result.visitorId;
  userLogin({
    uMail: account._object.account.includes('@') ?
        account._object.account : null,
    uPhone: account._object.account.includes('@') ?
        null : account._object.account,
    uPass: await encryptData($('.login-signup-pass').val(), publicKey),
    uFingerPrint: await encryptData(fpId.toString(), publicKey)
    // uPass: $('.login-signup-pass').val()
  }).then(res => {
    loadStop()
    if (res.code === 200) {
      store.commit('setNewToken', res.msg)
      // localStorage.setItem("utoken", res.msg)
      router.go(0)
    } else {
      displayWrong("密码错误，请输入其它密码或<a href=\"#\" class=\"login-forget-top\" style=\"margin-left: 0px\">找回密码</a>")
    }
  })
  .catch(error => {
    console.error(error);
  });
}

function moveLeft() {
  $('.set-name-display').css('display', 'block')
  setTimeout(() => {
    $('.pass-move').css('margin-left', "-912px")
    $('.set-name-move').css('margin-left', "-456px")
  }, 300)
  setTimeout(() => {hideWrong()}, 400)
}

</script>

<script>
import $ from "jquery";
import { warningDisplay, warningDisappear } from "./Login.vue";
import { ref } from "vue";


import { CryptoJS } from 'jsrsasign';

export async function encryptData(data, publicKey) {
  try {
    const re = AES.encrypt(data, publicKey)
    // console.log(AES.decrypt(re.info, re.decryptedKey))
    return re;
  } catch (error) {
    console.error('加密失败:', error);
    throw error;
  }
}

import JSEncrypt from 'jsencrypt'
function encryptWithRSA(publicKey, data) {
  const encryptor = new JSEncrypt();
  encryptor.setPublicKey(publicKey);
  return encryptor.encrypt(data);
}

// import CryptoJS from 'crypto-js'

function encrypt(plaintext, publicKey) {
  try {
    const aesKey = CryptoJS.lib.WordArray.random(16); // 128位密钥
    const key = CryptoJS.PBKDF2(aesKey.toString(), 'salt',
        { keySize: 128/32, iterations: 100 });
    console.log(key)
    // const key = CryptoJS.enc.Utf8.parse(process.env.REACT_APP_SECRET_AES_KEY as string)
    const iv = CryptoJS.lib.WordArray.random(16)
    const encrypted = CryptoJS.AES.encrypt(plaintext, key, {
      iv: iv,
      mode: CryptoJS.mode.CBC,
      padding: CryptoJS.pad.Pkcs7,
    })

    const encryptMessage = encrypted.ciphertext.toString(CryptoJS.enc.Base64)
    return {
      info: iv.toString(CryptoJS.enc.Base64) + encryptMessage,
      key: encryptWithRSA(publicKey, aesKey.toString()),
      // decryptedKey: aesKey.toString()
    };
  } catch (error) {
    return null
  }
}

function decrypt(encryptedData, aesKey) {
  try {
    // const key = CryptoJS.enc.Utf8.parse(aesKey)
    console.log(encryptedData, aesKey)
    const key = CryptoJS.PBKDF2(aesKey, 'salt',
        { keySize: 128/32, iterations: 100 });
    const ivString = encryptedData.substring(0, 24)
    console.log(ivString)
    const encryptedString = encryptedData.substring(24)

    const iv = CryptoJS.enc.Base64.parse(ivString)

    console.log(ivString)
    console.log(CryptoJS.AES)
    const decrypted = CryptoJS.AES.decrypt(
        { ciphertext: CryptoJS.enc.Base64.parse(encryptedString) }, key, {
      iv: iv,
      mode: CryptoJS.mode.CBC,
      padding: CryptoJS.pad.Pkcs7,
    })

    const decryptedText = decrypted.toString(CryptoJS.enc.Utf8)
    return decryptedText
  } catch (error) {
    console.log(error)
    return null
  }
}

export const AES = {
  decrypt,
  encrypt,
}

export let isLogin = ref(false);

export function passTest(outString, isFormatValid) {
  let re = isFormatValid
  if (!/[0-9]+/.test(outString) || !/[A-Za-z]+/.test(outString)) {
    if (isFormatValid) {
      warningDisplay("密码应该包含大小写字母及数字")

      re = false
    }
  } else if (outString.length < 8) {
    if (isFormatValid) {
      warningDisplay("密码长度应该大于8位")

      re = false
    } else {
      $('.login-warning').html("密码长度应该大于8位")
    }
  } else {
    if (!isFormatValid) {
      warningDisappear()

      re = true
    }
  }
  return re;
}
</script>

<template>
  <div class="set-name-display">
    <set-name :account="account"/>
  </div>
  <div class="login-signup-display mask-display login-display">
    <div class="mask-display login-display sub-login-signup">
      <div class="login-net lp-net">HAMPSTER.WORK</div>
      <div class="pass-move move-ex">
        <div class="login-title">输入密码</div>
        <div class="login-mask login-display">
          <div class="login-loading ll-left"></div>
          <div class="login-loading ll-right" style="margin-left: -860px"></div>
        </div>
        <div class="login-wrong">密码错误，请输入其它密码或<a href="#" class="login-forget" style="margin-left: 0px">找回密码</a></div>
        <div class="login-input">
          <fieldset class="name-field">
            <legend class="name-legend">请输入密码</legend>
            <input type="password" value="" placeholder="••••••••••••" class="login-signup-pass login-input-general" maxlength="80">
          </fieldset>
          <div class="login-format">
            <small-warning width="16" height="16" style="margin-right: 4px; opacity: .8"/>
            <span id="login-warning">密码应该包含大小写字母，数字及至少一个特殊符号</span>
          </div>
          <span class="login-forget sub-login-forget">
            <input type="checkbox" class="login-show-pass login-forget" @click="showOrHide"/>
            <span style="margin-left: 29px">显示密码</span>
          </span>
        </div>
        <button class="pass-button login-button lp-button"
                :style="{ backgroundColor : buttonColor, pointerEvents : buttonPointer }"
                @click.native="addAccount" v-if="!isLogin">下一步</button>
        <button class="pass-button login-button lp-button"
                :style="{ backgroundColor : buttonColor, pointerEvents : buttonPointer }"
                @click.native="login" v-if="isLogin">登陆</button>
      </div>
    </div>
  </div>
</template>

<style scoped>
.set-name-display {
  display: none;
  transition: 0.3s;
  position: fixed;
  margin-left: 0px;
  top: 50%;
  left: 50%;
  width: 480px;
  height: 336px;
  background-color: transparent;
  z-index: 7000;
  border-radius: 20px;
  transform: translate(-50%, -50%);
  /* box-shadow: 0 0 8px 3px #d0d0d0; 模拟边框
  overflow: hidden;*/
}
.login-mask {
  margin-left: 456px;
}
.pass-button {
  display: block !important;
}
.login-forget {
  /* margin: 0px 10px !important; */
  margin: 0px 30px 0px 10px;
  display: inline;
  align-items: center;
}
.sub-login-forget {
  margin-left: 60px;
}
.login-show-pass {
  margin-left: 0px !important;
  width: 18px;
  height: 18px;
  margin-top: 2px !important;
}
.pass-move {
  margin-left: 0px;
  width: 480px;
  transition: .3s;
}
.login-signup-display {
  z-index: 6100 !important;
  padding-right: 912px;
  /*display: none !important;*/
  background-color: transparent;
}
.sub-login-signup {
  z-index: 6101;
  position: relative;
  margin-top: -24px;
  box-shadow: none;
}
.lp-net {
  padding-top: 24px;
  margin-left: 480px;
}
.lp-button {
  margin-left: 480px;
}
.login-signup-pass {
  z-index: 7000;
  outline: none;
  border: none;
  border-radius: 0px;
  position: absolute;
  margin-top: -15px;
  text-indent: 4px;
  width: 360px;
  font-size: 16px;
}
.move-ex {
  padding-left: 911px;
}
.pass-button {
  display: none;
}
</style>