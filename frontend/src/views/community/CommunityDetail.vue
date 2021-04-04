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
      <!-- 댓글 입력창 -->
      <v-container class="comment">
            <v-textarea
            v-model="newReply"
            outlined
            color="#673fb4"
            placeholder="댓글을 남겨보세요."
            @keypress.enter="addComment">
                <template v-slot:append>
                    <v-btn
                        color="#E0E0E0"
                        class="registerBtn"
                        @click="addComment" 
                        style="margin-top:35px;color:white;border-radius: 30px;"
                        >
                        등록
                    </v-btn>
                </template>
            </v-textarea>
        </v-container>
      <!-- 댓글 갯수 -->
      <p class="commentInfo"><span class="countNumber" style="padding-bottom:20px;"> {{ replyCnt }} </span> 개의 댓글</p>
      <v-divider></v-divider>
      <CommentItem 
        v-for="reply in calData" 
        :talk="reply"
        v-bind:key="reply.replyId"/>
      <v-pagination
      v-model="curPageNum" color="#673fb4"
      :length="numOfPages"
      circle
      ></v-pagination>
      <div style="text-align:center;">
        <div v-if="isWriter" class="oneBtn" @click="$router.push('/community/community-modify')"><AppBtnMiddle :btnColor="'#673fb4'" :btnName="'수정'" :btnNameColor="'white'"/></div>
        <div v-if="isWriter" class="oneBtn" @click="deleteBoard"><AppBtnMiddle :btnColor="'#424242'" :btnName="'삭제'" :btnNameColor="'white'"/></div>
        <div class="oneBtn" @click="$router.push('/community')"><AppBtnMiddle :btnColor="'#616161'" :btnName="'목록'" :btnNameColor="'white'"/></div>
      </div>
  </div>
</template>

<script>
import CommentItem from '@/components/common/CommentItem.vue';
import AppBtnMiddle from '@/components/common/AppBtnMiddle.vue';
import "./community.css";
const storage = window.sessionStorage;
  export default {
    components: {
      CommentItem,
      AppBtnMiddle,
    },
    data: function() {
      return {
        isLiked: false,
        isWriter: false,
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
        replyList: [],
        replyCnt: 0,
        newReply: '',
        curPageNum: 1,
        dataPerPage: 3,
      }
    },
    created() {
      this.getBoardDetail();
      this.getBoardReplyList();
      
    },
    computed: {
      startOffset() {
        return ((this.curPageNum - 1) * this.dataPerPage);
      },
      endOffset() {
        return (this.startOffset + this.dataPerPage);
      },
      numOfPages() {
        return Math.ceil(this.replyList.length / this.dataPerPage);
      },
      calData() {   // 보여지고 있는 page의 숫자와 page당 보여질 data의 갯수에 따라서 계산된 startOffset과 endOffset을 이용해 slice 하여 return
        return this.replyList.slice(this.startOffset, this.endOffset)
      }
    },
    methods: {
      addLike() { 
        console.log('addLike 함수 안에는 들어감');
        console.log(`${this.$store.state.boardDetailId}`);
        this.$Axios
        .post('community/liked?boardId=' + `${this.$store.state.boardDetailId}`,{
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
        .delete('community/liked?boardId=' + `${this.$store.state.boardDetailId}`, {
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
        .get('community/board/' + `${this.$store.state.boardDetailId}`, {
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
            this.getWriterInfo();
          } else {
            console.log('아예 받아오질 못함.');
          }
        })
        .catch((error) => {
          console.log(error);
        })
      },
      getBoardReplyList() {
        this.$Axios
        .get('community/reply?boardId=' + `${this.$store.state.boardDetailId}`,{
          headers: {
            "jwt-auth-token": storage.getItem("jwt-auth-token"),
          }
        })
        .then((res)=>{
          if(Object.keys(res.data.replyList).length > 0) {
            this.replyList = res.data.replyList;
            this.replyCnt = Object.keys(res.data.replyList).length;
          } else {
            this.replyList = [];
            this.replyCnt = 0;
          }
        })
        .catch((error)=>{
          console.log(error);
        })
      },
      addComment() {
        console.log(this.newReply);
        this.$Axios
        .post('community/reply?boardId=' + `${this.$store.state.boardDetailId}` + '&content=' + this.newReply,{
          headers: {
            "jwt-auth-token": storage.getItem("jwt-auth-token"),
          }
        })
        .then((res)=>{
          if(res.data.success) {
            console.log('댓글 입력 성공!');
          } else {
            console.log('댓글 입력 실패');
          }
        })
        .catch((error)=>{
          console.log(error);
        })
      },
      getWriterInfo() {
        if(`${this.$store.state.user.userNickname}` ===  this.board.boardWriter){
          this.isWriter = true;
        } else {
          this.isWriter = false;
        }
      },
      deleteBoard() {
        this.$Axios
        .delete('/community/board/' + `${this.$store.state.boardDetailId}`,{
          headers: {
            "jwt-auth-token": storage.getItem("jwt-auth-token"),
          }
        })
        .then((res)=>{
          if(res.data.success) {
            alert('게시글 삭제 완료');
            this.$router.push('/community');
          } else {
            console.log('게시글 삭제 실패');
          }
        })
        .catch((error)=>{
          console.log(error);
        })
      }
    }
  }
</script>

<style>

</style>