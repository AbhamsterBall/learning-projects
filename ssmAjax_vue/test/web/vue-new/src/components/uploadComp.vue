<template>
  <form method="post" @submit.prevent="uploadFiles" enctype="multipart/form-data" class="form-container">
    <input type="file" ref="fileInput" multiple>
    <input type="submit" value="提交"/>
  </form>
  <table class="tableStyle" v-if="listed != null & listed.length > 0">
    <tr v-for="data in listed" :key="data">
      <td>{{ data }}</td>
      <td><a href="#/upload" @click="download([data, ])">下载文件</a></td>
    </tr>
    <tr><td colspan="2"><a href="#/upload" @click="download(listed)">全部下载</a></td></tr>
  </table>
  <div class="menu">
    <span id="re"></span>
  </div>
</template>

<script>
import $ from 'jquery';
import axios from 'axios';
export default {
  name: "uploadComp",
  data() {
    return {
      listed: []
    };
  },
  mounted() {
    this.loadSource()
  },
  methods: {
    async uploadFiles() {
      const files = this.$refs.fileInput.files;
      const formData = new FormData();

      for (let i = 0; i < files.length; i++) {
        formData.append("files", files[i]);
      }

      try {

        let response = await axios.post("http://localhost:8080/return/upload", { withCredentials: true }, formData, {
              "Content-Type": "multipart/form-data;charset=utf-8",
            })

        $("#re").html(response.data)
        setTimeout(() => {
          $("#re").html("")
        }, 3000)

        await this.loadSource()
      } catch (error) {
        console.error("上传失败", error);
      }
    },
    async loadSource() {
      const re = await axios.get("http://localhost:8080/values/uploaded", { withCredentials: true })
      this.listed = re.data
    },
    async download(files) {
      const formData = new FormData();
      formData.append("file", files);
      for (let index in files) {
        try {
          console.log(files[index])
          // const c = await axios.get("http://localhost:8080/download?file=" + files[index], { responseType: 'arraybuffer' } );
          // console.log(c)
          window.open("http://localhost:8080/download?file=" + files[index], { withCredentials: true })
          // console.log(re)

          // const blob = new Blob([response.data], { type: 'multipart/form-data' });
          // const url = window.URL.createObjectURL(blob);
          // const a = document.createElement('a');
          // a.style.display = 'none';
          // a.href = url;
          // a.download = files[index];
          // document.body.appendChild(a);
          // a.click();
          // window.URL.revokeObjectURL(url);

          $("#re").html("下载成功")
          setTimeout(() => {
            $("#re").html("")
          }, 3000)
        } catch (error) {
          $("#re").html(error)
          console.log(error)
        }
      }

    }
  }
}
</script>

<style scoped>

</style>