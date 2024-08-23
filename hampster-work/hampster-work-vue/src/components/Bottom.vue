<script setup>
  import $ from "jquery";
  import { ref } from "vue";
  import bottomFixed from "./bottom/BottomFixed.vue";
  import { indexBlogName } from "../api/blog";

  const name_data = ref([{
    bt_name: "",
    blogNames: []
  }])

  async function getName() {
    await new Promise(resolve => {
      indexBlogName().then(data => {
      // $.get("http://localhost:8081/json/index/b_name", function(data) {
        name_data.value = data
        for (let i = 0; i < name_data.value.length; i++) {
          name_data.value[i].blogNames = name_data.value[i].blogNames.split(",").slice(-3)
          name_data.value[i].blogNames.push("...")
        }

        resolve()
      });
    })
  }
  await getName()
</script>

<template>
  <div class="bottom">
    <div class="bottom-blog-index" v-for="unit in name_data">
      <div class="bottom-blog-index-title">{{ unit.bt_name }}</div>
      <div style="display: block; width: 250px" v-for="name in unit.blogNames">
        <a :href="name === '...' ? '/search/' + unit.bt_name : '#'" class="bottom-loading-context">{{ name }}</a>
      </div>
    </div>
    <bottom-fixed/>
  </div>
</template>

<style scoped>
.bottom-blog-index-title {
  margin: 15px 20px 5px 20px;
  font-size: 30px;
  color: white;
  font-weight: 600;
  font-family: chaparral-pro,"Times New Roman",Times,serif;
}
.bottom-loading-context {
  width: auto;
  display: inline-block;
  white-space: pre-wrap;
  border-radius: 2px;
  margin: 0px 14px;
  padding: 6px 6px;
  line-height: 16px;
  font-size: 16px;
  color: white;
  font-weight: 400;
  transition: .3s;
}
.bottom-loading-context:hover {
  color: #ffc459;
  cursor: pointer;
}
.bottom-loading-context:active {
  outline: 2px solid #ffc459;
}
.bottom-loading-context:focus {
  outline: 2px solid #ffc459;
}
.bottom-blog-index {
  width: 250px;
}

</style>