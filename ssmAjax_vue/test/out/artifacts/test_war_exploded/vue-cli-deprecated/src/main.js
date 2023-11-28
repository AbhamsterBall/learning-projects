import { createApp } from "vue";
import Emp from "./Emp.vue";
// import Home from "./emp.vue";
// import VueAxios from 'vue-axios';
// import Vue from 'vue';
// import axios from 'axios';
import "./registerServiceWorker";

// Vue.use(VueAxios, axios)
createApp(Emp).mount("#app");

// const routes = [
//   {
//     path: "/",
//     name: "Home",
//     component: Home,
//   },
// ];
//
// const router = VueRouter.createRouter({
//   history: VueRouter.createWebHashHistory(),
//   routes,
// });
//
// export default router;
