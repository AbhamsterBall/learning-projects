<script setup>
import loginIcon from '../../svg/LoginIcon.vue'
import $ from 'jquery'
import { setColor } from '../../svg/LoginIcon.vue'
import login from "../../../views/account/Login.vue";

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
  console.log(localStorage.getItem("utoken"))
  console.log(localStorage.getItem("uuuuuu"))
  if (localStorage.getItem("utoken") != null) {
    $(".login").css("display", "none")
    $(".loginIcon").css("display", "none")
    $(".profile-img").attr("src", "http://47.109.149.213:9000/profile/" + localStorage.getItem("utoken") + ".svg");
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
  <a href="#" @click="loginClick" class="login">登录</a>
  <a href="#" class="login-mini-back"></a>
  <login-icon @click="loginClick" width="48" height="48" color="white" class="loginIcon"/>
  <div class="profile">
    <img src="/default-profile.svg" alt="profile" class="profile-img">
  </div>
  <login/>
</template>

<style scoped>
.main-title-text {
  transition: .3s;
  font-family: chaparral-pro,"Times New Roman",Times,serif,'华文宋体','宋体','微软雅黑';
}
.main-title {
  line-height: 100px; /*当搜索收回的时候 高度变为100px -> 60px*/
  z-index: 50;
  position: relative;
}
.profile {
  display: block;
  position: absolute;
  right: 50px;
  top: 50px;
  width: 49px;
  height: 48px;
  //background-color: white;
  border: white solid 6px;
  cursor: pointer;
  z-index: 100;
  transition: .3s;
}
.profile-img {
  width: 52px;
  margin-left: -2px;
  margin-top: -2px;
  z-index: 100;
}
.login {
  position: absolute;
  right: 30px;
  top: 30px;
  //color: #2759b2;
  color: white;
  text-decoration: none;
  font-size: 18px;
  margin: 20px;
  transition: 0.3s;
  border: white solid 2px;
  padding: 15px 36px;
  border-radius: 20px;
  box-shadow: 0 0 0 3px #2759b2; /* 模拟边框 */
  outline: #2759b2 solid 1.5px;
  //background-color: white;
  z-index: 100;
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
  z-index: 100;
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
  z-index: 100;
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
</style>