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
  MOVETOBOARDCATEGORY(state, payload) {
    state.categoryId = payload.value;
    state.categoryName = payload.name;
  },
  FETCHSTUDYING(state) {
    state.user.isStudying = true;
  },
  STOPSTUDYING(state) {
    state.user.isStudying = false;
  },
  InitializeInterruption(state) {
    state.awayTime = 0;
    state.phoneTime = 0;
    state.sleepTime = 0;
    state.talkTime = 0;
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
  setSleepTime(state) {
    state.sleepTime++;
  },
  setTalkTime(state) {
    state.talkTime++;
  },
  RECOMMENDGROUP(state, payload) {
    state.Rgroup = payload;
  },
  setIsStartCam(state) {
    state.isStartCam = !state.isStartCam;
  },
};
