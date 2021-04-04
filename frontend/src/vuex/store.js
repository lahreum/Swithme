import Vue from 'vue';
import Vuex from 'vuex';
import createPersistedState from 'vuex-persistedstate';

Vue.use(Vuex);

export const store = new Vuex.Store({
  // modules,
  plugins: [createPersistedState()],
  state: {
    user: {},
    host: 'http://localhost:9999/',
    boardDetailId: 0,
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
    }
  },
});
