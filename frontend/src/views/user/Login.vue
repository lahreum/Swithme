<template>
    <div style="width:50%;">
        <!-- 버튼도 v-dialog와 동시에 출력되도록 수정필요 -->
        <!-- <v-btn
        fab
        dark
        small
        color="#BDBDBD" v-if="dialog" @click="dialog = false"
        > -->
        <!-- </v-btn> -->
        <v-dialog
        v-model="dialog"
        max-width="420" persistent
        >
        <v-card class="round" max-width="420">
            <div class="cancelBtn">
                <v-icon class="cancelBtn float-right" dark color="#BDBDBD" v-if="dialog" @click="dialog = false">
                    mdi-close
                </v-icon>
            </div>
            <v-card-title>
                <p class="headLine">LOGIN</p>
            </v-card-title>
            <!-- 이메일, 비밀번호 입력란 -->
            <v-card-text style="padding-bottom:0px;">
                <hr style="border:solid 1px #E0E0E0">
                <br>

                <v-container>
                    <v-row class="noPadding">
                        <v-col class="middleLetter formLetter" cols="3">이메일</v-col>
                        <v-col cols="9"><input-bar placeholder="이메일"></input-bar></v-col>
                    </v-row>
                    <v-row class="noPadding">
                        <v-col class="middleLetter formLetter" cols="3">비밀번호</v-col>
                        <v-col cols="9"><input-bar placeholder="비밀번호"></input-bar></v-col>
                    </v-row>
                </v-container>
            </v-card-text>

            <v-card-actions style="padding-bottom:0px;padding-top:0px;">
                <div class="buttonGroup" >
                    <div @click="dialog = false"><app-btn-large class="btnOption" :btnColor="'#673fb4'" :btnName="'로그인'" :btnNameColor="'white'" ></app-btn-large></div>
                    <v-img @click="dialog = false" src="@/assets/img/kakao_long.png" width="380"></v-img>
                    <v-img @click="dialog = false" src="@/assets/img/naver_long.png" width="380" style="margin-bottom:100px;"></v-img>
                    <div class="bottomOption">
                        <v-img src="@/assets/img/pattern.jpg" style="width:100%;"></v-img>
                        <router-link to="/join" style="text-decoration: none; color:#616161;"><span style="margin-right:40px;">비밀번호 찾기</span></router-link>   
                        <span >|</span>
                        <router-link to="/join" style="text-decoration: none; color:#616161;"><span style="margin-left:40px;">회원가입</span></router-link>    
                    </div>
                </div>
            </v-card-actions>

            </v-card>
        </v-dialog>
    </div>
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
.noPadding{ 
    padding-top: 0px;
}
.v-text-field.v-text-field--enclosed .v-text-field__details{
    display: none !important;
}
</style>