<template>
  <router-view>
    <update-comp/>
  </router-view>
</template>

<script>
import updateComp from "./components/updateComp";
export default {
  name: "UpdatePage",
  path: "/update",
  components: {
    updateComp
  }
}
</script>

<style scoped>

</style>