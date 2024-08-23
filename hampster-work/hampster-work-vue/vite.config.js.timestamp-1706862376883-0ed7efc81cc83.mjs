// // vite.config.js
// import { defineConfig } from "file:///F:/JAVA/20231010/hampster-work/hampster-work-vue/node_modules/vite/dist/node/index.js";
// import vue from "file:///F:/JAVA/20231010/hampster-work/hampster-work-vue/node_modules/@vitejs/plugin-vue/dist/index.mjs";
// var vite_config_default = defineConfig({
//   plugins: [vue()],
//   transpileDependencies: true,
//   devServer: {
//     proxy: {
//       "/": {
//         target: "http://localhost:8080/",
//         // 本地后端地址
//         changeOrigin: true,
//         //允许跨域
//         ws: false
//       },
//       "/web": {
//         target: "http://localhost:80/",
//         // 本地后端地址
//         changeOrigin: true,
//         //允许跨域
//         ws: false
//       }
//     }
//   }
// });
// export {
//   vite_config_default as default
// };
// //# sourceMappingURL=data:application/json;base64,ewogICJ2ZXJzaW9uIjogMywKICAic291cmNlcyI6IFsidml0ZS5jb25maWcuanMiXSwKICAic291cmNlc0NvbnRlbnQiOiBbImNvbnN0IF9fdml0ZV9pbmplY3RlZF9vcmlnaW5hbF9kaXJuYW1lID0gXCJGOlxcXFxKQVZBXFxcXDIwMjMxMDEwXFxcXGhhbXBzdGVyLXdvcmtcXFxcaGFtcHN0ZXItd29yay12dWVcIjtjb25zdCBfX3ZpdGVfaW5qZWN0ZWRfb3JpZ2luYWxfZmlsZW5hbWUgPSBcIkY6XFxcXEpBVkFcXFxcMjAyMzEwMTBcXFxcaGFtcHN0ZXItd29ya1xcXFxoYW1wc3Rlci13b3JrLXZ1ZVxcXFx2aXRlLmNvbmZpZy5qc1wiO2NvbnN0IF9fdml0ZV9pbmplY3RlZF9vcmlnaW5hbF9pbXBvcnRfbWV0YV91cmwgPSBcImZpbGU6Ly8vRjovSkFWQS8yMDIzMTAxMC9oYW1wc3Rlci13b3JrL2hhbXBzdGVyLXdvcmstdnVlL3ZpdGUuY29uZmlnLmpzXCI7aW1wb3J0IHsgZGVmaW5lQ29uZmlnIH0gZnJvbSAndml0ZSdcbmltcG9ydCB2dWUgZnJvbSAnQHZpdGVqcy9wbHVnaW4tdnVlJ1xuLy8gY29uc3QgeyBkZWZpbmVDb25maWcgfSA9IHJlcXVpcmUoJ0B2dWUvY2xpLXNlcnZpY2UnKVxuLy8gbGV0IHsgQ29weVdlYnBhY2tQbHVnaW4gfSA9IHJlcXVpcmUoXCJjb3B5LXdlYnBhY2stcGx1Z2luXCIpXG5cbi8vIGh0dHBzOi8vdml0ZWpzLmRldi9jb25maWcvXG5leHBvcnQgZGVmYXVsdCBkZWZpbmVDb25maWcoe1xuICBwbHVnaW5zOiBbdnVlKCldLFxuICB0cmFuc3BpbGVEZXBlbmRlbmNpZXM6IHRydWUsXG4gIGRldlNlcnZlcjoge1xuICAgIHByb3h5OiB7XG4gICAgICAnLyc6IHtcbiAgICAgICAgdGFyZ2V0OiAnaHR0cDovL2xvY2FsaG9zdDo4MDgwLycsIC8vIFx1NjcyQ1x1NTczMFx1NTQwRVx1N0FFRlx1NTczMFx1NTc0MFxuICAgICAgICBjaGFuZ2VPcmlnaW46IHRydWUsIC8vXHU1MTQxXHU4QkI4XHU4REU4XHU1N0RGXG4gICAgICAgIHdzOiBmYWxzZVxuICAgICAgfSxcbiAgICAgICcvd2ViJzoge1xuICAgICAgICB0YXJnZXQ6ICdodHRwOi8vbG9jYWxob3N0OjgwLycsIC8vIFx1NjcyQ1x1NTczMFx1NTQwRVx1N0FFRlx1NTczMFx1NTc0MFxuICAgICAgICBjaGFuZ2VPcmlnaW46IHRydWUsIC8vXHU1MTQxXHU4QkI4XHU4REU4XHU1N0RGXG4gICAgICAgIHdzOiBmYWxzZVxuICAgICAgfVxuICAgIH0sXG4gIH1cbn0pXG4vLyBsZXQgeyBDb3B5V2VicGFja1BsdWdpbiB9ID0gcmVxdWlyZShcImNvcHktd2VicGFjay1wbHVnaW5cIilcbi8vIG1vZHVsZS5leHBvcnRzID0gZGVmaW5lQ29uZmlnKHtcbi8vICAgdHJhbnNwaWxlRGVwZW5kZW5jaWVzOiB0cnVlLFxuLy8gICBkZXZTZXJ2ZXI6IHtcbi8vICAgICBwcm94eToge1xuLy8gICAgICAgJy8nOiB7XG4vLyAgICAgICAgIHRhcmdldDogJ2h0dHA6Ly9sb2NhbGhvc3Q6ODA4MC8nLCAvLyBcdTY3MkNcdTU3MzBcdTU0MEVcdTdBRUZcdTU3MzBcdTU3NDBcbi8vICAgICAgICAgY2hhbmdlT3JpZ2luOiB0cnVlLCAvL1x1NTE0MVx1OEJCOFx1OERFOFx1NTdERlxuLy8gICAgICAgICB3czogZmFsc2Vcbi8vICAgICAgIH0sXG4vLyAgICAgICAnL3dlYic6IHtcbi8vICAgICAgICAgdGFyZ2V0OiAnaHR0cDovL2xvY2FsaG9zdDo1MTczLycsIC8vIFx1NjcyQ1x1NTczMFx1NTQwRVx1N0FFRlx1NTczMFx1NTc0MFxuLy8gICAgICAgICBjaGFuZ2VPcmlnaW46IHRydWUsIC8vXHU1MTQxXHU4QkI4XHU4REU4XHU1N0RGXG4vLyAgICAgICAgIHdzOiBmYWxzZVxuLy8gICAgICAgfVxuLy8gICAgIH0sXG4vLyAgIH1cbi8vIH0pIl0sCiAgIm1hcHBpbmdzIjogIjtBQUE0VSxTQUFTLG9CQUFvQjtBQUN6VyxPQUFPLFNBQVM7QUFLaEIsSUFBTyxzQkFBUSxhQUFhO0FBQUEsRUFDMUIsU0FBUyxDQUFDLElBQUksQ0FBQztBQUFBLEVBQ2YsdUJBQXVCO0FBQUEsRUFDdkIsV0FBVztBQUFBLElBQ1QsT0FBTztBQUFBLE1BQ0wsS0FBSztBQUFBLFFBQ0gsUUFBUTtBQUFBO0FBQUEsUUFDUixjQUFjO0FBQUE7QUFBQSxRQUNkLElBQUk7QUFBQSxNQUNOO0FBQUEsTUFDQSxRQUFRO0FBQUEsUUFDTixRQUFRO0FBQUE7QUFBQSxRQUNSLGNBQWM7QUFBQTtBQUFBLFFBQ2QsSUFBSTtBQUFBLE1BQ047QUFBQSxJQUNGO0FBQUEsRUFDRjtBQUNGLENBQUM7IiwKICAibmFtZXMiOiBbXQp9Cg==
