<script setup>
defineProps({
  width: String,
  height: String,
  color: String
})
</script>

<template>
<!--  <svg version="1.0" xmlns="http://www.w3.org/2000/svg" aria-hidden="true" role="img"-->
<!--       :width="width" :height="height" viewBox="0 0 1280.000000 1230.000000"-->
<!--       preserveAspectRatio="xMaxYMax meet">-->
<!--    <metadata>-->
<!--      Created by potrace 1.15, written by Peter Selinger 2001-2017-->
<!--    </metadata>-->
<!--    <g transform="translate(0.000000,1230.000000) scale(0.100000,-0.100000)"-->
<!--       :fill="color" stroke="none">-->
<!--      <path d="M4970 12294 c-25 -2 -106 -8 -180 -14 -1181 -95 -2334 -616 -3184-->
<!-- -1440 -317 -307 -568 -614 -792 -967 -449 -708 -709 -1478 -796 -2358 -17-->
<!-- -173 -17 -720 0 -900 69 -738 273 -1425 604 -2040 500 -928 1256 -1675 2189-->
<!-- -2164 919 -481 1996 -677 3049 -555 868 100 1728 430 2427 931 56 40 104 73-->
<!--108 73 3 0 641 -588 1418 -1307 776 -718 1437 -1326 1467 -1350 72 -58 235-->
<!-- -138 335 -165 116 -31 355 -31 470 0 600 165 884 837 581 1375 -78 138 76 -8-->
<!-- -1913 1831 l-1080 998 84 142 c167 280 340 657 449 978 403 1187 368 2487 -98-->
<!--3656 -388 976 -1074 1820 -1949 2402 -726 482 -1517 764 -2399 855 -144 15-->
<!-- -682 28 -790 19z m614 -1879 c656 -70 1247 -314 1771 -731 141 -112 406 -377-->
<!--519 -519 594 -744 844 -1668 705 -2608 -183 -1239 -1087 -2308 -2284 -2700-->
<!-- -389 -128 -707 -174 -1138 -164 -268 6 -406 22 -632 72 -950 213 -1757 815-->
<!-- -2233 1666 -373 666 -506 1454 -372 2209 160 909 697 1723 1475 2236 455 300-->
<!--954 479 1498 538 169 19 520 19 691 1z"/>-->
<!--    </g>-->
<!--  </svg>-->
  <svg id="Layer_1" data-name="Layer 1" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 84 82"
       :width="width" :height="height">
    <circle :style="{'stroke' : color}" class="cls-1" cx="34.5" cy="34.5" r="28.5"/>
    <line :style="{'stroke' : color}" class="cls-1" x1="78" y1="76" x2="58" y2="57"/>
  </svg>
</template>

<style scoped>
g {
  transition: .6s;
}
.cls-1 {
  fill: none;
  stroke: #000;
  stroke-linecap: round;
  stroke-linejoin: round;
  stroke-width: 10.5px;

  transition: .6s;
}
</style>