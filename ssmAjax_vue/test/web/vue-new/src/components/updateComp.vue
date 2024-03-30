<template>
  <form id="form" class="form-container" v-if="data !== null" @submit.prevent="updateData">
    <table class="tableStyle">
      <tr v-for="key in Object.keys(data)" :key="key">
        <td><span>{{ key }}</span></td>
        <td><input type="text" :placeholder="key" :name="key" :value="data[key]"/></td>
      </tr>
    </table>
    <input type="submit" value="update"/>
  </form>

  <div class="menu">
    <span id="re"></span>
    <router-link to="/">HOME</router-link>
  </div>
</template>

<script>
import $ from 'jquery';
import axios from 'axios';
export default {
  name: "updateComp",
  props: {
    msg: String
  },
  data() {
    return {
      data: {},
      pkStr: "id",
      pk: 1
    }
  },
  mounted() {
    this.getData()
  },
  methods: {
    async getData() {
      const response = await axios.get('http://localhost:8080/entity/' + this.$route.params.pk, { withCredentials: true });
      console.log(response)
      this.data = response.data.data
      this.pkStr = response.data.pkStr
      this.pk = this.$route.params.pk

      $(() => {
        $("#" + this.pkStr).prop("readonly", true)
        $("#" + this.pkStr).prop('style', "border-bottom:0px")
      })
    },
    async updateData() {
      const data0 = $("#form").serializeArray()
      // 创建一个空对象来存储参数
      const params = {};
      // 将数据数组转换为参数对象
      data0.forEach(item => {
        params[item.name] = item.value;
      });
      const response = await axios.get("http://localhost:8080/return/update", {params : params, withCredentials: true })
      $("#re").html(response.data)
    }
  }
}
</script>

<style scoped>

</style>