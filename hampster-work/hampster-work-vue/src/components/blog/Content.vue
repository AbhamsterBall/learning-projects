<script setup>
import $ from "jquery";
import { marked } from "marked";
import { ref } from "vue";
import Loading from "../Loading.vue";
import * as all from "../../views/Home.vue";

$(() => {
  getContent()

  // $("<script>").prop("src", "highlight/lib/vendor/highlight.js/highlight.js").appendTo("head");
  // $("<script>").html("  hljs.initHighlightingOnLoad();").appendTo("head");
  // setTimeout(() => {hljs()}, 5000);
  // change width & height of content-box
  checkOverFlow()
  if ($(window).width() > 1000)
    $('.blog-content').css('width', window.innerWidth - $('.list-outline').width() - 100 - 23 + 'px')
  else
    $('.blog-content').css('width', ($(window).width() - 100) + 'px')

  $(window).resize(function() {
    checkOverFlow()
  });

  marked.setOptions({
    "async": false,
    "breaks": false,
    "extensions": null,
    "gfm": true,
    "hooks": null,
    "pedantic": false,
    "silent": false,
    "tokenizer": null,
    "walkTokens": null
  });

  testSelected()

  $('table').each(function() {
    // 创建一个新的 div 元素
    let div = $('<div class="table-scroll"></div>');
    // 将 div 插入到当前表格元素之前
    $(this).before(div);
    // 将当前表格元素移动到新创建的 div 元素中
    $(this).appendTo(div);
  });

})
// 获取<textarea>元素



function getSelectedText() {
  var text = "";
  if (typeof window.getSelection != "undefined") {
    text = window.getSelection().toString();
  } else if (typeof document.selection != "undefined" && document.selection.type == "Text") {
    text = document.selection.createRange().text;
  }
  return text;
}

function showRoundedBox() {
  const rect = window.getSelection().getRangeAt(0).getBoundingClientRect()
  returnedText.value = ""

  $('.rounded-box').css({
    display: 'block',
    left: `${rect.left + rect.width / 2 - parseInt($('.rounded-box').css('width').substring(0, $('.rounded-box').css('width').length - 2)) / 2}px`,
    top: `${rect.top - 42}px`
  });

  setTimeout(() => {$('.rounded-box').css('opacity', '1')}, 100)
}

let selected = ref()
let selectedTemp = ref()

const testSelected = function() {
  setInterval(() => {
    if ( selectedTemp.value == getSelectedText() ) {
      // pass
    } else {
      selectedTemp.value = getSelectedText()

      if (selectedTemp.value !== "") {
        showRoundedBox()
      }
      else {
        $('.rounded-box').css('opacity', '0')
        setTimeout(() => {$('.rounded-box').css('display', 'none')}, 100)
      }
    }
  }, 10)
  setInterval(() => {
    if ( selected.value == getSelectedText() ) {
      // pass
    } else {
      selected.value = getSelectedText()

      if (selected.value !== "") {
        isLoading.value = true
        showRoundedBox()
        getSummary(selected.value) //new Promise获取值并isLoading.value = false
      }
      else {
        $('.rounded-box').css('opacity', '0')
        setTimeout(() => {$('.rounded-box').css('display', 'none')}, 100)
      }
    }
  }, 3000)
};

let returnedText = ref("")
let isLoading = ref(true)
async function getSummary(info) {
  await new Promise(resolve => {
    $.get("http://localhost:8081/json/blog/" + info + "/getsummary", function(data) {
      isLoading.value = false
      returnedText.value = data
      resolve()
    });
  })
}


</script>

<script>
import {ref} from "vue";

let blog_content = ref("")
let isContentLoading = ref(true)

export async function getContent() {
  await new Promise(resolve => {
    $(".markdown").eq(0).html("");
    isContentLoading.value = true
    let currentUrl = window.location.href.split('/');
    $.get("http://localhost:8081/json/blog/" + currentUrl[5], function(data) {
      isContentLoading.value = false
      blog_content.value = data
      prepMarkDown()
      resolve()
    });
  })
}

function prepMarkDown() {
  blog_content.value = blog_content.value.replace(/<textarea>/g, '').replace(/\/<textarea>/g, '');
  let text = marked.parse(blog_content.value);

  $(".markdown").eq(0).html(text);

  $("<script>").html("hljs.initHighlighting.called = false;  hljs.initHighlighting();").remove();
  $("<script>").html("hljs.initHighlighting.called = false;  hljs.initHighlighting();").appendTo("head");

  $('.blog-content').css('overflow-y', 'hidden')
  $('.blog-content').css('overflow-y', 'auto')
}
import $ from "jquery";
import { marked } from "marked";
import { hljs } from "highlight/lib/vendor/highlight.js/highlight.js";

export default {
  mounted() {
    // for (var i = 0 ; i < $(".markdown").length ; i++) {
    //   let text = marked.parse($(".markdown").eq(i).text());
    //   $(".markdown").eq(i).html(text);
    // }
    //
    // $('.blog-content').css('overflow-y', 'hidden')
    // $('.blog-content').css('overflow-y', 'auto')
  }
}

export function checkOverFlow() {
  if ($(window).width() > 1000) {
    const windowWidth = $(window).width()
    // $('table').css('width', window.innerWidth - $('.list-outline').width() - 100 - 17 + 'px')
    if (window.innerWidth * 0.18 < 216) {
      $('.blog-content').css({
        'left': 216 + 'px'
      })
    } else {
      $('.blog-content').css('left', '18%')
    }
    $(".blog-content").css({
      'height': window.innerHeight - 210 + 'px',
      'width': windowWidth - $('.list-outline').width() - 100 - 6 + 'px', 
      /* TODO:
        1.problem with merged TOP: unexpected error in console log...
        2.merge all ajax/axios api under a folder
      */
      'max-width': windowWidth + 'px'
    })
  } else {
    // this.$nextTick(() => {
      $('.blog-content').css({
        'width': ($(window).width() - 100) + 'px',
        'left': '0px',
        'max-width': $(window).width() + 'px',
        'height': window.innerHeight - 210 + 'px'
      });
    // })
  }
}

import { shouldRefresh } from "./List.vue";
</script>

<template>
  <div class="blog-content" v-if="shouldRefresh">
    <div class="rounded-box">
      <loading boxWidth="50px" boxHeight="14px" class="summary-loading" v-show="isLoading"/>
      {{ returnedText }}
    </div>
    <loading boxWidth="94%" boxHeight="190px" v-show="isContentLoading"/>
    <loading boxWidth="94%" boxHeight="150px" v-show="isContentLoading"/>
    <mark class="markdown preText">
<!--    <pre>-->
      {{ blog_content }}
<!--    </pre>-->
    </mark>
  </div>
</template>

<style scoped>
.rounded-box {
  transition: .3s;
  opacity: 0;
  display: none;
  position: fixed;
  top: 0;
  left: 0;
  padding: 14px;
  background-color: white;
  color: black;
  font-size: 14px;
  pointer-events: none;
  box-shadow: 0 0 6px 3px #e9eaee;
  height: 14px;
  line-height: 14px;
}
.blog-content {
  padding: 25px 50px;
  background-color: white;
  width: 74.6%;
  /* width: 70%; */
  /* height: 80%; */
  height: 500px;
  position: absolute;
  left: 18%;
  top: 160px;
  border-radius: 0px;
  z-index: 10;
  overflow-x: hidden;
  line-height: 30px;
}

.blog-content::-webkit-scrollbar {
  width: 6px; /* 设置滚动条宽度 */
  background-color: white;
}

.blog-content::-webkit-scrollbar-thumb {
  background-color: #d4d8e8; /* 设置滚动条滑块颜色 */
  border-radius: 3px; /* 设置滚动条滑块的圆角 */
  opacity: .5;
}

.preText{
  font-family: "Microsoft YaHei";
}
.pre {
  white-space: -moz-pre-wrap; /* Mozilla, supported since 1999 */
  white-space: -pre-wrap; /* Opera */
  white-space: -o-pre-wrap; /* Opera */
  white-space: pre-wrap; /* CSS3 - Text module (Candidate Recommendation) http://www.w3.org/TR/css3-text/#white-space */
  word-wrap: break-word; /* IE 5.5+ */
}
.code{
  /*white-space: pre;*/
  /*font-family: 'Courier New', monospace; !* 使用等宽字体 *!*/
  padding: 5%;
  background-color: #eee;
  width: 66%;
  border: solid 2px #eee;
  position: relative;
  left: 12%;
  color: #333333;
  font-weight: 100;
  margin-bottom: -4px;

  font-family: Consolas,"courier new";
  /*background-color: #f1f1f1;*/
  /*height: 800px;*/
  /*padding: 10px;*/
  /*border: 1px solid #ccc;*/
  white-space: pre-wrap; /* 保留换行符 */
  /*overflow: hidden;*/
  resize: none;
  transition: .6s;
}
.codeNonClose{
  /*white-space: pre;*/
  /*font-family: 'Courier New', monospace; !* 使用等宽字体 *!*/
  padding: 5%;
  background-color: #eee;
  width: 66%;
  border: solid 2px #eee;
  position: relative;
  left: 12%;
  color: #333333;
  font-weight: 100;
  margin-bottom: -4px;

  font-family: Consolas,"courier new";
  /*background-color: #f1f1f1;*/
  /*height: 800px;*/
  /*padding: 10px;*/
  /*border: 1px solid #ccc;*/
  white-space: pre-wrap; /* 保留换行符 */
  /*overflow: hidden;*/
  resize: none;
  transition: .6s;
}
a{
  text-decoration: none;
  transition: .6s;
  color: #4472C4;

  white-space: -moz-pre-wrap; /* Mozilla, supported since 1999 */
  white-space: -pre-wrap; /* Opera */
  white-space: -o-pre-wrap; /* Opera */
  white-space: pre-wrap; /* CSS3 - Text module (Candidate Recommendation) http://www.w3.org/TR/css3-text/#white-space */
  word-wrap: break-word; /* IE 5.5+ */
}
a:hover{
  color: #10387e;
}

/*滚动条框设置*/
textarea::-webkit-scrollbar{
  width: 2px;
  background-color: rgb(225, 225, 225);
  opacity: 0%;
  border-radius: 20px;
  display: none;
}
/*滚动条设置*/
textarea::-webkit-scrollbar-thumb{
  /*-webkit-box-shadow: inset 0 0 6px rgba(0,0,0,.3);*/
  background-color: #c2c2c2;
  display: none;
  border-radius: 20px;
}
.expand{
  color: white; font-size: 15px;
  line-height: 30px;
}
.expand:hover{
  color: #bfc1d2;
}
img{
  width: 100%;
}
.hljs{
  border-radius: 6px;
  font-family: "Microsoft YaHei";
}
.simi-mark{
  border-radius: 6px;
  background-color: #f1f1f1;
  padding: 1em;
}
.markdown{
  margin: unset;
  padding: unset;
}
h1,h2,h3,h4,h5{
  padding-top: 25px;
  padding-bottom: 25px;
}

</style>