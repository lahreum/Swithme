<template>
  <div>
    <div
      style="z-index: 1000; position:fixed; top: 0px; left: 0px; width: 100%;"
    >
      <app-header></app-header>
    </div>
    <study-btn
      style="position: fixed; z-index: 800; left: 25%; top: 55%;"
    ></study-btn>
    <div
      style="position: fixed; letter-spacing: -1px; font-size: 1.1rem; font-weight: 500; z-index: 800; left: 85%; top: 95%;"
    >
      ⓒ SWITHME All rights reserved.
    </div>
    <div style="z-index: 100;">
      <full-page ref="fullpage" :options="options" id="fullpage">
        <div class="section">
          <home-first></home-first>
        </div>
        <div class="section">
          <home-second></home-second>
        </div>
      </full-page>
    </div>
  </div>
</template>

<script>
import AppHeader from '../components/include/AppHeader.vue';
import HomeFirst from '../components/include/HomeFirst.vue';
import HomeSecond from '../components/include/HomeSecond.vue';
import StudyBtn from '../components/common/StudyBtn.vue';

export default {
  components: {
    'app-header': AppHeader,
    'home-first': HomeFirst,
    'home-second': HomeSecond,
    'study-btn': StudyBtn,
  },
  data() {
    return {
      options: {
        afterLoad: this.afterLoad,
      },
    };
  },

  methods: {
    afterLoad() {
      console.log("Emitted 'after load' event.");
    },
  },
};
</script>

<style></style>
