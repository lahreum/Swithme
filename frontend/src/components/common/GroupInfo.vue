<template>
  <div class="groupInfo">
    <div>
      <v-img
        v-if="src === NULL"
        class="groupInfoImg"
        src="https://ifh.cc/g/wyakuA.jpg"
        style="border-radius:10px;"
      >
        <div class="groupCnt">
          <v-icon color="black" dark>mdi-account</v-icon
          ><span>{{ groupCnt }} / {{ groupTotalCnt }}</span>
        </div>
      </v-img>
      <v-img v-else class="groupInfoImg" :src="src" style="border-radius:10px;">
        <div class="groupCnt">
          <v-icon color="black" dark>mdi-account</v-icon
          ><span>{{ groupCnt }} / {{ groupTotalCnt }}</span>
        </div>
      </v-img>
    </div>
    <div style="padding:5% 1%">
      <span class="groupName">{{ groupName }}</span
      ><br />
      <span class="groupDesc">{{ groupDesc }}</span>
    </div>
  </div>
</template>

<script>
export default {
  props: ["src", "groupName", "groupDesc", "groupCnt", "groupTotalCnt"],
};
</script>

<style>
.groupCnt {
  color: black;

  font-size: 20px;
  font-weight: 1000;

  position: absolute;
  bottom: 5%;
  left: 5%;
}
.groupName {
  font-weight: 600px;
  font-size: 20px;
}
.groupDesc {
  color: #bdbdbd;
}
.groupInfoImg {
  width: 250px;
  height: 250px;
}
</style>
