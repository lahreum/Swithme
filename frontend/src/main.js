import Vue from 'vue';
import App from './App.vue';
import VueRouter from 'vue-router';
import routes from './routes';
import store from './vuex/store';
import vuetify from './plugins/vuetify';
import VueFullPage from 'vue-fullpage.js';
import Carousel3d from 'vue-carousel-3d';
import axios from 'axios';

Vue.config.productionTip = false;

Vue.use(VueRouter);
Vue.use(VueFullPage);
Vue.use(Carousel3d);

axios.defaults.baseURL = 'http://j4b103.p.ssafy.io:8399/';

const router = new VueRouter({
  mode: 'history',
  routes,
});

new Vue({
  router,
  store,
  vuetify,
  render: (h) => h(App),
}).$mount('#app');
