<template>
  <v-container style="margin-left: 15%; margin-right: 15%;">
    <v-row
      no-gutters
      style="padding-top: 10%; padding-bottom: 10%;"
      justify="center"
    >
      <v-col cols="5" align="end" align-self="center">
        <img src="@/assets/img/character3.png" alt="swithme_캐릭터" />
      </v-col>
      <v-col align-self="center" style="margin-left: 20px;">
        <v-row no-gutters style="font-size: 1.8rem; letter-spacing: -1px;">
          앗, 소셜로그인을 해주셨네요!
        </v-row>
        <v-row no-gutters style="font-size: 1rem; letter-spacing: -1px;">
          잠시만요! 스윗미에서는 소셜로그인 사용자에게<br />
          개인정보보호를 위한 닉네임을 등록할 것을 권유하고 있습니다.<br />
          나의 개성을 표현할 수 있는 멋진 닉네임을 제게 알려주세요 :)
        </v-row>
        <v-form ref="nickname">
          <v-row no-gutters style="margin-top: 20px;">
            <v-col cols="7">
              <input-bar
                :rules="nicknameRules"
                :placeholder="'8자 이내의 문자 입력'"
                @pass-input="getNickname"
              ></input-bar>
            </v-col>
          </v-row>
        </v-form>
        <v-row no-gutters style="margin-top: 20px;">
          <v-col cols="7" align="end">
            <span @click="setNickname">
              <app-btn-middle
                v-bind:btn-color="'#673FB4'"
                v-bind:btn-name="'이걸로 할래요!'"
                v-bind:btn-name-color="'white'"
              >
              </app-btn-middle>
            </span>
          </v-col>
        </v-row>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import axios from 'axios';
import InputBar from '@/components/common/InputBar.vue';
import AppBtnMiddle from '@/components/common/AppBtnMiddle.vue';

const storage = window.sessionStorage;

export default {
  components: {
    'input-bar': InputBar,
    'app-btn-middle': AppBtnMiddle,
  },
  data() {
    return {
      tmpToken: this.$route.params.tmpToken,
      nickname: '',
      nicknameRules: [
        // (v) => v == null,
        (v) => !!v || '닉네임을 입력해주세요.',
        (v) => (v && v.length <= 8) || '닉네임은 8자 이하로 입력해주세요.',
      ],
    };
  },
  methods: {
    setNickname() {
      if (this.$refs.nickname.validate()) {
        axios
          .get('user/nickname?userNickname=' + this.nickname)
          .then((response) => {
            if (response.data.isPresent) {
              alert('이미 존재하는 닉네임입니다. 다시 입력해주세요.');
            } else {
              this.setNickname2();
            }
          })
          .catch((error) => {
            console.log(error);
          });
      } else {
        alert('닉네임은 8글자 이내로 작성해주세요.');
      }
    },
    setNickname2() {
      axios
        .post('user/signup-social', {
          nickname: this.nickname,
          token: this.tmpToken,
        })
        .then((response) => {
          if (response.data.success) {
            alert('닉네임 설정이 완료되었습니다. 환영해요!');
            storage.setItem('jwt-auth-token', response.data.token);
            this.$router.push('/');
          }
        })
        .catch((error) => {
          console.log(error);
        });
    },

    getNickname(value) {
      this.nickname = value;
    },
  },
};
</script>

<style></style>
