import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
// const { defineConfig } = require('@vue/cli-service')
// let { CopyWebpackPlugin } = require("copy-webpack-plugin")

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [vue()],
  transpileDependencies: true,
  devServer: {
    proxy: {
      '/': {
        target: 'http://localhost:8080/', // 本地后端地址
        changeOrigin: true, //允许跨域
        ws: false
      },
      '/web': {
        target: 'http://localhost:80/', // 本地后端地址
        changeOrigin: true, //允许跨域
        ws: false
      }
    },
  }
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