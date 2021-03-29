<template>
    <v-row justify="center">
        <div style="width:50%;">
            <!-- 버튼도 v-dialog와 동시에 출력되도록 수정필요 -->
            <v-btn
            class="cancelBtn"
            fab
            dark
            small
            color="#BDBDBD"
            >
                <v-icon dark>
                    mdi-close
                </v-icon>
            </v-btn>
        <v-dialog
        v-model="dialog"
        max-width="420" persistent
        >
        <v-card class="round" max-width="420" >
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
    </div>
  </v-row>
</template>

<script>
import "./user.css";
import AppBtnLarge from '@/components/common/AppBtnLarge.vue';
import InputBar from '@/components/common/InputBar.vue';

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

</style>