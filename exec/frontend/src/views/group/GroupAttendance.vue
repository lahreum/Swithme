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
          <div style="padding:30px; text-align:center">
            <v-btn x-large @click="weekMinus" icon
              ><v-icon>mdi-chevron-left</v-icon></v-btn
            >
            <span style="font-size:1.5rem; margin:0 5%"
              >{{ weekly1.getFullYear() }}년 {{ weekly1.getMonth() + 1 }}월
              {{ weekly1.getDate() }}일 ~ {{ weekly2.getFullYear() }}년
              {{ weekly2.getMonth() + 1 }}월 {{ weekly2.getDate() }}일</span
            >
            <v-btn x-large @click="weekPlus" icon
              ><v-icon>mdi-chevron-right</v-icon></v-btn
            >
          </div>
          <v-row justify="center" style="margin-bottom:50px;">
            <v-progress-circular
              :rotate="-90"
              :size="150"
              :width="30"
              :value="AttendanceRate"
              color="primary"
            >
              <div style="text-align:center">
                <h4>출석률</h4>
                <h4>{{ AttendanceRate }}%</h4>
              </div>
            </v-progress-circular>
          </v-row>
          <v-simple-table>
            <template v-slot:default>
              <thead>
                <tr style="background-color:#1976d2">
                  <th
                    style="font-size:1.5rem;text-align:center;border:1px solid black"
                    v-for="w in week"
                    :key="w"
                  >
                    {{ w }}
                  </th>
                </tr>
              </thead>
              <tbody>
                <tr
                  style="height:80px;text-align:center;border:1px solid black"
                  v-for="(grouper, idx) in groupers"
                  :key="idx"
                >
                  <td
                    style="font-size:1.2rem;text-align:center;border:1px solid black"
                  >
                    <h3>{{ grouper[0].timeDailyUserNickname }}</h3>
                  </td>
                  <td
                    style="font-size:1.2rem; text-align:center; border:1px solid black"
                    v-for="n in 7"
                    :key="n"
                  >
                    <h4
                      v-if="grouper[n - 1].timeDailyTime !== '00:00:00'"
                      style="background-color:#673fb4"
                    >
                      {{ grouper[n - 1].timeDailyTime }}
                    </h4>
                    <h4 v-else>
                      {{ grouper[n - 1].timeDailyTime }}
                    </h4>
                  </td>
                </tr>
              </tbody>
            </template>
          </v-simple-table>
        </v-col>
        <v-col cols="2"></v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>
import MiddleNav from "@/components/include/MiddleNav.vue";
import date from "@/date.js";
import studyTime from "@/changeSec.js";
import axios from "axios";
const storage = window.sessionStorage;
export default {
  components: {
    MiddleNav,
  },
  computed: {},

  data() {
    return {
      today: new Date(),
      weekly1: new Date(),
      weekly2: new Date(),
      tmp: "",
      AttendanceRate: 0,
      groupInfo: [],
      groupers: [],
      navInfo: [
        "sample2.png",
        "그룹",
        "목표가 같은 사람들끼리 모여 달려보세요.",
        "목표로 가는 길이 덜 힘들고, 더욱 든든해질 거예요",
      ],

      value: 80,
      week: {
        1: "가입순",
        2: "월",
        3: "화",
        4: "수",
        5: "목",
        6: "금",
        7: "토",
        8: "일",
      },
    };
  },
  methods: {
    toGroupHome() {
      this.$router.push({
        name: "GroupDetail",
        query: { groupId: this.$route.query.groupId },
      });
    },
    toGroupRanking() {
      this.$router.push({
        name: "GroupRanking",
        query: { groupId: this.$route.query.groupId },
      });
    },
    toGroupAttendance() {
      this.$router.push({
        name: "GroupAttendance",
        query: { groupId: this.$route.query.groupId },
      });
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
      this.getAttendance();
    },
    weekPlus() {
      this.tmp = new Date(this.weekly1);
      this.weekly1 = new Date(
        this.tmp.setDate(this.tmp.getDate() + 8 - this.tmp.getDay())
      );
      this.getWeekly2();
      this.getAttendance();
    },
    getAttendance() {
      let day = date.dateFunc(this.weekly1);
      axios
        .create({
          headers: {
            "jwt-auth-token": storage.getItem("jwt-auth-token"),
          },
        })
        .get(
          `group/attendance?datetime=${day}&groupId=${this.$route.query.groupId}`
        )
        .then((res) => {
          console.log("출석부받아오기", res);
          this.groupers = res.data.attendanceList;
          var cnt = 0;
          var i = 0;
          var j = 0;

          const CountGroupers = this.groupers.length;
          const a = CountGroupers * 7;
          for (i = 0; i < CountGroupers; i++) {
            for (j = 0; j < 7; j++) {
              if (this.groupers[i][j].timeDailyTime === 0) {
                cnt++;
              }
              this.groupers[i][j].timeDailyTime = studyTime(
                this.groupers[i][j].timeDailyTime
              );
            }
          }
          this.AttendanceRate = parseInt((100 * (a - cnt)) / a);
        });
    },
  },

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
        this.groupInfo = res.data.groupInfo;
        this.groupers = res.data.groupMemberList;
      });
    axios
      .create({
        headers: {
          "jwt-auth-token": storage.getItem("jwt-auth-token"),
        },
      })
      .get(
        `group/attendance?datetime=${day}&groupId=${this.$route.query.groupId}`
      )
      .then((res) => {
        console.log("출석부받아오기", res);
        this.groupers = res.data.attendanceList;
        var cnt = 0;
        var i = 0;
        var j = 0;

        const CountGroupers = this.groupers.length;
        const a = CountGroupers * 7;
        for (i = 0; i < CountGroupers; i++) {
          for (j = 0; j < 7; j++) {
            if (this.groupers[i][j].timeDailyTime === 0) {
              cnt++;
            }
            this.groupers[i][j].timeDailyTime = studyTime(
              this.groupers[i][j].timeDailyTime
            );
          }
        }
        this.AttendanceRate = parseInt((100 * (a - cnt)) / a);
        this.getWeekly1();
      });
  },
};
</script>

<style></style>
