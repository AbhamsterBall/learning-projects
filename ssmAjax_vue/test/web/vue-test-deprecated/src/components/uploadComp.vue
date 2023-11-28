<template>
  <div>
    <input type="file" ref="fileInput" multiple>
    <button @click="uploadFiles">上传文件</button>
    <span id="re"></span>
  </div>
</template>

<script>
import $ from 'jquery';
import axios from 'axios';
export default {
  name: "uploadComp",
  methods: {
    async uploadFiles() {
      const files = this.$refs.fileInput.files;
      const formData = new FormData();
      console.log(files)

      for (let i = 0; i < files.length; i++) {
        formData.append("files", files[i]);
        console.log(1)
      }

      console.log(formData.getAll("files"))

      const response = await axios.post("http://localhost:8080/return/upload", formData, {
        headers: {
          "Content-Type": "multipart/form-data"
        },
      })
      $("re").html(response)
      // 发送FormData对象到服务器
      // 使用Axios或其他HTTP库发送POST请求
    }
  }
}
</script>

<style scoped>

</style>