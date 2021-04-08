import Vue from 'vue';
import App from './App.vue';
import VueRouter from 'vue-router';
import routes from './routes';
// import { store } from './vuex/store'; // vuex 전역 세팅
import store from './vuex/store';
import vuetify from './plugins/vuetify';
import VueFullPage from 'vue-fullpage.js';
import Carousel3d from 'vue-carousel-3d';
import axios from 'axios';
import AOS from 'aos';
import 'aos/dist/aos.css';

Vue.prototype.$Axios = axios; // this.$Axios 로 전역으로 사용 가능
Vue.config.productionTip = false;
AOS.init();

Vue.use(VueRouter);
Vue.use(VueFullPage);
Vue.use(Carousel3d);

const router = new VueRouter({
  mode: 'history',
  routes,
});

// axios.defaults.baseURL = 'https://j4b103.p.ssafy.io/service/';
axios.defaults.baseURL = 'http://localhost:9999/';

new Vue({
  router,
  store,
  vuetify,
  render: (h) => h(App),
}).$mount('#app');
