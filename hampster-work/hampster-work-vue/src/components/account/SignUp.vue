<script setup>
import globalMask from '../GlobalMask.vue'
import $ from 'jquery'
import smallWarning from '../svg/SmallWarning.vue'
import { inputTest,inputResponse } from './Login.vue'
import { ref } from "vue";
import { loadMoving, loadStop, hideWrong, displayWrong, restorePos } from "./Login.vue";
import checkAccount from './CheckAccount.vue';
import { store } from '../../main.js';
import axios from "axios";
import {isNameExist} from "../../api/search.js";

let buttonColor = ref("#a9acba")
let buttonPointer = ref("none")

$(() => {

  inputResponse('.login-signup-input', "hampster@hampster.work | +86 1234567890")

  inputTest(".login-signup-input", buttonColor, buttonPointer)

})

function testAccount() {
  inputAC.value = $('.login-signup-input').val()
  loadMoving()
  let re = false

  try {
    // const ur = "http://localhost:8082/json/user/isexist/" + inputAC.value
    // axios.get(ur)
    isNameExist(inputAC.value)
      .then(data => {
        if (data) {
          loadStop()
          displayWrong("账号已存在，请<a href=\"#\" class=\"login-forget-top\" style=\"margin-left: 0px\">前往登录</a>")
          $('.login-forget-top').click(backToLogin)
        } else {

          if (inputAC.value.includes("@")) {
            getMailCode(inputAC.value)
          } else {

          }
        }
      })
      .catch(error => {
        console.error(error);
      });
  } catch (error) {
    console.error(error);
    loadStop(); // 停止加载动画
  }
}

function backToLogin() {

  setTimeout(() => {moveBack()}, 300)

  setTimeout(() => {hideWrong(); restorePos()}, 350)

  setTimeout(() => {
    $('.lp-display').css("opacity", "0")
    setTimeout(() => {$('.lp-display').css("display", "none")}, 100)
  }, 500)

}

function moveBack() {
  $('.login-move').css('margin-left', "0px")
  $('.veri-move').css('margin-left', "451px")
}


</script>

<template>
  <!--  <global-mask/>-->
  <div class="veri-display">
    <check-account :account="inputAC"/>
  </div>
  <div class="login-signup-display mask-display login-display">
    <div class="mask-display login-display sub-login-signup">
      <div class="login-net lp-net">HAMPSTER.WORK</div>
      <div class="login-move move-ex sign-up-move">
        <div class="login-title">注册账号</div>
        <div class="login-mask login-display">
          <div class="login-loading ll-left"></div>
          <div class="login-loading ll-right" style="margin-left: -860px"></div>
        </div>
        <div class="login-wrong">密码错误，请输入其它密码或<a href="#" class="login-forget" style="margin-left: 0px">找回密码</a></div>
        <div class="login-input">
          <fieldset class="name-field">
            <legend class="name-legend">使用手机号或邮箱</legend>
            <input type="text" value="" placeholder="hampster@hampster.work | (+86) 1234567890" class="login-signup-input" maxlength="114">
          </fieldset>
          <div class="login-format">
            <small-warning width="16" height="16" style="margin-right: 4px; opacity: .8"/>
            <span class="login-warning">密码应该包含大小写字母，数字及至少一个特殊符号</span>
          </div>
          <a href="#" class="login-forget">获取新的邮箱</a>
        </div>
        <button class="login-button lp-button"
                :style="{ backgroundColor : buttonColor, pointerEvents : buttonPointer }"
                @click.native="testAccount">下一步</button>
      </div>
    </div>
  </div>
</template>

<script>

import axios from "axios";
import $ from "jquery";
import {ref} from "vue";
import { hideWrong, restorePos, loadStop } from "./Login.vue";
import {getEmailCode} from "../../api/search.js";

export let inputAC = ref()
export async function getMailCode(account) {
  try {
    // const ur = "http://localhost:8082/json/user/getmailcode/" + account
    // axios.get(ur)
    getEmailCode(account)
      .then(data => {
        if (data === "ok") {
          loadStop()
          $('.veri-display').css('display', 'block')

          $('.sub-login-signup').css('background-color', 'transparent')

          setTimeout(() => {moveLeft()}, 100)
          setTimeout(() => {hideWrong(); restorePos()}, 400)
        }
      })
      .catch(error => {
        console.error(error);
      });
  } catch (error) {
    console.error(error);
    loadStop(); // 停止加载动画
  }
}

function moveLeft() {
  $('.login-move').css('margin-left', "-912px")
  $('.veri-move').css('margin-left', "-456px")

  setTimeout(() => {hideWrong()}, 400)
}

export function moveRight() {
  $('.login-move').css('margin-left', "-456px")
  $('.veri-move').css('margin-left', "5px")
}
moveRight()

</script>

<style scoped>
.login-mask {
  display: none;
  opacity: 0;
  margin-left: 456px;
}
.login-signup-display {
  z-index: 6000;
  padding-right: 912px;
  /*display: none !important;*/
  background-color: transparent;
}
.sub-login-signup {
  z-index: 6001;
  position: relative;
  margin-top: -24px;
  box-shadow: none;
  background-color: transparent !important;
}
.lp-net {
  padding-top: 24px;
  margin-left: 480px;
}
.lp-button {
  margin-left: 480px;
}
.login-signup-input {
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
.veri-display {
  display: none;
  transition: 0.3s;
  position: fixed;
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
</style>