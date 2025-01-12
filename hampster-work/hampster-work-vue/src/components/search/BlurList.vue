<script setup>
import { ref } from 'vue'
import $ from 'jquery'
import * as all from "../../views/Home.vue"
import { getTitle } from '../../views/search/index.vue'

await getName()

const windowWidth = ref($(window).width())

function updateWindowWidth() {
  windowWidth.value = $(window).width()
}

$(() => {

  let wid = window.innerWidth * 0.8 - 35
  $('.search-text').css('width', wid + 'px')

  $('em').css({
    'font-style': 'normal', // 设置为斜体
    'color': 'inherit', // 设置文字颜色为红色，你可以根据需要调整
    'font-weight': '900'
    // 其他样式...
  });

  $(window).resize(function() {
    updateWindowWidth()
    // 更新窗口大小信息
    all.keepWedgeUnmoved('.mainHref')

    let wid = window.innerWidth * 0.8 - 35
    $('.search-text').css('width', wid + 'px')
  });

  // $('.main-title').css('box-shadow', 'none')

  let isIn = true
  // $('.index-text').hover(
  //     function() {
  //       $('.main-title').css('box-shadow', 'none')
  //       isIn = true
  //     },
  //     function() {
  //       isIn = false
  //       setTimeout(() => {
  //         if (!isIn) $('.main-title').css('box-shadow', '0 0 8px 3px #e8e8e8') // 0 0 6px 3px #bfc8ec
  //       }, 600)
  //     }
  // )

})

function filterHTML(str) {
  return str.replace(/<h[1-6][^>]*>/g, '')
    .replace(/<\/h[1-6]>/g, '').replace(/""/g, '')
    .replace(/<textarea>/g, '').replace(/<\/textarea>/g, '')
    .replace(/</g, '&lt;')
    .replace(/&lt;\/em>/g, '</em>')
    .replace(/&lt;em>/g, '<em>');
}

let small_title = ref()
let description = ref()
let date = ref()

// function toBlog(name) {
//   this.$router.push({
//     path: '/blog/' + getUrlTitle() + '/' + name
//   })
// }
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
    <div class="mainHref"
         @click="toBlog(name_item.b_name)"
         v-for="(name_item, index) in name_data"
         :style="[index === name_data.length - 1 && windowWidth > 1000 ? { 'border-radius': '0px 0px 40px 40px' } : {},
               index === name_data.length - 1 && windowWidth <= 1000 ? { 'border-radius': '0px 0px 30px 30px' } : {}]"
    >
      <div class="search-title">{{ name_item.b_name.toUpperCase() }} <span class="title-seperator">|</span> {{ name_item.bt_name }}</div>
      <div class="search-text" v-html="filterHTML(name_item.b_highlights)"></div>
    </div>
  </div>
</template>

<script>
import {ref} from "vue";
import $ from "jquery";
import { getTitle } from '../../views/search/index.vue'
import { getPage } from './List.vue'
import {blogSearch} from "../../api/blog.js";

import { getUrlTitle } from "./List.vue";

export default {
  // name: 'BlurList',
  methods: {
    toBlog(name) {
      this.$router.push({
        path: '/blog/' + getUrlTitle() + '/' + name
      });
    }
  }
};

const name_data = ref([])
import { maxPage } from "../../views/search/index.vue";

// export async function getBlurMaxPage() {
//   await getMaxPageAjax()
//   return max_page.value
// }

const searchText = ref('是')

export function setSearchText(str) {
  searchText.value = str
}

export function getSearchText() {
  return searchText.value
}

export async function getName() {
  await new Promise(resolve => {
    let url = ""
    blogSearch(getTitle(), getSearchText(), getPage()).then((data) => {
    // if (!getTitle().includes("HAMPSTER"))
    //   url = "http://localhost:8081/json/search/blur/" + getTitle() + "/" + getSearchText() +  "/" + getPage()
    // else url = "http://localhost:8081/json/search/blur/" + getSearchText() +  "/" + getPage()
    // $.get(url, function(data) {
      name_data.value = data.content
      // max_page.value = data.max_page
      maxPage.value = data.max_page
      console.log(maxPage.value)
      // console.log(JSON.stringify(data))
      resolve()
    });
  })
}

// export async function getMaxPageAjax() {
//   await new Promise(resolve => {
//     // http://localhost:8081/json/search/blur/PYTHON/的/maxpage
//     let url = ""
//     if (!getTitle().includes("HAMPSTER"))
//       url = "http://localhost:8081/json/search/blur/" + getTitle() + "/" + getSearchText() +  "/maxpage"
//     else url = "http://localhost:8081/json/search/blur/" + getSearchText() +  "/maxpage"
//     $.get(url, function(data) {
//       max_page.value = data
//       resolve()
//     });
//   })
// }

</script>

<style scoped>
.main-title {
  box-shadow: 0 0 8px 3px #e8e8e8;

}
.title-seperator {
  font-family: 'Microsoft YaHei';
  margin-left: 10px;
  margin-right: 10px;
}
.mainHref {
  height: 138px;
  padding-top: 0px;
  padding-bottom: 0px;
  cursor: pointer;
  text-align: left;
}
.search-title {
  font-family: chaparral-pro,"Times New Roman",Times,serif,'华文宋体','宋体','微软雅黑';
  margin-left: 25px;
  padding-top: 15px;
}
.search-text {
  font-family: "Microsoft YaHei";
  font-size: 14px !important;
  line-height: 20px;
  font-weight: 400;
  margin-left: 25px;
  width: 95%;
  color: inherit;
  height: 60px;
  overflow: hidden;
  border-radius: 0px;
  opacity: .8;
  letter-spacing: .5px;
}
em {
  color: red;
}
.index-text {
  margin-top: 4px;
  box-shadow: 0 0 8px 3px #e8e8e8;
  border-radius: 0px 0px 40px 40px;
  z-index: 0;
}
@media screen and (max-width: 1000px) {
  .mainHref {
    height: 138px;
    padding-top: 0px !important;
    padding-bottom: 0px !important;
    cursor: pointer;
    text-align: left;
  }
  .index-text {
    margin-top: 0px;
  }
  .mainHref {
    padding-top: 42px;
    padding-bottom: 42px;
  }
}
</style>
