<script setup>
import { ref } from 'vue'
import { inputResponse } from "./Login.vue";
import smallWarning from '../svg/SmallWarning.vue'
import $ from "jquery";

let buttonColor = ref("#a9acba")
let buttonPointer = ref("none")

$(() => {

  inputResponse('.login-signup-pass', "••••••••••••")

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

</script>

<template>
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
            <input type="password" value="" placeholder="••••••••••••" class="login-signup-pass login-input-general" maxlength="114">
          </fieldset>
          <div class="login-format">
            <small-warning width="16" height="16" style="margin-right: 4px; opacity: .8"/>
            <span id="login-warning">密码应该包含大小写字母，数字及至少一个特殊符号</span>
          </div>
          <input type="checkbox" class="login-show-pass" @click="showOrHide"/>
          <a href="#" class="login-forget">显示密码</a>
        </div>
        <button class="pass-button login-button lp-button"
                :style="{ backgroundColor : buttonColor, pointerEvents : buttonPointer }"
                @click.native="testAccount">下一步</button>
      </div>
    </div>
  </div>
</template>

<style scoped>
.login-forget {
  margin: 0px 10px;
}
.login-show-pass {
  margin-left: 27px;
  width: 18px;
  height: 18px;
  margin-top: 2px;
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