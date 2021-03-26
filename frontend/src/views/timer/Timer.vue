<template>
  <div>
    <v-container class="outbox">
      <v-row no-gutters>
        <v-col cols="12" sm="6" md="8">
          <!-- 왼쪽 스트리밍 화면 -->
          <image-stream />
          <!-- <v-img src="https://ifh.cc/g/GribTx.jpg" max-width="800" style="background-size: contain;"></v-img> -->
          <!-- <v-img class="logo" src="@/assets/img/logo_wh.png"></v-img> -->
          <!-- <time-bar class="timer"></time-bar> -->
        </v-col>

        <v-col cols="6" md="4">
          <!-- 오른쪽 투두리스트 화면 -->
          <div style="background-color:#EEEEEE;">
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
            <v-card style="border-radius:30px;">
              <p class="date">2021/03/25</p>
              <todo-list></todo-list>
              <!-- <router-link to="/"> -->
              <app-btn-large
                class="cancelBtn"
                :btnColor="`#424242`"
                :btnName="'그만하기'"
                :btnNameColor="'white'"
                @click="goHome()"
              ></app-btn-large>
              <!-- </router-link> -->
            </v-card>
          </div>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>
import ImageStream from '../components/common/ImageStream.vue';
import TodoList from '../components/common/TodoList.vue';
import ProfileNormal from '../components/common/ProfileNormal.vue';
import AppBtnLarge from '../components/common/AppBtnLarge.vue';
// import TimeBar from '../components/common/TimeBar.vue';

export default {
  components: {
    'image-stream': ImageStream,
    'todo-list': TodoList,
    'profile-normal': ProfileNormal,
    'app-btn-large': AppBtnLarge,
    // 'time-bar': TimeBar,
  },
  methods: {
    goHome() {
      // this.$router.push({name: 'Home'});
      this.$router.push('/');
    },
  },
};
</script>

<style>
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
.cancelBtn {
  padding: 20px;
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
  /* top:480px;
  left: 530px; */
  top: 60%;
  left: 28%;
}
.logo {
  vertical-align: middle;
  position: absolute;
  top: 10px;
  left: 12%;
  max-width: 150px;
}
</style>
