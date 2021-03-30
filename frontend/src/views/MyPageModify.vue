<template>
  <div>
    <middle-nav v-bind:propsdata="navInfo"></middle-nav>
    <v-container style="padding-top: 3%; ">
      <!-- 상단 정보 -->
      <v-row no-gutters style="font-size: 1.8rem;" justify="center">
        회원정보 수정
      </v-row>
      <v-row no-gutters justify="center" style="margin-top: 5px;">
        <hr
          style="width: 80%; border: 0; height: 2px; background-color: black; "
        />
      </v-row>
      <!-- 프로필 사진 -->
      <v-row
        no-gutters
        justify="center"
        style="margin-top: 40px; margin-left: 40px;"
      >
        <profile-large
          :src="require(`@/assets/img/avatars/iu.jpg`)"
        ></profile-large>
        <v-btn
          class="btn-profile-edit"
          depressed
          fab
          dark
          small
          color="#673fb4"
          @click="modifyPic"
        >
          <v-icon dark>mdi-pencil</v-icon>
        </v-btn>
      </v-row>
      <!-- 기타 정보 -->
      <div style="padding-left: 15%; padding-right: 15%; margin-top: 50px;">
        <!-- 닉네임 -->
        <v-row class="mypage-item" style="margin-top: 30px;">
          <v-col class="mypage-key" cols="3">닉네임</v-col>
          <v-col class="pa-0">
            <v-row no-gutters>
              <v-col class="mypage-value pa-0" style="margin-right: 10px;">
                <input-bar></input-bar>
              </v-col>
              <v-col class="pa-0" cols="2" align="center">
                <div @click="isDuplicated" style="width: 100px; ">
                  <app-btn-large
                    :btnColor="'#673fb4'"
                    :btnName="btnStatus"
                    :btnNameColor="'white'"
                  ></app-btn-large>
                </div>
              </v-col>
            </v-row>
          </v-col>
        </v-row>
        <hr style="border: 0; height: 1px; background-color: #d9d9d9; " />
        <!-- 아이디(이메일) -->
        <v-row class="mypage-item" style="margin-top: 30px;">
          <v-col class="mypage-key" cols="3">아이디(이메일)</v-col>
          <v-col class="mypage-value">
            {{ user.email }}
          </v-col>
        </v-row>
        <hr style="border: 0; height: 1px; background-color: #d9d9d9; " />
        <!-- 비밀번호 -->
        <v-row class="mypage-item" style="margin-top: 30px;">
          <v-col class="mypage-key" cols="3">비밀번호</v-col>
          <v-col class="pa-0">
            <v-row no-gutters>
              <v-col class="mypage-value pa-0" style="margin-right: 10px;">
                <input-bar></input-bar>
              </v-col>
              <v-col class="pa-0" cols="2" align="center">
                <div style="width: 100px; "></div>
              </v-col>
            </v-row>
            <v-row no-gutters>
              <v-col class="mypage-value pa-0" style="margin-right: 10px;">
                <input-bar></input-bar>
              </v-col>
              <v-col class="pa-0" cols="2" align="center">
                <div style="width: 100px; ">
                  <app-btn-large
                    :btnColor="'#673fb4'"
                    :btnName="'저 장'"
                    :btnNameColor="'white'"
                  ></app-btn-large>
                </div>
              </v-col>
            </v-row>
          </v-col>
        </v-row>
        <hr style="border: 0; height: 1px; background-color: #d9d9d9; " />
        <!-- 상태 메시지 -->
        <v-row class="mypage-item" style="margin-top: 30px;">
          <v-col class="mypage-key" cols="3">상태 메시지</v-col>
          <v-col class="pa-0">
            <v-row no-gutters>
              <v-col class="mypage-value pa-0" style="margin-right: 10px;">
                <input-bar></input-bar>
              </v-col>
              <v-col class="pa-0" cols="2" align="center">
                <div @click="isDuplicated" style="width: 100px; ">
                  <app-btn-large
                    :btnColor="'#673fb4'"
                    :btnName="'저 장'"
                    :btnNameColor="'white'"
                  ></app-btn-large>
                </div>
              </v-col>
            </v-row>
          </v-col>
        </v-row>
        <hr style="border: 0; height: 1px; background-color: #d9d9d9; " />
      </div>
      <v-row no-gutters justify="center" style="margin-top: 50px;">
        <v-col
          cols="2"
          @click="goRouting"
          @mouseover="warningClose = true"
          @mouseleave="warningClose = false"
        >
          <app-btn-large
            :btnColor="'#424242'"
            :btnName="'닫 기'"
            :btnNameColor="'white'"
          ></app-btn-large>
        </v-col>
      </v-row>
      <v-row
        no-gutters
        justify="center"
        style="height: 30px; letter-spacing: -1px; color: red; font-size: 0.8rem; margin-top: 5px;"
        v-show="warningClose"
      >
        변경사항을 각각 저장하고 닫기를 눌러주세요.
      </v-row>
      <v-row
        no-gutters
        style="height: 30px; margin-top: 5px;"
        v-show="!warningClose"
      ></v-row>
    </v-container>
  </div>
</template>

<script>
import MiddleNav from '../components/include/MiddleNav.vue';
import ProfileLarge from '@/components/common/ProfileLarge.vue';
import AppBtnLarge from '@/components/common/AppBtnLarge.vue';
import InputBar from '@/components/common/InputBar.vue';

export default {
  components: {
    'middle-nav': MiddleNav,
    'profile-large': ProfileLarge,
    'app-btn-large': AppBtnLarge,
    'input-bar': InputBar,
  },
  data: function() {
    return {
      navInfo: [
        'sample1.jpg',
        '마이페이지',
        '첫번째 문장입니다. 첫번째 문장입니다. 첫번째 문장입',
        '두번째 문장입니다~! 두번째 문장입니다~! 두번째 문장입니다~! 두번째',
      ],
      user: {
        nickname: 'default',
        email: 'default@default.com',
        message: 'default, default, default.',
      },
      btnStatus: '중복확인',
      warningClose: false,
    };
  },
  methods: {
    goRouting: function() {
      this.$router.push('/my-page');
    },
    modifyPic: function() {
      alert('사진 수정!!!!!!');
    },
    isDuplicated: function() {
      this.btnStatus = '저 장';
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
.btn-profile-edit {
  position: relative;
  left: -45px;
  top: 150px;
}
</style>
