import Vue from 'vue';
import App from './App.vue';
import VueRouter from 'vue-router';
import routes from './routes';
import store from './vuex/store';
import vuetify from './plugins/vuetify';
import VueFullPage from 'vue-fullpage.js'

Vue.config.productionTip = false;

Vue.use(VueRouter);
Vue.use(VueFullPage);

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
