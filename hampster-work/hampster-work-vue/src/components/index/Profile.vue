<script setup>
import { getUserInfo } from "../../api/search/user.js";
import {ref} from "vue";

let userProfile = ref("/default-profile.svg")

async function getProfile() {
  await new Promise(resolve => {
    getUserInfo().then(data => {
      // // console.log(JSON.stringify(data))
      // // $.get("http://localhost:8081/json/index/bt_name", function(data) {
      // name_data.value = data.data
      userProfile.value = data.data.userProfile
      resolve()
    });
  })
}

await getProfile()
</script>

<template>
  <img :src=userProfile alt="profile" class="profile-img">
</template>

<style scoped>

</style>