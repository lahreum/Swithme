<template>
  <div style="display:inline-block">
    <v-btn class="btnStyle" style="margin:0.4rem; " height="40px" :color="btnColor">
      <span :style="'color:' + btnNameColor">{{ btnName }}</span>
    </v-btn>
  </div>
</template>

<script>
export default {
  props: ["btnColor", "btnName", "btnNameColor"],
};
</script>

<style>
.btnStyle {
  border-radius: 10px !important;
}
</style>
