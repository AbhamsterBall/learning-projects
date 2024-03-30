<!--<template>-->
<!--  <img alt="Vue logo" src="./assets/logo.png" />-->
<!--  <HelloWorld msg="Welcome to Your Vue.js App" />-->
<!--</template>-->

<!--<script>-->
<!--import HelloWorld from "./components/HelloWorld.vue";-->

<!--export default {-->
<!--  name: "App",-->
<!--  components: {-->
<!--    HelloWorld,-->
<!--  },-->
<!--};-->
<!--</script>-->

<!--<style lang="scss">-->
<!--#app {-->
<!--  font-family: Avenir, Helvetica, Arial, sans-serif;-->
<!--  -webkit-font-smoothing: antialiased;-->
<!--  -moz-osx-font-smoothing: grayscale;-->
<!--  text-align: center;-->
<!--  color: #2c3e50;-->
<!--  margin-top: 60px;-->
<!--}-->
<!--</style>-->

<template>
    <emp-comp msg="Welcome to Your Vue.js App" />
</template>

<script>
import empComp from "./components/empComp.vue";

export default {
  name: "empComp",
  components: {
    empComp
  },
  // this.$http.patch(
  //     'http://localhost:8080/values/emp',
  //     {
  //       data : data.data;
  //       this.pkStr = data.pkStr;
  //       this.end = data.end;
  //     }
  // );
};
</script>

<style src='./assets/all.css?vue&type=style&index=0&lang=css&external'>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>