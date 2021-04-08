<template>
  <div class="groupInfo" @click="clickGroupInfo">
    <div>
      <v-img class="groupInfoImg" :src="src" style="border-radius:10px;">
        <div class="groupCnt">
          <v-icon color="black" dark>mdi-account</v-icon
          ><span>{{ groupCnt }} / {{ groupTotalCnt }}</span>
        </div>
        <v-icon
          color="red"
          style="position:relative; top:5%; left:5%"
          v-if="groupPassword !== undefined"
          >mdi-lock</v-icon
        >
      </v-img>
    </div>
    <div style="padding:5% 1%">
      <span
        class="groupName d-inline-block text-truncate"
        style="max-width:100%"
        >{{ groupName }}</span
      ><br />
      <span
        class="groupDesc d-inline-block text-truncate"
        style="max-width:100%"
        >{{ groupDesc }}</span
      >
    </div>
  </div>
</template>

<script>
export default {
  props: [
    "src",
    "groupPassword",
    "groupName",
    "groupDesc",
    "groupCnt",
    "groupTotalCnt",
  ],
  methods: {
    clickGroupInfo() {
      this.$emit("clickGroupInfo");
    },
  },
};
</script>

<style>
.groupCnt {
  color: black;
  font-weight: 1000;
  position: absolute;
  bottom: 5%;
  left: 5%;
}
.groupName {
  font-weight: 600px;
  font-size: 1.1rem;
}
.groupDesc {
  color: #bdbdbd;
  font-size: 0.8rem;
}
.groupInfoImg {
  width: 250px;
  height: 250px;
}
</style>
