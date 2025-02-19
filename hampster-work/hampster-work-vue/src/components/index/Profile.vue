<script setup>
import { getUserInfo } from "../../api/search/user.js";

async function getProfile() {
  await new Promise(resolve => {
    getUserInfo().then(data => {
      // // console.log(JSON.stringify(data))
      // // $.get("http://localhost:8081/json/index/bt_name", function(data) {
      // name_data.value = data.data
      resolve()
    });
  })
}

await getProfile()
</script>

<template>
  <img src="/default-profile.svg" alt="profile" class="profile-img">
</template>

<style scoped>

</style>