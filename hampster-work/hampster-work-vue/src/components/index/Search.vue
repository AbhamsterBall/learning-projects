<script setup>
import searchIcon from '../svg/SearchIcon.vue'
import { ref } from 'vue'
import $ from 'jquery'
import * as all from '../../views/Home.vue'

const color = ref("#535bf2")
const getPosOfIcon = () => {
  if (window.innerWidth <= 1000) {
    $('.index-search').css("transition", "none")
    const le = $('#search')[0].offsetLeft + $('#search')[0].offsetWidth - 60
    $('.index-search').css("left", le + "px")
  } else {
    $('.index-search').css("transition", "none")
    $('.index-search').css("left", "50%")
  }
}

$(() => {

  getPosOfIcon()

  all.keepWedgeUnmoved("#search")

  $(".index-search").hover(
      function() {
        color.value = "#ffc459"
      },
      function() {
        color.value = "#535bf2"
      }
  );

  $(".index-search").click(function() {
    $("#searchForm").submit();
  });

  $(window).resize(function() {
    // 更新窗口大小信息
    all.keepWedgeUnmoved('#search')

    getPosOfIcon()
  });
});

</script>

<template>
  <form id="searchForm" action="/search/blur" method="get">
    <input id="search" type="text" name="searchkey" placeholder="搜索...">
    <searchIcon width="40" height="40" :color="color" class="index-search"/>
  </form>
</template>

<style scoped>
#search {
  background-color: white;
  width: 630px;
  border: solid 2px #1d2841; /* 2759b2 */
  height: auto;
  position: relative;
  color: #333333;
  line-height: 70px;
  /*margin-top: 100px;*/
  padding: 0% 2%;
  font-size: 20px;
  transition: .6s;
  margin: 100px auto auto auto;
  display: block;
  box-shadow: 0 0 6px 3px rgba(43, 68, 126, 0.31); /* bfc8ec 模拟边框 rgb(43 68 126 / 31%)*/
}
#search:focus {
  outline: none;
  border: solid 2px #CD853F;
  transition: .6s;
}
.index-search {
  position: absolute;
  left: 50%;
  margin: -59px 0px 0px 275px;
  transition: .6s;
  fill: #2759b2;
  cursor: pointer;
}

@media screen and (max-width: 1000px) {
  #search {
    margin-top: 50px;
    width: 82%;
    text-indent: 15px;
    border-radius: 22px;
  }
  .index-search {
    margin: -59px 0px 0px 0px;
  }
}
</style>