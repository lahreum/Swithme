<template>
  <div>
    <v-container style="margin-left: 15%; margin-right: 15%;">
      <v-row no-gutters style="padding-top: 5%;" justify="center">
        <v-col cols="5" align="end" align-self="center">
          <img src="@/assets/img/character2.png" alt="swithme_캐릭터" />
        </v-col>
        <v-col align-self="center" style="margin-left: 20px;">
          <v-row no-gutters style="font-size: 1.8rem; letter-spacing: -1px;">
            환영합니다!
          </v-row>
          <v-row no-gutters style="font-size: 1rem; letter-spacing: -1px;">
            로그인을 위해 아이디(이메일)와 비밀번호를 입력해주세요.
          </v-row>
          <v-row no-gutters style="margin-top: 20px;">
            <v-col cols="7">
              <input-bar
                :placeholder="'아이디(이메일)입력'"
                @pass-input="getUserId"
              ></input-bar>
            </v-col>
          </v-row>
          <v-row no-gutters style="margin-top: 20px;">
            <v-col cols="7">
              <input-bar
                :type="'password'"
                :placeholder="'8글자의 문자와 숫자 입력'"
                @pass-input="getPassword"
              ></input-bar>
            </v-col>
          </v-row>
          <v-row no-gutters style="margin-top: 20px;">
            <v-col cols="7" align="end">
              <span @click="loginRequest">
                <app-btn-middle
                  v-bind:btn-color="btnColor"
                  v-bind:btn-name="btnName"
                  v-bind:btn-name-color="btnNameColor"
                >
                </app-btn-middle>
              </span>
            </v-col>
            <!-- <v-col align="start" style="margin-left: 10px;" @click="goRouting">
            </v-col> -->
          </v-row>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>
import InputBar from '@/components/common/InputBar.vue';
import AppBtnMiddle from '@/components/common/AppBtnMiddle.vue';

const storage = window.sessionStorage;

export default {
  created: function() {
    this.$emit('move-step', 4);
  },
  components: {
    'input-bar': InputBar,
    'app-btn-middle': AppBtnMiddle,
  },
  data: function() {
    return {
      btnColor: '#673FB4',
      btnName: '로그인',
      btnNameColor: 'white',
      userId: '',
      password: '',
      userInfo: {},
    };
  },
  methods: {
    loginRequest: function() {
      let params = new URLSearchParams();
      params.append('userId', this.userId);
      params.append('userPassword', this.password);

      this.$Axios
        .post('user/login', params)
        .then((response) => {
          if (response.data.success) {
            storage.setItem(
              'jwt-auth-token',
              response.headers['jwt-auth-token']
            );
            this.getUserInfo(storage.getItem('jwt-auth-token'));
          }
        })
        .catch((error) => {
          console.log(error);
          alert('로그인 도중 오류 발생!');
        });
    },
    getUserInfo(value) {
      this.$Axios
        .create({
          headers: {
            'jwt-auth-token': value,
          },
        })
        .get('/user')
        .then((response) => {
          this.userInfo = response.data.data;
          this.$store.commit('LOGIN', this.userInfo);
          this.$router.push('/');
        })
        .catch((error) => {
          alert('사용자 정보를 얻어오는데 실패했습니다.');
          console.log(error);
        });
    },
    getUserId(value) {
      this.userId = value;
    },
    getPassword(value) {
      this.password = value;
    },
  },
};
</script>

<style></style>
