<template>
  <v-app id="app">
    <app-header></app-header>
    <app-footer></app-footer>
  </v-app>
</template>

<script>
import AppHeader from './components/include/AppHeader.vue';
import AppFooter from './components/include/AppFooter.vue';

export default {
  components: {
    'app-header': AppHeader,
    'app-footer': AppFooter,
  },
};
</script>

<style></style>
