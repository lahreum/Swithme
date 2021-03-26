<template>
  <div>
    <v-btn width="100%" height="55px" :color="btnColor">
      <h3 :style="'color:' + btnNameColor">{{ btnName }}</h3>
    </v-btn>
  </div>
</template>

<script>
export default {
  props: ['btnColor', 'btnName', 'btnNameColor'],
};
</script>

<style scoped>
.v-btn {
  border-radius: 7px !important;
}
</style>
