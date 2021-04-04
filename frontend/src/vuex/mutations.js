export default {
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
  userInit(state) {
    state.user = {};
  },
  setAwayTime(state) {
    state.awayTime++;
  },
  setPhoneTime(state) {
    state.phoneTime++;
  },
};
