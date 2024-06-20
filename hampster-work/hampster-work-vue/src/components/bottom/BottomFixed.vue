<script setup>
import $ from "jquery";
import mail from '../svg/Mail.vue'
import { setColor } from '../svg/Mail.vue'

$(() => {
  $(".bottom-mail").hover(
      function() {
        setColor("white")
      },
      function() {
        setColor("#dce6ef")
      }
  );
});
</script>

<template>
  <div class="bottom-text">
    <a href="/" class="bottom-net">HAMPSTER.WORK</a><br>
    <a href="mailto:ajh415@hotmail.com" class="bottom-mail">
      <mail width="20" height="20"/>
      &nbsp;ajh415@hotmail.com
    </a>
    何宇荟©版权所有
  </div>
</template>

<style scoped>
.bottom-net {
  width: auto;
  border-radius: 2px;
  padding: 6px 6px;
  margin-left: -6px;
}
.bottom-net:hover {
  color: #ffc459;
  cursor: pointer;
}
.bottom-net:active {
  outline: 2px solid #ffc459;
}
.bottom-net:focus {
  outline: 2px solid #ffc459;
}
</style>