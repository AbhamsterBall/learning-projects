import { createApp } from 'vue';
import Emp from "@/Emp";
import empComp from "@/components/empComp";
import updateComp from "@/components/updateComp";
import uploadComp from "@/components/uploadComp";
import { createRouter, createWebHashHistory } from 'vue-router';

const app = createApp(Emp);

const routes = [
    { path: '/', component: empComp },
    { path: '/update/:pk', component: updateComp },
    { path: '/upload/', component: uploadComp }
];

const router = createRouter({
    history: createWebHashHistory(),
    routes,
});

app.use(router);

app.mount('#app'); // 渲染Vue组件到页面
