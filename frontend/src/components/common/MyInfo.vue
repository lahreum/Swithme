<template>
  <v-row class="info-container" no-gutters align="center">
    <div style="width: 250px;" align="center">
      <profile-large
        :src="require(`@/assets/img/avatars/iu.jpg`)"
      ></profile-large>
    </div>
    <div class="info-right">
      <v-row no-gutters style=" font-size: 1.2rem;">
        <strong> {{ user.username }} </strong> 님, 오늘도 화이팅입니다!
      </v-row>
      <v-row
        no-gutters
        style="font-size: 4rem; font-weight: bold; margin-top: -10px; "
      >
        {{ user.studyTime }}
      </v-row>
      <div>
        <progress-bar :progressValue="30"></progress-bar>
      </div>
      <v-row no-gutters style="font-size: 1.2rem; margin-top: 10px;">
        "{{ user.myGoal }}"
      </v-row>
    </div>
  </v-row>
</template>

<script>
import ProfileLarge from '@/components/common/ProfileLarge.vue';
import ProgressBar from '@/components/common/ProgressBar.vue';

export default {
  components: {
    'profile-large': ProfileLarge,
    'progress-bar': ProgressBar,
  },
  data: function() {
    return {
      user: {
        profile: '프로필 사진',
        username: 'default',
        studyTime: '9:36:33',
        myGoal: '오늘 걷지 않으면, 내일 뛰어야 한다.',
      },
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
