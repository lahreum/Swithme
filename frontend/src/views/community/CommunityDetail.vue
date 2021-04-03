<template>
  <div>
      <div class="communitySection">커뮤니티 > {{ board.boardId }} </div>
      <v-divider></v-divider>
      <p class="boardTitle">{{ board.boardTitle }}</p>
      <div>
        <div class="boardInfo" style="float:left;"><strong>{{ board.boardWriter }}</strong></div>
        <div class="boardInfo" style="float:left;">{{ board.boardDate }}</div>
        <div class="boardInfo" style="float:left;">조회수 {{ board.boardView }}</div><br><br>
      </div>
      <v-divider></v-divider>
      <div class="boardContent">
        <p>{{ board.boardContent }}</p>
        <div class="heart">좋아요 {{ board.boardLiked }} 개<v-icon @click="addLike" v-if="!isLiked">mdi-heart-outline</v-icon><v-icon @click="deleteLike" v-if="isLiked">mdi-heart</v-icon></div>
      </div>
      <v-divider></v-divider>
      <CommentInputBar/>
      <CommentItem/>
  </div>
</template>

<script>
import CommentInputBar from '@/components/common/CommentInputBar.vue';
import CommentItem from '@/components/common/CommentItem.vue';
import "./community.css";
const storage = window.sessionStorage;
  export default {
    components: {
      CommentInputBar,
      CommentItem,
    },
    data: function() {
      return {
        isLiked: false,
        board: {
          boardId: 0,
          // boardCategory: "",
          boardWriter: "",
          boardTitle: "",
          boardContent: "",
          boardDate: "",
          boardView: 0,
          boardLiked: 0,
        },
      }
    },
    created() {
      this.getBoardDetail();
    },
    methods: {
      addLike() { 
        console.log('addLike 함수 안에는 들어감');
        console.log(`${this.$store.state.boardDetailId}`);
        this.$Axios
        .post('http://localhost:9999/community/liked?boardId=' + `${this.$store.state.boardDetailId}`,{
          headers: {
            "jwt-auth-token": storage.getItem("jwt-auth-token"),
          }
        })
        .then((res) => {
          if(res.data.success){
            console.log('좋아요 성공');
            this.isLiked = true;
            this.getBoardDetail();
          } else {
            console.log('좋아요 실패');
          }
        })
        .catch((error)=>{
          console.log(error);
        })
      },
      deleteLike() {
        `${this.$store.state.boardDetailId}`
        console.log('deleteLike 함수 안에는 들어감');
        this.$Axios
        .delete('http://localhost:9999/community/liked?boardId=' + `${this.$store.state.boardDetailId}`, {
          headers: {
            "jwt-auth-token": storage.getItem("jwt-auth-token"),
          }
        })
        .then((res) => {
          if(res.data.success){
            console.log('좋아요 취소 성공');
            this.isLiked = false;
            this.getBoardDetail();
          } else {
            console.log('좋아요 취소 실패');
          }
        })
        .catch((error) => {
          console.log(error);
        })
      },
      getBoardDetail() {
        this.$Axios
        .get(`http://localhost:9999/community/board/` + `${this.$store.state.boardDetailId}`, {
          headers: {
            "jwt-auth-token": storage.getItem("jwt-auth-token"),
          }
        })
        .then((res) => {
          if(res.data.boardDetail.boardId > 0 ) {
            this.board.boardId = res.data.boardDetail.boardId;
            // this.board.boardCategory = res.data.boardDetail.boardCategory;
            this.board.boardWriter = res.data.boardDetail.boardWriter;
            this.board.boardTitle = res.data.boardDetail.boardTitle;
            this.board.boardContent = res.data.boardDetail.boardContent;
            this.board.boardDate = res.data.boardDetail.boardDate;
            this.board.boardView = res.data.boardDetail.boardView;
            this.board.boardLiked = res.data.boardDetail.boardLiked;
            this.isLiked = res.data.didILiked;
          } else {
            console.log('아예 받아오질 못함.');
          }
        })
        .catch((error) => {
          console.log(error);
        })
      },
    }
  }
</script>

<style>

</style>