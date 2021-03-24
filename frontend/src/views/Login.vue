<template>
    <v-row justify="center">
        <v-dialog
        v-model="dialog"
        max-width="420" overlay-opacity="-1" persistent
        >
        <v-card class="round" max-width="420" >
            <!-- <v-btn
            class="cancelBtn"
            fab
            dark
            small
            color="#BDBDBD"
            >
                <v-icon dark>
                    mdi-close
                </v-icon>
            </v-btn> -->
            <!-- 로그인 -->
            <v-card-title>
            <p class="headLine">LOGIN</p>
            </v-card-title>
            <!-- 이메일, 비밀번호 입력란 -->
            <v-card-text style="padding-bottom:0px;">
                <hr style="border:solid 1px #E0E0E0">
                <br>
                <div>
                    <span class="email">이메일</span>
                    <input-bar placeholder="이메일"></input-bar>
                </div>
                <div class="passwordOut">
                    <span class="password">비밀번호</span>
                    <input-bar placeholder="비밀번호"></input-bar>
                </div>
            </v-card-text>

            <v-card-actions>
                <div class="buttonGroup">
                    <div @click="dialog = false"><app-btn-large class="btnOption" :btnColor="'#673fb4'" :btnName="'로그인'" :btnNameColor="'white'" ></app-btn-large></div>
                    <v-img @click="dialog = false" src="@/assets/img/kakao_long.png" width="380"></v-img>
                    <v-img @click="dialog = false" src="@/assets/img/naver_long.png" width="380" style="margin-bottom:100px;"></v-img>
                    <div class="bottomOption">
                        <v-img src="@/assets/img/pattern.jpg" style="width:100%;"></v-img>
                        <span style="margin-right:40px;">비밀번호 찾기</span>
                        <span >|</span>
                        <span style="margin-left:40px;">회원가입</span>    
                    </div>
                </div>
            </v-card-actions>

        </v-card>
    </v-dialog>
  </v-row>
</template>

<script>
import AppBtnLarge from '@/components/common/AppBtnLarge.vue';
import InputBar from '../components/common/InputBar.vue';

  export default {
    name: "Login",
    components: { 
        'app-btn-large': AppBtnLarge,
        'input-bar': InputBar,
    },
    data () {
      return {
        dialog: true,
      }
    },
  }
</script>

<style>
.btnOption {
    margin:10px;
}
.bottomLine {
    text-align: center;
}
.headLine {
    color:#673fb4;
    text-align: center;
    font-size: 40px;
    font-weight: 600;
    margin: auto;
    padding-top:30px;
}
.round {
    border-radius: 30px;
}

.v-card {
  border-radius: 25px!important;
  position: relative;
}
.v-dialog {
    border-radius: 25px!important;
    position: relative;
}

/* .password {
    position: absolute;
    top:200px;
} */
.password {
    font-weight: 800;
    font-size: 20px;
    float:left;
    padding-top: 25px;
    margin-right: 15px;
}
.email {
    font-weight: 800;
    font-size: 20px;
    float:left;
    padding-top: 25px;
    margin-right: 35px;
}

div.container {
    padding-bottom: 0px !important;
}
.passwordOut {
    position: absolute;
    top:200px;
}
.buttonGroup {
    margin-top:50px;
}
.bottomOption {
    color: #757575;
    background-color: #f4f4f4;
    text-align: center;
    font-weight: 600;
    margin-top: 30px;
    margin-bottom: 10px;
    font-size: 17px;
    height: 85px;
    width: 430px;
    border-radius: 25px;
    position: absolute;
    bottom: -10px;
    left: -10px;
}

/* .cancelBtn {
    position: absolute;
    top:-14px;
    right: -14px;
    cursor: pointer;
} */
</style>