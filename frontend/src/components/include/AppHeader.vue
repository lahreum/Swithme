<template>
  <header>
    <v-row no-gutters align="center" style="height: 100px">
      <v-col cols="2" class="logo-header">
        <img
          :src="
            darkmode
              ? require('@/assets/img/logo_wh.png')
              : require('@/assets/img/logo_bl.png')
          "
          style="width: 75%; height: auto"
          alt="swithme-logo"
          @click="goMain"
        />
      </v-col>
      <v-col cols="8">
        <v-row no-gutters align="center">
          <router-link
            class="menu-item"
            to="/tutorial"
            :class="{ 'white-text': darkmode, 'black-text': !darkmode }"
          >
            튜토리얼
          </router-link>
          <router-link
            class="menu-item"
            to="/timer"
            :class="{ 'white-text': darkmode, 'black-text': !darkmode }"
          >
            공부하러가기
          </router-link>
          <router-link
            class="menu-item"
            to="/my-study"
            :class="{ 'white-text': darkmode, 'black-text': !darkmode }"
          >
            나의학습
          </router-link>
          <router-link
            class="menu-item"
            to="/ranking"
            :class="{ 'white-text': darkmode, 'black-text': !darkmode }"
          >
            랭킹
          </router-link>
          <router-link
            class="menu-item"
            to="/group"
            :class="{ 'white-text': darkmode, 'black-text': !darkmode }"
          >
            그룹
          </router-link>
          <router-link
            class="menu-item"
            to="/community"
            :class="{ 'white-text': darkmode, 'black-text': !darkmode }"
          >
            커뮤니티
          </router-link>
        </v-row>
      </v-col>
      <!-- 로그인, 프로필 영역 -->
      <v-col cols="2">
        <v-row
          no-gutters
          v-if="!isLogin"
          justify="center"
          style="margin-left: 60px"
        >
          <div
            style="cursor: pointer"
            class="login-item"
            @click="openLogin"
            :class="{ 'white-text': darkmode, 'black-text': !darkmode }"
          >
            로그인
          </div>
          <div
            class="login-item"
            :class="{ 'white-text': darkmode, 'black-text': !darkmode }"
          >
            |
          </div>
          <router-link to="/join">
            <div
              class="login-item"
              :class="{ 'white-text': darkmode, 'black-text': !darkmode }"
            >
              회원가입
            </div>
          </router-link>
        </v-row>

        <v-row no-gutters v-else style="margin-left: 60px">
          <v-menu
            open-on-hover
            close-on-click
            offset-y
            transition="slide-y-transition"
            bottom
          >
            <template v-slot:activator="{ on }">
              <v-col cols="2">
                <profile-small
                  :src="'data:image/png;base64,' + userInfo.profileImg"
                ></profile-small>
              </v-col>
              <v-col
                style="cursor: pointer"
                align="start"
                align-self="center"
                v-on="on"
                :class="{ 'white-text': darkmode, 'black-text': !darkmode }"
              >
                {{ userNickname }}님, 안녕하세요!
              </v-col>
            </template>
            <v-list>
              <v-list-item>
                <v-list-item-title style="cursor: pointer" @click="goMyPage">
                  마이페이지
                </v-list-item-title>
              </v-list-item>
              <v-list-item>
                <v-list-item-title style="cursor: pointer" @click="signOut">
                  로그아웃
                </v-list-item-title>
              </v-list-item>
            </v-list>
          </v-menu>
        </v-row>
      </v-col>
    </v-row>
    <!-- 로그인 모달팝업 -->
    <div style="width: 50%" @click="openLogin">
      <!-- 버튼도 v-dialog와 동시에 출력되도록 수정필요 -->
      <!-- <v-btn
        fab
        dark
        small
        color="#BDBDBD" v-if="dialog" @click="dialog = false"
        > -->
      <!-- </v-btn> -->
      <v-dialog v-model="dialog" max-width="420" persistent>
        <v-card class="round" max-width="420">
          <div class="cancelBtn">
            <v-icon
              class="cancelBtn float-right"
              dark
              color="#BDBDBD"
              v-if="dialog"
              @click="dialog = false"
            >
              mdi-close
            </v-icon>
          </div>
          <v-card-title>
            <p class="headLine">LOGIN</p>
          </v-card-title>
          <!-- 이메일, 비밀번호 입력란 -->
          <v-card-text style="padding-bottom: 0px">
            <hr style="border: solid 1px #e0e0e0" />
            <br />

            <v-container>
              <v-row>
                <v-col class="middleLetter formLetter" cols="3">이메일</v-col>
                <v-col cols="9"
                  ><input-bar
                    @pass-input="getEmail"
                    placeholder="이메일"
                  ></input-bar
                ></v-col>
              </v-row>
              <v-row>
                <v-col class="middleLetter formLetter" cols="3">비밀번호</v-col>
                <v-col cols="9"
                  ><input-bar
                    @pass-input="getPw"
                    placeholder="비밀번호"
                  ></input-bar
                ></v-col>
              </v-row>
            </v-container>
          </v-card-text>

          <v-card-actions style="padding-bottom: 0px; padding-top: 0px">
            <div class="buttonGroup">
              <div @click="login">
                <app-btn-large
                  class="btnOption"
                  :btnColor="'#673fb4'"
                  :btnName="'로그인'"
                  :btnNameColor="'white'"
                ></app-btn-large>
              </div>
              <!-- <a href="https://j4b103.p.ssafy.io/service/oauth/google" -->
              <a href="http://localhost:9999/oauth/google"
                ><v-img
                  @click="dialog = false"
                  src="@/assets/img/google_long.png"
                  style="margin-top: 5px"
                  width="380"
                ></v-img
              ></a>
              <!-- <a href="https://j4b103.p.ssafy.io/service/oauth/naver"> -->
              <a href="http://localhost:9999/oauth/naver">
                <v-img
                  @click="dialog = false"
                  src="@/assets/img/naver_long.png"
                  width="380"
                  style="margin-bottom: 100px"
                ></v-img>
              </a>
              <div class="bottomOption">
                <v-img
                  src="@/assets/img/pattern.jpg"
                  style="width: 100%"
                ></v-img>
                <router-link
                  to="/find-pw"
                  style="text-decoration: none; color: #616161"
                  ><span style="margin-right: 40px"
                    >비밀번호 찾기</span
                  ></router-link
                >
                <span>|</span>
                <router-link
                  to="/join"
                  style="text-decoration: none; color: #616161"
                  ><span style="margin-left: 40px">회원가입</span></router-link
                >
              </div>
            </div>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </div>
  </header>
</template>

<script>
import '@/views/user/user.css';
import AppBtnLarge from '@/components/common/AppBtnLarge.vue';
import InputBar from '@/components/common/InputBar.vue';
import ProfileSmall from '@/components/common/ProfileSmall.vue';
import axios from 'axios';

const storage = window.sessionStorage;
export default {
  props: ['darkmode'],
  components: {
    'app-btn-large': AppBtnLarge,
    'input-bar': InputBar,
    'profile-small': ProfileSmall,
  },
  data: function () {
    return {
      isLogin: false,
      username: 'default',
      dialog: false,
      userInfo: {},
      email: '',
      pw: '',
      userNickname: '',
      recommendGroup: [],
      Rgroups: [],
    };
  },

  mounted() {
    // console.log('마운티드됨?');
    if (storage.getItem('jwt-auth-token')) {
      this.isLogin = true;
      this.getUserInfo(storage.getItem('jwt-auth-token'));
      // console.log(storage.getItem("jwt-auth-token"));
    }
  },

  methods: {
    checkLogin() {
      if (this.userInfo === null) {
        this.isLogin = false;
      } else {
        this.isLogin = true;
      }
    },
    signIn: function () {
      this.isLogin = true;
    },
    signOut: function () {
      alert('성공적으로 로그아웃 했습니다. 안녕히!');
      this.isLogin = false;
      storage.removeItem('jwt-auth-token');
      this.$store.commit('userInit');
      if (this.$router.currentRoute.path != '/') {
        this.$router.push('/');
      }
    },
    goMyPage: function () {
      if (this.userInfo.userType != null) {
        this.$router.push('/my-page');
      } else {
        this.$router.push('/my-page-access');
      }
    },
    goMain: function () {
      this.$router.push('/');
    },
    openLogin() {
      this.dialog = true;
    },
    getUserInfo(token) {
      axios
        .create({
          headers: {
            'jwt-auth-token': token,
          },
        })
        .get('user')
        .then((res) => {
          // console.log(res);
          // this.userInfo = res.data;

          this.userInfo = res.data.data;
          this.userInfo.profileImg = res.data.profileImg;
          this.$store.commit('LOGIN', this.userInfo);
          this.userNickname = this.$store.getters.getUserNickname;
          // console.log(this.userNickname);
          console.log('무야호', this.userInfo);
          this.profileImg = this.$store.getters.getUserImage;
          console.log(this.$store.getters.getUserImage);
          axios
            .create({
              headers: {
                'jwt-auth-token': storage.getItem('jwt-auth-token'),
              },
            })
            .get('group')
            .then((res) => {
              this.Rgroups = res.data.groupList;
              console.log(this.Rgroups);
              for (var i = 0; i < this.Rgroups.length; i++) {
                this.Rgroups[i]['src'] =
                  res.data.groupProfileList[i].groupProfileImg;
              }

              this.recommendGroup = this.Rgroups.filter(
                (group) => group.groupName.length > 4
              );
              this.recommendGroup = this.recommendGroup.slice(0, 6);
              console.log('zzzzz', this.recommendGroup);
              this.$store.commit('RECOMMENDGROUP', this.recommendGroup);
            })
            .catch((err) => {
              console.log(err);
            });
        })
        .catch((err) => {
          console.log(err);
        });
    },
    login() {
      var params = new URLSearchParams();
      params.append('userId', this.email);
      params.append('userPassword', this.pw);
      axios
        .post('user/login', params)
        .then((res) => {
          // console.log(res);
          if (res.data.success === true) {
            window.location.reload();
            this.dialog = false;
            this.isLogin = true;
            // console.log(res);
            this.getUserInfo(res.headers['jwt-auth-token']);
            storage.setItem('jwt-auth-token', res.headers['jwt-auth-token']);

            // console.log("유저닉네임", this.userNickname);
            // console.log(this.$store.state.user);

            // console.log("스토어", this.userNickname);
          } else {
            alert('아이디 또는 비밀번호를 잘못 입력하였습니다.');
          }
        })
        .catch((err) => console.log(err));
      // console.log(this.email);
      // console.log(this.pw);
    },
    getEmail(parm) {
      this.email = parm;
    },
    getPw(parm) {
      this.pw = parm;
    },
  },
};
</script>

<style>
.menu-item {
  margin-left: 40px;
  letter-spacing: -1px;
  font-size: 1.2rem;
  /* display: inline-block; */
  text-decoration: none;
}
.black-text {
  color: black !important;
}
.white-text {
  color: white !important;
}
.login-item {
  float: right;
  margin-right: 20px;
  letter-spacing: -1px;
  font-size: 1rem;
}
.logo-header {
  display: flex;
  justify-content: center;
  cursor: pointer;
}
.v-text-field.v-text-field--enclosed .v-text-field__details {
  display: none !important;
}
</style>
