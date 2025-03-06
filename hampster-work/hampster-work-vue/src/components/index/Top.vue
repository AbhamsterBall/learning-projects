<script setup>
import loginIcon from '../svg/LoginIcon.vue'
import $ from 'jquery'
import { setColor } from '../svg/LoginIcon.vue'
import login from "../account/Login.vue";
import {store} from "../../main.js";
import Bottom from "../Bottom.vue";
import BottomFixed from "../bottom/BottomFixed.vue";
import Loading from "../Loading.vue";
import Profile from "./Profile.vue";

const props = defineProps({
  title: String,
})

setColor("#333333")

$(() => {
  $(".loginIcon").hover(
      function() {
        $(".login-mini-back").css("opacity", "1")
        setColor("#2759b2")
      },
      function() {
        $(".login-mini-back").css("opacity", "0")
        setColor("#333333")
      }
  );

  checkIfLogin()
});

function checkIfLogin() {
  console.log(store.state.token.length)
  if (store.state.token.length !== 280) {
    $(".login").css("display", "none")
    $(".loginIcon").css("display", "none")
    // $(".profile-img").attr("src", import.meta.env.VITE_API_MINIO_BASE_URL +
    // //     "/profile/" + localStorage.getItem("utoken") + ".svg");
    // $(".profile-img").attr("src", import.meta.env.VITE_API_MINIO_BASE_URL +
    //     "/profile/" + localStorage.getItem("utoken") + ".svg");
    // TODO : LOADING IMG
  } else {
    $(".profile").css("display", "none")
  }
}

function loginClick() {
  $(".mask-display").css("display", "block")

  setTimeout(() => {
    $(".mask-display").css("opacity", "1")
  }, 300)
}
</script>

<template>
	<div class="main-title">
    <div class="main-title-text">{{ props.title }}</div>
	</div>
  <a href="#" @click="loginClick" class="login">登 录</a>
  <a href="#" class="login-mini-back"></a>
  <login-icon @click="loginClick" width="48" height="48" color="white" class="loginIcon"/>
  <div class="profile">
    <suspense>
      <template #default>
        <profile/>
      </template>
      <template #fallback>
    <div style="background-color: #f6f6f6;
          width: 48px; height: 48px; border-radius: 24px;
          margin-left: -2px">
        <loading box-width="48px" box-height="48px" bg="#f6f6f6"
                 border-radius="24px" class="profile-img"/>
    </div>
      </template>
    </suspense>
  </div>
  <login/>
</template>

<style scoped lang="scss">
.login {
  font-weight: 500;
  //text-shadow: 0.5px 0 black;
  //font-family: 'Noto Serif CJK SC', 'Noto Serif CJK', 'Source Han Serif SC', ‘Source Han Serif’, source-han-serif-sc, serif
}
.main-title-text {
  font-family: chaparral-pro,"Times New Roman",Times,serif,'华文宋体','宋体','微软雅黑';
}
.profile {
  display: block;
  position: absolute;
  right: 50px;
  top: 50px;
  width: 49px;
  height: 48px;
  //background-color: white;
  border: #ffffff00 solid 6px;
  cursor: pointer;
}
.profile-img {
  width: 52px;
  margin-left: -0px;
  margin-top: -2px;
}
.login:hover {
  background-color: white;
  color: #2759b2;
  border: #2759b2 solid 2px;
  box-shadow: 0 0 0 3px white; /* 模拟边框 */
  outline: #2759b2 solid 1.5px;
}
.loginIcon {
  display: none;
  position: absolute;
  right: 56px;
  top: 56px;
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
}
@media screen and (max-width: 600px) {
  .main-title {
    letter-spacing: 2px;
    height: 100px;
    line-height: 80px;
    font-size: 35px;
  }
  .profile {
    top: 25px;
    right: 20px;
    transform: scale(0.9);
  }
  .login {
    display: none;
  }
  .loginIcon {
    top: 31px;
    right: 26px;
    transform: scale(0.9);
    display: block;
  }
}
</style>