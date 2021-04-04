import Vue from 'vue';
import Vuex from 'vuex';
import createPersistedState from 'vuex-persistedstate';

Vue.use(Vuex);

const state = {
  awayTime: 0,
  phoneTime: 0,
};

export const store = new Vuex.Store({
  // modules,
  plugins: [createPersistedState()],
  state: {
    user: {},
    host: 'http://localhost:9999/',
    boardDetailId: 0,
    isLogin: false,
    awayTime: 0,
  phoneTime: 0,
  },
  getters: {
    /* 로그인한 유저의 정보 */
    getUserId(state) {
      return state.user.userId;
    },
    getUserNickname(state) {
      return state.user.userNickname;
    },
    // getUserImage(state) {
    //   return state.user.userImage;
    // },
    getUserMessage(state) {
      return state.user.userMessage;
    },
    getUserIsStudying(state) {
      return state.user.userIsStudying;
    },
    getAwayTime(state) {
    return state.awayTime;
  },
  getPhoneTime(state) {
    return state.phoneTime;
  },
  },
  mutations: {
    LOGIN(state, payload) {
      state.user = payload;
    },
    LOGOUT(state) {
      if (state.user) {
        state.user = {};
        alert('로그아웃되었습니다.');
      }
    },
    MOVETOBOARDDETAIL(state, id) {
      state.boardDetailId = id;
    },
    userInit(state){
      state.user = {};
    },
    setAwayTime(state) {
    state.awayTime++;
  },
  setPhoneTime(state) {
    state.phoneTime++;
  },
  },
});
