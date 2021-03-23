<template>
  <div class="talk">
    <!-- 댓글 갯수 -->
    <span class="countNumber"> 5 </span> 개의 댓글
    <!-- 댓글리스트 -->
    <div class="box">
      <article class="media">
          <div class="content">
            <!-- 닉네임 -->
            <p>
              <strong>닉네임</strong>
            </p>
            <!-- 댓글 내용 -->
            <p v-if="!modifying">댓글내용</p>
            <!-- 댓글 수정시 -->
            <div v-if="modifying">
              <v-col cols="12" md="6">
                <v-textarea
                  v-if="talk.reviewCommentId == modifyNumber"
                  name="modify"
                  rows="1"
                  row-height="5"
                  auto-grow
                  v-model="modifyContent"
                ></v-textarea>
              </v-col>
            </div>
            <!-- 수정중 아닐때, 수정 | 삭제 메뉴보임 -->
            <!-- <p v-if="!modifying">{{ talk.commentContent }}</p> -->
            <div v-if="!modifying">
              <span
                class="option"
                >수정</span>
              <span
                class="option"
                >삭제</span>
            </div>
            <!-- 수정중일때, 저장 취소 버튼 -->
            <div v-if="modifying">
              <span
                @click="modifyComment(modifyNumber, modifyContent)"
                v-if="talk.userNickname == nickname"
                class="option"
                >저장</span>
              <span
                @click="toggleModify"
                v-if="talk.userNickname == nickname"
                class="option"
                >취소</span>
            </div>
          </div>
      </article>
    </div>
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
      // 댓글 수정
      this.toggleModify();

    },
    modifyComment() {
      
    },
    deleteComment() {

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
  box-shadow: 0 2px 3px rgba(10,10,10,0.1), 0 0 0 1px rgba(10,10,10,0.1);
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