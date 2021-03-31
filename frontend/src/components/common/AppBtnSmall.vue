<template>
  <v-btn
    @click="realClick"
    class="btnStyle"
    style="margin:0.4rem; "
    height="40px"
    :color="btnColor"
  >
    <span :style="'color:' + btnNameColor">{{ btnName }}</span>
  </v-btn>
</template>

<script>
export default {
  props: ["btnColor", "btnName", "btnNameColor"],
  methods: {
    realClick() {
      console.log("dafsdfasdfdasfasdf");
      this.$emit("realClick");
    },
  },
};
</script>

<style>
.btnStyle {
  border-radius: 10px !important;
}
</style>
