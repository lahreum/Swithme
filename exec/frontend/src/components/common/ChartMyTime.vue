<template>
  <div>
    <v-row no-gutters style="width: 800px; margin-bottom: 10px;" justify="end">
      <v-col align="end">
        <v-btn text small @click="showDay" color="#673fb4">
          Day
        </v-btn>
        <v-btn text small @click="showWeek" color="#673fb4">
          Week
        </v-btn>
        <v-btn text small @click="showMonth" color="#673fb4">
          Month
        </v-btn>
      </v-col>
    </v-row>
    <v-row
      no-gutters
      style="width: 800px; min-width: 800px; height: 93%; background-color: #eaeaea; border-left: solid 1px black; border-bottom: solid 1px black;"
    >
      <v-col
        v-for="(time, index) in timeList"
        :key="index"
        :style="'height:' + (400 / 3600) * time + 'px;'"
        style="background-color: #673fb4;"
        align="center"
        align-self="end"
      >
        <template v-if="time != 0">
          <div
            style="letter-spacing: -1px; margin-top: -20px; text-align: center;"
          >
            {{ transTime(time) }}
          </div>
        </template>
      </v-col>
    </v-row>
    <v-row no-gutters style="width: 800px; min-width: 800px; height: 20px;">
      <v-col
        v-for="(time, index) in timeList"
        :key="index"
        style="letter-spacing: -1px;"
        align="center"
        align-self="end"
      >
        {{ index }}
      </v-col>
    </v-row>
  </div>
</template>

<script>
import date from '@/date.js';
import changeSec from '@/changeSec.js';

const storage = window.sessionStorage;
export default {
  data: function() {
    return {
      timeList: [],
    };
  },
  created: function() {
    this.getEachTimeAverage('day');
  },
  methods: {
    showDay() {
      this.getEachTimeAverage('day');
    },
    showWeek() {
      this.getEachTimeAverage('week');
    },
    showMonth() {
      this.getEachTimeAverage('month');
    },
    getEachTimeAverage(tmpRange) {
      let today = date.dateFunc(new Date());
      // console.log(today);
      this.$Axios
        .create({
          headers: { 'jwt-auth-token': storage.getItem('jwt-auth-token') },
        })
        .get(`timer/hourly/${tmpRange}?datetimeOrigin=${today}`)
        .then((response) => {
          if (response.data.eachTimeAverage.length != 0) {
            this.timeList = response.data.eachTimeAverage;
          }
        });
    },
    transTime(value) {
      let tmp = changeSec(value);
      let str = tmp.substr(3);
      return str;
    },
  },
};
</script>

<style></style>
