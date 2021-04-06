<template>
  <div class="talk">
    <div class="box">
      <article class="media">
          <div class="content">
            <!-- 닉네임 -->
            <p>
              <strong>{{ talk.replyWriter }}</strong>
            </p>
            <!-- 댓글 내용 -->
            <p v-if="!modifying">{{ talk.replyContent }}</p>
            <div v-if="modifying">
                <v-textarea
                  rows="5"
                  height="100"
                  style="width:60vw"
                  auto-grow
                  v-model="modifyContent"
                >{{ talk.replyContent }}</v-textarea>
            </div>
            <p style="color:#BDBDBD;">{{ talk.replyDate }}</p>
            <!-- 댓글 작성자와 로그인 정보가 같을때 댓글 수정가능 -->
            <!-- 수정중 아닐때, 수정 | 삭제 메뉴보임 -->
            <div v-if="isWriter&&!modifying">
              <span
                class="option" @click="toggleModify"
                >수정</span> 
              <span
                class="option" style="padding-left:10px;" @click="deleteComment"
                >삭제</span>
            </div>
            <!-- 수정중일때, 저장 취소 버튼 -->
            <div v-if="isWriter&&modifying">
              <p
                @click="modifyComment"
                class="option"
                >저장 
              <span
                @click="cancelComment"
                class="option" style="padding-left:10px;"
                >취소</span></p>
            </div>
          </div>
      </article>
    </div>
    <v-divider></v-divider>
  </div>
</template>

<script>
const storage = window.sessionStorage;

export default {
  
  props: {
    talk: Object,
  },
  data() {
    return {
      modifying: false,
      modifyContent: '',
      isWriter: false,
    }
  },
  created() {
    this.modifyContent = this.talk.replyContent;
    this.checkWriter();
  },
  methods: {
    toggleModify() {
      console.log('toggle!!!!!!!!!');
      this.modifying = !this.modifying;
    },
    checkWriter() {
      if(this.talk.replyWriter === `${this.$store.state.user.userNickname}`) {
        this.isWriter = true;
      } else {
        this.isWriter = false;
      }
    },  
    deleteComment() {
      this.$Axios
      .delete('community/reply?replyId=' + this.talk.replyId,{
        headers: {
            'jwt-auth-token': storage.getItem('jwt-auth-token'),
          },
      })
      .then((res)=>{
        if(res.data.success) {
          alert('댓글 삭제 성공');
          window.location.reload();
        } else {
          alert('댓글 삭제 실패');
        }
      })
      .catch((error)=>{
        console.log(error);
      })
    },
    modifyComment() {
      let params = new URLSearchParams();
      params.append('content', this.modifyContent);
      params.append('replyId', this.talk.replyId);

      this.$Axios
      .put('community/reply', params, {
        headers: {
          'jwt-auth-token': storage.getItem('jwt-auth-token'),
        },
      })
      .then((res)=>{
        if(res.data.success) {
          console.log('수정 성공');
          this.toggleModify();
          this.$emit('modified', true);
        } else {
          console.log('수정 실패');
        }
      })
      .catch((error)=>{
        console.log(error);
      })
    },
    cancelComment(){
      this.toggleModify();
      this.modifyContent = this.talk.replyContent;
    }
  }
}
</script>

<style>
.countNumber{
  color: #673fb4;
}
.talk {
  background-color: #fff;
  color: #373737;
  /* box-shadow: 0 2px 3px rgba(10,10,10,0.1), 0 0 0 1px rgba(10,10,10,0.1); */
  padding: 20px;
}
.media {
  display: flex;
}
.option {
  color: gray;
  font-size: 10px;
  font-weight: 600;
  margin-right: 10px;
}
</style>