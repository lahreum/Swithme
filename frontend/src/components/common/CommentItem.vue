<template>
  <div class="talk">
    
    <!-- 댓글리스트 -->
    <div class="box">
      <article class="media">
          <div class="content">
            <!-- 닉네임 -->
            <p>
              <strong>{{ talk.replyWriter }}</strong>
            </p>
            <!-- 댓글 내용 -->
            <p v-if="!modifying">{{ talk.replyContent }}</p>
            <p style="color:#BDBDBD;">{{ talk.replyDate }}</p>
            <!-- 댓글 작성자와 로그인 정보가 같을때 댓글 수정가능 -->
            <div v-if="modifying">
              <v-col cols="12" md="6">
                <v-textarea
                  v-if="false"
                  rows="1"
                  row-height="5"
                  auto-grow
                  v-model="modifyContent"
                ></v-textarea>
              </v-col>
            </div>
            <!-- 수정중 아닐때, 수정 | 삭제 메뉴보임 -->
            <div v-if="!modifying">
              <p
                class="option"
                >수정 
              <span
                class="option" style="padding-left:10px;"
                >삭제</span></p>
            </div>
            <!-- 수정중일때, 저장 취소 버튼 -->
            <div v-if="modifying">
              <p
                @click="modifyComment(modifyNumber, modifyContent)"
                v-if="talk.userNickname == nickname"
                class="option"
                >저장 
              <span
                @click="toggleModify"
                v-if="talk.userNickname == nickname"
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


export default {
  
  props: {
    talk: Object,
  },
  data() {
    return {
      modifying: false,
      modifyContent: '',
    }
  },
  methods: {
    toggleModify() {
      this.modifying = !this.modifying;
    },
    sendCommentInfo() {
      // 댓글 입력

    },
    modifyComment() {
      // 댓글 수정
      this.toggleModify();
    },
    deleteComment() {
      // 댓글 삭제
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