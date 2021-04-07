<template>
  <v-row class="fill-height">
    <v-col>
      <v-sheet height="64" width="450">
        <v-toolbar flat color="white">
          <v-row>
            <v-btn fab text small color="grey darken-2" @click="prev">
              <v-icon small>mdi-chevron-left</v-icon>
            </v-btn>
            <v-toolbar-title v-if="$refs.calendar">
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
          @change="updateRange"
        ></v-calendar>
      </v-sheet>
    </v-col>
    <div>
      {{ myDate }}
    </div>
  </v-row>
</template>

<script>
const storage = window.sessionStorage;
export default {
  data: () => ({
    myDate: '',
    focus: '',
    events: [],
    dates: ['210306', '210425', '210401'],
    times: ['02:00:03', '05:21:33', '13:22:49'],
  }),
  mounted() {
    this.$refs.calendar.checkChange();
  },
  created() {
    this.$Axios
      .create({
        headers: { 'jwt-auth-token': storage.getItem('jwt-auth-token') },
      })
      .get('/timer/total')
      .then((response) => {
        if (response.data.success) {
          alert('성공!!');
          console.log(response);
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
      this.myDate = date;
    },
    setToday() {
      this.focus = '';
    },
    prev() {
      this.$refs.calendar.prev();
    },
    next() {
      this.$refs.calendar.next();
    },
    showEvent() {},
    updateRange() {
      var tmpEvents = [];
      var eventCount = this.dates.length;

      var parseDate = function(str) {
        let y = '20' + str.substr(0, 2);
        let m = '' + str.substr(2, 2);
        let d = '' + str.substr(4);

        let all = y + '-' + m + '-' + d;

        return new Date(all);
      };

      for (let i = 0; i < eventCount; i++) {
        tmpEvents.push({
          name: this.times[i],
          start: parseDate(this.dates[i]),
        });
      }

      this.events = tmpEvents;
    },
  },
};
</script>

<style></style>
