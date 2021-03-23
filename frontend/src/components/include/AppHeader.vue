<template>
  <header>
    <v-row no-gutters align="center" style="height: 100px;">
      <v-col cols="2" class="logo">
        <img
          src="../assets/img/logo_bl.png"
          alt="swithme-logo"
          style="width: 75%; height: auto;"
        />
      </v-col>
      <v-col cols="8">
        <v-row no-gutters align="center">
          <div class="menu-item">튜토리얼</div>
          <div class="menu-item">공부하러가기</div>
          <div class="menu-item">나의학습</div>
          <div class="menu-item">랭킹</div>
          <div class="menu-item">그룹</div>
          <div class="menu-item">커뮤니티</div>
          <div class="menu-item"></div>
        </v-row>
      </v-col>
      <!-- 로그인, 프로필 영역 -->
      <v-col cols="2">
        <v-row no-gutters v-if="!isLogin" justify="center">
          <div class="login-item" @click="signIn">로그인</div>
          <div class="login-item">|</div>
          <div class="login-item">회원가입</div>
        </v-row>
        <v-row no-gutters v-else>
          <!-- 여기에 프로필 사진 컴포넌트 넣어야 함 -->
          <v-col align="center" @click="showPop">
            {{ username }}님, 안녕하세요!
          </v-col>
          <!-- 이 밑 코드는 로그아웃 보여주기 위한 임시 코드임 -->
          <v-btn color="success" v-if="flag" @click="signOut">로그아웃</v-btn>
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
      flag: false,
    };
  },
  methods: {
    signIn: function() {
      this.isLogin = true;
    },
    signOut: function() {
      this.flag = false;
      this.isLogin = false;
    },
    showPop: function() {
      this.flag = !this.flag;
    },
  },
};
</script>

<style>
.menu-item {
  float: left;
  margin-left: 40px;
  letter-spacing: -1px;
  font-size: 1.2rem;
}
.login-item {
  float: right;
  margin-right: 20px;
  letter-spacing: -1px;
  font-size: 1rem;
}
.logo {
  display: flex;
  justify-content: center;
}
</style>
