import Vue from 'vue';
import Vuex from 'vuex';
import getters from './getters';
import actions from './actions';
import mutations from './mutations';
import createPersistedState from 'vuex-persistedstate';

Vue.use(Vuex);

const state = {
  user: {},
  boardDetailId: 0,
  categoryId: 1,
  categoryName: '',
  isLogin: false,
  awayTime: 0,
  phoneTime: 0,
  sleepTime: 0,
  busyTime: 0,
};

export default new Vuex.Store({
  state,
  mutations,
  getters,
  actions,
  plugins: [createPersistedState()],
});
