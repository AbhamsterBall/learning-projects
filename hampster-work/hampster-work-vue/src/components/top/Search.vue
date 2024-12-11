<script setup>
import searchIcon from '../svg/SearchIcon.vue'
import { ref } from 'vue'
import $ from 'jquery'
import * as all from '../../views/Home.vue'
import { getTitle as getBlogTitle } from "../../views/blog/index.vue";
import { getTitle as getSearchTitle } from "../../views/search/index.vue";
import { useRoute } from 'vue-router';

// const color = ref("#535bf2")
const color = ref("rgb(232 232 234)")
const getPosOfIcon = () => {
  // if (window.innerWidth <= 1000) {
  //   $('.index-search').css("transition", "none")
  //   const le = $('#search')[0].offsetLeft + $('#search')[0].offsetWidth - 60
  //   $('.index-search').css("left", le + "px")
  // } else {
  if (!isExpand) {
    $('.index-search').css("transition", ".3s")
    $('.index-search').css("left", "50%")
  } else {
    if (window.innerWidth <= 1000) {
      $('.index-search').css("transition", "none")
      lessThan1000Expand()
    } else {
      $('.index-search').css("transition", "none")
      moreThan1000Expand()
    }
  }

  // }
}

let isExpand = false

$(() => {
  $('.index-search').css("transition", ".3s")
  $('#search').css('width', '0px')

  getPosOfIcon()

  all.keepWedgeUnmoved("#search")

  $(".index-search").hover(
      function() { // mouse enter
        if (isExpand) color.value = "#ffc459"
        else color.value = "#535bf2"
      },
      function() { // mouse pointer leave
        if (isExpand) color.value = "#535bf2"
        else color.value = "rgb(232 232 234)" // "#a9acba"

      }
  );

  $(window).resize(function() {
    // 更新窗口大小信息
    all.keepWedgeUnmoved('#search')

    getPosOfIcon()
  });

  $('#search').focusout(function() {
    if ($('#search').val() == "") {
      $('.index-search').css("transition", ".3s")
      $('.index-search').css('left', "50%")
      $('#search').css('width', '0px')
      $('.main-title-text').css('margin-top', '-10px')
      $('.index-search').css('margin-left', '-24px')
      $('#search').css('opacity', '0')
      color.value = "rgb(232 232 234)"
      isExpand = false
      $('.profile').css('top', "50px")
    }
  });

});

function getTitle() {
  return useRoute().name === 'Blog' ? getBlogTitle() : getSearchTitle()
}

function expandSearch() {
  // $('#search').css('display', 'block')
  if (isExpand) {
    $("#searchForm").submit();
  } else {
    $('#search').css('opacity', '1')
    setTimeout(() => {
          if (window.innerWidth <= 1000) {
            lessThan1000Expand()
          } else {
            moreThan1000Expand()
          }
        }
        , 20)
  }
}

function lessThan1000Expand() {
  $('#search').css('width', '82%')
  $('.main-title-text').css('margin-top', '-25px')
  let left = window.innerWidth * 0.91
  $('.index-search').css('margin-left', left - 65 + "px")
  color.value = "#535bf2"
  $('#search').focus()
  isExpand = true
  $('.profile').css('top', "15px")
  $('.index-search').css('left', "0%")
}

function moreThan1000Expand() {
  $('.index-search').css('left', "50%")
  $('#search').css('width', '630px')
  $('.main-title-text').css('margin-top', '-25px')
  $('.index-search').css('margin-left', '275px')
  color.value = "#535bf2"
  $('#search').focus()
  isExpand = true
  $('.profile').css('top', "50px")
}

</script>

<template>
  <form id="searchForm" :action="'/search' + (getTitle().includes('HAMPSTER') ? '' : '/' + getTitle()) + '/blur'" method="get">
    <input id="search" type="text" name="search" placeholder="搜索...">
    <searchIcon width="40" height="40" :color="color" class="index-search" @click="expandSearch"/>
  </form>
</template>

<style scoped>
#search {
  opacity: 0;
  background-color: white;
  width: 0px; /* original width 630px */
  border: solid 2px #2759b2;
  height: auto;
  position: relative;
  color: #333333;
  //line-height: 70px;
  //margin-top: 100px;
  padding: 25px 2%;
  font-size: 20px;
  transition: .6s;
  margin: -90px auto auto auto;
  display: block;
  //box-shadow: 0 0 6px 3px #1e4488; /* 模拟边框 */
  box-shadow: 0 0 6px 3px rgba(105, 105, 105, 0.1); /* 模拟边框 */
  z-index: 1000;
  line-height: 20px;
  border-radius: 22px;
}
#search:focus {
  outline: none;
  border: solid 2px #CD853F;
  //border: solid 2px white;
  transition: .6s;
}
.index-search {
  position: absolute;
  left: 50%;
  margin: -61px 0px 0px -24px; /*original left 275px*/
  transition: .3s;
  fill: #2759b2; /* original #2759b2 */
  cursor: pointer;
  z-index: 1000;
}

@media screen and (max-width: 1000px) {
  #search {
    /*margin-top: 50px;*/
    margin-top: -88px;
    width: 82%;
    text-indent: 15px;
    border-radius: 40px;
  }
  .index-search {
    margin: -61px 0px 0px -20px;
  }
}

</style>