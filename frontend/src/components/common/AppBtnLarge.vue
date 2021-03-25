<template>
  <div>
    <v-btn width="360px" height="55px" :color="btnColor">
      <h3 :style="'color:' + btnNameColor">{{ btnName }}</h3>
    </v-btn>
  </div>
</template>

<script>
export default {
  props: ["btnColor", "btnName", "btnNameColor"],
};
</script>

<style>
.v-btn {
  border-radius: 7px!important;
}
</style>
