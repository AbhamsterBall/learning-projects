import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
// const { defineConfig } = require('@vue/cli-service')
// let { CopyWebpackPlugin } = require("copy-webpack-plugin")

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [vue()],
  transpileDependencies: true,
  server: {
      host: '0.0.0.0',
      port: 8989,
      open: true,
      proxy: {
          // '^/blog-api^': { // '/api'是代理标识，用于告诉node，url前面是/api的就是使用代理的
          //     target: "http://localhost:80/astaff.php/", //目标地址，一般是指后台服务器地址
          //     changeOrigin: true, //是否跨域
          // },
        '^/blog-api': {
          target: 'http://localhost:8081/', // 8081本地后端地址
          changeOrigin: true, //允许跨域
          // ws: false,
          rewrite: (path) => path.replace(/^\/blog-api/, ''),
        },
      }
  },
    // proxy: "http://localhost:9090",
  //   proxy: {
  //       // '^': {
  //       //     target: 'http://localhost:9090/json/search/blur/%E6%98%AF/1', // 8081本地后端地址
  //       //     changeOrigin: true, //允许跨域
  //       //     // ws: false,
  //       //     // rewrite: (path) => path.replace(/^\/blog-api/, ''),
  //       // },
  //       '^/blog-api': {
  //         target: 'http://localhost:9090/', // 8081本地后端地址
  //         changeOrigin: true, //允许跨域
  //         // ws: false,
  //         // rewrite: (path) => path.replace(/^\/blog-api/, ''),
  //       },
  //       '/user': {
  //         target: 'http://localhost:8082/', // 本地后端地址
  //         changeOrigin: true, //允许跨域
  //         ws: false
  //       },
  //       '/web': {
  //         target: 'http://localhost:80/', // 本地后端地址
  //         changeOrigin: true, //允许跨域
  //         ws: false
  //       }
  //   }
  // },
  // devServer: {
  // //   proxy: "http://localhost:9090",
  //   proxy: {
  //     '^/blog-api': {
  //       target: 'http://localhost:9090',
  //       // router: () => 'http://localhost:8081', // 8081本地后端地址
  //       changeOrigin: true, //允许跨域
  //       ws: false,
  //       pathRewrite: {
  //         '^/blog-api': '', // 去掉路径中的/blog-api
  //       },
  // //   //   },
  // //   //   '/user': {
  // //   //     target: 'http://localhost:8082/', // 本地后端地址
  // //   //     changeOrigin: true, //允许跨域
  // //   //     ws: false
  // //   //   },
  // //   //   '/web': {
  // //   //     target: 'http://localhost:80/', // 本地后端地址
  // //   //     changeOrigin: true, //允许跨域
  // //   //     ws: false
  //     }
  //   }
  // }
})
// let { CopyWebpackPlugin } = require("copy-webpack-plugin")
// module.exports = defineConfig({
//   transpileDependencies: true,
//   devServer: {
//     proxy: {
//       '/': {
//         target: 'http://localhost:8080/', // 本地后端地址
//         changeOrigin: true, //允许跨域
//         ws: false
//       },
//       '/web': {
//         target: 'http://localhost:5173/', // 本地后端地址
//         changeOrigin: true, //允许跨域
//         ws: false
//       }
//     },
//   }
// })
