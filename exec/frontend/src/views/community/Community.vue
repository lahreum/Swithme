<template>
  <div>
      <MiddleNav v-bind:propsdata="navInfo"/>
      <div class="community">
        <router-view></router-view>
      </div>
  </div>
</template>

<script>
import "./community.css";
import MiddleNav from '@/components/include/MiddleNav.vue';

export default {
    components: {
        MiddleNav,
    },
    data: function() {
        return {
            navInfo: [
                'nav6.jpg',
                '커뮤니티',
                '같은 시험을 준비하는 사람들과 정보를 공유해보세요.',
            ]
        }
    },
}
</script>

<style>

</style>