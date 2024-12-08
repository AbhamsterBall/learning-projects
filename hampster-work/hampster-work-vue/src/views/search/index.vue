<script setup>
// import top from '../../components/search/top/Top.vue'
import top from '../../components/top/Top.vue' // can I use Top.vue instead of search/top/Top.vue ???!!!
import { ref, onMounted } from 'vue';
import $ from "jquery";
import { getCurrenTitle } from "../Home.vue";
import index_search from '../../components/top/Search.vue'
import search_list from '../../components/search/List.vue'
import loading from '../../components/Loading.vue'
import * as left from '../../components/svg/LeftArrow.vue'
import * as right from '../../components/svg/RightArrow.vue'
import page_navi from '../../components/search/PageNavi.vue'
import bottom from '../../components/Bottom.vue'
import bottomFixed from "../../components/bottom/BottomFixed.vue";
import blur_list from '../../components/search/BlurList.vue'

if (!isTitleChanged) getCurrenTitle(title)

$(() => {

  $(window).resize(function() {
    if (!isTitleChanged) getCurrenTitle(title)
  });

});
let isBlurPage = window.location.href.indexOf("blur") !== -1;

let currentUrl = window.location.href.split('/');
if (currentUrl.length > 4) {
  if (!isBlurPage) {
    setTitle(currentUrl[currentUrl.length - 1])
  } else {
    if (currentUrl[4].indexOf("blur") !== -1) {
      setTitle("HAMPSTER.WORK")
      setSearchText(currentUrl[4].split("?")[1].split("=")[1])
    } else {
      setTitle(currentUrl[4])
      console.log(currentUrl[5].split("?")[1].split("=")[1])
      setSearchText(currentUrl[5].split("?")[1].split("=")[1])
    }
  }
}
</script>

<script>
import { ref } from "vue";
import { setSearchText } from "../../components/search/BlurList.vue";

const title = ref("HAMPSTER.WORK")
let isTitleChanged = false

export function setTitle(tempTitle) {
  title.value = tempTitle
  isTitleChanged = true
}

export function getTitle() {
  return title.value
}

const showSuspense = ref(true)

export function setShowSuspense(bool) {
  showSuspense.value = bool
}
</script>

<template>
  <top :title="title"/>
  <index_search/>
  <suspense v-if="showSuspense">
    <template #default>
      <template v-if="isBlurPage">
<!--        <div>blur {{ isSearching.value }}</div>-->
        <blur_list/>
      </template>
      <template v-else>
<!--        <div>search {{ isSearching.value }}</div>-->
        <search_list/>
      </template>
    </template>
    <template #fallback>
      <div class="text index-text" style="text-align: center; color: #444444;padding: 0.1% 0.1%">
        <loading boxWidth="94%" boxHeight="190px"/>
        <loading boxWidth="94%" boxHeight="190px"/>
      </div>
    </template>
  </suspense>
  <suspense>
    <template #default>
      <page_navi/>
    </template>
    <template #fallback>
      <div  class="navigator" style="text-align: center; color: #444444; padding: 0.1% 0.1%">
        <loading boxWidth="94%" boxHeight="40px"/>
      </div>
    </template>
  </suspense>
  <div class="seperator" style="width: 20px;height: 30px;background-color: transparent; margin-top: 12px"></div>
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
</template>

<style scoped>
.index-text {
  margin-top: 4px;
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
@media screen and (max-width: 1000px) {
  .seperator {
    margin-top: 55px !important;
  }
}
</style>