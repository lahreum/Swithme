<template>
  <div>
    <middle-nav v-bind:propsdata="navInfo"></middle-nav>
    <v-container>
      <v-row>
        <v-col cols="2"></v-col>
        <v-col>
          <h1 style="margin:50px 0">정처기 합격가즈아</h1>
          <v-row
            justify="space-between"
            style="font-size:1.5rem; margin-bottom:50px;"
          >
            정처기 원콤을 목표로 하는 스터디입니다
            <span
              ><v-icon>mdi-account</v-icon>5
              <v-icon>mdi-crown</v-icon>dldkfma</span
            >
          </v-row>

          <v-row justify="space-around" style="margin-bottom:50px;">
            <v-btn icon color="black" x-large @click="ToHome"
              ><v-icon>mdi-home</v-icon> 홈</v-btn
            >
            <v-btn icon color="black" x-large
              ><v-icon>mdi-poll</v-icon> 랭킹</v-btn
            >
            <v-btn icon color="black" x-large
              ><v-icon>mdi-calendar-month</v-icon> 출석부</v-btn
            >
          </v-row>

          <v-row style="margin-bottom:50px;">
            <v-col cols="3" style="font-size:1.5rem;">지금 2명이 열공중!</v-col>
            <v-col
              ><v-chip outlined>
                D-39 정보처리기사 실기
              </v-chip></v-col
            >
          </v-row>

          <v-row justify="space-around" style="margin-bottom:50px;">
            <v-col
              ><div class="GroupStudyUser">
                <ProfileStudying :src="'https://ifh.cc/g/wyakuA.jpg'" />
                <h2 style="margin:30px 0 0 0">이아름</h2>
                <h3>03:01:01</h3>
              </div></v-col
            >
            <v-col
              ><div class="GroupStudyUser">
                <ProfileStudying :src="'https://ifh.cc/g/wyakuA.jpg'" />
                <h2 style="margin:30px 0 0 0">이아름</h2>
                <h3>03:01:01</h3>
              </div></v-col
            >
            <v-col
              ><div class="GroupStudyUser">
                <ProfileStudying :src="'https://ifh.cc/g/wyakuA.jpg'" />
                <h2 style="margin:30px 0 0 0">이아름</h2>
                <h3>03:01:01</h3>
              </div></v-col
            >
            <v-col
              ><div class="GroupStudyUser">
                <ProfileStudying :src="'https://ifh.cc/g/wyakuA.jpg'" />
                <h2 style="margin:30px 0 0 0">이아름</h2>
                <h3>03:01:01</h3>
              </div></v-col
            >
            <v-col
              ><div class="GroupStudyUser">
                <ProfileStudying :src="'https://ifh.cc/g/wyakuA.jpg'" />
                <h2 style="margin:30px 0 0 0">이아름</h2>
                <h3>03:01:01</h3>
              </div></v-col
            >
          </v-row>
        </v-col>
        <v-col cols="2"></v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>
import ProfileStudying from "../components/common/ProfileStudying.vue";
import MiddleNav from "../components/include/MiddleNav.vue";
export default {
  data() {
    return {
      navInfo: [
        "sample2.png",
        "그룹",
        "목표가 같은 사람들끼리 모여 달려보세요.",
        "목표로 가는 길이 덜 힘들고, 더욱 든든해질 거예요",
      ],
    };
  },
  components: {
    MiddleNav,
    ProfileStudying,
  },
  methods: {
    ToHome() {
      this.$router.push("/group-detail");
    },
  },
};
</script>

<style>
.GroupStudyUser {
  text-align: center;
}
</style>
