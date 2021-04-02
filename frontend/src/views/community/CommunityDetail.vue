<template>
  <div>
      <div class="section">커뮤니티 > 취업</div>
      <v-divider></v-divider>
      <p class="boardTitle">아니 이번 실기문제 뭐야? 이상해..</p>
      <div>
        <div class="boardInfo" style="float:left;"><strong>정처기out</strong></div>
        <div class="boardInfo" style="float:left;">2021.03.13 12:10</div>
        <div class="boardInfo" style="float:left;">조회수 29</div><br><br>
      </div>
      <v-divider></v-divider>
      <div class="boardContent">
        <p>이번 1번 문제 답 애자일인가요?</p>
        <div class="heart">좋아요 1개 <v-icon @click="addLike" v-if="isLiked">mdi-heart-outline</v-icon><v-icon @click="addLike" v-if="!isLiked">mdi-heart</v-icon></div>
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

  export default {
    components: {
      CommentInputBar,
      CommentItem,
    },
    data: function() {
      return {
        isLiked: false,
      }
    },
    methods: {
      addLike() {
        this.isLiked = !this.isLiked;
      }
    }
  }
</script>

<style>

</style>