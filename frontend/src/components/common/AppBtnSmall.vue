<template>
  <div style="display:inline-block">
    <v-btn style="margin:0.4rem; " height="40px" :color="btnColor">
      <span :style="'color:' + btnNameColor">{{ btnName }}</span>
    </v-btn>
  </div>
</template>

<script>
export default {
  props: ["btnColor", "btnName", "btnNameColor"],
};
</script>

<style scoped>
.v-btn {
  border-radius: 7px !important;
}
</style>
