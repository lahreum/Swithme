<template>
  <v-app id="app">
    <app-header></app-header>
  </v-app>
</template>

<script>
import AppHeader from './components/AppHeader.vue';

export default {
  components: {
    'app-header': AppHeader,
  },
};
</script>

<style></style>
