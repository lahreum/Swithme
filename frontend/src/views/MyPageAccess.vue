<template>
  <div>
    <middle-nav v-bind:propsdata="navInfo"></middle-nav>
    <v-container style="margin-left: 15%; margin-right: 15%;">
      <v-row no-gutters style="padding-top: 5%;" justify="center">
        <v-col cols="5" align="end">
          <img src="@/assets/img/character1.png" alt="swithme_캐릭터" />
        </v-col>
        <v-col align-self="center" style="margin-left: 20px;">
          <v-row no-gutters style="font-size: 1.8rem; letter-spacing: -1px;">
            앗, 잠시만요!
          </v-row>
          <v-row no-gutters style="font-size: 1rem; letter-spacing: -1px;">
            마이페이지로 가려면 비밀번호를 다시 입력해야해요.
          </v-row>
          <v-row no-gutters style="margin-top: 20px;">
            <v-col cols="5">
              <input-bar
                :type="'password'"
                @pass-input="getPassword"
              ></input-bar
            ></v-col>
            <v-col
              align="start"
              style="margin-left: 10px;"
              @click="routingRequest"
            >
              <div style="width: 100px;">
                <app-btn-large
                  v-bind:btn-color="btnColor"
                  v-bind:btn-name="btnName"
                  v-bind:btn-name-color="btnNameColor"
                >
                </app-btn-large>
              </div>
            </v-col>
          </v-row>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>
import MiddleNav from '../components/include/MiddleNav.vue';
import InputBar from '../components/common/InputBar.vue';
import AppBtnLarge from '../components/common/AppBtnLarge.vue';

const storage = window.sessionStorage;
export default {
  components: {
    'middle-nav': MiddleNav,
    'input-bar': InputBar,
    'app-btn-large': AppBtnLarge,
  },
  data: function() {
    return {
      navInfo: [
        'sample3.gif',
        '마이페이지',
        '첫번째 문장입니다. 첫번째 문장입니다. 첫번째 문장입',
        '두번째 문장입니다~! 두번째 문장입니다~! 두번째 문장입니다~! 두번째',
      ],
      btnColor: '#673FB4',
      btnName: '확 인',
      btnNameColor: 'white',
      password: '',
    };
  },
  methods: {
    routingRequest: function() {
      let params = new URLSearchParams();
      params.append('userPassword', this.password);

      this.$Axios
        .post('user/mypage', params, {
          headers: {
            'jwt-auth-token': storage.getItem('jwt-auth-token'),
          },
        })
        .then((response) => {
          if (response.data.isCorrect) {
            this.$router.push('/my-page');
          } else {
            alert('잘못된 비밀번호 입니다.');
          }
        });
    },
    getPassword: function(value) {
      this.password = value;
    },
  },
};
</script>

<style></style>
