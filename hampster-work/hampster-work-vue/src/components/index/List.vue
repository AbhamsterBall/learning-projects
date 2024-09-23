<script setup>
import { ref } from 'vue'
import $ from 'jquery'
import * as all from "../../views/Home.vue";
import { setTitle } from "../../views/search/index.vue";
import {indexBtName} from "../../api/blog.js";

const name_data = ref([])
async function getName() {
  await new Promise(resolve => {
    indexBtName().then(data => {
    // $.get("http://localhost:8081/json/index/bt_name", function(data) {
      name_data.value = data.data
      resolve()
    });
  })
}

await getName()

const windowWidth = ref($(window).width())

function updateWindowWidth() {
  windowWidth.value = $(window).width()
}

$(() => {
  $(window).resize(function() {
    updateWindowWidth()
    // 更新窗口大小信息
    all.keepWedgeUnmoved('.mainHref')
  });
})

let title = ref()
let description = ref()
let date = ref()
</script>

<template>
  <teleport to="head">
    <component :is="'script'" type="application/ld+json">
      {
      "@context": "https://schema.org",
      "@type": "Article",
      "headline": "{{ title }}",
      "description": "{{ description }}",
      "datePublished": "{{ date }}",
      "author": {
      "@type": "Person",
      "name": "何宇荟"
      }
      }
    </component>
  </teleport>
	 <div class="text index-text" style="text-align: center; color: #444444;">
			 <a :href="'/search/' + name_item.btName" class="mainHref"
          v-for="(name_item, index) in name_data"
          :style="[index === name_data.length - 1 && windowWidth > 1000 ? { 'border-radius': '0px 0px 30px 30px' } : {},
               index === 0 && windowWidth > 1000 ? { 'border-radius': '30px 30px 0px 0px' } : {},
               index === name_data.length - 1 && windowWidth <= 1000 ? { 'border-radius': '0px 0px 22px 22px' } : {},
               index === 0 && windowWidth <= 1000 ? { 'border-radius': '22px 22px 0px 0px' } : {}]"
       >
         {{ name_item.btName }}
       </a>
			 <!-- <a href="/python/index.html" class="mainHref">PYTHON</a> -->
	 </div>
</template>

<style scoped>
.mainHref {
  cursor: pointer;
}
@media screen and (max-width: 1000px) {
  .index-text {
    margin-top: 40px;
  }
  .mainHref {
    padding-top: 42px;
    padding-bottom: 42px;
  }
}
</style>
