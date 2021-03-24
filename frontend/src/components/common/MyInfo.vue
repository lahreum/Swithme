<template>
  <v-row class="info-container" no-gutters align="center">
    <div style="width: 200px;" align="center">
      {{ profile }}
    </div>
    <div class="info-right">
      <v-row no-gutters>
        <strong> {{ username }} </strong> 님, 오늘도 화이팅입니다!
      </v-row>
      <v-row
        no-gutters
        style="font-size: 3.3rem; font-weight: bold; margin-top: -10px; margin-bottom: -10px;"
      >
        {{ studyTime }}
      </v-row>
      <div>
        ( progress-bar )
      </div>
      <v-row no-gutters style="font-size: 1.2rem;"> "{{ myGoal }}" </v-row>
    </div>
  </v-row>
</template>

<script>
export default {
  data: function() {
    return {
      profile: '프로필 사진',
      username: 'default',
      studyTime: '9:36:33',
      myGoal: '오늘 걷지 않으면, 내일 뛰어야 한다.',
    };
  },
};
</script>

<style>
.info-container {
  height: 200px;
  border-radius: 20px;
  background-color: #efefef;
  padding: 20px;
}
.info-right {
  padding-left: 20px;
  letter-spacing: -0.7px;
}
</style>
