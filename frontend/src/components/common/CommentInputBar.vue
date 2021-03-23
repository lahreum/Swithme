<template>
  <!-- 댓글 입력창 부분 -->
    <v-app id="app">
        <v-container class="comment">
            <v-textarea
            v-model="input"
            outlined
            color="#673fb4"
            placeholder="댓글을 남겨보세요."
            @keypress.enter="addComment">
                <template v-slot:append>
                    <v-btn
                        color="#E0E0E0"
                        class="registerBtn"
                        @click="addComment" style="margin-top:35px;"
                        >
                        등록
                    </v-btn>
                </template>
            </v-textarea>
        </v-container>
    </v-app>
</template>

<script>
export default {
    name: "CommentInputBar",
    data() {
        return {
            input:'',
        }
    },
    methods: {
        addComment() {
            alert(this.input);
        }
    }
}
</script>

<style scoped>
.comment {
    width:1000px;
}

.registerBtn {
    color:white;
    border-radius: 30px;
}
</style>