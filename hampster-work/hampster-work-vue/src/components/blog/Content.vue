<script setup>
import $ from "jquery";
import { marked } from "marked";
import { ref } from "vue";
import Loading from "../Loading.vue";
import { getBlogContentSummary } from "../../api/blog/blog.js";

$(() => {


  // $("<script>").prop("src", "highlight/lib/vendor/highlight.js/highlight.js").appendTo("head");
  // $("<script>").html("  hljs.initHighlightingOnLoad();").appendTo("head");
  // setTimeout(() => {hljs()}, 5000);
  // change width & height of content-box
  // console.log("width: " + $(window).width())
  // checkOverFlow()
  if ($(window).width() > 1000)
    $('.blog-content').css('width', window.innerWidth - $('.list-outline').width() - 100 - 23 + 'px')
  else
    $('.blog-content').css('width', ($(window).width() - 100) + 'px')

  // $(window).resize(function() {
  //   checkOverFlow()
  // });

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
    top: `${(rect.top - 42) < 166 ? 166 : (rect.top - 42)}px`
  });

  // console.log(`top: ${(rect.top - 42) < 166 ? 166 : (rect.top - 42)}px`)

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
      } else {
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
    getBlogContentSummary(info).then(data => {
    // $.get("http://localhost:8081/json/blog/" + info + "/getsummary", function(data) {
      isLoading.value = false
      returnedText.value = data
      resolve()
    });
  })
}


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

<script>
import {ref} from "vue";
import { setTitle } from "../../views/blog/index.vue";
// const module = await import("../../views/blog/index.vue")
// const { setTitle } = module

let blog_content = ref("")
let isContentLoading = ref(true)

import * as hljs from '../../highlight/highlight.min.js'

// function
import $ from "jquery";
import { marked } from "marked";
// import { hljs } from "highlight/lib/vendor/highlight.js/highlight.js";

export default {
  name: "Content",
  mounted() {
    this.getContent(this)
    // for (var i = 0 ; i < $(".markdown").length ; i++) {
    //   let text = marked.parse($(".markdown").eq(i).text());
    //   $(".markdown").eq(i).html(text);
    // }
    //
    // $('.blog-content').css('overflow-y', 'hidden')
    // $('.blog-content').css('overflow-y', 'auto')
  },
  methods: {
    async getContent(self) {
      await new Promise(resolve => {
        $(".markdown").eq(0).html("");
        isContentLoading.value = true
        let currentUrl = window.location.href.split('/');
        if (currentUrl[4] !== undefined && currentUrl[4] !== "") {
          setTitle(
              currentUrl[4] === "ALL" ? (
                  ($(window).width() > 1000) ? "HAMPSTER.WORK" : "HAMPSTER"
              ) : currentUrl[4]
          )
        }
        if (currentUrl[5] !== undefined && currentUrl[5] !== "")
          getBlogContent(currentUrl[5]).then((data) => {
            // $.get("http://localhost:8081/json/blog/" + currentUrl[5], function(data) {
            isContentLoading.value = false
            blog_content.value = data
            this.prepMarkDown(self)
            resolve()
          });
        else {
          isContentLoading.value = false
          this.prepMarkDown(self)
          resolve()
        }
      })
    },
    prepMarkDown(self) {
      blog_content.value = blog_content.value.replace(/<textarea>/g, '').replace(/\/<textarea>/g, '');
      let text = marked.parse(blog_content.value);

      // text = hljs.highlight(text, { language: 'javascript' }).value

      self.$forceUpdate();

      self.$nextTick(() => {
        $(".markdown").eq(0).html(text);

        console.log(text)

        $("<script>").html("hljs.initHighlighting.called = false;  hljs.initHighlighting();").remove();
        $("<script>").html("hljs.initHighlighting.called = false;  hljs.initHighlighting();").appendTo("body");

        // setTimeout(() => {
        //   hljs.initHighlighting.called = false;
        //   hljs.initHighlighting();
        // }, 1000)

        // hljs.initHighlightingOnLoad();
        // hljs.highlightAll()
        // hljs.highlightBlock($(".markdown").eq(0))

        $('.blog-content').css('overflow-y', 'hidden')
        $('.blog-content').css('overflow-y', 'auto')
      })

    }
  }
}



import { shouldRefresh } from "./List.vue";
import {getBlogContent} from "../../api/blog/blog.js";
</script>

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
  padding: 20px 50px;
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
  z-index: 3000;
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