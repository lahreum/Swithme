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
                    {{ grouper.name }}
                  </td>
                  <td
                    style="font-size:1.2rem; text-align:center; border:1px solid black"
                    v-for="n in 7"
                    :key="n"
                  >
                    {{ grouper.arr[n - 1] }}
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
import MiddleNav from "../components/include/MiddleNav.vue";
export default {
  components: {
    MiddleNav,
  },
  computed: {},
  created() {
    var cnt = 0;
    var i = 0;
    var j = 0;

    const CountGroupers = this.groupers.length;
    const a = CountGroupers * 7;
    for (i = 0; i < CountGroupers; i++) {
      for (j = 0; j < 7; j++) {
        if (this.groupers[i].arr[j] === "") {
          cnt += 1;
        }
      }
    }
    this.AttendanceRate = parseInt((100 * (a - cnt)) / a);
    console.log(this.AttendanceRate);
  },
  data() {
    return {
      AttendanceRate: 0,
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
      groupers: [
        {
          name: "dldkfma",
          arr: [
            "01:03:03",
            "02:03:03",
            "",
            "04:03:03",
            "05:03:03",
            "06:03:03",
            "07:03:03",
          ],
        },
        {
          name: "빛봉현",
          arr: [
            "01:03:03",
            "02:03:03",
            "03:03:03",
            "04:03:03",
            "05:03:03",
            "06:03:03",
            "",
          ],
        },
        {
          name: "별빛지현",
          arr: [
            "01:03:03",
            "02:03:03",
            "",
            "04:03:03",
            "",
            "06:03:03",
            "07:03:03",
          ],
        },
        {
          name: "녹용파는사슴",
          arr: [
            "01:03:03",
            "",
            "03:03:03",
            "04:03:03",
            "05:03:03",
            "06:03:03",
            "07:03:03",
          ],
        },
        {
          name: "정처기out",
          arr: [
            "01:03:03",
            "02:03:03",
            "03:03:03",
            "",
            "05:03:03",
            "06:03:03",
            "07:03:03",
          ],
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

<style></style>
