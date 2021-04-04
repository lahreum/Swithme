<template>
  <v-row>
    <v-col style="margin-left: 500px; margin-right: 500px">
      <v-text-field v-model="nickname" label="닉네임"></v-text-field>
      <v-btn style="margin-right: 10px" @click="check()"
        >닉네임 중복 확인</v-btn
      >
      <v-btn :disabled="!valid" @click="submit()">회원가입</v-btn>
    </v-col>
  </v-row>
</template>

<script>
import axios from 'axios';

const storage = window.sessionStorage;

export default {
  data() {
    return {
      tmpToken: this.$route.params.tmpToken,
      nickname: '',
      valid: false,
    };
  },
  methods: {
    check() {
      // 프론트에서 닉네임 범위 판별해줘야함
      axios
        .get('user/nickname?nickname=' + this.nickname)
        .then((response) => {
          if (response.data.isPresent) {
            this.valid = false;
            alert('이미 존재하는 닉네임입니다. 다시 입력해주세요.');
          } else {
            this.valid = true;
            alert('사용할 수 있는 닉네임입니다.');
          }
        })
        .catch((error) => {
          console.log(error);
        });
    },
    submit() {
      axios
        .post('user/signup-social', {
          nickname: this.nickname,
          token: this.tmpToken,
        })
        .then((response) => {
          if (response.data.success) {
            alert('회원가입이 완료되었습니다.');
            storage.setItem('jwt-auth-token', response.data.token);
            this.$router.push('/');
          }
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
  watch: {
    nickname() {
      this.valid = false;
    },
  },
};
</script>

<style></style>
