<script setup>
import $ from "jquery";
import mail from '../svg/Mail.vue'
import { setColor } from '../svg/Mail.vue'
import {ref, watch} from 'vue'

const mailHover = ref(false)
const mailFocus = ref(false)

$(() => {
  setColor("#9eacb8")
  $(".bottom-mail").hover(
      function() {
        setColor("white")
        mailHover.value = true
      },
      function() {
        // setColor("#dce6ef")
        setColor("#9eacb8")
        mailHover.value = false
      }
  );
});

// Watch for changes in mailHover or mailFocus
watch([mailHover, mailFocus], ([newMailHover, newMailFocus]) => {
  if (newMailHover || newMailFocus) {
    setColor('white')
  } else {
    setColor('#9eacb8')
  }
})

</script>

<template>
  <div class="bottom-text">
    <a href="/" class="bottom-net">HAMPSTER.WORK</a><br>
    <a href="mailto:ajh415@hotmail.com"
       @mouseenter="mailHover.value = true"
       @mouseleave="mailHover.value = false"
       @focus="mailFocus.value = true"
       @blur="mailFocus.value = false"
       :style="{ color: mailHover || mailFocus ? 'white' : '#9eacb8' }"
       class="bottom-mail">
      <div class="mail-icon">
        <mail width="20" height="20"/>
      </div>
      <span class="mail-text">ajh415@hotmail.com</span>
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
.bottom-mail {
  display: block;
}
.mail-icon {
  display: block;
  margin-right: -8px;
  margin-bottom: -10px;
  width: 20px;
  height: 7px;
  margin-top: 11px;
  margin-left: 0px;
  transform: scale(1.1);
}
.mail-text {
  padding-left: 29px;
}
@media screen and (max-width: 600px) {
  .bottom-text {
    text-align: center;
  }
  .mail-icon {
    margin-left: 47px;
  }
  .mail-text {
    padding-left: 33px;
  }
}
</style>