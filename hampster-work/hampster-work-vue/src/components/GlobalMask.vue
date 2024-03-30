<script setup>
import $ from 'jquery'
function disappear() {
  $(".mask-display").css("opacity", "0")

  setTimeout(() => {
    $(".mask-display").css("display", "none")
  }, 300)
}
</script>

<template>
  <div class="mask mask-display" @click="disappear"></div>
</template>

<style scoped>
.mask {
  transition: 0.3s;
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(255, 255, 255, 0.5); /* 白色，半透明 */
  backdrop-filter: blur(10px);
  z-index: 2000;
  border-radius: 0px;
}
</style>