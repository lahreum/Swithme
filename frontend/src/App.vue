<template>
  <v-app>
    <app-header></app-header>
    <pop-up></pop-up>
    <router-view />
    <app-footer></app-footer>
  </v-app>
</template>

<script>
import AppFooter from './components/include/AppFooter.vue';
import AppHeader from './components/include/AppHeader.vue';
import PopUp from './components/common/PopUp.vue';

export default {
  components: {
    'app-header': AppHeader,
    'app-footer': AppFooter,
    'pop-up': PopUp,
  },
};
</script>

<style></style>
