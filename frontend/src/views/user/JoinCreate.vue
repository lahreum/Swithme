<template>
  <div class="joinForm">
    <div>
      <p style="text-align:center;padding:20px;">
        (주)스윗미에서는 익명 사용자로 인한 피해를 방지하고자 [정보통신망
        이용촉진 및 정보보호 등에 관한 법률 제 23조의 2제 1항] '주민등록번호의
        사용 제한'에 의거하여 주민등록번호 사용 및 수집을 중단하고 휴대전화
        인증을 통해 본인을 확인하고 있습니다.<br /><br />
      </p>
    </div>
    <!-- <v-form class="signup">
        <div class="outBox">
          <p class="inBox">아이디(이메일)</p>
          <input-bar class="inBox longInput"></input-bar>
        </div>
        <div class="outBox">
          <p class="inBox">이름</p>
          <input-bar class="inBox longInput"></input-bar>
        </div>
        <div class="outBox">
          <p class="inBox">닉네임</p>
          <input-bar class="inBox longInput"></input-bar>
          <app-btn-middle class="inBox" :btnColor="'#673fb4'" :btnName="'중복체크'" :btnNameColor="'#ffffff'"></app-btn-middle>
        </div>
        <div class="outBox">
          <p class="inBox">비밀번호</p>
          <input-bar class="inBox longInput"></input-bar>
        </div>
        <div class="outBox">
          <p class="inBox">비밀번호 확인</p>
          <input-bar class="inBox longInput"></input-bar>
        </div>
      </v-form> -->
    <v-container class="joinForm">
      <v-row>
        <v-col cols="4" class="formLetter">
          아이디(이메일)
        </v-col>
        <v-col cols="7"><input-bar></input-bar></v-col>
        <v-col cols="1"></v-col>
      </v-row>
      <v-row>
        <v-col cols="4" class="formLetter">닉네임</v-col>
        <v-col cols="7">
          <input-bar
            :placeholder="'8자 이내로 입력해주세요.'"
            @pass-input="getNickname"
          >
          </input-bar>
        </v-col>
        <v-col cols="1">
          <div @click="duplChk">
            <app-btn-middle
              class="inBox"
              :btnColor="'#673fb4'"
              :btnName="'중복체크'"
              :btnNameColor="'#ffffff'"
            >
            </app-btn-middle>
          </div>
        </v-col>
      </v-row>
      <v-row>
        <v-col cols="4" class="formLetter" v-model="password">비밀번호</v-col>
        <v-col cols="7">
          <input-bar
            :placeholder="'문자, 숫자 8자리 이상입니다.'"
            :type="'password'"
            @pass-input="getPassword"
          >
          </input-bar>
        </v-col>
        <v-col cols="1"></v-col>
      </v-row>
      <v-row>
        <v-col cols="4" class="formLetter" v-model="passwordConfirm"
          >비밀번호 확인</v-col
        >
        <v-col cols="7">
          <input-bar
            :placeholder="'다시 한 번 더 입력해주세요.'"
            :type="'password'"
          ></input-bar>
        </v-col>
        <v-col cols="1"></v-col>
      </v-row>
    </v-container>
    <div style="text-align:center;">
      <div class="oneBtn" @click="$router.push('/join/join-auth')">
        <app-btn-middle
          :btnColor="'#FAFAFA'"
          :btnName="'이전'"
          :btnNameColor="'#424242'"
        ></app-btn-middle>
      </div>
      <div class="oneBtn" @click="$router.push('/join/join-complete')">
        <app-btn-middle
          :btnColor="'#424242'"
          :btnName="'가입하기'"
          :btnNameColor="'white'"
        ></app-btn-middle>
      </div>
    </div>
  </div>
</template>

<script>
import './user.css';
import InputBar from '@/components/common/InputBar.vue';
import AppBtnMiddle from '@/components/common/AppBtnMiddle.vue';
import axios from 'axios';

export default {
  components: {
    'input-bar': InputBar,
    'app-btn-middle': AppBtnMiddle,
  },
  data: function() {
    return {
      email: '',
      nickname: '',
      password: '',
      passwordConfirm: '',
      passwordRules: [
        (v) => !!v || '비밀번호를 입력해주세요.',
        (v) => (v && v.length >= 8) || '비밀번호는 8자 이상으로 입력해주세요.',
        (v) => /(?=.*[A-Za-z])/.test(v) || '문자도 포함해볼까요?',
        (v) => /(?=.*\d)/.test(v) || '숫자를 꼭 포함해야 해요!',
      ],
    };
  },
  methods: {
    getNickname: function(value) {
      this.nickname = value;
    },
    getPassword: function(value) {
      this.password = value;
    },
    duplChk: function() {
      axios
        .get('http://localhost:9999/user/nickname', this.nickname)
        .then((response) => {
          console.log(response);
        });
    },
  },
};
</script>

<style></style>
