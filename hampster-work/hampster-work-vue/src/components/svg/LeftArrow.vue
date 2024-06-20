<script setup>
defineProps({
  width: String,
  height: String,
})
</script>

<script>
import { ref } from "vue";

const color = ref("#000000")
export function setColor(str) {
  color.value = str
}
</script>

<template>
  <!-- Generator: Adobe Illustrator 26.2.1, SVG Export Plug-In . SVG Version: 6.00 Build 0)  -->
  <svg version="1.1" id="Layer_1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px"
       :width="width" :height="height" viewBox="0 0 88 88" style="enable-background:new 0 0 88 88;" xml:space="preserve">
    <polyline :style="{'stroke' : color}" class="st0" points="29.4,15.4 60.4,44 29.4,72.6 "/>
</svg>

</template>

<style scoped>
.st0{
  transition: .3s;
  fill:#FFFFFF;stroke:#000000;stroke-width:12;stroke-linecap:round;stroke-linejoin:round;stroke-miterlimit:10;}
</style>