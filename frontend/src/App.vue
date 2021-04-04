<template>
  <v-app>
    <app-header
      v-show="$route.path === '/timer' || $route.path === '/' ? false : true"
    ></app-header>
    <home-page v-if="$route.path === '/'"></home-page>
    <router-view v-else />
    <app-footer
      v-show="$route.path === '/timer' || $route.path === '/' ? false : true"
    ></app-footer>
  </v-app>
</template>

<script>
import AppFooter from './components/include/AppFooter.vue';
import AppHeader from './components/include/AppHeader.vue';
import HomePage from './views/Home.vue';

export default {
  components: {
    'app-header': AppHeader,
    'app-footer': AppFooter,
    'home-page': HomePage,
  },
};
</script>

<style></style>
