<template>
  <div>
    <v-row
      v-if="isMounted"
      no-gutters
      style="height: 50px; min-height: 50px; width: 400px; background-color: #eaeaea;"
    >
      <v-col
        v-for="(pattern, index) in patternList"
        :key="index"
        align="center"
      >
        <div
          v-if="pattern.timeDailyAction == 0"
          style="background-color: #673fb4; height: 50px;"
          :style="'width:' + (400 / totalTime) * pattern.timeDailyTime + 'px;'"
        ></div>
        <div
          v-else
          style="background-color: black; height: 50px;"
          :style="'width:' + (400 / totalTime) * pattern.timeDailyTime + 'px;'"
        ></div>
      </v-col>
    </v-row>
    <v-row
      no-gutters
      style="border: #dddddd 2px solid; border-radius: 20px; margin-top: 50px; height: 200px;"
      justify="center"
      align="center"
    >
      <v-col style="width: 100%;" align="center">
        <v-row
          no-gutters
          justify="center"
          style="letter-spacing: -2px; font-weight: bold; font-size: 3rem;"
          align="end"
        >
          {{ transTotalTime }}
        </v-row>
        <v-row no-gutters style="width: 80%;">
          <v-progress-linear
            rounded
            stream
            :value="focusPercentage"
            height="20"
            color="teal"
          ></v-progress-linear>
        </v-row>
        <v-row no-gutters>
          <v-col>
            <v-row
              no-gutters
              justify="start"
              style="letter-spacing: -1px; margin-left: 20px; margin-bottom: 15px; margin-top: 5px;"
            >
              {{ transFocusTime }}
            </v-row>
            <v-row
              no-gutters
              justify="center"
              style="letter-spacing: -1px; font-weight: bolder; font-size: 1.1rem;"
            >
              <v-icon color="#009688">mdi-circle</v-icon>
              {{ focusPercentage }}% 집중시간
            </v-row>
          </v-col>
          <v-col>
            <v-row
              no-gutters
              justify="end"
              style="letter-spacing: -1px;margin-right: 20px;  margin-bottom: 15px; margin-top: 5px;"
            >
              {{ transNotFocusTime }}
            </v-row>
            <v-row
              no-gutters
              justify="center"
              style="letter-spacing: -1px; font-weight: bolder; font-size: 1.1rem;"
            >
              <v-icon color="#B3E0DC">mdi-circle</v-icon>
              {{ notFocusPercentage }}% 비집중시간
            </v-row>
          </v-col>
        </v-row>
      </v-col>
    </v-row>
  </div>
</template>

<script>
import date from '@/date.js';
import changeSec from '@/changeSec.js';

const storage = window.sessionStorage;
export default {
  props: ['propdate'],
  data: function() {
    return {
      patternList: [],
      totalTime: 1,
      transTotalTime: '',
      focusTime: 0,
      transFocusTime: '',
      notFocusTime: 0,
      transNotFocusTime: '',
      focusPercentage: 0,
      notFocusPercentage: 100,
      inputDate: '',
      isMounted: false,
    };
  },
  created() {
    let today = date.dateFunc(new Date());
    this.getDisturbingCause(today);
  },
  watch: {
    propdate() {
      this.inputDate = date.dateFunc(this.propdate);
      console.log('?!?!?!?!?', this.inputDate);
      this.initialize();
      this.getDisturbingCause(this.inputDate);
    },
  },
  methods: {
    getDisturbingCause(date) {
      console.log(date);
      this.$Axios
        .create({
          headers: { 'jwt-auth-token': storage.getItem('jwt-auth-token') },
        })
        .get(`timer/not-study?datetime=${date}`)
        .then((response) => {
          if (response.data.disturbingCause.length != 0) {
            this.patternList = response.data.disturbingCause;
            this.getTimes();
          }
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getTimes() {
      let tmp = this.patternList;

      for (let i = 0; i < tmp.length; i++) {
        if (tmp[i].timeDailyAction == 0) {
          this.focusTime += tmp[i].timeDailyTime;
          this.totalTime += tmp[i].timeDailyTime;
        } else {
          this.notFocusTime += tmp[i].timeDailyTime;
          this.totalTime += tmp[i].timeDailyTime;
        }
      }
      this.transTotalTime = changeSec(this.totalTime - 1);
      this.transNotFocusTime = changeSec(this.notFocusTime);
      this.transFocusTime = changeSec(this.focusTime);
      this.focusPercentage = (
        (this.focusTime / (this.totalTime - 1)) *
        100
      ).toFixed(1);
      this.notFocusPercentage = (100 - this.focusPercentage).toFixed(1);

      this.isMounted = true;
    },
    initialize() {
      this.patternList = [];
      this.totalTime = 1;
      this.transTotalTime = '';
      this.focusTime = 0;
      this.transFocusTime = '';
      this.notFocusTime = 0;
      this.transNotFocusTime = '';
      this.focusPercentage = 0;
      this.notFocusPercentage = 100;
    },
  },
};
</script>

<style></style>
