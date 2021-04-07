<template>
  <div>
    <middle-nav v-bind:propsdata="navInfo"></middle-nav>
    <v-container>
      <v-row style="margin-top:50px">
        <v-col cols="2"></v-col>
        <v-col>
          <v-row align="center">
            <span style="font:bold;font-size:2.5rem;margin-right:2%">{{
              groupInfo.groupName
            }}</span>
            <v-icon
              @click="toGroupModify(groupInfo.groupId)"
              v-if="IsGM"
              x-large
              >mdi-cog-outline</v-icon
            >
            <v-col align="end">
              <v-btn
                v-if="
                  !nowUser &&
                    groupInfo.groupCurMemberCount <
                      groupInfo.groupMaxMemberCount
                "
                @click="joinGroup"
                icon
                color="green"
                style="margin:0 5% 0 600px"
                ><v-icon>mdi-login</v-icon>가입하기</v-btn
              >

              <v-btn
                v-if="
                  nowUser && loginNickname !== groupInfo.groupMasterNickname
                "
                @click="leaveGroup"
                icon
                color="red"
                ><v-icon>mdi-logout</v-icon>탈퇴하기</v-btn
              >
            </v-col>
          </v-row>

          <v-row
            justify="space-between"
            style="font-size:1.5rem; margin:50px 0;"
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
            <v-btn
              :disabled="!nowUser"
              icon
              color="black"
              x-large
              @click="toGroupRanking"
              ><v-icon>mdi-poll</v-icon> 랭킹</v-btn
            >
            <v-btn
              :disabled="!nowUser"
              icon
              color="black"
              x-large
              @click="toGroupAttendance"
              ><v-icon>mdi-calendar-month</v-icon> 출석부</v-btn
            >
          </v-row>

          <v-row style="margin-bottom:50px;">
            <v-col cols="3" style="font-size:1.5rem;"
              >지금 {{ studying.length }}명이 열공중!</v-col
            >
            <v-col>
              <v-chip v-if="groupInfo.groupGoalTitle !== NULL" outlined>
                D-{{ Dday }} {{ groupInfo.groupGoalTitle }}
              </v-chip>
            </v-col>
          </v-row>

          <v-row style="margin-bottom:50px;">
            <v-col
              cols="12"
              sm="3"
              v-for="(grouper, idx) in studying"
              :key="'studying' + idx"
              ><div class="GroupStudyUser">
                <ProfileStudying
                  :src="grouper.profileImg"
                  :IsStudying="grouper.studying"
                />
                <h2 style="margin:30px 0 0 0">{{ grouper.nickname }}</h2>
                <h3 v-if="grouper.todayStudyTime === 0">00:00:00</h3>
                <h3 v-else>{{ grouper.todayStudyTime }}</h3>
              </div></v-col
            >
            <v-col
              cols="12"
              sm="3"
              v-for="(grouper, idx) in notStudying"
              :key="'notStudying' + idx"
              ><div class="GroupStudyUser">
                <ProfileStudying
                  :src="'data:image/png;base64,' + grouper.profileImg"
                  :IsStudying="grouper.studying"
                />
                <h2 style="margin:30px 0 0 0">{{ grouper.nickname }}</h2>
                <h3 v-if="grouper.todayStudyTime === 0">00:00:00</h3>
                <h3 v-else>{{ grouper.todayStudyTime }}</h3>
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
import ProfileStudying from "@/components/common/ProfileStudying.vue";
import MiddleNav from "@/components/include/MiddleNav.vue";
import date from "@/date.js";
import axios from "axios";
const storage = window.sessionStorage;

export default {
  data() {
    return {
      studying: [],
      notStudying: [],
      IsGM: false,
      Dday: 0,
      loginNickname: "",
      navInfo: [
        "sample2.png",
        "그룹",
        "목표가 같은 사람들끼리 모여 달려보세요.",
        "목표로 가는 길이 덜 힘들고, 더욱 든든해질 거예요",
      ],
      groupInfo: [],
      groupers: [],
    };
  },
  // props: ["groupId"],
  created() {
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
        console.log("디테일만들어질때", res);
        this.groupInfo = res.data.groupInfo;
        this.groupers = res.data.groupMemberList;
        this.loginNickname = this.$store.getters.getUserNickname;
        for (var i = 0; i < this.groupers.length; i++) {
          if (this.groupers[i].Studying) {
            this.studying.push(this.groupers[i]);
          } else {
            this.notStudying.push(this.groupers[i]);
          }
        }
        console.log(this.groupInfo);
        console.log(this.groupers);
        console.log(this.groupers.includes(this.loginNickname));
        console.log("공부중아닌그룹원들", this.notStudying);
        let Goal = new Date(this.groupInfo.groupGoalDate);
        this.Dday = Math.abs(Goal.getTime() - today.getTime());
        this.Dday = Math.ceil(this.Dday / (1000 * 3600 * 24));

        if (this.loginNickname === this.groupInfo.groupMasterNickname) {
          this.IsGM = true;
        }
      })
      .catch((err) => {
        console.log(err);
      });
    // console.log("받아왓니", this.$route.params.groupId);
    // console.log("dfasdf", this.studying);
    // console.log(this.notStudying);
  },
  computed: {
    nowUser() {
      for (var i = 0; i < this.groupers.length; i++) {
        if (this.groupers[i].nickname === this.loginNickname) {
          return true;
        }
      }
      return false;
    },
  },
  components: {
    MiddleNav,
    ProfileStudying,
  },
  methods: {
    toGroupHome() {
      this.$router.push({
        name: "GroupDetail",
        query: { groupId: this.groupInfo.groupId },
      });
    },
    toGroupRanking() {
      this.$router.push({
        name: "GroupRanking",
        query: { groupId: this.groupInfo.groupId },
      });
    },
    toGroupAttendance() {
      this.$router.push({
        name: "GroupAttendance",
        query: { groupId: this.groupInfo.groupId },
      });
    },
    toGroupModify(g) {
      this.$router.push({ name: "GroupModify", query: { groupId: g } });
    },
    joinGroup() {
      axios
        .create({
          headers: {
            "jwt-auth-token": storage.getItem("jwt-auth-token"),
          },
        })
        .post(`group/${this.groupInfo.groupId}`)
        .then((res) => {
          console.log("그룹가입하기눌럿을때", res);
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
              console.log("그룹가입하기성공햇을때", res);
              this.groupers = res.data.groupMemberList;
              this.studying = [];
              this.notStudying = [];
              for (var i = 0; i < this.groupers.length; i++) {
                if (this.groupers[i].Studying) {
                  this.studying.push(this.groupers[i]);
                } else {
                  this.notStudying.push(this.groupers[i]);
                }
              }
            });
        });
    },
    leaveGroup() {
      axios
        .create({
          headers: {
            "jwt-auth-token": storage.getItem("jwt-auth-token"),
          },
        })
        .delete(`group/${this.groupInfo.groupId}`)
        .then((res) => {
          console.log(res);
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
              console.log(res);
              this.groupers = res.data.groupMemberList;
              this.studying = [];
              this.notStudying = [];
              for (var i = 0; i < this.groupers.length; i++) {
                if (this.groupers[i].Studying) {
                  this.studying.push(this.groupers[i]);
                } else {
                  this.notStudying.push(this.groupers[i]);
                }
              }
            });
        });
    },
  },
};
</script>

<style>
.GroupStudyUser {
  text-align: center;
}
</style>
