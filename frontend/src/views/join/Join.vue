<template>
  <div>
      <middle-nav v-bind:propsdata="navInfo"></middle-nav>
      <progress-stepper ref="progress"></progress-stepper>
      <router-view></router-view>
      <div style="text-align:center;">
        <app-btn-middle class="oneBtn" :btnColor="'#FAFAFA'" :btnName="'취소'" :btnNameColor="'#424242'" @click="moveToHome()"></app-btn-middle>
        <app-btn-middle class="oneBtn" :btnColor="'#424242'" :btnName="'다음'" :btnNameColor="'white'" @click="moveForward()"></app-btn-middle>
      </div>
  </div>
</template>

<script>
import AppBtnMiddle from '@/components/common/AppBtnMiddle.vue';
import ProgressStepper from '@/components/common/ProgressStepper.vue';
import MiddleNav from '@/components/include/MiddleNav.vue';

export default {
    components: {
        'progress-stepper': ProgressStepper,
        'middle-nav': MiddleNav,
        'app-btn-middle': AppBtnMiddle,
    },
    data: function() {
    return {
      navInfo: [
        'sample1.jpg',
        '회원가입',
        'SWITHME 회원이 되어, 스마트하게 학습관리를 해보세요.',
      ],
    };
  },
  methods: {
    moveForward() {
      this.$refs.progress.moveForward();
    }
  }
}
</script>

<style>
.oneBtn {
    display: inline-block;
    padding-left: 20px;
    padding-right: 20px;
    padding-top: 50px;
}
</style>