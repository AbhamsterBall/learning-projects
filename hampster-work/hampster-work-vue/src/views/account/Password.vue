<script setup>
import {ref, toRefs} from 'vue'
import { inputResponse, inputTest, loadMoving, loadStop, hideWrong } from "./Login.vue";
import smallWarning from '../../components/svg/SmallWarning.vue'
import $ from "jquery";
import axios from "axios";
import bcrypt from 'bcryptjs';
import SetName from "./SetName.vue";

let buttonColor = ref("#a9acba")
let buttonPointer = ref("none")

let props = defineProps({
  account: String
})

let { account } = toRefs(props) // 解构为单独的变量
console.log(account)

$(() => {

  inputResponse('.login-signup-pass', "••••••••••••")

  inputTest('.login-signup-pass', buttonColor, buttonPointer)

})

function showOrHide() {
  console.log("showOrHide")
  let x = $(".login-signup-pass")[0];
  console.log(x)
  console.log(x.type)
  if (x.type === "password") {
    x.type = "text";
  } else {
    x.type = "password";
  }
}

function addAccount() {
  console.log(account)
  console.log(account._object.account)
  console.log($('.login-signup-pass').val())
  loadMoving()
  const ur = "http://localhost:8082/json/user/register"
  axios.post(ur, {
    uId: 0,
    uName: "Registered Hamster",
    uMail: account._object.account,
    uPass: bcrypt.hashSync($('.login-signup-pass').val(), bcrypt.genSaltSync())
  })
      .then(data => {
        if (data.data.status) {
          localStorage.setItem("utoken", data.data.user_token)
          const accessKey = 'IEJiPMBh0214n5H8wNPH';
          const secretKey = 'RKcBEB9AXN4zyr02rMG1GpQ5ZDldGJhZEvdHo3nw';
          axios.get("http://47.109.149.213:9000/profile/default.svg", {responseType: 'blob'})
              .then(response => {
                console.log(response)
                const blob = new Blob([response.data]);
                axios.put("http://47.109.149.213:9000/profile/" + data.data.user_token + ".svg",
                    blob,  {
                  headers: {
                    'Content-Type': 'image/svg+xml', // 指定内容类型为二进制流
                    'Authorization': `Bearer ${accessKey}:${secretKey}`
                  }
                })
                    .then(response => {
                      loadStop()
                      moveLeft()
                      // toSetName()
                      console.log("add ok")
                    })
                    .catch(error => {
                      console.error(error);
                    });
              });
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

export function passTest(outString, isFormatValid) {
  let re = isFormatValid
  console.log(outString)
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
                @click.native="addAccount">下一步</button>
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