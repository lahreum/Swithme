<template>
  <v-app>
    <app-header></app-header>
    <comment-input-bar></comment-input-bar>
    <v-main>
      <router-view />
    </v-main>
    <app-footer></app-footer>
  </v-app>
</template>

<script>
import AppFooter from './components/include/AppFooter.vue';
import AppHeader from './components/include/AppHeader.vue';
import CommentInputBar from './components/common/CommentInputBar.vue';

export default {
  components: {
    'app-header': AppHeader,
    'app-footer': AppFooter,
    'comment-input-bar': CommentInputBar,
  },
};
</script>

<style></style>
