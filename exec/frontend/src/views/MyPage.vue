<template>
  <div>
    <middle-nav v-bind:propsdata="navInfo"></middle-nav>
    <v-container style="padding-top: 3%; ">
      <!-- 상단 정보 -->
      <v-row no-gutters style="font-size: 1.8rem;" justify="center">
        회원정보
      </v-row>
      <v-row no-gutters justify="center" style="margin-top: 5px;">
        <hr
          style="width: 80%; border: 0; height: 2px; background-color: black; "
        />
      </v-row>
      <!-- 프로필 사진 -->
      <v-row no-gutters justify="center" style="margin-top: 40px;">
        <profile-large
          :src="'data:image/png;base64,' + user.profileImg"
        ></profile-large>
      </v-row>
      <!-- 기타 정보 -->
      <div style="padding-left: 15%; padding-right: 15%; margin-top: 50px;">
        <v-row class="mypage-item">
          <v-col class="mypage-key" cols="3">닉네임</v-col>
          <v-col class="mypage-value">
            {{ user.nickname }}
          </v-col>
        </v-row>
        <hr style="border: 0; height: 1px; background-color: #d9d9d9; " />
        <v-row class="mypage-item">
          <v-col class="mypage-key" cols="3">아이디(이메일)</v-col>
          <v-col class="mypage-value">
            {{ user.userId }}
          </v-col>
        </v-row>
        <hr style="border: 0; height: 1px; background-color: #d9d9d9; " />
        <v-row class="mypage-item">
          <v-col class="mypage-key" cols="3">상태 메시지</v-col>
          <v-col class="mypage-value">
            {{ user.message }}
          </v-col>
        </v-row>
        <hr style="border: 0; height: 1px; background-color: #d9d9d9; " />
      </div>
      <v-row no-gutters justify="center" style="margin-top: 70px;">
        <v-col @click="goRouting" cols="2">
          <app-btn-large
            :btnColor="'#673fb4'"
            :btnName="'수 정'"
            :btnNameColor="'white'"
          ></app-btn-large>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>
import MiddleNav from '../components/include/MiddleNav.vue';
import ProfileLarge from '@/components/common/ProfileLarge.vue';
import AppBtnLarge from '@/components/common/AppBtnLarge.vue';

const storage = window.sessionStorage;
export default {
  components: {
    'middle-nav': MiddleNav,
    'profile-large': ProfileLarge,
    'app-btn-large': AppBtnLarge,
  },
  created: function() {
    this.$Axios
      .create({
        headers: { 'jwt-auth-token': storage.getItem('jwt-auth-token') },
      })
      .get('user')
      .then((response) => {
        this.user.nickname = response.data.data.userNickname;
        this.user.userId = response.data.data.userId;
        this.user.message = response.data.data.userMessage;
        this.user.profileImg = response.data.profileImg;
      })
      .catch((error) => {
        console.log(error);
      });
  },
  data: function() {
    return {
      navInfo: [
        'sample2.png',
        '마이페이지',
        '첫번째 문장입니다. 첫번째 문장입니다. 첫번째 문장입',
        '두번째 문장입니다~! 두번째 문장입니다~! 두번째 문장입니다~! 두번째',
      ],
      user: {
        nickname: '',
        userId: '',
        message: '',
        profileImg: '',
      },
    };
  },
  methods: {
    goRouting: function() {
      this.$router.push('/my-page-modify');
    },
  },
};
</script>

<style scoped>
.mypage-item {
  margin-left: 0;
  margin-right: 0;
  margin-top: 20px;
  margin-bottom: 20px;
  letter-spacing: -1px;
}
.mypage-key {
  font-size: 1.1rem;
  font-weight: bold;
}
.mypage-value {
  font-size: 1rem;
  color: gray;
}
</style>
