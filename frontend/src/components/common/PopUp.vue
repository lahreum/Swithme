<template>
    <v-row justify="center">
        <v-dialog
        v-model="dialog"
        max-width="300" overlay-opacity="-1" persistent
        >
        <v-card class="round" max-width="300" >
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
            <!-- 상단 문구 -->
            <v-card-title>
            <p class="headLine">로그인이 필요한 서비스 입니다.</p>
            </v-card-title>
            <!-- 하단 문구 -->
            <v-card-text>
            <p class="bottomLine">로그인 후 이용하실 수 있습니다.</p>
            </v-card-text>

            <v-card-actions>
            <v-spacer></v-spacer>

            <div @click="dialog = false"><app-btn-small class="btnOption" :btnColor="'#673fb4'" :btnName="'로그인'" :btnNameColor="'white'" ></app-btn-small></div>
            <div @click="dialog = false"><app-btn-small class="btnOption" :btnColor="'#424242'" :btnName="'취소'" :btnNameColor="'white'"></app-btn-small></div>
            </v-card-actions>
        </v-card>
    </v-dialog>
  </v-row>
</template>

<script>
import AppBtnSmall from './AppBtnSmall.vue';

  export default {
    name: "PopUp",
    components: { 
        'app-btn-small': AppBtnSmall,
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
    text-align: center;
    font-size: 30px;
    font-weight: 600;
    margin-top: 50px;
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
/* .cancelBtn {
    position: absolute;
    top:-14px;
    right: -14px;
    cursor: pointer;
} */
</style>