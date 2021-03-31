<template>
  <div style="border:1px solid black; width:110px; height:110px">
    <v-btn @click="selectLike" icon style="float:right; ">
      <v-icon color="red" size="20">{{
        selectedLike ? "mdi-heart" : "mdi-heart-outline"
      }}</v-icon>
    </v-btn>

    <div
      style="position:relative; 
      top:50%; 
      left:50%; 
      transform:translate(-50%, -50%);
      width:70px;
      height:70px"
    >
      <v-icon size="70">{{ CategoryIcon }}</v-icon>
      <h6 style="text-align:center">{{ CategoryName }}</h6>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return { selectedLike: false };
  },
  props: ["CategoryIcon", "CategoryName"],
  methods: {
    selectLike() {
      this.selectedLike = !this.selectedLike;
      if (this.selectedLike) {
        this.$emit("selectLike", this.CategoryName, true);
      } else {
        this.$emit("selectLike", this.CategoryName, false);
      }
    },
  },
};
</script>

<style></style>
