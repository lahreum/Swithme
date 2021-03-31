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
            <v-btn icon color="black" x-large @click="ToGroupHome"
              ><v-icon>mdi-home</v-icon> 홈</v-btn
            >
            <v-btn icon color="black" x-large @click="ToGroupRanking"
              ><v-icon>mdi-poll</v-icon> 랭킹</v-btn
            >
            <v-btn icon color="black" x-large @click="ToGroupAttendance"
              ><v-icon>mdi-calendar-month</v-icon> 출석부</v-btn
            >
          </v-row>
          <v-card>
            <v-toolbar>
              <template>
                <v-tabs
                  v-model="tabs"
                  fixed-tabs
                  centered
                  slider-color="#673fb4"
                >
                  <v-tab v-for="item in items" :key="item">
                    {{ item.title }}
                  </v-tab>
                </v-tabs>
              </template>
            </v-toolbar>

            <v-tabs-items v-model="tabs">
              <v-tab-item>
                <v-card>
                  <v-row style="height:600px" justify="center" align="end">
                    <v-col
                      cols="3"
                      style="text-align:center;background-color:#673fb4; height:45%"
                      ><h1 style="position:relative; top:-25%">
                        {{ Day1stTo3rd[1].name }}
                      </h1>
                      <h2 style="position:relative; top:25%; color:white">
                        {{ Day1stTo3rd[1].time }}
                      </h2></v-col
                    >
                    <v-col
                      cols="3"
                      style="text-align:center;background-color:#524072; height:70%"
                      ><h1 style="position:relative; top:-15%">
                        {{ Day1stTo3rd[0].name }}
                      </h1>
                      <h2 style="position:relative; top:35%; color:white">
                        {{ Day1stTo3rd[0].time }}
                      </h2></v-col
                    >
                    <v-col
                      cols="3"
                      style="text-align:center;background-color: #9471d1; height:30%"
                      ><h1 style="position:relative; top:-35%">
                        {{ Day1stTo3rd[2].name }}
                      </h1>
                      <h2 style="position:relative; top:10%; color:white">
                        {{ Day1stTo3rd[2].time }}
                      </h2></v-col
                    >
                  </v-row>
                </v-card>
              </v-tab-item>
              <v-tab-item>
                <v-card>
                  <v-row style="height:600px" justify="center" align="end">
                    <v-col
                      cols="3"
                      style="text-align:center;background-color:#673fb4; height:45%"
                      ><h1 style="position:relative; top:-25%">
                        {{ Week1stTo3rd[1].name }}
                      </h1>
                      <h2 style="position:relative; top:25%; color:white">
                        {{ Week1stTo3rd[1].time }}
                      </h2></v-col
                    >
                    <v-col
                      cols="3"
                      style="text-align:center;background-color:#524072; height:70%"
                      ><h1 style="position:relative; top:-15%">
                        {{ Week1stTo3rd[0].name }}
                      </h1>
                      <h2 style="position:relative; top:35%; color:white">
                        {{ Week1stTo3rd[0].time }}
                      </h2></v-col
                    >
                    <v-col
                      cols="3"
                      style="text-align:center;background-color: #9471d1; height:30%"
                      ><h1 style="position:relative; top:-35%">
                        {{ Week1stTo3rd[2].name }}
                      </h1>
                      <h2 style="position:relative; top:10%; color:white">
                        {{ Week1stTo3rd[2].time }}
                      </h2></v-col
                    >
                  </v-row>
                </v-card> </v-tab-item
              ><v-tab-item>
                <v-card>
                  <v-row style="height:600px" justify="center" align="end">
                    <v-col
                      cols="3"
                      style="text-align:center;background-color:#673fb4; height:45%"
                      ><h1 style="position:relative; top:-25%">
                        {{ Month1stTo3rd[1].name }}
                      </h1>
                      <h2 style="position:relative; top:25%; color:white">
                        {{ Month1stTo3rd[1].time }}
                      </h2></v-col
                    >
                    <v-col
                      cols="3"
                      style="text-align:center;background-color:#524072; height:70%"
                      ><h1 style="position:relative; top:-15%">
                        {{ Month1stTo3rd[0].name }}
                      </h1>
                      <h2 style="position:relative; top:35%; color:white">
                        {{ Month1stTo3rd[0].time }}
                      </h2></v-col
                    >
                    <v-col
                      cols="3"
                      style="text-align:center;background-color: #9471d1; height:30%"
                      ><h1 style="position:relative; top:-35%">
                        {{ Month1stTo3rd[2].name }}
                      </h1>
                      <h2 style="position:relative; top:10%; color:white">
                        {{ Month1stTo3rd[2].time }}
                      </h2></v-col
                    >
                  </v-row>
                </v-card>
              </v-tab-item>
            </v-tabs-items>
          </v-card>
        </v-col>
        <v-col cols="2"></v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>
import MiddleNav from "../components/include/MiddleNav.vue";
export default {
  components: {
    MiddleNav,
  },
  data() {
    return {
      navInfo: [
        "sample2.png",
        "그룹",
        "목표가 같은 사람들끼리 모여 달려보세요.",
        "목표로 가는 길이 덜 힘들고, 더욱 든든해질 거예요",
      ],
      tabs: null,
      text:
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
      items: [{ title: "일간" }, { title: "주간" }, { title: "월간" }],
      groupInfo: {
        groupName: "정처기 합격가즈아",
        groupIntroduce: "정처기 원콤을 목표로 하는 스터디입니다.",
        groupMaster: "dldkfma",
      },
      Day1stTo3rd: [
        { name: "dldkfma", time: "02:14:25" },
        { name: "빛봉현", time: "02:14:25" },
        { name: "녹용파는사슴", time: "02:14:25" },
      ],
      Week1stTo3rd: [
        { name: "녹용파는사슴", time: "02:14:25" },
        { name: "빛봉현", time: "02:14:25" },
        { name: "별빛지현", time: "02:14:25" },
      ],
      Month1stTo3rd: [
        { name: "dldkfma", time: "02:14:25" },
        { name: "빛봉현", time: "02:14:25" },
        { name: "정처기out", time: "02:14:25" },
      ],
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
.dfadf {
  color: #9471d1;
}
</style>
