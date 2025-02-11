<script setup>
import globalMask from '../GlobalMask.vue'
import $ from 'jquery'
import smallWarning from '../svg/SmallWarning.vue'
import signUp from './SignUp.vue'
import password from './Password.vue'
import { ref } from "vue";
import { moveRight } from "./SignUp.vue";
import { veriTest } from "./CheckAccount.vue";
import {isNameExist} from "../../api/search.js";
import Password from "./Password.vue";

$(() => {

  // console.log(ElMessage)
  // ElMessage({ message: '正在登录...',
  //   type: 'warning',
  //   center: true,
  //   duration: 0,
  //   showClose: false,
  //   onClose: () => {
  //     console.log('消息已关闭')
  //   },
  //   offset: 50,
  //   customClass: 'custom-message',
  //   round: true
  // })

  inputResponse('.login-username', "hampster@hampster.work | +86 1234567890")

  inputTest(".login-username", buttonColor, buttonPointer)

})

function waitServer() {
  loadMoving()

  getName()
}

async function getName() {
  await new Promise(resolve => {
    let name = $('.login-username').val()
    setAccount(name)
    isNameExist(name).then(data => {
    // $.get("http://localhost:8082/json/user/isexist/" + name, function(data) {
      loadStop()
      if (!data) {
        displayWrong("账号不存在，请输入其它账号或<a href=\"#\" class=\"login-forget-top\" style=\"margin-left: 0px\" @click=\"toRegister\">获取新账号</a>")
      } else
        toLogin()
    });
  })
}

function toRegister() {
  $('.lp-display').css("display", "block")
  setTimeout(() => {$('.lp-display').css("opacity", "1")}, 100)

  setTimeout(() => {moveRight()}, 300)

  setTimeout(() => {hideWrong(); restorePos()}, 400)
}

import { isLogin } from "./Password.vue"
import { inputAC } from "./SignUp.vue"
import {ElMessage} from "element-plus";
function toLogin() {
  inputAC.value = $('.login-username').val()
  $('.lp-display').css("display", "block")
  setTimeout(() => {$('.lp-display').css("opacity", "1")}, 100)

  isLogin.value = true
  // $('.login-move').css('transition', "3s")
  // $('.pass-move').css('transition', "3s")
  // $('.veri-move').css('transition', "3s")
  $('.login-move').css('margin-left', "0px")
  $('.pass-move').css('margin-left', "0px")
  $('.sign-up-move').css('opacity', 0)
  $('.veri-move').css('margin-left', "5px")
  $('.veri-display').css('display', 'block')
  $('.pass-display').css('display', 'block')
  setTimeout(() => {
    // moveRight()
    // $('.login-move').css('margin-left', "-456px")
    // $('.veri-move').css('margin-left', "5px")
    $('.login-move').css('margin-left', "-456px")
    $('.sign-up-move').css('margin-left', "-912px")
    // $('.veri-move').css('margin-left', "-912px")
    $('.pass-move').css('margin-left', "-455px")
    // $('.sign-up-move').css('opacity', 1)
  }, 300)

  setTimeout(() => {
    // $('.sign-up-move').css('opacity', 1)
    hideWrong();
    restorePos()}, 400)

  setTimeout(() => {
    $('.sign-up-move').css('opacity', 1)
  }, 3000)
}

</script>

<template>
  <!--  <div :class="className">-->
  <global-mask/>
  <div class="lp-display">
    <sign-up/>
  </div>
<!--  <div class="lp-login-display">-->
<!--    <password :account="account.value" :isLogin=true />-->
<!--  </div>-->
  <div class="mask-display login-display" style="overflow: hidden">
    <div class="login-net"><!--HAMPSTER.WORK--></div>
    <div class="login-move">
      <div class="login-title">输入账号</div>
      <div class="login-mask login-display">
        <div class="login-loading ll-left"></div>
        <div class="login-loading ll-right" style="margin-left: -860px"></div>
      </div>
      <div class="login-wrong">账号不存在，请输入其它账号或<a href="#" class="login-forget-top" style="margin-left: 0px" @click="toRegister">获取新账号</a></div>
      <div class="login-input">
        <fieldset class="name-field">
          <legend class="name-legend">邮箱或手机号</legend>
          <input type="text" value="ajh415@hotmail.com" placeholder="hampster@hampster.work | (+86) 12345678900" class="login-username" maxlength="114">
        </fieldset>
        <div class="login-format">
          <small-warning width="16" height="16" style="margin-right: 4px; opacity: .8"/>
          <span class="login-warning">中国大陆(+86)手机号应为11位</span>
        </div>
        <a href="#" class="login-forget" @click="toRegister">注册账号</a>
      </div>
      <div class="login-button">
        <button class="login-button-sub"
                :style="{ backgroundColor : buttonColor, pointerEvents : buttonPointer }"
                @click.native="waitServer">下一步</button>
      </div>
    </div>
  </div>
  <!--  </div>-->
</template>

<script>
import $ from "jquery";
import { ref } from "vue";
import { passTest } from "./Password.vue";

let movingLoad = null

export function displayWrong(wrongInfo) {
  $('.login-wrong').html(wrongInfo)
  $('.login-wrong').css('display', 'block')
  adjustSignupPos()
}
export function hideWrong() {
  $('.login-wrong').css('display', 'none')
  adjustSignupPos()
}
export function loadMoving() {
  loadStop()
  $('.login-mask').css('display', 'block ')
  $('.login-mask').css('opacity', '1')
  $('.login-loading').css('opacity', '1')
  let right = 860;
  let left = 0
  let moving = false;

  movingLoad = setInterval(() => {
    if (!moving) {
      moving = true;
      if (right >= 860) {
        right = -860;
      }
      if (left >= 860) {
        left = -860;
      }
      $('.ll-left').css('margin-left', left + 'px');
      left += 4;
      $('.ll-right').css('margin-left', right + 'px');
      right += 4;
      moving = false;
    }
  }, 1);

}

export function loadStop() {
  clearInterval(movingLoad)
  $('.login-mask').css('opacity', '0')
  $('.login-loading').css('opacity', '0')
  $('.login-mask').css('display', 'none')
}

export const warningDisplay = (warningInfo) => {
  $('.login-warning').html(warningInfo)
  $('.login-format').css('display', 'flex')
  setTimeout(() => {$('.login-format').css('opacity', '1')}, 180)
  setTimeout(function() {
    $('.login-format').css('margin-top', '-6px')
    $('.login-forget').css('margin-top', '24px')
    adjustSignupPos()
  }, 200)
}

export const warningDisappear = () => {
  $('.login-format').css('margin-top', '-30px')
  $('.login-forget').css('margin-top', '0px')
  setTimeout(() => {$('.login-format').css('opacity', '0')}, 50)
  setTimeout(() => {$('.login-format').css('display', 'none')}, 100)
  setTimeout(() => {$('.login-format').css('display', 'flex')}, 150)

  // if ($('.login-wrong').css("display") == "block")
  //   $('.login-forget').css("display", "none")
  setTimeout(() => {adjustSignupPos()}, 200)
}

export const adjustSignupPos = () => {

  if ($('.login-wrong').css("display") == "block") {
    // $('.login-forget').css("display", "none")
    if ($('.login-format').css("opacity") == "1")
      // $('.login-forget').css("margin", "-7px 24px 0px 389px")
      // 18px 24px 0px 28px
      $('.login-forget').css({
        "margin": "18px 24px 0px 28px",
      })
    else {
      // $('.login-forget').attr('style', 'margin: -7px 30px 0px 30px !important');
      // $('.login-forget').attr('style', 'margin: 2px 0px !important')
      // $('.login-forget').css({
      //   "margin-top": "-7px !important",
      //   "margin-right": "30px !important",
      //   "margin-bottom": "0px !important",
      //   "margin-left": "30px !important"
      // })
    }
    setTimeout(() => {$('.login-forget').css("display", "flex")}, 300)
  } else {

  }

}

export const restorePos = () => {
  $('.login-forget').attr('style', 'margin: 0px 30px 0px 30px');
  setTimeout(() => {$('.login-forget').attr('style', 'display: flex; margin: 0px 30px 0px 30px')}, 20)

}

let buttonColor = ref("#a9acba")
let buttonPointer = ref("none")

let account = ref('')
export function setAccount(acc) {
  account.value = acc
}

export function inputTest(inputName = ".login-username", buttonColor, buttonPointer) {
  let isFormatValid = true
  $(inputName).on('input', function() {

    let inputValue = $(inputName).val()
    // console.log(inputName)
    // console.log(inputValue)
    let isLenValid = true
    if (inputValue.length == 0) {
      isLenValid = false
      warningDisappear()
      isFormatValid = true
    }
    let outString = inputValue.replace(/[\s()（）]/g, '');

    if (isLenValid) {
      switch (inputName) {
        case ".login-username":
        case ".login-signup-input":
          isFormatValid = userTest(outString, isFormatValid)
              break
        case ".login-signup-veri":
          isFormatValid = veriTest(outString, isFormatValid, account)
              break
        case ".login-signup-pass":
          isFormatValid = passTest(outString, isFormatValid)
              break
        default: isFormatValid = false
      }
    }

    if (isLenValid && isFormatValid) {
      buttonPointer.value = "auto"
      buttonColor.value = "#2759b2"
      $(".login-button").hover(() => {
        $(".login-button").css("cursor", "pointer")
        buttonColor.value = "#214890"
      }, () => {
        buttonColor.value = "#2759b2"
      })
    } else {
      buttonPointer.value = "none"
      buttonColor.value = "#a9acba"
      $(".login-button").hover(() => {
        $(".login-button").css("cursor", "default")
        buttonColor.value = "#a9acba"
      })
    }
  });
}

function userTest(outString, isFormatValid) {
  let re = isFormatValid
  if (/^[0-9+()（）]+$/.test(outString)) {
    if ((outString.includes("+86") && outString.length != 14) || (!outString.includes("+86") && outString.length != 11)) {
      if (isFormatValid) {
        warningDisplay("中国大陆(+86)手机号应为11位")

        re = false
      }
    } else {
      if (!isFormatValid) {
        warningDisappear()

        re = true
      }
    }
  } else {
    if (!/^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/.test(outString)) {
      if (isFormatValid) {
        warningDisplay("邮箱格式应为a@b.c，c不能包含特殊字符且c长度大于1")

        re = false
      }
    } else {
      if (!isFormatValid) {
        warningDisappear()

        re = true
      }
    }
  }
  return re;
}

export function inputResponse(inputClass, place_holder) {
  $(inputClass).focus(function() {
    $('.name-field').css('border-color', '#2759b2')
    $('.name-legend').css('color', '#CD853F')
    $('.name-field').css('box-shadow', '0 0 4px 2px #bfc8ec') // #e8d9bf
    $(inputClass).placeholder = ""
  })
  $(inputClass).blur(function() {
    $('.name-field').css('border-color', '#2759b2')
    $('.name-legend').css('color', 'black')
    $('.name-field').css('box-shadow', 'none')
    $(inputClass).placeholder = place_holder
  })
}

</script>

<style scoped lang="scss">
.login-button {
  width: 100%;
  height: 48px;
  left: 0px;
  top: 208px;
  background-color: transparent;
  padding: 0;
  margin: 56px 0px;
}
.login-button-sub {
  width: 90%;
  height: 48px;
  border-radius: 10px;
  outline: none;
  border: none;
  margin: auto;
  /*background-color: #2759b2;*/
  background-color: #888888;
  color: white;
  transition: .3s;
  position: relative;
  letter-spacing: 36px;
  text-align: center;
  padding: 0 5px 0 40px;
  font-size: 16px;
  //left: 0px;
  //top: 0px;
  pointer-events: none;
}
.login-net:after {
  content: "HAMPSTER.WORK";
}
.login-server-response {
  display: none;
}
.login-forget-top {
  margin: 0px 30px 0px 30px;
  position: absolute;
  transition: .3s;
}
.lp-display {
  display: none;
  opacity: 0;
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
  /* box-shadow: 0 0 8px 3px #d0d0d0; 模拟边框*/
  overflow: hidden;
}
.lp-login-display {
  display: none;
  opacity: 0;
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
  /* box-shadow: 0 0 8px 3px #d0d0d0; 模拟边框*/
  overflow: hidden;
}
@media screen and (max-width: 500px) {
  .login-net:after {
    content: "HAMPSTER";
  }
  .login-display {
    width: 90%;
  }
  .name-field {
    width: 77% !important;
    margin: 6px auto 12px auto !important;
  }
  .login-move {
    width: 100% !important;
  }
  .login-username {
    width: 74% !important;
  }
  .login-button-sub {
    width: 85% !important;
  }
}

</style>