import { createRouter, createWebHistory } from 'vue-router'
import SearchIndex from "../views/search/index.vue";
import Home from "../views/Home.vue";
import BlogIndex from "../views/blog/index.vue";

// const routes = [
//     {
//         path: '/',
//         name: 'Home',
//         component: Home
//     },
//     {
//         path: '/search/:type',
//         name: 'Search',
//         component: SearchIndex
//     },
//     {
//         path: '/search/:pathMatch(.*)*',
//         name: 'Search',
//         component: SearchIndex
//     },
//     {
//         path: '/blog/:type/:name',
//         name: 'Blog',
//         component: BlogIndex
//     },
//     {
//         path: '/blog/:pathMatch(.*)*',
//         name: 'Blog',
//         component: BlogIndex
//     },
//     // {
//     //     path: '/:pathMatch(.*)*',
//     //     redirect: '/'
//     // }
//
// ]

const routes = [
    {
        path: '/',
        name: 'Home',
        component: () => import("../views/Home.vue")
    },
    {
        path: '/search/:type',
        name: 'Search',
        component: () => import("../views/search/index.vue")
    },
    {
        path: '/search/:pathMatch(.*)*',
        name: 'Search',
        component: () => import("../views/search/index.vue")
    },
    {
        path: '/blog/:type/:name',
        name: 'Blog',
        component: () => import("../views/blog/index.vue")
    },
    {
        path: '/blog/:pathMatch(.*)*',
        name: 'Blog',
        component: () => import("../views/blog/index.vue")
    }
]


const router = createRouter({
    history: createWebHistory(),
    routes: routes
})


export default router