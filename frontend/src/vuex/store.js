import Vue from 'vue';
import Vuex from 'vuex';
import getters from './getters';
import actions from './actions';
import mutations from './mutations';

Vue.use(Vuex);

const state = {
  awayTime: 0,
  phoneTime: 0,
};

export default new Vuex.Store({
  state,
  mutations,
  getters,
  actions,
});
