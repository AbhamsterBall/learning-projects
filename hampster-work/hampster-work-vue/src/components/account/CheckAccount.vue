<script setup>
import { ref, toRefs } from 'vue'
import { inputResponse, displayWrong, loadMoving, loadStop, inputTest, setAccount, hideWrong, restorePos } from "./Login.vue";
import smallWarning from '../svg/SmallWarning.vue'
import $ from "jquery";
import {store} from "../../main.js";
import axios from "axios";
import Password from "./Password.vue";
import { getMailCode } from "./SignUp.vue"
import {checkMailCode} from "../../api/search/user.js";

let buttonColor = ref("#a9acba")
let buttonPointer = ref("none")

let props = defineProps({
  account: String
})

let { account } = toRefs(props) // 解构为单独的变量
setAccount(account)

$(() => {

  inputResponse('.login-signup-veri', "012345")

  inputTest(".login-signup-veri", buttonColor, buttonPointer)

})

function testVeri() {
  loadMoving()
  let cIn = $('.login-signup-veri').val()

  // 直接传给后端哈希对比
  // const ur = "http://localhost:8082/json/user/checkmailcode/" + account._object.account +　"/" + cIn
  // axios.get(ur)
  checkMailCode(account._object.account, cIn)
    .then(data => {
      if (data === true) {
        loadStop()
        moveLeft()
        setTimeout(() => {hideWrong(); restorePos()}, 400)
      } else if (data === "expired") {
        loadStop()
        displayWrong("验证码过期，请检查或<a href=\"#\" class=\"login-forget-top\" style=\"margin-left: 0px\">重新发送</a>")
      } else {
        loadStop()
        displayWrong("验证码错误，请检查或<a href=\"#\" class=\"login-forget-top\" style=\"margin-left: 0px\">重新发送</a>")
      }
    })
    .catch(error => {
      console.error(error);
    });

}

</script>

<script>
import {toRefs} from "vue";
import { warningDisplay, warningDisappear, hideWrong } from './Login.vue'
import $ from "jquery";

export function veriTest(outString, isFormatValid, account) {
  let re = isFormatValid
  if (/^[0-9+()（）]+$/.test(account.value._object.account)) {
    if (outString.length != 6 || !/^[0-9]+$/.test(outString)) {
      if (isFormatValid) {
        warningDisplay("验证码应该为6位数字")

        re = false
      }
    } else {
      if (!isFormatValid) {
        warningDisappear()

        re = true
      }
    }
  } else {
    if (outString.length != 6 || !/^[0-9A-Za-z]+$/.test(outString)) {
      $('.login-warning').html("验证码应该为6位数字或字母")
      if (isFormatValid) {
        warningDisplay("验证码应该为6位数字或字母")

        re = false
      }
    } else {
      if (!isFormatValid) {
        warningDisappear()

        re = true
      }
    }
  }
  return re
}

import { isLogin } from "./Password.vue"
function moveLeft() {
  isLogin.value = false
  $('.pass-display').css('display', 'block')
  setTimeout(() => {
    $('.veri-move').css('margin-left', "-912px")
    $('.pass-move').css('margin-left', "-456px")
  }, 300)
  setTimeout(() => {hideWrong()}, 400)
}
</script>

<template>
  <div class="pass-display">
    <password :account="account"/>
  </div>
  <div class="login-signup-display mask-display login-display">
    <div class="mask-display login-display sub-login-signup">
      <div class="login-net lp-net">HAMPSTER.WORK</div>
      <div class="veri-move move-ex">
        <div class="login-title">验证账号</div>
        <div class="login-mask login-display">
          <div class="login-loading ll-left"></div>
          <div class="login-loading ll-right" style="margin-left: -860px"></div>
        </div>
        <div class="login-wrong">验证码错误，请检查或重新发送<a href="#" class="login-forget" style="margin-left: 0px">找回密码</a></div> <!--验证码已过期（15分钟内）-->
        <div class="login-input">
          <fieldset class="name-field">
            <legend class="name-legend">请输入发送到 {{ account }} 的验证码</legend>
            <input type="text" value="" placeholder="012345" class="login-signup-veri login-input-general" maxlength="114">
          </fieldset>
          <div class="login-format">
            <small-warning width="16" height="16" style="margin-right: 4px; opacity: .8"/>
            <span class="login-warning">验证码为6位数字</span>
          </div>
          <a href="#" class="login-forget" @click="getMailCode(account)">重新发送</a>
        </div>
        <button class="veri-button login-button lp-button" style="display: block !important"
                :style="{ backgroundColor : buttonColor, pointerEvents : buttonPointer }"
                @click.native="testVeri">下一步</button>
      </div>
    </div>
  </div>
</template>

<style scoped>
.login-mask {
  //display: none !important;
  margin-left: 456px;
}
.veri-move {
  margin-left: 456px;
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
.login-signup-veri {
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
.veri-button {
  display: none;
}
.pass-display {
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
</style>