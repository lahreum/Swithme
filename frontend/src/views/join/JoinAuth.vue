<template>
  <div style="width:70%;margin:0 auto;">
      <hr align="center" style="border:solid 1px #BDBDBD;margin:auto;">
      <div>
            <p style="text-align:center;padding:20px;">
                (주)스윗미에서는 익명 사용자로 인한 피해를 방지하고자 [정보통신망 이용촉진 및 정보보호 등에 관한 법률
            제 23조의 2제 1항](주민등록번호의 사용 제한)에 의거하여 주민등록번호 사용 및 수집을 중단하고 이메일 인증을 통해
            본인을 확인하고 있습니다.<br><br>
            </p>
      </div>
      <div class="borderBox">
        <div class="outBox">
          <div class="inBox">이메일</div>
          <div class="inBox"><input-bar></input-bar></div>
          <div class="inBox"><email-input></email-input></div>
          <div class="inBox"><app-btn-middle :btnColor="'#673fb4'" :btnName="'인증번호 받기'" :btnNameColor="'#ffffff'"></app-btn-middle></div>
        </div>
        <div class="outBox">
          <div class="inBox">인증번호</div>
          <div class="inBox numberInput"><input-bar></input-bar></div>
          <div class="inBox"><app-btn-middle :btnColor="'#673fb4'" :btnName="'확인'" :btnNameColor="'#ffffff'"></app-btn-middle></div>
        </div>
      </div>
      <div style="text-align:center;">
        <div class="oneBtn" @click="$router.push('/join')"><app-btn-middle :btnColor="'#FAFAFA'" :btnName="'이전'" :btnNameColor="'#424242'" ></app-btn-middle></div>
        <div class="oneBtn" @click="$router.push('/join/join-create')"><app-btn-middle :btnColor="'#424242'" :btnName="'다음'" :btnNameColor="'white'" @click="moveForward"></app-btn-middle></div>
      </div>
  </div>
</template>

<script>
import InputBar from '@/components/common/InputBar.vue';
import EmailInput from '@/components/common/EmailInput.vue';
import AppBtnMiddle from '@/components/common/AppBtnMiddle.vue';

export default {
  components: {
    'input-bar': InputBar,
    'email-input': EmailInput,
    'app-btn-middle': AppBtnMiddle,
  }
}
</script>

<style>
.outBox {
  margin: 0 auto;
  text-align: center;
}
.inBox {
  display: inline-block;
  margin-left:7px;
  margin-right:7px;
}
.borderBox {
  border: 1.5px solid black;
  padding: 20px;
}
.numberInput {
  width: 45%;
}
</style>