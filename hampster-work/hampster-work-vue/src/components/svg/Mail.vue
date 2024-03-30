<script setup>
defineProps({
  width: String,
  height: String,
})
</script>

<script>
import { ref } from "vue";

const color = ref("#dce6ef")
export function setColor(str) {
  color.value = str
}
</script>

<template>
  <!-- Generator: Adobe Illustrator 26.2.1, SVG Export Plug-In . SVG Version: 6.00 Build 0)  -->
  <svg version="1.1" id="Layer_1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px"
       :width="width" :height="height" viewBox="0 0 88 88" :style="{'enable-background':'new 0 0 88 88', 'fill':color, 'transition':'.3s'}" xml:space="preserve">
      <path d="M69.8,80.5H18.2c-8.7,0-15.7-7.1-15.7-15.7V23.3c0-8.7,7.1-15.7,15.7-15.7h51.5c8.7,0,15.7,7.1,15.7,15.7v41.4C85.5,73.4,78.4,80.5,69.8,80.5z M21.2,19.5c-3.7,0-6.7,3-6.7,6.7v35.4c0,3.7,3,6.8,6.8,6.8h45.6c3.7,0,6.7-3,6.7-6.7V26.3c0-3.7-3-6.8-6.8-6.8H21.2z"/>
      <path class="st0" :style="{'stroke' : color}" d="M64.4,28.4L51.1,44c-0.6,0.7-1.3,1.4-2.1,1.9c-3.2,2.2-6.9,2.1-10.1-0.2c-0.8-0.6-1.5-1.2-2.1-2L23.5,28"/>
  </svg>
</template>

<style scoped>
.st0 {
  fill: none;
  stroke-width: 12;
  stroke-linecap: round;
  stroke-miterlimit: 10;
  transition: .6s;
}
</style>