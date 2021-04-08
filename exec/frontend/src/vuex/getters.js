export default {
  /* 로그인한 유저의 정보 */
  getUserId(state) {
    return state.user.userId;
  },
  getUserNickname(state) {
    return state.user.userNickname;
  },
  getUserImage(state) {
    return state.user.profileImg;
  },
  getUserMessage(state) {
    return state.user.userMessage;
  },
  getUserIsStudying(state) {
    return state.user.userIsStudying;
  },
  getUserIsLogin(state) {
    return state.user.isLogin;
  },
  getAwayTime(state) {
    return state.awayTime;
  },
  getPhoneTime(state) {
    return state.phoneTime;
  },
  getRecommendGroup(state){
    return state.Rgroup
  },
  getSleepTime(state) {
    return state.sleepTime;
  },
  getTalkTime(state) {
    return state.talkTime;
  },
};
