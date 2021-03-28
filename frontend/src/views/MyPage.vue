<template>
  <div>
    <middle-nav v-bind:propsdata="navInfo"></middle-nav>
    <h1>real my page!!!!!!!!!</h1>
  </div>
</template>

<script>
import MiddleNav from '../components/include/MiddleNav.vue';

export default {
  components: {
    'middle-nav': MiddleNav,
  },
  data: function() {
    return {
      navInfo: [
        'sample2.png',
        '마이페이지',
        '첫번째 문장입니다. 첫번째 문장입니다. 첫번째 문장입',
        '두번째 문장입니다~! 두번째 문장입니다~! 두번째 문장입니다~! 두번째',
      ],
    };
  },
};
</script>

<style></style>
