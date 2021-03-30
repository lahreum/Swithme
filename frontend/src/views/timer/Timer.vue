<template>
  <div style="width:100%;">
    <v-container class="outbox">
      <v-row no-gutters>
          <!-- 왼쪽 스트리밍 화면 -->
          <v-col cols="9">
            <image-stream />
            <!-- <v-img src="https://ifh.cc/g/GribTx.jpg" max-width="800" style="background-size: contain;"></v-img> -->
            <v-img class="timerLogo" src="@/assets/img/logo_bl.png"></v-img>
            <time-bar class="timer"></time-bar>
          </v-col>

            <!-- 오른쪽 투두리스트 화면 -->
          <v-col cols="3">
            <div style="background-color: #EEEEEE;">
              <!-- 회원정보 -->
              <div>
                <div class="user">
                  <profile-normal
                    class="profile"
                    :src="require(`@/assets/img/avatars/iu.jpg`)"
                  ></profile-normal>
                </div>
                <div class="user">
                  <span><strong>dlwlrma</strong></span
                  ><br />
                  <span>한국대학교 23학번:)</span>
                </div>
              </div>
              <!-- 투두리스트 -->
              <div>
                <p class="todolist">TO DO LIST</p>
              </div>
              <v-card style="border-radius:60%;">
                <p class="date">2021/03/25</p>
                <todo-list></todo-list>
                <br>
                <div @click="$router.push('/')" style="text-align:center;">
                  <app-btn-large
                  :btnColor="`#424242`"
                  :btnName="'그만하기'"
                  :btnNameColor="'white'"
                  ></app-btn-large>
                </div>
                <br>
              </v-card>
            </div>
          </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>
import ImageStream from '@/components/common/ImageStream.vue';
import TodoList from '@/components/common/TodoList.vue';
import ProfileNormal from '@/components/common/ProfileNormal.vue';
import AppBtnLarge from '@/components/common/AppBtnLarge.vue';
import TimeBar from '@/components/common/TimeBar.vue';

export default {
  components: {
    'image-stream': ImageStream,
    'todo-list': TodoList,
    'profile-normal': ProfileNormal,
    'app-btn-large': AppBtnLarge,
    'time-bar': TimeBar,
  },
};
</script>

<style scoped>
.outbox {
  width: 100%;
  padding: 0;
}
.date {
  font-size: 25px;
  text-align: center;
  padding-top: 20px;
}
.todolist {
  font-size: 30px;
  padding-left: 20px;
  margin-top: 20px;
}
.user {
  display: inline-block;
  font-size: 18px;
  vertical-align: middle;
  padding-left: 7px;
  padding-top: 10px;
  /* float:left; */
}
.profile {
  padding-left: 10px;
}
.timer {
  vertical-align: middle;
  position: absolute;
  bottom: 5%;
  left: 33%;
}
.timerLogo {
  vertical-align: middle;
  position: absolute;
  top: 10px;
  left: 10px;
  max-width: 150px;
}
.container{
 Max-width : 100vw !important;
}
</style>
