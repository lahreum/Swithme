<template>
  <v-btn rounded width="200px" height="50px" :color="btnColor">
    <h3 :style="'color:' + btnNameColor">{{ btnName }}</h3>
  </v-btn>
</template>

<script>
export default {
  props: ['btnColor', 'btnName', 'btnNameColor'],
};
</script>

<style></style>
