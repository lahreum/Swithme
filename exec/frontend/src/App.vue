<template>
  <v-app>
    <app-header
      :darkmode="$route.path !== '/' ? false : darkmode"
      v-show="$route.path === '/timer' ? false : true"
    ></app-header>
    <home-page @isDarkmode="getDarkmode" v-if="$route.path === '/'"></home-page>
    <router-view v-else />
    <app-footer
      v-show="$route.path === '/timer' || $route.path === '/' ? false : true"
    ></app-footer>
  </v-app>
</template>

<script>
import AppFooter from "./components/include/AppFooter.vue";
import AppHeader from "./components/include/AppHeader.vue";
import HomePage from "./views/Home.vue";

export default {
  components: {
    "app-header": AppHeader,
    "app-footer": AppFooter,
    "home-page": HomePage,
  },
  data() {
    return {
      darkmode: true,
    };
  },
  methods: {
    getDarkmode(e) {
      this.darkmode = e;
    },
  },
};
</script>

<style></style>
