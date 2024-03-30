const { defineConfig } = require('@vue/cli-service')
let { CopyWebpackPlugin } = require("copy-webpack-plugin")
module.exports = defineConfig({
  transpileDependencies: true,
  devServer: {
    proxy: {
      '/': {
        target: 'http://localhost:8080/', // 本地后端地址
        changeOrigin: true, //允许跨域
        ws: false
      },
      '/web': {
        target: 'http://localhost:8081/', // 本地后端地址
        changeOrigin: true, //允许跨域
        ws: false
      }
    },
  }
})
