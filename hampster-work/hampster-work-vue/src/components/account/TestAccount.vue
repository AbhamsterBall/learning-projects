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

<style scoped>

</style>