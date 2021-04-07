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
                <app-calendar></app-calendar>
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
                  <todo-list></todo-list>
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
          <div style="width: 800px; min-width: 800px;">
            <v-row justify="center" style="background-color: #E4F7BA;">
              (공부시간 막대그래프 자리)
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
              <v-col cols="10">주요 공부 시간대</v-col>
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
                <v-row justify="center" style="background-color: #D4F4FA;">
                  (주요 공부시간대 원그래프 자리)
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
              <v-col cols="10">집중 지속시간</v-col>
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
              <div style="min-width: 380px;">
                <v-row justify="center" style="background-color: #FAF4C0;">
                  (집중 지속시간 바코드 그래프 자리)
                </v-row>
              </div>
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
              <div style="min-width: 380px;">
                <v-row justify="center" style="background-color: #FAE0D4;">
                  (그룹 내 공부시간 순위 자리)
                </v-row>
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
            <v-row no-gutters justify="center" style="margin-top: 50px;">
              <div style="min-width: 380px;">
                <v-row justify="center" style="background-color: #FFD9EC;">
                  (주요 방해요인 원그래프 자리)
                </v-row>
              </div>
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
import date from '@/date.js';

const storage = window.sessionStorage;
export default {
  components: {
    'middle-nav': MiddleNav,
    'my-info': MyInfo,
    'todo-list': TodoList,
    'app-calendar': AppCalendar,
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

    // 공부 시간 받아오기
    let today = new Date();
    let day = date.dateFunc(today);

    this.$Axios
      .create({
        headers: {
          'jwt-auth-token': storage.getItem('jwt-auth-token'),
        },
      })
      .get('timer/today?datetime=' + day)
      .then((response) => {
        // console.log('RESPONSEEEEEEE!!!!', response);
        if (
          response.data.todayStudyTime == null ||
          response.data.todayStudyTime === 0
        ) {
          this.user.todayStudyTime = '0:00:00';
          // console.log('TYPE♡♡♡♡♡♡', typeof response.data.todayStudyTime);
        } else {
          // 이거 확실하게 int로 받아오는 거 맞나요ㅜ
          this.user.todayStudyTime = response.data.todayStudyTime;
        }
      })
      .catch((error) => {
        console.log(error);
      });
  },
  data: function() {
    return {
      navInfo: [
        'sample1.jpg',
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
      },
    };
  },
};
</script>

<style></style>
