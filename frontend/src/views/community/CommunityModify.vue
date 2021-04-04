<template>
  <div>
      <p class="letter mainTitle">수정하기</p>
      <hr class="lineStyle">
      <v-container style="margin-top:10px;">
        <v-row no-gutters style="margin-bottom:10px;"> 
          <v-col class="letter" cols="1">카테고리</v-col>
          <v-col cols="3"><v-select
            disabled
            :items="items"
            :placeholder="categoryName"
            outlined
          ></v-select></v-col>
          <v-col cols="7"></v-col>
        </v-row>
        <v-row no-gutters>
          <v-col class="letter" cols="1">제목</v-col>
          <v-col cols="11">
            <v-text-field 
            placeholder="3자 이상 입력" v-model="board.boardTitle"
            outlined
          >{{ board.boardTitle }}</v-text-field>
          </v-col>
        </v-row>
        <v-row class="writeText">
          <v-textarea
          outlined
          placeholder="10자 이상 입력" height="400" v-model="board.boardContent"
        >{{ board.boardContent }}</v-textarea>
        </v-row>
      </v-container>
      <div style="text-align:center;">
        <div class="oneBtn" @click="goHome"><AppBtnMiddle :btnColor="'#616161'" :btnName="'취소'" :btnNameColor="'white'"/></div>
        <div class="oneBtn" @click="modifyBoard"><AppBtnMiddle :btnColor="'#673fb4'" :btnName="'등록하기'" :btnNameColor="'white'"/></div>
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
      categoryName: '',
      board: {
          boardId: 0,
          boardCategory: 0,
          boardWriter: "",
          boardTitle: "",
          boardContent: "",
          boardDate: "",
          boardView: 0,
          boardLiked: 0,
        },
    }),
    created() {
      this.getBoardDetail();
    },
    methods: {
      getBoardDetail() {
        this.$Axios
        .get('community/board/' + `${this.$store.state.boardDetailId}`, {
          headers: {
            "jwt-auth-token": storage.getItem("jwt-auth-token"),
          }
        })
        .then((res) => {
          if(res.data.boardDetail.boardId > 0 ) {
            this.board.boardId = res.data.boardDetail.boardId;
            this.board.boardCategory = res.data.boardDetail.boardCategory;
            this.board.boardWriter = res.data.boardDetail.boardWriter;
            this.board.boardTitle = res.data.boardDetail.boardTitle;
            this.board.boardContent = res.data.boardDetail.boardContent;
            this.board.boardDate = res.data.boardDetail.boardDate;
            this.board.boardView = res.data.boardDetail.boardView;
            this.board.boardLiked = res.data.boardDetail.boardLiked;
            this.isLiked = res.data.didILiked;
            this.isWriter = res.data.isWriter;
            this.categoryName = res.data.categoryName;
          } else {
            console.log('아예 받아오질 못함.');
          }
        })
        .catch((error) => {
          console.log(error);
        })
      },
      modifyBoard(){
        this.$Axios
        .put('community/board',{
          boardCategory: this.board.boardCategory,
          boardContent: this.board.boardContent,
          boardId: this.board.boardId,
          boardTitle: this.board.boardTitle,
          boardWriter: this.board.boardWriter},
          {
            headers: {
              "jwt-auth-token": storage.getItem("jwt-auth-token")
            }
          }
        )
        .then((res)=>{
          if(res.data.success) {
            alert('글 수정 성공');
            this.$router.push('/community/community-detail');
          } else {
            console.log('글 수정 실패')
          }
        })
        .catch((error)=>{
          console.log(error);
        })
      },
      createBoard() {
        alert('글을 작성하였습니다.');
        this.$router.push('/community');
      },
      goHome() {
        alert('작성한 글이 취소됩니다.');
        this.$router.push('/community');
      }
    }
  }
</script>

<style>

</style>