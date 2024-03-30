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
    <span class="bottom-net">HAMPSTER.WORK</span><br>
    <a href="mailto:ajh415@hotmail.com" class="bottom-mail">
      <mail width="20" height="20"/>
      &nbsp;ajh415@hotmail.com
    </a>
    何宇荟©版权所有
  </div>
</template>

<style scoped>

</style>