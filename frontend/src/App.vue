<template>
  <v-app>
    <header-black
      v-show="$route.path === '/timer' || $route.path === '/' ? false : true"
    ></header-black>
    <home-page v-if="$route.path === '/'"></home-page>
    <router-view v-else />
    <app-footer
      v-show="$route.path === '/timer' || $route.path === '/' ? false : true"
    ></app-footer>
  </v-app>
</template>

<script>
import AppFooter from './components/include/AppFooter.vue';
import HeaderBlack from './components/include/HeaderBlack.vue';
import HomePage from './views/Home.vue';

export default {
  components: {
    'header-black': HeaderBlack,
    'app-footer': AppFooter,
    'home-page': HomePage,
  },
};
</script>

<style></style>
