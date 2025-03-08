import { createApp } from 'vue'
import { createStore } from 'vuex'
import Vuex from 'vuex'
import './assets/global.css'
import './assets/hljs.css'
import App from './App.vue'
import { createRouter, createWebHistory } from 'vue-router';
import router from "./router"
import {hljs} from "highlight/lib/vendor/highlight.js/highlight.js";
import 'element-plus/dist/index.css';
import FingerprintJS from "@fingerprintjs/fingerprintjs";

const fp = await FingerprintJS.load();
// 生成指纹
export const fingerprint = await fp.get();

const app = createApp(App);

function generateRandomString(length) {
  const characters = '0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ';
  let randomString = '';
  for (let i = 0; i < length; i++) {
    const randomIndex = Math.floor(Math.random() * characters.length);
    randomString += characters.charAt(randomIndex);
  }
  return randomString;
}

// Vuex store
export const store = new Vuex.Store({
  state: {
    token: localStorage.getItem('token')
  },
  mutations: {
    setToken(state) {
      state.token = generateRandomString(280);
      localStorage.setItem('token', state.token);
    },
    setNewToken(state, newToken) {
      state.token = newToken;
      localStorage.setItem('token', state.token);
    },
    clearToken(state) {
      state.token = '';
      localStorage.removeItem('token');
    }
  },
  actions: {
    initToken({ commit }) {
      const token = localStorage.getItem('token');
      if (token == null) {
        commit('setToken');
      }
    }
  }
});

// 在应用初始化时调用initToken action
store.dispatch('initToken').then(r => { 
  // pass
});

app.use(router)
app.mount('#app')

// hljs.initHighlightingOnLoad();
// app.use(store)
