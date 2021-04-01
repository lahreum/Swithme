<template>
  <div>
      <div>커뮤니티 > 취업</div>
      <hr>
      <p>아니 이번 실기문제 뭐야? 이상해..</p>
      <div>
        <div style="float:left;">정처기out</div>
        <div style="float:left;">2021.03.13 12:10</div>
        <div style="float:left;">조회수 29</div><br>
      </div>
      <hr>
      <v-textarea
          value="The Woodman set to work at once, and so sharp was his axe that the tree was soon chopped nearly through."
        ></v-textarea>
      <CommentInputBar/>
  </div>
</template>

<script>
import CommentInputBar from '@/components/common/CommentInputBar.vue';
import "./community.css";

  export default {
    components: {
      CommentInputBar,
    }
  }
</script>

<style>

</style>