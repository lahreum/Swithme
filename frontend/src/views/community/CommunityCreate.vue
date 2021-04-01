<template>
  <div>
      <p class="letter mainTitle">글쓰기</p>
      <hr class="lineStyle">
      <v-container style="margin-top:10px;">
        <v-row no-gutters style="margin-bottom:10px;"> 
          <v-col class="letter" cols="1">카테고리</v-col>
          <v-col cols="3"><v-select
            :items="items"
            placeholder="선택"
            outlined
          ></v-select></v-col>
          <v-col cols="7"></v-col>
        </v-row>
        <v-row no-gutters>
          <v-col class="letter" cols="1">제목</v-col>
          <v-col cols="11">
            <v-text-field
            placeholder="3자 이상 입력" v-model="title"
            outlined
          ></v-text-field>
          </v-col>
        </v-row>
        <v-row class="writeText">
          <v-textarea
          outlined
          placeholder="10자 이상 입력" height="400" v-model="content"
        ></v-textarea>
        </v-row>
      </v-container>
      <div style="text-align:center;">
        <div class="oneBtn" @click="deleteBoard"><AppBtnMiddle :btnColor="'#616161'" :btnName="'취소'" :btnNameColor="'white'"/></div>
        <div class="oneBtn" @click="createBoard"><AppBtnMiddle :btnColor="'#673fb4'" :btnName="'등록하기'" :btnNameColor="'white'"/></div>
      </div>
  </div>
</template>

<script>
import "./community.css";
import AppBtnMiddle from "@/components/common/AppBtnMiddle.vue";

  export default {
    components: {
      AppBtnMiddle,
    },
    data: () => ({
      items: ['초중고', '수능', '대학교',  '대학원', '취업', '공무원시험', '자격증', '어학', '기타'],
      title: '',
      content: '',
    }),
    methods: {
      createBoard() {
        alert('글을 작성하였습니다.');
        this.$router.push('/community');
      },
      deleteBoard() {
        alert('작성한 글이 취소됩니다.');
        this.$router.push('/community');
      }
    }
  }
</script>

<style>

</style>