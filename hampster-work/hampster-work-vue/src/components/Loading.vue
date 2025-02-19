<script setup>
  import $ from 'jquery'
	const props = defineProps({
		boxHeight: String,
		boxWidth: String,
    bg: {
      type: String,
      default: '#ffffff'
    },
    shadow: {
      type: String,
      default: '#eeeeee'
    },
    borderRadius: {
      type: String,
      default: '20px'
    }
  })

  function createFrame() {
    // 创建 keyframes 动画
    let keyframes = `
        @keyframes keyframes-${props.bg.substring(1)}-${props.shadow.substring(1)} {
    `;
    // 判断页面中是否已经存在 keyframes
    if (!$(`style:contains('${keyframes}')`).length) {

      for (let i = 0; i < 100; i += 3.33) {
        keyframes += `
          ${i}% {
              background: linear-gradient(135deg, ${props.bg} ${i - 50 + 3.33}%, ${props.shadow} ${i}%, ${props.bg} ${i + 50 - 3.33}%);
              opacity: ${i + 3.33 > 100 ? 0.05 : i > 50 ? 1 - ((i - 50) * 2) * 0.01 : (i * 2) * 0.01};
          }
      `;
      }

      keyframes += `
        }
    `;

      // 将 keyframes 添加到页面中的样式标签中
      $("<style>").prop("type", "text/css").html(keyframes).appendTo("head");
    }

  }
  createFrame()

</script>

<template>
  <div class="gradient-box" :style="{'width':boxWidth, 'height':boxHeight
  , 'background': bg
  , 'border-radius': borderRadius
  , 'animation':'keyframes-' + bg.substring(1) + '-' + shadow.substring(1) + ' 800ms infinite'}"></div>
</template>

<style scoped lang="scss">
.gradient-box {
  margin: 3%;
  // border-radius: 20px;
  background: linear-gradient(135deg, #ffffff -20%, #eeeeee 20%, #ffffff 60%);
  ///*animation: keyframes 800ms infinite;*/
}
</style>