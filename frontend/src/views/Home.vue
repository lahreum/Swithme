<template>
  <div>
    <div
      style="z-index: 100; position:fixed; top: 0px; left: 0px; width: 100%;"
    >
      <app-header :darkmode="isDarkmode"></app-header>
    </div>
    <study-btn
      style="position: fixed; z-index: 100; left: 25%; top: 65%;"
      v-bind:propsdata="isTop"
    ></study-btn>
    <div
      style="position: fixed; letter-spacing: -1px; font-size: 1.1rem; font-weight: 500; z-index: 800; left: 85%; top: 95%;"
      :style="{ color: isDarkmode ? 'white' : 'black' }"
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
        <div class="section">
          <home-third></home-third>
        </div>
        <div class="section">
          <home-fourth></home-fourth>
        </div>
      </full-page>
    </div>
  </div>
</template>

<script>
import AppHeader from "../components/include/AppHeader.vue";
import HomeFirst from "../components/include/HomeFirst.vue";
import HomeSecond from "../components/include/HomeSecond.vue";
import HomeThird from "../components/include/HomeThird.vue";
import HomeFourth from "../components/include/HomeFourth.vue";
import StudyBtn from "../components/common/StudyBtn.vue";
// import axios from "axios";
// const storage = window.sessionStorage;

export default {
  components: {
    "app-header": AppHeader,
    "home-first": HomeFirst,
    "home-second": HomeSecond,
    "home-third": HomeThird,
    "home-fourth": HomeFourth,
    "study-btn": StudyBtn,
  },
  data() {
    return {
      options: {
        afterLoad: this.afterLoad,
        onLeave: this.onLeave,
      },
      isDarkmode: true,
      isTop: true,
      userInfo: [],
    };
  },
  methods: {
    afterLoad() {
      console.log("Emitted 'after load' event.");
    },
    onLeave(index, nextIndex, direction) {
      // isTop
      if (index.index == 0 && direction == "down") {
        this.isTop = false;
      } else if (index.index == 1 && direction == "up") {
        this.isTop = true;
      }

      // isDarkmode
      if (
        (index.index == 0 && direction == "down") ||
        (index.index == 3 && direction == "up")
      ) {
        this.isDarkmode = false;
      } else if (
        (index.index == 1 && direction == "up") ||
        (index.index == 2 && direction == "down")
      ) {
        this.isDarkmode = true;
      }
    },
  },

  created() {},
};
</script>

<style></style>
