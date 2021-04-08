<template>
  <div>
    <middle-nav v-bind:propsdata="navInfo"></middle-nav>
    <v-container style="padding-top: 3%; ">
      <!-- part: 1 -->
      <v-row
        no-gutters
        style="font-size: 1.8rem; letter-spacing: -1px;"
        justify="center"
      >
        <v-col cols="4" align="center">
          {{ this.user.userNickname }}님의 학습분석
        </v-col>
      </v-row>
      <v-row no-gutters justify="center" style="margin-top: 5px; ">
        <hr
          style="width: 80%; border: 0; height: 2px; background-color: black;"
        />
      </v-row>
      <!-- 내 정보 -->
      <v-row justify="center" no-gutters style="margin-top: 50px;">
        <div style="width: 800px; min-width: 800px;">
          <my-info
            :username="user.userNickname"
            :message="user.userMessage"
            :profile="user.profileImg"
            :studyTime="user.todayStudyTime"
          ></my-info>
        </div>
      </v-row>
      <!-- part: 2 -->
      <v-container style="padding-left: 12%; padding-right: 12%">
        <!-- 학습 캘린더 -->

        <v-row
          no-gutters
          style="font-size: 1.5rem; letter-spacing: -2px; margin-top: 70px;"
          justify="start"
        >
          <v-col>학습 캘린더</v-col>
        </v-row>
        <v-row no-gutters justify="center" style="margin-top: 5px; ">
          <hr
            style="width: 100%; border: 0; height: 1px; background-color: black;"
          />
        </v-row>
        <v-row no-gutters justify="center" style="margin-top: 50px;">
          <div style="width: 800px; min-width: 800px;">
            <v-row>
              <v-col
                cols="7"
                align="center"
                style="background-color: #eaeaea; margin-right: 10px;"
              >
                <app-calendar @pickedDate="getPickedDate"></app-calendar>
              </v-col>
              <v-col
                align="center"
                style="border-radius: 30px; background-color: #eaeaea; margin-left: 10px;"
              >
                <v-row
                  justify="center"
                  style="letter-spacing: -3px; font-size: 3rem; font-weight: lighter; margin-top: 2px; margin-bottom: 2px;"
                >
                  TODO
                </v-row>
                <v-row
                  style="margin: 8px; background-color: white; padding-bottom: 10px;"
                >
                  <todo-list
                    :todoList="user.todoList"
                    :date="pickedDate"
                    @updateTodoList="getTodoList"
                  ></todo-list>
                </v-row>
              </v-col>
            </v-row>
          </div>
        </v-row>
        <!-- 나의 학습시간 -->
        <v-row
          no-gutters
          style="font-size: 1.5rem; letter-spacing: -2px; margin-top: 70px;"
          justify="start"
          align="end"
        >
          <v-col cols="10">나의 학습 시간</v-col>
          <v-col align="end" style="color: #999999; font-size: 1rem;">
            (단위: 분)
          </v-col>
        </v-row>
        <v-row no-gutters justify="center" style="margin-top: 5px; ">
          <hr
            style="width: 100%; border: 0; height: 1px; background-color: black;"
          />
        </v-row>
        <v-row no-gutters justify="center" style="margin-top: 50px;">
          <div style="width: 800px; min-width: 800px; ">
            <v-row justify="center" style="height: 400px;">
              <chart-my-time></chart-my-time>
            </v-row>
          </div>
        </v-row>
        <!-- 주요 공부 시간대 & 집중 지속 시간 -->
        <v-row no-gutters>
          <!-- 주요 공부 시간대 -->
          <v-col cols="6" style="padding-right: 10px;">
            <v-row
              no-gutters
              style="font-size: 1.5rem; letter-spacing: -2px; margin-top: 70px;"
              justify="start"
              align="end"
            >
              <v-col cols="10">
                주요 공부시간대
              </v-col>
              <v-col align="end" style="color: #999999; font-size: 1rem;">
                (단위: 시)
              </v-col>
            </v-row>
            <v-row no-gutters justify="center" style="margin-top: 5px; ">
              <hr
                style="width: 100%; border: 0; height: 1px; background-color: black;"
              />
            </v-row>
            <v-row no-gutters justify="center" style="margin-top: 50px;">
              <div style="min-width: 380px;">
                <v-row justify="center" v-if="isFinished">
                  <chart-main-time
                    :labels="timeLabel"
                    :timedataset="timeDataset"
                  ></chart-main-time>
                </v-row>
              </div>
            </v-row>
          </v-col>
          <!-- 집중 지속 시간 -->
          <v-col style="padding-left: 10px;">
            <v-row
              no-gutters
              style="font-size: 1.5rem; letter-spacing: -2px; margin-top: 70px;"
              justify="start"
              align="end"
            >
              <v-col cols="10">나의 집중패턴</v-col>
              <v-col align="end" style="color: #999999; font-size: 1rem;">
                (단위: 분)
              </v-col>
            </v-row>
            <v-row no-gutters justify="center" style="margin-top: 5px; ">
              <hr
                style="width: 100%; border: 0; height: 1px; background-color: black;"
              />
            </v-row>
            <v-row
              no-gutters
              justify="center"
              style="min-width: 380px; height: 400px; margin-top: 50px;"
              align="center"
            >
              <v-row no-gutters>
                <v-col align="end" align-self="center">
                  <v-btn icon @click="goBeforeDay">
                    <v-icon x-large>
                      mdi-chevron-left
                    </v-icon>
                  </v-btn>
                </v-col>
                <v-col
                  cols="7"
                  align="center"
                  align-self="center"
                  style="letter-spacing: -1px; font-size: 2rem;"
                >
                  {{ dateForFocusChart.substr(0, 10) }}
                </v-col>
                <v-col align="start" align-self="center">
                  <v-btn icon @click="goAfterDay">
                    <v-icon x-large>
                      mdi-chevron-right
                    </v-icon>
                  </v-btn>
                </v-col>
              </v-row>
              <chart-focus-time></chart-focus-time>
            </v-row>
          </v-col>
        </v-row>
        <!-- 그룹 내 공부시간 순위 & 주요 방해요인 -->
        <v-row no-gutters>
          <!-- 그룹 내 공부시간 순위 -->
          <v-col cols="6" style="padding-right: 10px;">
            <v-row
              no-gutters
              style="font-size: 1.5rem; letter-spacing: -2px; margin-top: 70px;"
              justify="start"
              align="end"
            >
              <v-col cols="10">그룹 내 공부시간 순위</v-col>
              <v-col align="end" style="color: #999999; font-size: 1rem;">
                (단위: %)
              </v-col>
            </v-row>
            <v-row no-gutters justify="center" style="margin-top: 5px; ">
              <hr
                style="width: 100%; border: 0; height: 1px; background-color: black;"
              />
            </v-row>
            <v-row no-gutters justify="center" style="margin-top: 50px;">
              <div
                v-if="!myRankLoading"
                style="min-width:100%; overflow-x: hidden; max-height:300px; min-height:300px"
              >
                <v-row
                  v-for="(group, idx) in groupListThatIAm"
                  :key="idx"
                  justify="center"
                >
                  <v-col>
                    <h3 style="max-width:90%; margin-bottom:10px;">
                      {{ groupListThatIAm[idx].groupName }}
                    </h3>
                    <span style="color:#673fb4; font-size:1.3rem">{{
                      groupListThatIAm[idx].groupMaxMemberCount
                    }}</span
                    >명 중,
                    <span style="color:#673fb4; font-size:1.3rem">{{
                      myRankList[idx]
                    }}</span
                    >등!
                  </v-col>
                </v-row>
              </div>
              <div
                v-else
                style="min-width:100%; min-height:300px; max-height:300px"
              >
                <v-progress-circular
                  style="position:relative; top:50%; left:50%; transform:translate(-50%, -50%);"
                  indeterminate
                  color="purple"
                  :size="50"
                ></v-progress-circular>
              </div>
            </v-row>
          </v-col>
          <!-- 주요 방해요인 -->
          <v-col style="padding-left: 10px;">
            <v-row
              no-gutters
              style="font-size: 1.5rem; letter-spacing: -2px; margin-top: 70px;"
              justify="start"
              align="end"
            >
              <v-col cols="10">주요 방해요인</v-col>
              <v-col align="end" style="color: #999999; font-size: 1rem;">
                (단위: %)
              </v-col>
            </v-row>
            <v-row no-gutters justify="center" style="margin-top: 5px; ">
              <hr
                style="width: 100%; border: 0; height: 1px; background-color: black;"
              />
            </v-row>
            <v-row
              no-gutters
              justify="center"
              style="min-width: 380px; height: 400px; margin-top: 50px;"
              align="center"
              v-if="isFinished2"
            >
              <chart-cause :countdataset="causeCntSet"></chart-cause>
              <!-- <v-col cols="3" align="end">
                <img src="@/assets/img/warning.gif" width="50" />
              </v-col>
              <v-col style="letter-spacing: -1px;">
                <v-row no-gutters align="end">
                  <span style="font-size: 1.3rem; font-weight: bold;">앗,</span>
                  프리미엄 회원을 위한 기능이에요.
                </v-row>
                <v-row no-gutters style="font-size: 0.7rem;">
                  (저희가 야심차게 준비했다는 것만 알려드릴게요. *속닥속닥*)
                </v-row>
              </v-col> -->
            </v-row>
          </v-col>
        </v-row>
      </v-container>
    </v-container>
  </div>
</template>

<script>
import MiddleNav from '../components/include/MiddleNav.vue';
import MyInfo from '@/components/common/MyInfo.vue';
import TodoList from '@/components/common/TodoList.vue';
import AppCalendar from '@/components/common/AppCalendar.vue';
import ChartMyTime from '@/components/common/ChartMyTime.vue';
import ChartMainTime from '@/components/common/ChartMainTime.vue';
import ChartFocusTime from '@/components/common/ChartFocusTime.vue';
import ChartCause from '@/components/common/ChartCause.vue';
import date from '@/date.js';
import changeSec from '@/changeSec.js';

const storage = window.sessionStorage;
export default {
  components: {
    'middle-nav': MiddleNav,
    'my-info': MyInfo,
    'todo-list': TodoList,
    'app-calendar': AppCalendar,
    'chart-my-time': ChartMyTime,
    'chart-main-time': ChartMainTime,
    'chart-focus-time': ChartFocusTime,
    'chart-cause': ChartCause,
  },
  created: function() {
    // user 정보 받아오기
    this.$Axios
      .create({
        headers: { 'jwt-auth-token': storage.getItem('jwt-auth-token') },
      })
      .get('user')
      .then((response) => {
        this.user.userId = response.data.data.userId;
        this.user.userNickname = response.data.data.userNickname;
        this.user.userMessage = response.data.data.userMessage;
        this.user.userIsStudying = response.data.data.userIsStudying;
        this.user.profileImg = response.data.profileImg;
      });

    let today = new Date();
    let day = date.dateFunc(today);

    // 그룹 내 공부시간 순위
    this.$Axios
      .create({
        headers: {
          'jwt-auth-token': storage.getItem('jwt-auth-token'),
        },
      })
      .get(`group/that-i-am?datetime=${day}`)
      .then((res) => {
        // console.log(res.data.groupListThatIAm[0].groupName);
        this.myRankList = res.data.myRankList;
        this.groupListThatIAm = res.data.groupListThatIAm;
        this.myRankLoading = false;
      });

    // 공부 시간 받아오기
    this.$Axios
      .create({
        headers: {
          'jwt-auth-token': storage.getItem('jwt-auth-token'),
        },
      })
      .get('timer/today?datetime=' + day)
      .then((response) => {
        if (
          response.data.todayStudyTime == null ||
          response.data.todayStudyTime === 0
        ) {
          this.user.todayStudyTime = '0:00:00';
        } else {
          this.user.todayStudyTime = changeSec(response.data.todayStudyTime);
        }
      })
      .catch((error) => {
        console.log(error);
      });

    // 투두리스트 받아오기
    this.$Axios
      .create({
        headers: { 'jwt-auth-token': storage.getItem('jwt-auth-token') },
      })
      .get('todo?datetime=' + this.pickedDate)
      .then((response) => {
        if (response.data.todoList.length != 0) {
          this.user.todoList = response.data.todoList;
        } else {
          this.user.todoList = [];
        }
      })
      .catch((error) => {
        console.log('TODO-LIST ERROR!!!!!', error);
      });

    // 공부 시간대 받아오기
    this.getEachTimeAverage('month');

    // 방해요소 가져오기
    this.getDisturbingCause();
  },
  data: function() {
    return {
      navInfo: [
        'nav3.jpg',
        '나의 학습',
        '첫asdf번째 문장입니다. 첫번째 문장입니다. 첫번째 문장입',
        '두번째 문장입니다~! 두번째 문장입니다~! 두번째 문장입니다~! 두번째',
      ],
      user: {
        userId: '',
        userNickname: '',
        userMessage: '',
        userIsStudying: false,
        profileImg: '',
        userType: '',
        todayStudyTime: '',
        todoList: [],
      },
      pickedDate: date.dateFunc(new Date()),
      causeCntSet: [0, 0, 0, 0],
      timeList: [],
      timeDataset: [0, 0, 0, 0, 0, 0],
      timeLabel: ['새벽', '아침', '오전', '오후', '저녁', '밤'],
      isFinished: false,
      isFinished2: false,
      myRankLoading: true,
      myRankList: [],
      groupListThatIAm: [],
      dateForFocusChart: date.dateFunc(new Date()),
      // flagDay: 1,
    };
  },
  methods: {
    getPickedDate: function(value) {
      this.pickedDate = value;
      console.log(this.myRankList);
      console.log(this.groupListThatIAm);

      // 투두리스트 받아오기
      this.$Axios
        .create({
          headers: { 'jwt-auth-token': storage.getItem('jwt-auth-token') },
        })
        .get('todo?datetime=' + this.pickedDate)
        .then((response) => {
          if (response.data.todoList.length != 0) {
            this.user.todoList = response.data.todoList;
          } else {
            this.user.todoList = [];
          }
        })
        .catch((error) => {
          console.log('TODO-LIST ERROR!!!!!', error);
        });
    },
    getEachTimeAverage(tmpRange) {
      let today = date.dateFunc(new Date());
      console.log(today);
      this.$Axios
        .create({
          headers: { 'jwt-auth-token': storage.getItem('jwt-auth-token') },
        })
        .get(`timer/hourly/${tmpRange}?datetimeOrigin=${today}`)
        .then((response) => {
          if (response.data.eachTimeAverage.length != 0) {
            this.timeList = response.data.eachTimeAverage;
            if (this.timeList != null) {
              this.divideTime();
            }
          }
        });
    },
    divideTime() {
      let tmp = [0, 0, 0, 0, 0, 0];
      for (let i = 0; i < this.timeList.length; i++) {
        if (i >= 1 && i <= 4) {
          tmp[0] += this.timeList[i];
        } else if (i >= 5 && i <= 8) {
          tmp[1] += this.timeList[i];
        } else if (i >= 9 && i <= 12) {
          tmp[2] += this.timeList[i];
        } else if (i >= 13 && i <= 16) {
          tmp[3] += this.timeList[i];
        } else if (i >= 17 && i <= 20) {
          tmp[4] += this.timeList[i];
        } else {
          tmp[5] += this.timeList[i];
        }
      }
      this.timeDataset = tmp;
      this.isFinished = true;
    },
    getTodoList(value) {
      if (value) {
        this.getPickedDate(this.pickedDate);
      }
    },
    getDisturbingCause(value) {
      console.log(value);
      let tmpDC = [0, 0, 0, 0, 0];

      this.$Axios
        .create({
          headers: { 'jwt-auth-token': storage.getItem('jwt-auth-token') },
        })
        .get(`timer/not-study?datetime=2021-04-08`)
        .then((response) => {
          if (response.data.disturbingCause.length != 0) {
            for (let i = 0; i < response.data.disturbingCause.length; i++) {
              if (response.data.disturbingCause[i].timeDailyAction == 1) {
                tmpDC[0] += 1;
              } else if (
                response.data.disturbingCause[i].timeDailyAction == 2
              ) {
                tmpDC[1] += 1;
              } else if (
                response.data.disturbingCause[i].timeDailyAction == 3
              ) {
                tmpDC[2] += 1;
              } else if (
                response.data.disturbingCause[i].timeDailyAction == 4
              ) {
                tmpDC[3] += 1;
              } else {
                console.log('공부 Action');
              }
            }
            this.causeCntSet = tmpDC;
          }
          this.isFinished2 = true;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    goBeforeDay() {
      console.log('구현중');
      // var tmpDate = new Date();
      // console.log('===========================');
      // console.log(tmpDate);
      // console.log(tmpDate.getDate() - 1);
      // console.log(tmpDate.setDate(tmpDate.getDate() - 1));
    },
    goAfterDay() {
      console.log('구현중');
      // let tmp = new Date();
      // this.dateForFocusChart = date.dateFunc(
      //   tmp.setDate(tmp.getDate() + this.flagDay)
      // );
      // console.log(this.dateForFocusChart);
      // this.flagDay++;
    },
  },
};
</script>

<style></style>
