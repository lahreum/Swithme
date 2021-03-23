<template>
  <v-app>
    <app-header></app-header>
    <info-chip :text="'상위 30%'"></info-chip>
    <AppBtnLarge
      :btnColor="'purple'"
      :btnNameColor="'white'"
      :btnName="'로그인'"
    />
    <br />
    <AppBtnMiddle
      :btnColor="'purple'"
      :btnNameColor="'white'"
      :btnName="'완료'"
    />
    <br />
    <AppBtnSmall
      :btnColor="'purple'"
      :btnNameColor="'white'"
      :btnName="'중복체크'"
    />
    <ProfileStudying :src="'https://ifh.cc/g/wyakuA.jpg'" />
    <ProgressBar :progressValue="30" />
    <v-main>
      <router-view />
    </v-main>
    <app-footer></app-footer>
  </v-app>
</template>

<script>
import AppFooter from "./components/include/AppFooter.vue";
import AppHeader from "./components/include/AppHeader.vue";
import InfoChip from "./components/common/InfoChip.vue";
import AppBtnLarge from "./components/common/AppBtnLarge.vue";
import AppBtnMiddle from "./components/common/AppBtnMiddle.vue";
import AppBtnSmall from "./components/common/AppBtnSmall.vue";
import ProfileStudying from "./components/common/ProfileStudying.vue";
import ProgressBar from "./components/common/ProgressBar.vue";

export default {
  components: {
    "app-header": AppHeader,
    "app-footer": AppFooter,
    "info-chip": InfoChip,
    AppBtnMiddle,
    AppBtnLarge,
    AppBtnSmall,
    ProfileStudying,
    ProgressBar,
  },
};
</script>

<style></style>
