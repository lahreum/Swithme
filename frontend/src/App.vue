<template>
  <v-app>
    <app-header v-show="$route.path=== '/timer' ? false : true"></app-header>
    <router-view />
    <app-footer v-show="$route.path=== '/timer' || $route.path=== '/'  ? false : true"></app-footer>
  </v-app>
</template>

<script>
import AppFooter from './components/include/AppFooter.vue';
import AppHeader from './components/include/AppHeader.vue';

export default {
  components: {
    'app-header': AppHeader,
    'app-footer': AppFooter,
  },
};
</script>

<style></style>
