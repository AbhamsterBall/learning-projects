import { createRouter, createWebHistory } from 'vue-router'
import SearchIndex from "../views/search/index.vue";
import Home from "../views/Home.vue";
import BlogIndex from "../views/blog/index.vue";

const routes = [
    {
        path: '/',
        name: 'Home',
        component: Home
    },
    {
        path: '/search/:type',
        name: 'Search',
        component: SearchIndex
    },
    {
        path: '/search/:pathMatch(.*)*',
        name: 'Search',
        component: SearchIndex
    },
    {
        path: '/blog/:type/:name',
        name: 'Blog',
        component: BlogIndex
    },
    {
        path: '/blog/:pathMatch(.*)*',
        name: 'Blog',
        component: BlogIndex
    },
    // {
    //     path: '/:pathMatch(.*)*',
    //     redirect: '/'
    // }
]

const router = createRouter({
    history: createWebHistory(),
    routes: routes
})


export default router