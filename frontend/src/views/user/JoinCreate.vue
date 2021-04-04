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
      <v-form ref="form" lazy-validation>
        <v-row>
          <v-col cols="4" class="formLetter">
            아이디(이메일)
          </v-col>
          <v-col cols="7">
            <input-bar :isDisabled="true" :placeholder="email"></input-bar>
          </v-col>
          <v-col cols="1"></v-col>
        </v-row>
        <v-row>
          <v-col cols="4" class="formLetter">닉네임</v-col>
          <v-col cols="7">
            <input-bar
              :placeholder="'8자 이내로 입력해주세요.'"
              :rules="nicknameRules"
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
                :isDisabled="!isValidNickname"
              >
              </app-btn-middle>
            </div>
          </v-col>
        </v-row>
        <v-row>
          <v-col cols="4" class="formLetter" v-model="password">비밀번호</v-col>
          <v-col cols="7">
            <!-- <v-text-field ref="password" :rules="passwordRules"></v-text-field> -->
            <input-bar
              :placeholder="'문자, 숫자 8자리 이상입니다.'"
              :rules="passwordRules"
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
              :rules="passwordConfirmRules"
              :type="'password'"
            ></input-bar>
          </v-col>
          <v-col cols="1"></v-col>
        </v-row>
      </v-form>
    </v-container>
    <div style="text-align:center;">
      <div class="oneBtn" @click="$router.push('/join/join-auth')">
        <app-btn-middle
          :btnColor="'#FAFAFA'"
          :btnName="'이전'"
          :btnNameColor="'#424242'"
        ></app-btn-middle>
      </div>
      <div class="oneBtn" @click="joinRequest">
        <app-btn-middle
          :isDisabled="
            isMounted && (!isAllValid || !isduplComplete || !isValidNickname)
          "
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
  created: function() {
    this.$emit('move-step', 3);
    let emailF = this.$route.query.emailF;
    let emailB = this.$route.query.emailB;
    this.email = emailF + '@' + emailB;
  },
  mounted: function() {
    this.isMounted = true;
  },
  data: function() {
    return {
      email: '',
      userNickname: '',
      password: '',
      passwordConfirm: '',
      isduplComplete: false,
      isValidNickname: false,
      isMounted: false,
      // isAllValid: false,
      nicknameRules: [
        // (v) => v == null,
        (v) => !!v || '닉네임을 입력해주세요.',
        (v) => (v && v.length <= 8) || '닉네임은 8자 이하로 입력해주세요.',
      ],
      passwordRules: [
        (v) => !!v || '비밀번호를 입력해주세요.',
        (v) => (v && v.length >= 8) || '비밀번호는 8자 이상으로 입력해주세요.',
        (v) => /(?=.*[A-Za-z])/.test(v) || '문자와 숫자를 꼭 포함해주세요.',
        (v) => /(?=.*\d)/.test(v) || '문자와 숫자를 꼭 포함해주세요.',
      ],
      passwordConfirmRules: [
        (v) => !!v || '비밀번호를 입력해주세요.',
        (v) => v == this.password || '비밀번호와 일치해야해요.',
      ],
    };
  },
  computed: {
    isAllValid: function() {
      if (this.$refs.form.validate()) {
        return true;
      } else {
        return false;
      }
    },
  },
  watch: {
    userNickname: function() {
      if (this.userNickname == 0 || this.userNickname.length > 8) {
        this.isValidNickname = false;
        this.isduplComplete = false;
        // this.isAllValid = false;
      } else {
        this.isValidNickname = true;
        this.isduplComplete = false;
        // this.isAllValid = false;
      }
    },
  },
  methods: {
    getNickname: function(value) {
      this.userNickname = value;
    },
    getPassword: function(value) {
      this.password = value;
    },
    joinRequest: function() {
      if (this.isAllValid) {
        axios
          .post('user/signup', {
            userId: this.email,
            userMessage: '',
            userNickname: this.userNickname,
            userPassword: this.password,
          })
          .then((response) => {
            if (response.data.success) {
              this.$emit('move-forward');
              this.$router.push('/join/join-complete');
            } else {
              alert('회원가입 도중 문제가 생겼습니다.');
              this.$router.push('/');
            }
          })
          .catch((error) => {
            console.log(error);
          });
      }
      // if (this.$refs.form.validate()) {
      //   alert(this.$refs.form.validate());
      // } else {
      //   alert(this.$refs.form.validate());
      // }
    },
    duplChk: function() {
      if (this.isValidNickname) {
        axios
          .get('user/nickname?userNickname=' + this.userNickname)
          .then((response) => {
            if (response.data.isPresent) {
              alert('이미 존재하는 닉네임입니다.');
            } else {
              alert('사용할 수 있는 닉네임입니다.');
              this.isduplComplete = true;
            }
          })
          .catch((error) => {
            console.log(error);
          });
      }
    },
  },
};
</script>

<style></style>
