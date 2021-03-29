<template>
  <div class="joinForm">
      <div>
            <p style="text-align:center;padding:20px;">
                (주)스윗미에서는 익명 사용자로 인한 피해를 방지하고자 [정보통신망 이용촉진 및 정보보호 등에 관한 법률
            제 23조의 2제 1항](주민등록번호의 사용 제한)에 의거하여 주민등록번호 사용 및 수집을 중단하고 이메일 인증을 통해
            본인을 확인하고 있습니다.<br><br>
            </p>
      </div>
      <v-container class="joinForm borderBox">
        <v-row>
          <v-col class="formLetter">이메일</v-col>
          <v-col><input-bar></input-bar></v-col>
          <v-col><email-input></email-input></v-col>
          <v-col><app-btn-middle :btnColor="'#673fb4'" :btnName="'인증번호 받기'" :btnNameColor="'#ffffff'"></app-btn-middle></v-col>
        </v-row>
        <v-row>
          <v-col class="formLetter" cols="3">인증번호</v-col>
          <v-col cols="6"><input-bar ></input-bar></v-col>
          <v-col cols="3"><app-btn-middle :btnColor="'#673fb4'" :btnName="'학인'" :btnNameColor="'#ffffff'"></app-btn-middle></v-col>
        </v-row>
      </v-container>
      <div style="text-align:center;">
        <div class="oneBtn" @click="$router.push('/join')"><app-btn-middle :btnColor="'#FAFAFA'" :btnName="'이전'" :btnNameColor="'#424242'" ></app-btn-middle></div>
        <div class="oneBtn" @click="$router.push('/join/join-create')"><app-btn-middle :btnColor="'#424242'" :btnName="'다음'" :btnNameColor="'white'"></app-btn-middle></div>
      </div>
  </div>
</template>

<script>
import "./user.css";
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

</style>