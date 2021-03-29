<template>
  <div>
    <v-btn class="btnStyle" width="100%" height="55px" :color="btnColor">
      <h3 :style="'color:' + btnNameColor">{{ btnName }}</h3>
    </v-btn>
  </div>
</template>

<script>
export default {
  props: ['btnColor', 'btnName', 'btnNameColor'],
};
</script>

<style>
.btnStyle {
  border-radius: 10px !important;
  padding: 0;
  margin:0;
}
</style>
