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
            {{ groupInfo.groupNotice }}
            <span
              ><v-icon>mdi-account</v-icon>{{ groupers.length }}
              <v-icon>mdi-crown</v-icon
              >{{ groupInfo.groupMasterNickname }}</span
            >
          </v-row>

          <v-row justify="space-around" style="margin-bottom:50px;">
            <v-btn icon color="black" x-large @click="toGroupHome"
              ><v-icon>mdi-home</v-icon> 홈</v-btn
            >
            <v-btn icon color="black" x-large @click="toGroupRanking"
              ><v-icon>mdi-poll</v-icon> 랭킹</v-btn
            >
            <v-btn icon color="black" x-large @click="toGroupAttendance"
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
                  <v-tab @click="inItDay" v-for="item in items" :key="item">
                    {{ item }}
                  </v-tab>
                </v-tabs>
              </template>
            </v-toolbar>

            <v-tabs-items v-model="tabs">
              <v-tab-item>
                <v-card>
                  <div style="padding:30px; text-align:center">
                    <v-btn x-large @click="dayMinus" icon
                      ><v-icon>mdi-chevron-left</v-icon></v-btn
                    ><span style="font-size:1.5rem; margin:0 5%;"
                      >{{ daily.getFullYear() }}년 {{ daily.getMonth() + 1 }} 월
                      {{ daily.getDate() }}일</span
                    >
                    <v-btn x-large @click="dayPlus" icon
                      ><v-icon>mdi-chevron-right</v-icon></v-btn
                    >
                  </div>

                  <v-row style="height:600px" justify="center" align="end">
                    <v-col
                      cols="3"
                      style="text-align:center;background-color:#673fb4; height:45%"
                      ><h1 style="font-size:1.5em;position:relative; top:-25%">
                        {{ Day1stTo3rd[1].nickname }}
                      </h1>
                      <h2
                        style="font-size:1.5em;position:relative; top:25%; color:white"
                      >
                        {{ Day1stTo3rd[1].todayStudyTime }}
                      </h2></v-col
                    >
                    <v-col
                      cols="3"
                      style="text-align:center;background-color:#524072; height:70%"
                      ><h1 style="font-size:1.5em;position:relative; top:-15%">
                        {{ Day1stTo3rd[0].nickname }}
                      </h1>
                      <h2
                        style="font-size:1.5em;position:relative; top:35%; color:white"
                      >
                        {{ Day1stTo3rd[0].todayStudyTime }}
                      </h2></v-col
                    >
                    <v-col
                      cols="3"
                      style="text-align:center;background-color: #9471d1; height:30%"
                      ><h1 style="font-size:1.5em;position:relative; top:-35%">
                        {{ Day1stTo3rd[2].nickname }}
                      </h1>
                      <h2
                        style="font-size:1.5em;position:relative; top:10%; color:white"
                      >
                        {{ Day1stTo3rd[2].todayStudyTime }}
                      </h2></v-col
                    >
                  </v-row>
                </v-card>
              </v-tab-item>
              <v-tab-item>
                <v-card>
                  <div style="padding:30px; text-align:center">
                    <v-btn x-large @click="weekMinus" icon
                      ><v-icon>mdi-chevron-left</v-icon></v-btn
                    >
                    <span style="font-size:1.5rem; margin:0 5%"
                      >{{ weekly1.getFullYear() }}년
                      {{ weekly1.getMonth() + 1 }}월 {{ weekly1.getDate() }}일 ~
                      {{ weekly2.getFullYear() }}년
                      {{ weekly2.getMonth() + 1 }}월
                      {{ weekly2.getDate() }}일</span
                    >
                    <v-btn x-large @click="weekPlus" icon
                      ><v-icon>mdi-chevron-right</v-icon></v-btn
                    >
                  </div>
                  <v-row style="height:600px" justify="center" align="end">
                    <v-col
                      cols="3"
                      style="text-align:center;background-color:#673fb4; height:45%"
                      ><h1 style="font-size:1.5em;position:relative; top:-25%">
                        {{ Week1stTo3rd[1].nickname }}
                      </h1>
                      <h2
                        style="font-size:1.5em;position:relative; top:25%; color:white"
                      >
                        {{ Week1stTo3rd[1].todayStudyTime }}
                      </h2></v-col
                    >
                    <v-col
                      cols="3"
                      style="text-align:center;background-color:#524072; height:70%"
                      ><h1 style="font-size:1.5em;position:relative; top:-15%">
                        {{ Week1stTo3rd[0].nickname }}
                      </h1>
                      <h2
                        style="font-size:1.5em;position:relative; top:35%; color:white"
                      >
                        {{ Week1stTo3rd[0].todayStudyTime }}
                      </h2></v-col
                    >
                    <v-col
                      cols="3"
                      style="text-align:center;background-color: #9471d1; height:30%"
                      ><h1 style="font-size:1.5em;position:relative; top:-35%">
                        {{ Week1stTo3rd[2].nickname }}
                      </h1>
                      <h2
                        style="font-size:1.5em;position:relative; top:10%; color:white"
                      >
                        {{ Week1stTo3rd[2].todayStudyTime }}
                      </h2></v-col
                    >
                  </v-row>
                </v-card> </v-tab-item
              ><v-tab-item>
                <v-card>
                  <div style="padding:30px; text-align:center">
                    <v-btn x-large @click="monthMinus" icon
                      ><v-icon>mdi-chevron-left</v-icon></v-btn
                    ><span style="font-size:1.5rem; margin:0 30px">
                      {{ monthly.getFullYear() }} 년
                      {{ monthly.getMonth() + 1 }}월</span
                    >
                    <v-btn x-large @click="monthPlus" icon
                      ><v-icon>mdi-chevron-right</v-icon></v-btn
                    >
                  </div>
                  <v-row style="height:600px" justify="center" align="end">
                    <v-col
                      cols="3"
                      style="text-align:center;background-color:#673fb4; height:45%"
                      ><h1 style="font-size:1.5em;position:relative; top:-25%">
                        {{ Month1stTo3rd[1].nickname }}
                      </h1>
                      <h2
                        style="font-size:1.5em;position:relative; top:25%; color:white"
                      >
                        {{ Month1stTo3rd[1].todayStudyTime }}
                      </h2></v-col
                    >
                    <v-col
                      cols="3"
                      style="text-align:center;background-color:#524072; height:70%"
                      ><h1 style="font-size:1.5em;position:relative; top:-15%">
                        {{ Month1stTo3rd[0].nickname }}
                      </h1>
                      <h2
                        style="font-size:1.5em;position:relative; top:35%; color:white"
                      >
                        {{ Month1stTo3rd[0].todayStudyTime }}
                      </h2></v-col
                    >
                    <v-col
                      cols="3"
                      style="text-align:center;background-color: #9471d1; height:30%"
                      ><h1 style="font-size:1.5em;position:relative; top:-35%">
                        {{ Month1stTo3rd[2].nickname }}
                      </h1>
                      <h2
                        style="font-size:1.5em;position:relative; top:10%; color:white"
                      >
                        {{ Month1stTo3rd[2].todayStudyTime }}
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
import MiddleNav from "@/components/include/MiddleNav.vue";
import date from "@/date.js";
import axios from "axios";
const storage = window.sessionStorage;
export default {
  components: {
    MiddleNav,
  },
  data() {
    return {
      today: new Date(),
      daily: new Date(),
      weekly1: new Date(),
      weekly2: new Date(),
      monthly: new Date(),
      tmp: "",
      gId: 0,
      groupInfo: [],
      groupers: [],
      range: ["day", "week", "month"],
      navInfo: [
        "sample2.png",
        "그룹",
        "목표가 같은 사람들끼리 모여 달려보세요.",
        "목표로 가는 길이 덜 힘들고, 더욱 든든해질 거예요",
      ],
      tabs: null,
      items: ["일간", "주간", "월간"],

      Day1stTo3rd: [
        { nickname: "", todayStudyTime: "" },
        { nickname: "", todayStudyTime: "" },
        { nickname: "", todayStudyTime: "" },
      ],
      Week1stTo3rd: [
        { nickname: "", todayStudyTime: "" },
        { nickname: "", todayStudyTime: "" },
        { nickname: "", todayStudyTime: "" },
      ],
      Month1stTo3rd: [
        { nickname: "", todayStudyTime: "" },
        { nickname: "", todayStudyTime: "" },
        { nickname: "", todayStudyTime: "" },
      ],
    };
  },
  methods: {
    toGroupHome() {
      this.$router.push({
        name: "GroupDetail",
        query: { groupId: this.gId },
      });
    },
    toGroupRanking() {
      this.$router.push({
        name: "GroupRanking",
        query: { groupId: this.gId },
      });
    },
    toGroupAttendance() {
      this.$router.push({
        name: "GroupAttendance",
        query: { groupId: this.gId },
      });
    },
    dayMinus() {
      this.tmp = new Date(this.daily);
      this.daily = new Date(this.tmp.setDate(this.tmp.getDate() - 1));
      this.getRanking();
    },
    dayPlus() {
      this.tmp = new Date(this.daily);
      this.daily = new Date(this.tmp.setDate(this.tmp.getDate() + 1));
      this.getRanking();
    },
    getWeekly1() {
      this.weekly1 = new Date(
        this.today.setDate(this.today.getDate() - this.today.getDay() + 1)
      );
      console.log("weekly1", this.weekly1);
      this.tmp = new Date(this.weekly1);
      this.weekly2 = new Date(this.tmp.setDate(this.tmp.getDate() + 6));
    },
    getWeekly2() {
      this.tmp = new Date(this.weekly1);
      this.weekly2 = new Date(this.tmp.setDate(this.tmp.getDate() + 6));
    },

    weekMinus() {
      this.tmp = new Date(this.weekly1);
      this.weekly1 = new Date(
        this.tmp.setDate(this.tmp.getDate() - 6 - this.tmp.getDay())
      );
      this.getWeekly2();
      this.getRanking();
    },
    weekPlus() {
      this.tmp = new Date(this.weekly1);
      this.weekly1 = new Date(
        this.tmp.setDate(this.tmp.getDate() + 8 - this.tmp.getDay())
      );
      this.getWeekly2();
      this.getRanking();
    },
    monthMinus() {
      this.monthly = new Date(
        this.monthly.getFullYear(),
        this.monthly.getMonth(),
        1
      );
      this.monthly = new Date(this.monthly.setDate(this.monthly.getDate() - 1));
      console.log(this.monthly);
      this.getRanking();
    },
    monthPlus() {
      this.monthly = new Date(
        this.monthly.getFullYear(),
        this.monthly.getMonth(),
        31
      );
      this.monthly = new Date(this.monthly.setDate(this.monthly.getDate() + 1));
      console.log(this.monthly);
      this.getRanking();
    },
    inItDay() {
      var param = new Date();
      var day = date.dateFunc(param);

      console.log("인잇했을때 현재 날짜", day);
      axios
        .create({
          headers: {
            "jwt-auth-token": storage.getItem("jwt-auth-token"),
          },
        })
        .get(
          `group/ranking?datetime=${day}&groupId=${
            this.groupInfo.groupId
          }&range=${this.range[this.tabs]}`
        )
        .then((res) => {
          console.log(res);
          if (this.tabs === 0) {
            this.Day1stTo3rd = res.data.rankingList;
          } else if (this.tabs === 1) {
            this.Week1stTo3rd = res.data.rankingList;
          } else {
            this.Month1stTo3rd = res.data.rankingList;
          }
        });
    },
    getRanking() {
      if (this.tabs === 0) {
        var param = this.daily;
        var day = date.dateFunc(param);
      } else if (this.tabs === 1) {
        param = this.weekly1;
        day = date.dateFunc(param);
      } else if (this.tabs === 2) {
        param = this.monthly;
        day = date.dateFunc(param);
      }

      console.log("겟랭킹했을때 현재 날짜", day);
      axios
        .create({
          headers: {
            "jwt-auth-token": storage.getItem("jwt-auth-token"),
          },
        })
        .get(
          `group/ranking?datetime=${day}&groupId=${
            this.groupInfo.groupId
          }&range=${this.range[this.tabs]}`
        )
        .then((res) => {
          console.log(res);
          if (this.tabs === 0) {
            this.Day1stTo3rd = res.data.rankingList;
          } else if (this.tabs === 1) {
            this.Week1stTo3rd = res.data.rankingList;
          } else {
            this.Month1stTo3rd = res.data.rankingList;
          }
        });
    },
  },
  created() {
    // let month = this.daily.getMonth() + 1;
    // let date = this.daily.getDate();
    // this.daily = `${month}월 ${date}일`;
    this.getWeekly1();
    this.gId = this.groupInfo.groupId;
    let today = new Date();
    let day = date.dateFunc(today);
    axios
      .create({
        headers: {
          "jwt-auth-token": storage.getItem("jwt-auth-token"),
        },
      })
      .get(`group/${this.$route.query.groupId}?datetime=${day}`)
      .then((res) => {
        this.groupInfo = res.data.groupInfo;
        this.groupers = res.data.groupMemberList;
        axios
          .create({
            headers: {
              "jwt-auth-token": storage.getItem("jwt-auth-token"),
            },
          })
          .get(
            `group/ranking?datetime=${day}&groupId=${
              this.groupInfo.groupId
            }&range=${this.range[this.tabs]}`
          )
          .then((res) => {
            console.log("크리에이티드할떄", day);
            console.log(res);
            this.Day1stTo3rd = res.data.rankingList;
          });
      });
  },
};
</script>

<style>
.dfadf {
  color: #9471d1;
}
</style>
