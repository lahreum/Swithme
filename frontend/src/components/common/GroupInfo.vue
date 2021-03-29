<template>
  <div class="groupInfo">
    <div>
      <v-img class="groupInfoImg" :src="src" style="border-radius:10px;">
        <div class="groupCnt">
          <v-icon color="black" dark>mdi-account</v-icon
          ><span>{{ groupCnt }} / {{ groupTotalCnt }}</span>
        </div>
      </v-img>
    </div>
    <div>
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
  color: #635f5f;
  padding-top: 85%;
  font-size: 15px;
  font-weight: 500;
  padding-left: 5%;
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
