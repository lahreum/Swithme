<template>
  <div class="joinForm">
    <div style="margin-top:80px; text-align:center; letter-spacing: -1px;">
      <p class="purpleBigLetter">회원가입이 완료되었습니다!</p>
      <p class="smallLetter">SWITHME의 가족이 되신 것을 환영합니다</p>
    </div>

    <div style="text-align:center;">
      <div class="oneBtn" @click="$router.push('/')">
        <app-btn-middle
          :btnColor="'#FAFAFA'"
          :btnName="'홈으로'"
          :btnNameColor="'#424242'"
        ></app-btn-middle>
      </div>
      <div class="oneBtn" @click="$router.push('/join/join-login')">
        <app-btn-middle
          :btnColor="'#424242'"
          :btnName="'바로 로그인하기'"
          :btnNameColor="'white'"
        ></app-btn-middle>
      </div>
    </div>
  </div>
</template>

<script>
import './user.css';
import AppBtnMiddle from '@/components/common/AppBtnMiddle.vue';

export default {
  created: function() {
    this.$emit('move-step', 4);
  },
  components: {
    'app-btn-middle': AppBtnMiddle,
  },
};
</script>

<style></style>
