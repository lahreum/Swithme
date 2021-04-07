<template>
  <v-row class="fill-height">
    <v-col>
      <v-sheet height="64" width="450">
        <v-toolbar flat color="white">
          <v-row>
            <v-btn fab text small color="grey darken-2" @click="prev">
              <v-icon small>mdi-chevron-left</v-icon>
            </v-btn>
            <v-toolbar-title v-if="isMounted">
              {{ $refs.calendar.title }}
            </v-toolbar-title>
            <v-btn fab text small color="grey darken-2" @click="next">
              <v-icon small>mdi-chevron-right</v-icon>
            </v-btn>
          </v-row>
          <v-spacer></v-spacer>
          <div
            @click="setToday"
            style="font-weight: ligter; letter-spacing: -1px; cursor: pointer;font-size: 0.9rem;"
          >
            <v-icon color="#673fb4">mdi-cat</v-icon>
            Today
          </div>
        </v-toolbar>
      </v-sheet>
      <v-sheet height="500" width="450">
        <v-calendar
          ref="calendar"
          v-model="focus"
          color="primary"
          :events="events"
          event-color="#673fb4"
          @click:date="viewDay"
        ></v-calendar>
      </v-sheet>
    </v-col>
  </v-row>
</template>

<script>
import dateJS from '@/date.js';
import changeSec from '@/changeSec.js';

const storage = window.sessionStorage;
export default {
  data: () => ({
    focus: '',
    pickedDate: '',
    events: [],
    dates: [],
    times: [],
    histories: Array,
    title: '',
    isMounted: false,
  }),
  mounted() {
    this.isMounted = true;
  },
  created() {
    // 자료 받아오기
    this.$Axios
      .create({
        headers: { 'jwt-auth-token': storage.getItem('jwt-auth-token') },
      })
      .get('/timer/total')
      .then((response) => {
        if (response.data.myTotalStudyHistory.length != 0) {
          this.histories = response.data.myTotalStudyHistory;
          for (let i = 0; i < this.histories.length; i++) {
            this.dates.push(this.histories[i].timeDailyYearMonthDay);
            this.times.push(this.histories[i].timeDailyTime);
            this.events.push({
              name: changeSec(this.times[i]) + '',
              start: this.parseDate(this.dates[i]),
            });
          }
        } else {
          alert('기기바보');
        }
      })
      .catch((error) => {
        alert('에러 발생!!');
        console.log(error);
      });
  },
  methods: {
    viewDay({ date }) {
      this.focus = date;
      this.pickedDate = date;
      this.$emit('pickedDate', this.pickedDate);
    },
    setToday() {
      this.focus = '';
      this.pickedDate = dateJS.dateFunc(new Date());
      this.$emit('pickedDate', this.pickedDate);
    },
    prev() {
      this.$refs.calendar.prev();
    },
    next() {
      this.$refs.calendar.next();
    },
    // updateRange() {
    //   var tmpEvents = [];
    //   var eventCount = this.dates.length;

    //   var parseDate = function(str) {
    //     let y = '20' + str.substr(0, 2);
    //     let m = '' + str.substr(2, 2);
    //     let d = '' + str.substr(4);

    //     let all = y + '-' + m + '-' + d;

    //     return new Date(all);
    //   };

    //   for (let i = 0; i < eventCount; i++) {
    //     tmpEvents.push({
    //       name: this.times[i],
    //       start: parseDate(this.dates[i]),
    //     });
    //   }
    //   // console.log('???????????', tmpEvents);

    //   this.events = tmpEvents;
    // },
    parseDate: function(str) {
      let y = '20' + str.substr(0, 2);
      let m = '' + str.substr(2, 2);
      let d = '' + str.substr(4);

      let all = y + '-' + m + '-' + d;

      return new Date(all);
    },
  },
};
</script>

<style></style>
