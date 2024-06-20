<script setup>
import { ref } from 'vue'
import $ from 'jquery'
import * as all from "../Home.vue"
import { getTitle } from './SearchIndex.vue'

console.log(getTitle())

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

  $('.main-title').css('box-shadow', 'none')

  let isIn = true
  $('.index-text').hover(
      function() {
        $('.main-title').css('box-shadow', 'none')
        isIn = true
      },
      function() {
        isIn = false
        setTimeout(() => {
          if (!isIn) $('.main-title').css('box-shadow', '0 0 6px 3px #bfc8ec')
        }, 600)
      }
  )

})

let small_title = ref()
let description = ref()
let date = ref()
</script>

<script>
import {ref} from "vue";
import $ from "jquery";
import { getTitle } from './SearchIndex.vue'

const page = ref(1)

export function getPage() {
  return page.value
}
export function setPage(pageNum) {
  page.value = pageNum
}

const name_data = ref([])
const max_page = ref(1)

export async function getMaxPage() {
  await getMaxPageAjax()
  console.log(max_page.value)
  return max_page.value
}

export async function getName() {
  await new Promise(resolve => {
    console.log("http://localhost:8081/json/search/" + getTitle() + "/b_name/" + page.value)
    $.get("http://localhost:8081/json/search/" + getTitle() + "/b_name/" + page.value, function(data) {
      console.log(data)
      name_data.value = data
      resolve()
    });
  })
}

export async function getMaxPageAjax() {
  await new Promise(resolve => {
    $.get("http://localhost:8081/json/search/" + getTitle() + "/b_name/maxpage", function(data) {
      console.log(data)
      max_page.value = data
      console.log(max_page.value)
      resolve()
    });
  })
}
</script>

<template>
  <teleport to="head">
    <component :is="'script'" type="application/ld+json">
      {
        "@context": "https://schema.org",
        "@type": "Article",
        "headline": "{{ small_title }}",
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
			 <a href="/java/index.html" class="mainHref"
          v-for="(name_item, index) in name_data"
          :style="[index === name_data.length - 1 && windowWidth > 1000 ? { 'border-radius': '0px 0px 40px 40px' } : {},
               index === name_data.length - 1 && windowWidth <= 1000 ? { 'border-radius': '0px 0px 30px 30px' } : {}]"
       >
         {{ name_item.b_name.toUpperCase() }}
       </a>
			 <!-- <a href="/python/index.html" class="mainHref">PYTHON</a> -->
	 </div>
</template>

<style scoped>
.index-text {
  margin-top: 4px;
  box-shadow: 0 0 8px 3px #e8e8e8;
  border-radius: 0px 0px 40px 40px;
  z-index: 0;
}
@media screen and (max-width: 1000px) {
  .index-text {
    margin-top: 0px;
  }
  .mainHref {
    padding-top: 42px;
    padding-bottom: 42px;
  }
}
</style>
