<script setup>
import {ref, toRefs} from 'vue'
import { inputResponse, inputTest, loadMoving, loadStop } from "./Login.vue";
import smallWarning from '../svg/SmallWarning.vue'
import $ from "jquery";
import axios from "axios";
import bcrypt from 'bcryptjs';

let buttonColor = ref("#a9acba")
let buttonPointer = ref("none")

$(() => {

  inputResponse('.login-signup-pass', "••••••••••••")

  inputTest('.login-signup-pass', buttonColor, buttonPointer)

})

function setName() {
  if (localStorage.getItem("utoken") !== null) {
    const ur = "http://localhost:8082/json/user/setname/" + localStorage.getItem("utoken")
    axios.put(ur, {
      uName: $('.login-signup-name').val()
    })
        .then(data => {
          if (data.data == "更新成功") {
            $('.mask-display').css("opacity", "0")
            setTimeout(() => {
              $('.mask-display').css("display", "none")
              $('.set-name-display').css("display", "none")
              window.location.reload()
            }, 300)
          } else {

          }
        })
        .catch(error => {
          console.error(error);
        });
  } else {
    window.location.reload()
  }

}
</script>

<template>
  <div class="login-signup-display mask-display login-display">
    <div class="mask-display login-display sub-login-signup">
      <div class="login-net lp-net">HAMPSTER.WORK</div>
      <div class="set-name-move move-ex">
        <div class="login-title">设置用户名</div>
        <div class="login-mask login-display">
          <div class="login-loading ll-left"></div>
          <div class="login-loading ll-right" style="margin-left: -860px"></div>
        </div>
        <div class="login-wrong">密码错误，请输入其它密码或<a href="#" class="login-forget" style="margin-left: 0px">找回密码</a></div>
        <div class="login-input">
          <fieldset class="name-field">
            <legend class="name-legend">请输入用户名</legend>
            <input type="text" placeholder="Registered Hamster" value="Registered Hamster" class="login-signup-name login-input-general" maxlength="24">
          </fieldset>
          <div class="login-format">
            <small-warning width="16" height="16" style="margin-right: 4px; opacity: .8"/>
            <span id="login-warning">密码应该包含</span>
          </div>
<!--          <span class="login-forget sub-login-forget">-->
<!--            <input type="checkbox" class="login-show-pass login-forget" @click="showOrHide"/>-->
<!--            <span style="margin-left: 29px">显示密码</span>-->
<!--          </span>-->
        </div>
        <button class="pass-button login-button lp-button"
                :style="{ backgroundColor : buttonColor, pointerEvents : buttonPointer }"
                @click.native="setName">完成</button>
      </div>
    </div>
  </div>
</template>

<style scoped>
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
.set-name-move {
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
.login-signup-name {
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