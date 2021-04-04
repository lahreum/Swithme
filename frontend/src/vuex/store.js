import Vue from 'vue';
import Vuex from 'vuex';
import getters from './getters';
import actions from './actions';
import mutations from './mutations';
import createPersistedState from 'vuex-persistedstate';

Vue.use(Vuex);

const state = {
  user: {},
  host: 'http://localhost:9999/',
  boardDetailId: 0,
  isLogin: false,
  awayTime: 0,
  phoneTime: 0,
};

export default new Vuex.Store({
  state,
  mutations,
  getters,
  actions,
  plugins: [createPersistedState()],
});
