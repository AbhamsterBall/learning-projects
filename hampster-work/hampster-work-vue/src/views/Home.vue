<script setup>
import index_search from '../components/index/Search.vue'
import index_list from '../components/index/List.vue'
import top from '../components/Top.vue'
import loading from '../components/Loading.vue'
import bottom from '../components/Bottom.vue'
import $ from "jquery";
import mail from '../components/svg/Mail.vue'
import { ref } from 'vue'
import bottomFixed from "../components/bottom/BottomFixed.vue";
import { store } from '../main.js'
import axios from "axios";

getCurrenTitle(title)

$(() => {

  $(window).resize(function() {
    getCurrenTitle(title)
  });

  // getTest()

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
      // pass
    } else {
      selected.value = getSelectedText()
    }
  }, 3000)
};

</script>

<script>
import $ from "jquery";
import {ref} from "vue";

export function keepWedgeUnmoved(Wedge, time=1000) {
  $(Wedge).css("transition", "none")
  setTimeout(function() {
    $(Wedge).css("transition", ".3s")
  }, time)
}

const title = ref("HAMPSTER.WORK")
export const getCurrenTitle = (title) => {
  if ($(window).width() <= 1000) {
    title.value = "HAMPSTER"
  } else {
    title.value = "HAMPSTER.WORK"
  }
  $(".main-title").title = title.value
}

// /**
//  * test only
//  * @returns {Promise<void>}
//  */
// export async function getTest() {
//   await new Promise(resolve => {
//     $.get("http://PC-202403042047:8082/actuator/health", function(data) {
//       name_data.value = data.data
//       name_data.value.unshift({ btName: "ALL" })
//       resolve()
//     });
//   })
// }
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
          <loading boxWidth="300px" boxHeight="30px" bg="#1d2841" shadow="#161e2f" class="bottom-loading-title"/>  <!-- #0d111c -->
          <loading box-width="400px" box-height="125px" bg="#1d2841" shadow="#161e2f" class="bottom-loading-context"/>
        </div>
        <bottom-fixed/>
      </div>
    </template>
  </suspense>
</template>

<style scoped>
@media screen and (max-width: 600px) {
  .index-text {
    margin-top: 22px;
  }
}
</style>
