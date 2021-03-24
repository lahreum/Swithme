<template>
  <div style="border:1px solid black; width:110px; height:110px">
    <v-btn @click="selectLike" icon style="float:right; "
      ><v-icon v-if="selectedLike === false" color="red" size="20"
        >mdi-heart</v-icon
      >
      <v-icon v-else size="20">mdi-heart-outline</v-icon>
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
    return { selectedLike: "false" };
  },
  props: ["CategoryIcon", "CategoryName"],
  methods: {
    selectLike() {
      this.selectedLike = !this.selectedLike;
    },
  },
};
</script>

<style></style>
