<script setup>
import index_search from './components/index/search.vue'
import index_list from './components/index/list.vue'
import top from './components/top.vue'
import loading from './components/loading.vue'
import bottom from './components/bottom.vue'
import $ from "jquery";
import mail from './components/svg/Mail.vue'
import { ref } from 'vue'
import bottomFixed from "./components/bottom/BottomFixed.vue";
import { store } from './main.js'

const title = ref("HAMPSTER.WORK")
const getPosOfIcon = () => {
  if ($(window).width() <= 1000) {
    title.value = "HAMPSTER"
  } else {
    title.value = "HAMPSTER.WORK"
  }
  $(".main-title").title = title.value
}

getPosOfIcon()

$(() => {

  $(window).resize(function() {
    getPosOfIcon()
  });

});

function getSelectedText() {
  var text = "";
  if (typeof window.getSelection != "undefined") {
    text = window.getSelection().toString();
  } else if (typeof document.selection != "undefined" && document.selection.type == "Text") {
    text = document.selection.createRange().text;
  }
  return text;
}

let selected = ref()

document.onmouseup = document.onkeyup = document.onselectionchange = function() {
  setInterval(() => {
    if ( selected.value == getSelectedText() ) {
      // console.log(getSelectedText());
    } else {
      selected.value = getSelectedText()
    }
  }, 3000)
};

// console.log(store.state.token)
</script>

<script>
import $ from "jquery";

export function keepWedgeUnmoved(Wedge, time=1000) {
  $(Wedge).css("transition", "none")
  setTimeout(function() {
    $(Wedge).css("transition", ".3s")
  }, time)
}
</script>

<template>
	<top :title="title"/>
  <index_search/>
	<suspense>
		<template #default>
			<index_list/>
		</template>
		<template #fallback>
			<div class="text index-text" style="text-align: center; color: #444444;padding: 0.1% 0.1%">
				<loading boxWidth="94%" boxHeight="190px"/>
			</div>
		</template>
	</suspense>
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

</style>
