<template>
  <v-row class="info-container" no-gutters align="center">
    <div style="width: 250px;" align="center">
      <profile-large
        :src="require(`@/assets/img/avatars/iu.jpg`)"
      ></profile-large>
    </div>
    <div class="info-right">
      <v-row no-gutters style=" font-size: 1.2rem;">
        <span>
          <strong>{{ user.username }}</strong>
          님의 총 공부시간은 <strong>{{ user.studyTime }}</strong
          >로
        </span>
      </v-row>
      <v-row
        no-gutters
        align="end"
        style="font-size: 2rem; font-weight: bold; margin-top: -10px; "
      >
        <span>
          현재 <span style="font-size: 4rem;">{{ rank }}</span>
          위 (상위
          <span style="color: #673fb4;">{{ rankPercentage }}%</span>)</span
        >
      </v-row>
      <v-row no-gutters style="font-size: 1.2rem; ">
        <span
          >(소근소근, <strong>{{ rank - 1 }}위</strong>와 겨우
          <span style="color: #673fb4; font-weight: bold;">{{ timeDiff }}</span>
          차이네요.)</span
        >
      </v-row>
    </div>
  </v-row>
</template>

<script>
import ProfileLarge from '@/components/common/ProfileLarge.vue';

export default {
  components: {
    'profile-large': ProfileLarge,
  },
  data: function() {
    return {
      user: {
        profile: '프로필 사진',
        username: 'default',
        studyTime: '9:36:33',
      },
      rank: 516,
      rankPercentage: 29,
      timeDiff: '3분 33초',
    };
  },
};
</script>

<style>
.info-container {
  height: 240px;
  border-radius: 20px;
  background-color: #efefef;
  padding: 20px;
}
.info-right {
  padding-left: 20px;
  letter-spacing: -1px;
}
</style>
