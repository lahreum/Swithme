<template>
  <div>
    <middle-nav v-bind:propsdata="navInfo"></middle-nav>
    <v-container>
      <v-row>
        <v-col cols="2"></v-col>
        <v-col>
          <h1 style="margin:50px 0">{{ groupInfo.groupName }}</h1>
          <v-row
            justify="space-between"
            style="font-size:1.5rem; margin-bottom:50px;"
          >
            {{ groupInfo.groupIntroduce }}
            <span
              ><v-icon>mdi-account</v-icon>{{ groupers.length }}
              <v-icon>mdi-crown</v-icon>{{ groupInfo.groupMaster }}</span
            >
          </v-row>

          <v-row justify="space-around" style="margin-bottom:50px;">
            <v-btn icon color="black" x-large @click="ToHome"
              ><v-icon>mdi-home</v-icon> 홈</v-btn
            >
            <v-btn icon color="black" x-large @click="ToGroupRanking"
              ><v-icon>mdi-poll</v-icon> 랭킹</v-btn
            >
            <v-btn icon color="black" x-large @click="ToGroupAttendance"
              ><v-icon>mdi-calendar-month</v-icon> 출석부</v-btn
            >
          </v-row>

          <v-row style="margin-bottom:50px;">
            <v-col cols="3" style="font-size:1.5rem;"
              >지금 {{ studying.length }}명이 열공중!</v-col
            >
            <v-col
              ><v-chip outlined>
                D-39 정보처리기사 실기
              </v-chip></v-col
            >
          </v-row>

          <v-row style="margin-bottom:50px;">
            <v-col cols="12" sm="2" v-for="grouper in studying" :key="grouper"
              ><div class="GroupStudyUser">
                <ProfileStudying
                  :src="grouper.profile"
                  :IsStudying="grouper.IsStudying"
                />
                <h2 style="margin:30px 0 0 0">{{ grouper.name }}</h2>
                <h3>{{ grouper.time }}</h3>
              </div></v-col
            >
            <v-col
              cols="12"
              sm="2"
              v-for="grouper in notStudying"
              :key="grouper"
              ><div class="GroupStudyUser">
                <ProfileStudying
                  :src="grouper.profile"
                  :IsStudying="grouper.IsStudying"
                />
                <h2 style="margin:30px 0 0 0">{{ grouper.name }}</h2>
                <h3>{{ grouper.time }}</h3>
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
      studying: [],
      notStudying: [],
      navInfo: [
        "sample2.png",
        "그룹",
        "목표가 같은 사람들끼리 모여 달려보세요.",
        "목표로 가는 길이 덜 힘들고, 더욱 든든해질 거예요",
      ],
      groupInfo: {
        groupName: "정처기 합격가즈아",
        groupIntroduce: "정처기 원콤을 목표로 하는 스터디입니다.",
        groupMaster: "dldkfma",
      },
      groupers: [
        {
          name: "dldkfma",
          time: "03:13:24",
          profile: "https://ifh.cc/g/wyakuA.jpg",
          IsStudying: true,
        },
        {
          name: "빛봉현",
          time: "03:53:26",
          profile: "https://ifh.cc/g/wyakuA.jpg",
          IsStudying: false,
        },
        {
          name: "녹용파는사슴",
          time: "03:12:21",
          profile: "https://ifh.cc/g/wyakuA.jpg",
          IsStudying: false,
        },
        {
          name: "별빛지현",
          time: "01:13:24",
          profile: "https://ifh.cc/g/wyakuA.jpg",
          IsStudying: true,
        },
        {
          name: "dddddut",
          time: "05:13:54",
          profile: "https://ifh.cc/g/wyakuA.jpg",
          IsStudying: true,
        },
        {
          name: "정qqweeut",
          time: "05:13:54",
          profile: "https://ifh.cc/g/wyakuA.jpg",
          IsStudying: false,
        },
        {
          name: "정처기out",
          time: "05:13:54",
          profile: "https://ifh.cc/g/wyakuA.jpg",
          IsStudying: false,
        },
        {
          name: "정hohout",
          time: "05:13:54",
          profile: "https://ifh.cc/g/wyakuA.jpg",
          IsStudying: true,
        },
        {
          name: "정처기기기",
          time: "05:13:54",
          profile: "https://ifh.cc/g/wyakuA.jpg",
          IsStudying: false,
        },
        {
          name: "aaa정처at",
          time: "05:13:54",
          profile: "https://ifh.cc/g/wyakuA.jpg",
          IsStudying: true,
        },
      ],
    };
  },
  created() {
    var i = 0;
    for (i; i < this.groupers.length; i++) {
      if (this.groupers[i].IsStudying) {
        this.studying.push(this.groupers[i]);
      } else {
        this.notStudying.push(this.groupers[i]);
      }
    }
    console.log("dfasdf", this.studying);
    console.log(this.notStudying);
  },
  components: {
    MiddleNav,
    ProfileStudying,
  },
  methods: {
    ToGroupHome() {
      this.$router.push("/group-detail");
    },
    ToGroupRanking() {
      this.$router.push("/group-ranking");
    },
    ToGroupAttendance() {
      this.$router.push("/group-attendance");
    },
  },
};
</script>

<style>
.GroupStudyUser {
  text-align: center;
}
</style>
