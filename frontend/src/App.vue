<template>
  <v-app>
    <app-header></app-header>
    <info-chip :text="'상위 30%'"></info-chip>
    <v-main>
      <router-view />
    </v-main>
    <app-footer></app-footer>
  </v-app>
</template>

<script>
import AppFooter from './components/include/AppFooter.vue';
import AppHeader from './components/include/AppHeader.vue';
import InfoChip from './components/common/InfoChip.vue';

export default {
  components: {
    'app-header': AppHeader,
    'app-footer': AppFooter,
    'info-chip': InfoChip,
  },
};
</script>

<style></style>
