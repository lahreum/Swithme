<template>
  <div>
    <middle-nav v-bind:propsdata="navInfo"></middle-nav>
    <h1>tutorial!!!!!!</h1>
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
        'sample1.jpg',
        '튜토리얼',
        '첫번째 문장입니다. 첫번째 문장입니다. 첫번째 문장입',
        '두번째 문장입니다~! 두번째 문장입니다~! 두번째 문장입니다~! 두번째',
      ],
    };
  },
};
</script>

<style></style>
