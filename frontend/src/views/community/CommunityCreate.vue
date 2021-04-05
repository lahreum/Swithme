<template>
  <div>
      <p class="letter mainTitle">{{ categoryName }} > 글쓰기</p>
      <hr class="lineStyle">
      <v-container style="margin-top:10px;">
        <v-row no-gutters style="margin-bottom:10px;"> 
          <v-col class="letter" cols="1">카테고리</v-col>
          <v-col cols="3">
            <v-select 
            disabled
            :items="items" 
            :placeholder=categoryName
            outlined :rules="categoryRules"
            ></v-select>
          </v-col>
          <v-col cols="7"></v-col>
        </v-row>
        <v-row no-gutters>
          <v-col class="letter" cols="1">제목</v-col>
          <v-col cols="11">
            <v-text-field
            placeholder="3자 이상 입력" v-model="title"
            outlined :rules="textRules"
          ></v-text-field>
          </v-col>
        </v-row>
        <v-row class="writeText">
          <v-textarea
          outlined
          placeholder="3자 이상 입력" height="400" 
          v-model="content" :rules="textRules"
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

const storage = window.sessionStorage;

  export default {
    components: {
      AppBtnMiddle,
    },
    data: () => ({
      items: ['초중고', '수능', '대학교',  '대학원', '취업', '공무원시험', '자격증', '어학', '기타'],
      title: '',
      content: '',
      categoryName: '',
      lengthOk: false,
      textRules: [v => v.length >= 3 || '3자 이상 입력해주세요'],
      categoryRules: [(v) => !!v || '카테고리를 선택해주세요.'],
      board: {
        boardCategory: 0,
        boardContent: '',
        boardDate: '',
        boardId: 0,
        boardLiked: 0,
        boardTitle: '',
        boardView: 0,
        boardWriter: '',
      }
    }),
    created() {
      this.categoryName = `${this.$store.state.categoryName}`;
      console.log(this.categoryName);
    },
    methods: {
      createBoard() {
        this.checkLength();
        if(this.lengthOk) {
          this.$Axios     // request body에 담김. params로 보내면 request params로 꺼내옴
          .post('community/board',{
            boardCategory: `${this.$store.state.categoryId}`,
            boardContent: this.content,
            boardTitle: this.title,
            boardWriter: `${this.$store.state.user.userId}`}, 
            {
              headers: {
                "jwt-auth-token": storage.getItem("jwt-auth-token")
              }
            }
          )
          .then((res) => {
            if(res.data.success) {
              alert('글 등록 성공');
              this.$router.push('/community');
            } else {
              alert('글 등록 실패');
            }
          })
          .catch((error)=>{
            console.log(error);
          })
        } else {
          alert('제목과 내용은 3자 이상 입력해주세요.');
        }
      },
      deleteBoard() {
        alert('작성한 글이 취소됩니다.');
        this.$router.push('/community');
      },
      checkLength() {
        if(this.title.length >=3 && this.content.length >=3) {
          this.lengthOk = true;
        } else {
          this.lengthOk = false;
        }
      }
    }
  }
</script>

<style>

</style>