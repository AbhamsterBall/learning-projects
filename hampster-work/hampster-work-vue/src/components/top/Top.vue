<script setup>
import loginIcon from '../svg/LoginIcon.vue'
import $ from 'jquery'
import { setColor } from '../svg/LoginIcon.vue'
import login from "../account/Login.vue";

const props = defineProps({
  title: String,
})

setColor("white")

$(() => {

  $(".loginIcon").hover(
      function() {
        $(".login-mini-back").css("opacity", "1")
        setColor("#2759b2")
      },
      function() {
        $(".login-mini-back").css("opacity", "0")
        setColor("white")
      }
  );

  checkIfLogin()
});

function checkIfLogin() {
  if (localStorage.getItem("utoken") != null) {
    $(".login").css("display", "none")
    $(".loginIcon").css("display", "none")
    $(".profile-img").attr("src", import.meta.env.VITE_API_MINIO_BASE_URL
      + "/profile/" + localStorage.getItem("utoken") + ".svg");
  } else {
    $(".profile").css("display", "none")
  }
}

</script>

<template>
  <div class="top-background">
    <div class="main-title">
      <div class="main-title-text">{{ props.title }}</div>
    </div>
    <a href="#" @click="loginClick" class="login">登 录</a>
    <a href="#" class="login-mini-back"></a>
    <login-icon @click="loginClick" width="48" height="48" color="white" class="loginIcon"/>
    <div class="profile">
      <img src="/default-profile.svg" alt="profile" class="profile-img">
    </div>
    <div class="more">
      <img src="/img/more.svg" alt="more" class="more-svg">
    </div>
    <login/>
  </div>
</template>

<script>
import $ from "jquery";

export function loginClick() {
  $(".mask-display").css("display", "block")

  setTimeout(() => {
    $(".mask-display").css("opacity", "1")
  }, 300)
}
</script>

<style scoped lang="scss">
.top-background {
  background: transparent;
  border-radius: 0px;
  position: relative;
  //z-index: 2000;
}
.main-title-text {
  transition: .3s;
  font-family: chaparral-pro,"Times New Roman",Times,serif,'华文宋体','宋体','微软雅黑';
}
.main-title {
  line-height: 100px; /*当搜索收回的时候 高度变为100px -> 60px*/
  z-index: 1000;
  position: relative;
}
.profile {
  display: block;
  position: absolute;
  right: 50px;
  top: 50px;
  width: 49px;
  height: 48px;
  /*background-color: white;*/
  border: white solid 6px;
  cursor: pointer;
  z-index: 2000;
  transition: .3s;
}
.more {
  display: none;
  position: absolute;
  left: 50px;
  top: 50px;
  width: 49px;
  height: 48px;
  /*background-color: white;*/
  border: white solid 6px;
  cursor: pointer;
  z-index: 2000;
  transition: .3s;
}
.profile-img {
  width: 52px;
  margin-left: -2px;
  margin-top: -2px;
  z-index: 2000;
}
//.login {
//  font-weight: 600
//}
.login:hover {
  background-color: white;
  color: #2759b2;
  border: #2759b2 solid 2px;
  box-shadow: 0 0 0 2.6px white;
  outline: #2759b2 solid 1.3px;
}
.loginIcon {
  display: none;
  position: absolute;
  right: 56px;
  top: 56px;
  z-index: 2000;
}
.loginIcon:hover {
  cursor: pointer;
}
.login-mini-back {
  display: block;
  opacity: 0;
  position: absolute;
  right: 61px;
  top: 61px;
  width: 38px;
  height: 38px;
  border-radius: 24px;
  background-color: white;
  border-width: 0px;
  outline: none;
  transition: .3s;
  z-index: 2000;
}
@media screen and (max-width: 1000px) {
  .main-title {
    letter-spacing: 5px;
  }
  .login {
    display: none;
  }
  .loginIcon {
    display: block;
  }
  .more {
    display: block;
  }
}

.main-title-text {
  margin-top: -10px;
}

.login {
  z-index: 2000;
  box-shadow: 0 0 0 2.6px #333333;
  outline: #333333 solid 1.3px;
}
</style>