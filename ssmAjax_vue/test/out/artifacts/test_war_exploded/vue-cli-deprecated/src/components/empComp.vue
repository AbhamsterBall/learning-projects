<template>
    <div id="re" style="height: 30px"></div>
    <form id="form" class="form-container" v-if="columnName.length > 0" @submit.prevent="search">
      <input type="text" v-for="key in columnName"  :key="key" :placeholder="key" :name="key" :style="'width:' + inputWidth + 'px'"/>
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
  <!--      <th>delete</th>-->
      </tr>
      <tbody v-if="datas !== null && datas.length > 0">
      <tr v-for="item in datas" :key="item">
        <td v-for="key in Object.keys(datas[0])" :key="key">{{ item[key] || "N/A"}}</td>
        <td><a :href="'/update/' + item[pkStr]">update</a></td>
  <!--      <td><a @click="deleteRow(item[pkStr])">delete</a></td>-->
      </tr>
      </tbody>
    </table>
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
      ends: -1,
      columnName: [],
      inputWidth: 100
    };
  },
  mounted() {
    this.myMethod();
  },
  methods: {
    async myMethod() {
      try {
        const response = await axios.get('http://localhost:8080/values/emp');
        console.log(response)
        this.datas = response.data.data;
        this.pkStr = response.data.pkStr;
        this.ends = response.data.end;
        this.columnName = Object.keys(this.datas[0]);

        this.inputWidth = ($(window).width() * 0.8 - (this.columnName.length + 1) * 20) / (this.columnName.length + 1)
        console.log(this.inputWidth)
      } catch (error) {
        console.error(error);
      }
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
<style scoped lang="scss">
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
