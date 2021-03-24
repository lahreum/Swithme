<template>
  <v-app>
    <app-header></app-header>
    <router-view />
    <app-footer></app-footer>
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
