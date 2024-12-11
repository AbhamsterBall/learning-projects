<script setup>
import { ref } from 'vue'
import $ from 'jquery'
import * as all from "../../views/Home.vue"
import { getTitle, setTitle } from '../../views/blog/index.vue'
import BlogRefresh from "../svg/BlogRefresh.vue";
import Content from "./Content.vue"

await getName()

const windowWidth = ref($(window).width())

function updateWindowWidth() {
  windowWidth.value = $(window).width()
}

function adjustListShowSuspense() {
  // hide list when window is resized to smaller than 1000px 手机端隐藏列表，并放在左上角
  if ($(window).width() < 1000) {
    $('.list-outline').css({
      'display': 'block',
      'z-index': '-2',
    })
    $('.extra-outline').css({
      'display': 'block',
      'z-index': '-1',
    })
  } else {
    $('.list-outline').css({
      'display': 'block',
      'z-index': '100',
    })
    $('.extra-outline').css('display', 'none')
  }
}
$(() => {
  setShowRefresh()

  $('.list-outline').css('height', window.innerHeight - 160 + 'px')
  $(window).resize(function() {
    updateWindowWidth()
    // 更新窗口大小信息
    all.keepWedgeUnmoved('.mainHref')

    $('.list-outline').css('height', window.innerHeight - 160 + 'px')
    checkOverflow()
    adjustListShowSuspense()
  });

  // $('.main-title').css('box-shadow', 'none')
  //
  // let isIn = true
  // $('.index-text').hover(
  //     function() {
  //       $('.main-title').css('box-shadow', 'none')
  //       isIn = true
  //     },
  //     function() {
  //       isIn = false
  //       setTimeout(() => {
  //         if (!isIn) $('.main-title').css('box-shadow', '0 0 8px 3px #e8e8e8')
  //       }, 600)
  //     }
  // )

  checkOverflow()
  adjustListShowSuspense()

  // let sepHref = window.location.href.split('/');

  $("<style>").prop("type", "text/css").html("@keyframes rotate {\n" +
      "  from {\n" +
      "    transform: rotate(0deg); /* 从0度开始旋转 */\n" +
      "  }\n" +
      "  to {\n" +
      "    transform: rotate(360deg); /* 旋转到360度 */\n" +
      "  }\n" +
      "}").appendTo("head");

  for (let i = 0; i < name_data.value.length; i++) {
    $('.' + name_data.value[i].btName + '-href').hover(() => {
      if (!showRefresh.value[i])
       $('.' + name_data.value[i].btName + '-refresh').css('opacity', '0.84')
    }, () => {
      if (parseFloat($('.' + name_data.value[i].btName + '-refresh').css('opacity')) < 0.85) {
        $('.' + name_data.value[i].btName + '-refresh').css('opacity', '0')
      }
    })
  }
})

let small_title = ref()
let description = ref()
let date = ref()
</script>

<script>
import {ref} from "vue";
import $ from "jquery";
import { getTitle, setTitle } from '../../views/blog/index.vue'

const page = ref(1)

export function getPage() {
  return page.value
}
export function setPage(pageNum) {
  page.value = pageNum
}

const name_data = ref([])
// const max_page = ref(1)

// export async function getMaxPage() {
//   await getMaxPageAjax()
//   return max_page.value
// }

export async function getName() {
  await new Promise(resolve => {
    indexBtName().then(data => {
    // $.get("http://localhost:8081/json/index/bt_name", function(data) {
      name_data.value = data.data
      name_data.value.unshift({ btName: "ALL" })
      resolve()
    });
  })
}

// export async function getMaxPageAjax() {
//   await new Promise(resolve => {
//     $.get("http://localhost:8081/json/search/" + getTitle() + "/b_name/maxpage", function(data) {
//       max_page.value = data
//       resolve()
//     });
//   })
// }

function checkOverflow() {
  let scrollArea = $('.list-outline');
  let content = $('.index-text');

  // 如果内容高度小于等于元素高度，则隐藏滚动条
  if (parseInt(content.outerHeight()) < parseInt(scrollArea.css('height').split("px")[0]) - 3) {
    scrollArea.css('overflow-y', 'hidden');
  } else {
    scrollArea.css('overflow-y', 'scroll');
  }

}

let showRefresh = ref([])

async function setShowRefresh() {
  await getName()
  for (let i = 0; i < name_data.value.length; i++) {
    showRefresh.value.push(false)
  }
}

async function expandCategory(btName, index) {
  if (!showRefresh.value[index]) {
    $('.' + btName + '-refresh').css('opacity', '.85');
    $('.' + btName + '-refresh').css('animation', 'rotate 1s linear infinite')
    // $('.blog-refresh').css('animation', 'rotate 1s linear infinite')
    await getBlogName(btName, index)
    setTimeout(function() {
      $('.' + btName + '-refresh').css('animation', 'none');
      $('.' + btName + '-refresh').css('opacity', '0');
      showRefresh.value[index] = true
    }, 500);
  } else {
    // for (let i = 0 ; i < $('.blogHref').length ; i++) {
    //   // $('.blogHref')[i].style.backgroundColor = "rgb(243, 243, 243)";
    //   $('.blogHref')[i].style.backgroundColor = "transparent";
    //   $('.blogHref')[i].style.color = 'black';
    // }
    $('.' + btName).css('background-color', 'white')
    $('.' + btName).css('height', '0px')
    setTimeout(() => {$('.' + btName).css('opacity', '0')}, 300)
    $('.' + btName)[0].style.backgroundColor = 'white'
    $('.' + btName)[0].style.height = '0px'
    // $('.' + btName).css('opacity', '0')
    setTimeout(() => {
      checkOverflow()
    }, 500)
    showRefresh.value[index] = false
  }
}

let blog_name = ref({})

export async function getBlogName(btName, index) {
  await new Promise(resolve => {
    getBlogNameByType(btName).then(data => {
    // $.get("http://localhost:8081/json/blog/" + btName + "/b_name", function(data) {
      // setTimeout($('.' + btName + '-refresh').css('animation', 'none'), 10000)
      blog_name.value[btName] = data
      if (btName === 'ALL') {
        if ($(window).width() < 1000)
          setTitle("HAMPSTER")
        else
          setTitle("HAMPSTER.WORK")
      } else {
        setTitle(btName)
      }
      $('.' + btName).css('opacity', '1')
      setTimeout(() => {
        $('.' + btName)[0].style.backgroundColor = '#f3f3f3'
        $('.' + btName).css('background-color', '#f3f3f3')
        if (index === showRefresh.value.length - 1) {
          $('.' + btName)[0].style.height = ($('.' + btName)[0].scrollHeight - 45) + 'px'
          $('.' + btName).css('height', ($('.' + btName)[0].scrollHeight - 45) + 'px')}
        else {
          $('.' + btName)[0].style.height = ($('.' + btName)[0].scrollHeight - 30) + 'px'
          $('.' + btName).css('height', ($('.' + btName)[0].scrollHeight - 30) + 'px') }
          showRefresh.value[index] = true
      }, 500)
      setTimeout(() => {
        checkOverflow()
      }, 1000)
      resolve()
    });
  })
}

export let current_blog_name = ref("")
export let shouldRefresh = ref(true)

import { getContent} from "./Content.vue";
import {getBlogNameByType, indexBtName} from "../../api/blog.js";

function clicked(e, type, name) {
  let all_list = $('.blogHref')
  for (let i = 0 ; i < $('.blogHref').length ; i++) {
    // $('.blogHref')[i].style.backgroundColor = "rgb(243, 243, 243)";
    $('.blogHref')[i].style.backgroundColor = "transparent";
    $('.blogHref')[i].style.color = 'black';
  }
  event.target.style.backgroundColor = '#476fb7';
  event.target.style.color = 'white';

  let newState = { page: "example" };
  let newUrl = "/blog/" + type + "/" + name;
  window.history.pushState(newState, "", newUrl);

  if (type === 'ALL') {
    if ($(window).width() < 1000)
      setTitle("HAMPSTER")
    else
      setTitle("HAMPSTER.WORK")
  } else {
    setTitle(type)
  }
  current_blog_name = name;

  getContent()
  // // 设置条件变量为true，触发组件重新渲染
  // shouldRefresh.value = false;
  // // 在一段时间后将条件变量重置为false，以便下次刷新
  // setTimeout(() => {
  //   shouldRefresh.value = true;
  // }, 100); // 设置一个适当的延迟时间

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
  <div class="list-outline" ref="listOutline">
	 <div class="text index-text" style="text-align: center; color: #444444;">
     <div v-for="(name_item, index) in name_data">
       <button :class="'mainHref ' + name_item.btName + '-href'" @click="expandCategory(name_item.btName, index)">
         {{ name_item.btName }} <blog-refresh width="25" height="25" :class="'blog-refresh ' + name_item.btName + '-refresh'"/>
       </button>
       <div :class="'title-list ' + name_item.btName">
          <div class="blogHref" v-for="(name, blog_index) in blog_name[name_item.btName]" @click="clicked($event, name_item.btName, name.b_name)">{{ name.b_name }}</div>
       </div>
     </div>
			 <!-- <a href="/python/index.html" class="mainHref">PYTHON</a> -->
	 </div>
  </div>
</template>

<style scoped>
.title-list {
  transition: .3s;
  height: 0px;
  /* background-color: #f3f3f3; */
  background-color: white;
  border-radius: 0px;
  margin-top: -15px;
  z-index: 200;
  position: relative;
  margin-bottom: -15px;
  overflow: hidden;
  padding-top: 15px;
  padding-bottom: 15px;
}
.blog-refresh {
  display: block;
  opacity: 0;
  left: 79%;
  position: absolute;
  margin-top: -31px;
  transition: .6s;
}
.blogHref {
  transition: .6s;
  padding-top: 22px;
  padding-bottom: 22px;
  font-size: 16px;
  line-height: 16px;
  text-align: left;
  padding-left: 25px;
  padding-right: 25px;
  line-height: 22px;
  border-radius: 0px;
}
.blogHref:hover {
  cursor: pointer;
  background-color: #476fb7 !important;
  color: white !important;
}
.blogHref:visited {
  cursor: pointer;
  background-color: #476fb7;
  color: white;
}
.mainHref:hover {
  cursor: pointer;
  .blog-refresh {
    display: block;
    opacity: .85;
  }
}
.mainHref:focus {
  .blog-refresh {
    opacity: .85;
    animation: rotate 1s linear infinite; /* 以2秒为周期，线性无限次播放 */
  }
}
@keyframes rotate {
  from {
    transform: rotate(0deg); /* 从0度开始旋转 */
  }
  to {
    transform: rotate(360deg); /* 旋转到360度 */
  }
}
.index-text {
  box-shadow: none !important;
}
.mainHref {
  outline: none;
  border: none;
  height: 87px;
  padding-top: 25px;
  padding-bottom: 25px;
  border-radius: 20px;
  text-align: left;
  text-indent: 25px;
  transition: 1s !important;
  z-index: 300;
  position: relative;
  background-color: white;
}
.mainHref:hover {
  background-color: #fafbff;
  color: black;
  box-shadow: 0 0 6px 3px #e9eaee;
  z-index:400;
}
.list-outline {
  z-index: 15;
  position: relative;
  box-shadow: 0 0 4px 2px #e8e8e8 !important;
  width: 18%;
  min-width: 216px;
  left: 0px;
  background-color: white;
  height: 80%;
  border-radius: 0px;
  overflow: scroll;
  overflow-x: hidden;
}

.list-outline::-webkit-scrollbar {
  width: 6px; /* 设置滚动条宽度 */
  background-color: #f3f3f3;
}

.list-outline::-webkit-scrollbar-thumb {
  background-color: #d4d8e8; /* 设置滚动条滑块颜色 */
  border-radius: 3px; /* 设置滚动条滑块的圆角 */
  opacity: .5;
}

.text {
  width: 100%;
  left: 0%;
}
.index-text {
  height: auto;
  margin-top: 16px;
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
