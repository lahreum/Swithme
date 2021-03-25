<template>
  <div>
    <v-btn width="110px" height="40px" :color="btnColor">
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
  border-radius: 7px!important;
}
</style>
