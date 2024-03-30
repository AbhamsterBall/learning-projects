<template>
    <div id="re" style="height: 30px"></div>
    <form id="form" class="form-container" v-if="columnName.length > 0" @submit.prevent="search">
      <input type="text" v-for="key in columnName" :key="key" :placeholder="key" :name="key" :style="'width:' + inputWidth + 'px'"/>
      <input type="submit" value="search"/>
    </form>
    <form id="formInsert" class="form-container" v-if="columnName.length > 0" @submit.prevent="insertRow">
      <input type="text" v-for="key in columnName" :key="key" :placeholder="key" :name="key" :style="'width:' + inputWidth + 'px'"/>
      <input type="submit" value="insert"/>
    </form>
    <table class="tableStyle">
      <tr v-if="columnName.length > 0">
        <th v-for="key in columnName" :key="key">{{ key }}</th>
        <th>update</th>
        <th>delete</th>
      </tr>
      <tbody v-if="datas !== null && datas.length > 0">
      <tr v-for="item in datas" :key="item">
        <td v-for="key in Object.keys(datas[0])" :key="key">{{ item[key] || "N/A"}}</td>
        <td><router-link :to="'/update/' + item[pkStr]">update</router-link></td>
<!--        <router-view></router-view>-->
        <td><a @click="deleteRow(item[pkStr])" href="#">delete</a></td>
      </tr>
      </tbody>
    </table>
  <div class="menu">
    <a @click="home" href="#">home</a>
    <a @click="up" href="#">up</a>
    <a @click="next" href="#">next</a>
    <a @click="footer" href="#">end</a>
  </div>
</template>

<script>
import $ from 'jquery';
// import api from "@/api";
import axios from 'axios';
export default {
  name: "HelloWorld",
  props: {
    msg: String,
  },
  data() {
    return {
      datas: [],
      pkStr: '',
      endPage: -1,
      columnName: [],
      inputWidth: 100,
      page: 1
    };
  },
  mounted() {
    this.getPage();
  },
  methods: {
    async getPage() {
      try {
        const response = await axios.get('http://localhost:8080/values/emp?page=' + this.page, { withCredentials: true });
        console.log(response)
        this.datas = response.data.data;
        this.pkStr = response.data.pkStr;
        this.endPage = response.data.end;
        this.columnName = Object.keys(this.datas[0]);

        this.inputWidth = ($(window).width() * 0.8 - (this.columnName.length + 1) * 20) / (this.columnName.length + 1)
        console.log(this.inputWidth)
      } catch (error) {
        console.error(error);
      }
    },
    async deleteRow(pk) {
      const response = await axios.get('http://localhost:8080/return/delete/' + pk)
        $("#re").html(response.data)
        setTimeout(() => {
          $("#re").html("") // 隐藏元素
        }, 3000); // 3000毫秒（即3秒）

        await this.getPage()
    },
    async search() {
      const data0 = $("#form").serializeArray()
      // 创建一个空对象来存储参数
      const params = {};
      // 将数据数组转换为参数对象
      data0.forEach(item => {
        params[item.name] = item.value;
      });
      const response = await axios.get("http://localhost:8080/values/emp", {params : params})

      this.datas = response.data.data;
      this.pkStr = response.data.pkStr;
      this.endPage = response.data.end;
    },
    async insertRow() {
      const data0 = $("#formInsert").serializeArray()
      // 创建一个空对象来存储参数
      const params = {};
      // 将数据数组转换为参数对象
      data0.forEach(item => {
        params[item.name] = item.value;
      });
      const response = await axios.get("http://localhost:8080/return/insert", {params : params})
      $("#re").html(response.data)
      setTimeout(() => {
        $("#re").html("") // 隐藏元素
      }, 3000); // 3000毫秒（即3秒）

      await this.getPage()
    },
    home() {
      this.page = 1
      this.getPage()
    },
    up() {
      this.page = this.page == 1 ? 1 : --this.page
      this.getPage()
    },
    next() {
      this.page = this.page == this.endPage ? this.endPage : ++this.page
      this.getPage()
    },
    footer() {
      this.page = this.endPage
      this.getPage()
    }
  }
  // data() {
  //   return {
  //     userData: null,
  //   };
  // },
  // created() {
  //   // 在组件创建时获取后端数据
  //   api.fetchUserData().then((response) => {
  //     this.userData = response.data;
  //   });
  // },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
