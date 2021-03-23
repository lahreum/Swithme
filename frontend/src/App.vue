<template>
  <v-app>
    <app-header></app-header>
    <comment-item></comment-item>
    <v-main>
      <router-view />
    </v-main>
    <app-footer></app-footer>
  </v-app>
</template>

<script>
import AppFooter from './components/include/AppFooter.vue';
import AppHeader from './components/include/AppHeader.vue';
import CommentItem from './components/common/CommentItem.vue';

export default {
  components: {
    'app-header': AppHeader,
    'app-footer': AppFooter,
    'comment-item': CommentItem,
  },
};
</script>

<style></style>
