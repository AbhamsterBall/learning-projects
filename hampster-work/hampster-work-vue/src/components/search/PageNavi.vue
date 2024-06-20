<script setup>
import $ from "jquery";
import { getPage, setPage, getName, getMaxPage } from "./List.vue";
import { setShowSuspense } from "./SearchIndex.vue";
import rightArrow from '../svg/RightArrow.vue'
import leftArrow from '../svg/LeftArrow.vue'
import * as left from "../svg/LeftArrow.vue";
import * as right from "../svg/RightArrow.vue";
import { getIsSearching } from "./SearchIndex.vue";
import { getBlurMaxPage } from "./BlurList.vue";

let maxPage = 1;
if (getIsSearching())
  maxPage = await getBlurMaxPage()
else
  maxPage = await getMaxPage()

$(() => {

  setNavigatorStyle()

  $('.turn-pre').hover(
      function() {
        left.setColor('#2759b2')
        $('.pre-page').css('color', '#2759b2')
      },
      function() {
        left.setColor('#000000')
        $('.pre-page').css('color', '#000000')
      }
  )

  $('.turn-next').hover(
      function() {
        right.setColor('#2759b2')
        $('.next-page').css('color', '#2759b2')
      },
      function() {
        right.setColor('#000000')
        $('.next-page').css('color', '#000000')
      }
  )

  pageNaviStyle()

})

async function pageProcess() {
  setShowSuspense(false)
  setTimeout(() => {setShowSuspense(true);  setNavigatorStyle()}, 10)
  await getName()
}
async function toNextPage() {
  setPage(getPage() + 1 > maxPage ? maxPage === 0 ? 1 : maxPage : getPage() + 1)
  await pageProcess()
}
async function toPrePage() {
  setPage(getPage() - 1 < 1 ? 1 : getPage() - 1)
  await pageProcess()
}
async function toFirstPage() {
  setPage(1)
  await pageProcess()
}

async function toLastPage() {
  setPage(maxPage)
  await pageProcess()
}

function pageNaviStyle() {
  if (maxPage == 2) {
    $('.middle').css('display', 'none')
    $('.navigator').css('width', '320px')
    $('.navigator').css('margin-left', '-160px')
    $('.next-page').css('transition', '0s')
    $('.right-arrow').css('transition', '0s')
    $('.next-page').css('margin-left', '231px')
    $('.right-arrow').css('margin-left', '281px')
    setTimeout(() => {
      $('.next-page').css('transition', '0.3s')
      $('.right-arrow').css('transition', '0.3s')
    }, 300)
  } else if (maxPage <= 1) {
    $('.middle').css('display', 'none')
    $('.right').css('display', 'none')
    $('.navigator').css('width', '260px')
    $('.navigator').css('margin-left', '-130px')
    $('.next-page').css('transition', '0s')
    $('.right-arrow').css('transition', '0s')
    $('.next-page').css('margin-left', '171px')
    $('.right-arrow').css('margin-left', '221px')
    setTimeout(() => {
      $('.next-page').css('transition', '0.3s')
      $('.right-arrow').css('transition', '0.3s')
    }, 300)
  } else {
    $('.middle').css('display', 'block')
    $('.navigator').css('width', '380px')
    $('.navigator').css('margin-left', '-190px')
    $('.next-page').css('transition', '0s')
    $('.right-arrow').css('transition', '0s')
    $('.next-page').css('margin-left', '291px')
    $('.right-arrow').css('margin-left', '341px')
    setTimeout(() => {
      $('.next-page').css('transition', '0.3s')
      $('.right-arrow').css('transition', '0.3s')
    }, 300)
  }
}

function setNavigatorStyle() {
  clearNaviStyle()
  if (getPage() == 1) {
    $('.left').css('border', '2px black solid')
    $('.left').css('font-weight', '600')
  } if (getPage() == maxPage ) {
    $('.right').css('border', '2px black solid')
    $('.right').css('font-weight', '600')
  } else {
    $('.middle').html(getPage())
    $('.middle').css('border', '2px black solid')
    $('.middle').css('font-weight', '600')
  }
}

function clearNaviStyle() {
  $('.left').css('border', '2px transparent solid')
  $('.middle').css('border', '2px transparent solid')
  $('.right').css('border', '2px transparent solid')
  $('.left').css('font-weight', '400')
  $('.middle').css('font-weight', '400')
  $('.right').css('font-weight', '400')
}

</script>

<template>
  <div class="navigator">
    <div class="turn-page turn-pre" @click="toPrePage">
      <span class="pre-page">上一页</span>
      <left-arrow width="40" class="left-arrow" color="#000000"/>
    </div>
    <div class="page-num">
      <a class="left" @click="toFirstPage">1</a>
      <a class="middle">...</a>
      <a class="right" @click="toLastPage">{{ maxPage }}</a>
    </div>
    <div class="turn-page turn-next" @click="toNextPage">
      <span class="next-page">下一页</span>
      <right-arrow width="40" class="right-arrow"/>
    </div>
  </div>
</template>

<style scoped>
.left:hover, .right:hover, .middle:hover {
  color: #2759b2;
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
.right-arrow {
  position: absolute;
  margin-left: 341px;
  margin-top: 18px;
  width: 24px;
}
.next-page {
  position: absolute;
  margin-left: 291px;
  margin-top: 20px;
  font-size: 14px;
  transition: .3s;
}
.left-arrow {
  position: absolute;
  transform: scaleX(-1);
  margin-left: 17px;
  margin-top: 18px;
  width: 24px;
}
.pre-page {
  position: absolute;
  margin-left: 48px;
  margin-top: 20px;
  font-size: 14px;
  transition: .3s;
}
.page-num {
  position: absolute;
  margin-left: 98px;
  margin-top: 21px;
  font-size: 14px;
  cursor: pointer;
}
.page-num a {
  padding: 20px 25px;
  border: 1px black solid;
  border-radius: 3px;
  color: black;
}
.page-num a:focus {
  font-weight: 900;
  border: 1px black solid;
}
.turn-page {
  transition: .3s;
}
.turn-page:hover {
  cursor: pointer;
  color: #2759b2;
}
</style>