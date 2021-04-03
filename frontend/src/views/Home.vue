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
const storage = window.sessionStorage;

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

  created() {
    // axios
    //   .create({
    //     headers: {
    //       "jwt-auth-token":
    //         "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzd2l0aG1lVG9rZW4iLCJleHAiOjE2MTc0MzU0NTYsIlVzZXIiOnsidXNlcklkIjoiZWVhcGJoQGdtYWlsLmNvbSIsInVzZXJOaWNrbmFtZSI6ImJvbmcxIiwidXNlclR5cGUiOiJnb29nbGUiLCJ1c2VySXNTdHVkeWluZyI6ZmFsc2V9fQ.idRpfPJjV4FrrBm6kWl9w1lusM7_JwkjHM0DWhYduMw",
    //     },
    //   })
    //   .get("user")
    //   .then((response) => {
    //     console.log(response);
    //   })
    //   .catch((err) => {
    //     console.log(err);
    //   });
    // axios
    //   .request((rq) => {
    //     console.log("리퀘스트", rq);
    //   })
    //   .get("user", {
    //     headers: {
    //       "jwt-auth-token": storage.getItem("jwt-auth-token"),
    //     },
    //   })
    //   .then((response) => {
    //     console.log(response);
    //   });

    this.$Axios.get("user", {
      headers: {
        "Content-Type": "application/json",
        "jwt-auth-token": storage.getItem("jwt-auth-token"),
      },
    });
    console.log(storage.getItem("jwt-auth-token"));

    // if (storage.getItem("jwt-auth-token")) {
    //   axios
    //     .get("user", {
    //       headers: {
    //         "jwt-auth-token": storage.getItem("jwt-auth-token"),
    //       },
    //     })
    //     .then((res) => {
    //       console.log(res);
    //     })
    //     .catch((error) => console.log(error));
    // }
  },
};
</script>

<style></style>
