<template>
  <v-btn
    :disabled="disabled"
    class="btnStyle"
    width="150px"
    height="50px"
    :color="btnColor"
  >
    <span :style="'color:' + btnNameColor">{{ btnName }}</span>
  </v-btn>
</template>

<script>
export default {
  props: ['btnColor', 'btnName', 'btnNameColor', 'disabled'],
};
</script>

<style>
.btnStyle {
  border-radius: 10px !important;
  letter-spacing: -0.5px;
  /* font-weight: bold; */
}
</style>
