<template>
  <div style="text-align:center;">
    <middle-nav v-bind:propsdata="navInfo"></middle-nav>
    <div class="middleLetter" style="margin-top:80px;margin-bottom:30px;">
      비밀번호를 잃어버리셨나요?<br />
      SWITHME에 가입한 이메일을 정확히 입력해 주세요.<br />
      이메일을 통해 비밀번호 수정 링크가 전송됩니다.<br />
    </div>
    <div style="width:30%;margin:0 auto;">
      <div style="width:98%; padding-left:10px; margin-bottom:20px;">
        <input-bar @pass-input="getInput"></input-bar>
      </div>
      <div @click="sendEmail">
        <app-btn-large
          class="btnOption"
          :btnColor="'#673fb4'"
          :btnName="'이메일 보내기'"
          :btnNameColor="'white'"
        ></app-btn-large>
      </div>
    </div>
  </div>
</template>

<script>
import "./user.css";
import MiddleNav from "@/components/include/MiddleNav.vue";
import InputBar from "@/components/common/InputBar.vue";
import AppBtnLarge from "@/components/common/AppBtnLarge.vue";

export default {
  components: {
    "middle-nav": MiddleNav,
    "input-bar": InputBar,
    "app-btn-large": AppBtnLarge,
  },
  data: function() {
    return {
      navInfo: [
        "sample1.jpg",
        "비밀번호 찾기",
        "비밀번호를 잊어버리셨나요?",
        "이메일로 비밀번호 변경링크를 보내드립니다.",
      ],
      email: "",
    };
  },
  methods: {
    getInput(e) {
      console.log(e);
      this.email = e;
    },
    sendEmail() {
      var params = new URLSearchParams();
      params.append("userEmail", this.email);
      this.$Axios.post("user/password", params).then((res) => {
        console.log(res);
      });
      alert("이메일 전송을 완료하였습니다.");
    },
  },
};
</script>

<style lang="scss" scoped></style>
