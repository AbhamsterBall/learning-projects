<script setup>
defineProps({
  width: String,
  height: String,
})
</script>

<script>
import { ref } from "vue";

const color = ref("white")
export function setColor(str) {
  color.value = str
}
</script>

<template>
  <!-- created by adobe illustration -->
  <svg id="a" xmlns="http://www.w3.org/2000/svg"
       :width="width" :height="height" viewBox="0 0 83.67 85.56">
    <line class="b" :stroke="color" x1="37.92" y1="23.41" x2="59.13" y2="41.83"/>
    <line class="b" :stroke="color" x1="38.43" y1="61.71" x2="59.63" y2="43.28"/>
    <path class="b" :stroke="color"
          d="M9.29,23.95C15.71,13.2,27.46,6,40.89,6c20.31,0,36.78,16.47,36.78,36.78s-16.47,36.78-36.78,36.78c-12.83,0-24.12-6.56-30.7-16.52"/>
    <line class="b" :stroke="color" x1="6" y1="42.78" x2="60.02" y2="42.78"/>
  </svg>
</template>

<style scoped>
.b {
  fill: none;
  //stroke: #000;
  stroke-linecap: round;
  stroke-linejoin: round;
  stroke-width: 8px;
}
</style>