<template>
  <v-app id="app">
    <app-header></app-header>
    <app-footer></app-footer>
  </v-app>
</template>

<script>
import AppHeader from './components/AppHeader.vue';
import AppFooter from './components/AppFooter.vue';

export default {
  components: {
    'app-header': AppHeader,
    'app-footer': AppFooter,
  },
};
</script>

<style></style>
