<template>
  <header>
    <v-row no-gutters align="center" style="height: 100px;">
      <v-col cols="2" class="logo-header">
        <img
          src="../../assets/img/logo_wh.png"
          alt="swithme-logo"
          style="width: 75%; height: auto;"
          @click="goMain"
        />
      </v-col>
      <v-col cols="8">
        <v-row no-gutters align="center">
          <router-link class="menu-item-wh" to="/tutorial">
            튜토리얼
          </router-link>
          <router-link class="menu-item-wh" to="/timer">
            공부하러가기
          </router-link>
          <router-link class="menu-item-wh" to="/mystudy">
            나의학습
          </router-link>
          <router-link class="menu-item-wh" to="/ranking"> 랭킹 </router-link>
          <router-link class="menu-item-wh" to="/group"> 그룹 </router-link>
          <router-link class="menu-item-wh" to="/community">
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
          style="margin-left: 60px; color: white;"
        >
          <div class="login-item-wh" @click="signIn">로그인</div>
          <div class="login-item-wh">|</div>
          <div class="login-item-wh">회원가입</div>
        </v-row>
        <v-row no-gutters v-else style="margin-left: 60px; color: white;">
          <v-menu
            open-on-hover
            close-on-click
            offset-y
            transition="slide-y-transition"
            bottom
          >
            <template v-slot:activator="{ on }">
              <!-- 이 위치에 프로필 사진 컴포넌트 넣어야 함 -->
              <v-col align="center" v-on="on">
                {{ username }}님, 안녕하세요!
              </v-col>
            </template>
            <v-list>
              <v-list-item>
                <v-list-item-title @click="goMyPage">
                  마이페이지
                </v-list-item-title>
              </v-list-item>
              <v-list-item>
                <v-list-item-title @click="signOut">
                  로그아웃
                </v-list-item-title>
              </v-list-item>
            </v-list>
          </v-menu>
        </v-row>
      </v-col>
    </v-row>
  </header>
</template>

<script>
export default {
  data: function() {
    return {
      isLogin: false,
      username: 'default',
    };
  },
  methods: {
    signIn: function() {
      this.isLogin = true;
    },
    signOut: function() {
      alert('로그아웃!!!!');
      this.isLogin = false;
    },
    goMyPage: function() {
      this.$router.push('/my-page-access');
    },
    goMain: function() {
      this.$router.push('/');
    },
  },
};
</script>

<style>
.menu-item-wh {
  margin-left: 40px;
  letter-spacing: -1px;
  font-size: 1.2rem;
  color: white !important;
  /* display: inline-block; */
  text-decoration: none;
}
.login-item-wh {
  float: right;
  margin-right: 20px;
  letter-spacing: -1px;
  font-size: 1rem;
  color: white !important;
}
.logo-header {
  display: flex;
  justify-content: center;
}
</style>
