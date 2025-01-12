<script setup>
import rightArrow from '../svg/RightArrow.vue'
import leftArrow from '../svg/LeftArrow.vue'
import { maxPage } from "../../views/search/index.vue";
import $ from "jquery";
import * as left from "../svg/LeftArrow.vue";
import * as right from "../svg/RightArrow.vue";

$(() => {

})
</script>

<script>
import $ from "jquery";
import { getPage, setPage, getName} from "./List.vue";
import { setShowSuspense } from "../../views/search/index.vue";
import * as left from "../svg/LeftArrow.vue";
import * as right from "../svg/RightArrow.vue";
import { maxPage } from "../../views/search/index.vue";

export default {
  name: "PageNavi",
  mounted() {
    this.setNavigatorStyle()

    // console.log(maxPage.value)
    this.pageNaviStyle()


    left.setColor('#000000')
    right.setColor('#000000')
  },
  created() {

  },
  data() {
    return {
      preHover: false,
      nextHover: false,
    }
  },
  methods: {
    // pre style
    preOver() {
      this.preHover = true
      left.setColor('#2759b2')
      $('.pre-page').css('color', '#2759b2')
    },
    preOut() {
      this.preHover = false
      left.setColor('#000000')
      $('.pre-page').css('color', '#000000')
    },
    turnOver() {
      this.nextHover = true
      right.setColor('#2759b2')
      $('.next-page').css('color', '#2759b2')
    },
    turnOut() {
      this.nextHover = false
      right.setColor('#000000')
      $('.next-page').css('color', '#000000')
    },
    async pageProcess() {
      setShowSuspense(false)
      setTimeout(() => {setShowSuspense(true); this.setNavigatorStyle()}, 10)
      // await getName()
    },
    async toNextPage() {
      setPage(getPage() + 1 > maxPage.value ? maxPage.value === 0 ? 1 : maxPage.value : getPage() + 1)
      await this.pageProcess()
    },
    async toPrePage() {
      setPage(getPage() - 1 < 1 ? 1 : getPage() - 1)
      await this.pageProcess()
    },
    async toFirstPage() {
      setPage(1)
      await this.pageProcess()
    },
    async toLastPage() {
      setPage(maxPage.value)
      await this.pageProcess()
    },
    pageNaviStyle() {
      if (maxPage.value == 2) {
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
      } else if (maxPage.value <= 1) {
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
        // $('.middle').css('display', 'block')
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
    },
    setNavigatorStyle() {
      this.clearNaviStyle()
      if (getPage() == 1) {
        $('.left').css('border', '2px black solid')
        $('.left').css('font-weight', '600')
      } else if (getPage() == maxPage.value ) {
        $('.right').css('border', '2px black solid')
        $('.right').css('font-weight', '600')
      } else {
        $('.middle').html(getPage())
        $('.middle').css('border', '2px black solid')
        $('.middle').css('font-weight', '600')
      }
    },
    clearNaviStyle() {
      $('.middle').html("...")
      $('.left').css('border', '2px transparent solid')
      $('.middle').css('border', '2px transparent solid')
      $('.right').css('border', '2px transparent solid')
      $('.left').css('font-weight', '400')
      $('.middle').css('font-weight', '400')
      $('.right').css('font-weight', '400')
    }
  }
}
</script>

<template>
  <div class="navigator">
    <div class="turn-page turn-pre" @click="toPrePage"
          @focus="preOver"
          @blur="preOut"
          @mouseenter="preOver"
          @mouseleave="preOut"
         :style="{ color: preHover ? '#2759b2 !important' : '#000000 !important' }">
      <span class="pre-page">上一页</span>
      <left-arrow width="40" class="left-arrow" color="#000000"/>
    </div>
    <div class="page-num">
      <a class="left" @click="toFirstPage">1</a>
      <a class="middle">...</a>
      <a class="right" @click="toLastPage">{{ maxPage }}</a>
    </div>
    <div class="turn-page turn-next" @click="toNextPage"
         @focus="turnOver"
         @blur="turnOut"
        @mouseenter="turnOver"
        @mouseleave="turnOut"
         :style="{ color: nextHover ? '#2759b2 !important' : '#000000 !important' }">
      <span class="next-page">下一页</span>
      <right-arrow width="40" class="right-arrow"/>
    </div>
  </div>
</template>

<style scoped lang="scss">
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
  //color: #2759b2;
}
//.turn-page:focus {
//  cursor: pointer;
//  color: #2759b2;
//}

</style>