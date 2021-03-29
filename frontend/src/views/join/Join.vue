<template>
  <div>
      <middle-nav v-bind:propsdata="navInfo"></middle-nav>
      <progress-stepper ref="progress"></progress-stepper>
      <router-view></router-view>
      
  </div>
</template>

<script>

import ProgressStepper from '@/components/common/ProgressStepper.vue';
import MiddleNav from '@/components/include/MiddleNav.vue';

export default {
    components: {
        'progress-stepper': ProgressStepper,
        'middle-nav': MiddleNav,
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
      this.$refs.progress.moveForward();  // 컴포넌트 내 메소드 실행(상단 프로그레스 바 전진)
      this.$router.push('/join-auth');
    },
    moveToHome() {
      this.$router.push('/');
    }
  }
}
</script>

<style>

</style>