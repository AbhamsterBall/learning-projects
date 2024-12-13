<script setup>
import { ref } from "vue";
import top from '../../components/top/Top.vue'
import index_search from '../../components/top/Search.vue'
import search_list from '../../components/blog/List.vue'
import Bottom from "../../components/Bottom.vue";
import Loading from "../../components/Loading.vue";
import BottomFixed from "../../components/bottom/BottomFixed.vue";
import $ from "jquery";
import Content from "../../components/blog/Content.vue";

$(() => {
  // $('.list-outline').css('height', window.innerHeight - 160 + 'px')

  adjustListShowSuspense()
  checkOverFlow()

  $(window).resize(function() {
    adjustListShowSuspense()
    checkOverFlow()

    // $('.bottom').css('margin-top', '69%') // 屏幕高减去top高度
  });
})

function checkOverFlow() {
  let height = window.innerHeight - 194 + 'px'
  if ($(window).width() > 1000) {
    const windowWidth = $(window).width()
    let listWidth = 0
    // $('table').css('width', window.innerWidth - $('.list-outline').width() - 100 - 17 + 'px')
    if (window.innerWidth * 0.18 < 216) {
      $('.blog-content').css({
        'left': 216 + 'px'
      })
      listWidth = 216
    } else {
      $('.blog-content').css('left', '18%')
      listWidth = windowWidth * 0.18
    }
    $(".blog-content").css({
      'height': height,
      // 'width': windowWidth - $('.list-outline').width() - 100 - 6 + 'px',
      'width': windowWidth - $('.list-outline').width() - 100 + 'px',
      /* TODO:
        # 1.problem with merged TOP: unexpected error in console log... // expected: only if no content
        2.merge all ajax/axios api under a folder **
        3.unexpected behavior for Content Panel Width while resize(<1000px -> >1000px)
      */
      'max-width': windowWidth - listWidth - 100 + 'px'
    })
    // console.log((windowWidth - listWidth - 100) + 'px ' + (windowWidth - $('.list-outline').width() - 100 - 6))
  } else {
    // this.$nextTick(() => {
    $('.blog-content').css({
      'width': ($(window).width() - 100) + 'px',
      'left': '0px',
      'max-width': ($(window).width()) + 'px',
      'height': height
    });

    // $('.list-outline').css({
    //   'display': 'none'
    // })
    // })
  }
}

function adjustListShowSuspense() {
  // hide list when window is resized to smaller than 1000px 手机端隐藏列表，并放在左上角
  // console.log($(window).width())
  if ($(window).width() < 1000) {
    if (getTitle() === "HAMPSTER.WORK")
      setTitle("HAMPSTER")
    $('.list-outline').css({
      'display': 'block',
      'z-index': '-2',
    })
    $('.extra-outline').css({
      'display': 'block',
      'z-index': '-1',
    })
    // console.log("enter")
  } else {
    if (getTitle() === "HAMPSTER")
      setTitle("HAMPSTER.WORK")
    $('.list-outline').css({
      'display': 'block',
      'z-index': '100',
    })
    $('.extra-outline').css('display', 'none')
  }

  checkOverFlow()
  // adjust height of content
  const element = document.querySelector('.extra-outline');
  if (element)
    element.style.setProperty('--display-value', window.innerHeight - 160 + 'px');
  // // console.log($('.list-outline').css('z-index'))

}
</script>

<template>
  <top :title="title"/>
  <div data-v-84162407="" style="height: 25px;background-color: white;margin-top: -30px;border-radius: 0px;z-index: 600;position: relative;/* width: 100px; */">
    <div data-v-84162407="" style="height: 25px; background-color: white; border-radius: 0px;"></div>
  </div>
  <index_search/>
  <suspense>
    <template #default>
      <search_list/>
    </template>
    <template #fallback>
      <div class="list-outline" ref="listOutline">
        <div class="text index-text" style="text-align: center; color: #444444;padding: 0.1% 0.1%">
          <loading boxWidth="82%" boxHeight="100px" class="loading-style"/>
          <loading boxWidth="82%" boxHeight="100px" class="loading-style"/>
          <loading boxWidth="82%" boxHeight="100px" class="loading-style"/>
          <loading boxWidth="82%" boxHeight="100px" class="loading-style"/>
          <loading boxWidth="82%" boxHeight="100px" class="loading-style"/>
        </div>
      </div>
    </template>
  </suspense>
<!--  <div class="list-outline extra-outline"-->
<!--       ref="listOutline"></div>-->

  <content/>


  <suspense>
    <template #default>
      <bottom/>
    </template>
    <template #fallback>
      <div class="bottom">
        <div class="bottom-blog-index">
          <loading boxWidth="300px" boxHeight="30px" bg="#2759b2" shadow="#214890" class="bottom-loading-title"/>
          <loading box-width="400px" box-height="125px" bg="#2759b2" shadow="#214890" class="bottom-loading-context"/>
        </div>
        <bottom-fixed/>
      </div>
    </template>
  </suspense>
  <div style="height: 40px;background-color: white;border-radius: 0px;position: relative;z-index: 200;"></div>
</template>

<script>
import {ref} from "vue";

const title = ref("HAMPSTER.WORK")
let isTitleChanged = false

const genre = window.location.href.split("//")[1].split("/")[2]
if (genre === "ALL")
  if ($(window).width() > 1000)
    setTitle("HAMPSTER.WORK")
  else
    setTitle("HAMPSTER")
else if (genre)
  setTitle(genre)
export function setTitle(tempTitle) {
  title.value = tempTitle
  isTitleChanged = true
}

export function getTitle() {
  return title.value
}

// const showListSuspense = ref(true)
//
// export function setshowListSuspense(bool) {
//   showListSuspense.value = bool
// }
//
// export function getshowListSuspense() {
//   return showListSuspense.value
// }

// let isSearching = ref(false)

// export function setIsSearching(bol) {
//   isSearching.value = bol
// }
//
// export function getIsSearching() {
//   return isSearching.value
// }

</script>

<!--<style>-->
<!--body {-->
<!--  background-color: black;-->
<!--}-->
<!--</style>-->

<style lang="scss" scoped>
.index-text {
  margin-top: 16px;
  box-shadow: 0 0 8px 3px #e8e8e8;
  border-radius: 0px 0px 40px 40px;
  z-index: 0;
}
.navigator {
  width: 380px;
  height: 60px;
  border-radius: 60px;
  background-color: white;
  position: absolute;
  left: 50%;
  margin: 35px 0px 50px -190px;
  display: flex;
}
.bottom {
  margin-top: 0px;
  z-index: 2000;
  position: absolute;
}
.loading-style {
  left: 6%;
  position: relative;
  margin-top: 25px
}
@media screen and (max-width: 1000px) {
  .seperator {
    margin-top: 55px !important;
  }
  /* .bottom {
    /* margin-top: 4px; 
    margin-top: 80%;
  } */
}
.index-text {
  box-shadow: none !important;
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
  height: var(--display-value);
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
}
</style>