<!-- @deprecated -->
<script setup>

</script>

<template>
  <!--  <global-mask/>-->
  <div class="login-signup-display mask-display login-display">
    <div class="mask-display login-display sub-login-signup">
      <div class="login-net lp-net">HAMPSTER.WORK</div>
      <div class="login-move move-ex">
        <div class="login-title">验证账号</div>
        <div class="login-wrong">密码错误，请输入其它密码或<a href="#" class="login-forget" style="margin-left: 0px">找回密码</a></div>
        <div class="login-input">
          <fieldset class="name-field">
            <legend class="name-legend">使用手机号或邮箱</legend>
            <input type="text" value="" placeholder="hampster@hampster.work | (+86) 12345678900" class="login-signup-input" maxlength="114">
          </fieldset>
          <div class="login-format">
            <small-warning width="16" height="16" style="margin-right: 4px; opacity: .8"/>
            <span id="login-warning">密码应该包含大小写字母，数字及至少一个特殊符号</span>
          </div>
          <a href="#" class="login-forget">获取新的邮箱</a>
        </div>
        <button class="login-button lp-button" @click.native="testAccount">下一步</button>
      </div>
    </div>
  </div>
</template>

<script>

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