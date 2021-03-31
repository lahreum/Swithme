<template>
  <v-btn
    style="padding-left: 100px; padding-right: 100px;"
    color="grey lighten-1"
    :href="link"
    outlined
    large
    block
  >
    <v-img src="@/assets/img/google_logo.png"></v-img>
    <span class="ml-3" style="color: black;"
      ><strong>구글 계정으로 로그인</strong></span
    >
  </v-btn>
</template>

<script>
export default {
  data() {
    return {
      link: 'http://localhost:9999/oauth/google',
    };
  },
};
</script>

<style></style>
