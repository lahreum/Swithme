<template>
  <v-app id="app">
    <app-header></app-header>
  </v-app>
</template>

<script>
import AppHeader from './components/include/AppHeader.vue';

export default {
  components: {
    'app-header': AppHeader,
    'input-bar': InputBar,
  },
};
</script>

<style></style>
