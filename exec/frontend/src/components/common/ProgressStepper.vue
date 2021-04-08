<template>
  <!-- 뒤로가기에 따라서 isActive 변수 값들 처리해야 함 -->
  <v-row class="pg-container" no-gutters>
    <!-- 1 -->
    <v-col class="pg-item pg-order-1" align-self="center">
      <v-row no-gutters justify="center" class="pg-order-1">
        <v-avatar size="60" :color="isActive == 1 ? '#673fb4' : '#d9d9d9'">
          <span
            :class="{
              'active-num': isActive == 1,
              'default-num': !(isActive == 1),
            }"
          >
            1
          </span>
        </v-avatar>
      </v-row>
      <v-row class="pg-word" justify="center">약관동의</v-row>
    </v-col>
    <!-- dot -->
    <v-col class="pg-item pg-order-1" align-self="center">
      <v-row no-gutters justify="center">
        <v-icon :color="isActive == 1 ? 'black' : '#d9d9d9'" size="50">
          mdi-dots-horizontal
        </v-icon>
      </v-row>
    </v-col>
    <!-- 2 -->
    <v-col class="pg-item pg-order-2" align-self="center">
      <v-row no-gutters justify="center">
        <v-avatar size="50" :color="isActive == 2 ? '#673fb4' : '#d9d9d9'">
          <span
            :class="{
              'active-num': isActive == 2,
              'default-num': !(isActive == 2),
            }"
          >
            2
          </span>
        </v-avatar>
      </v-row>
      <v-row class="pg-word" justify="center">가입인증</v-row>
    </v-col>
    <!-- dot -->
    <v-col class="pg-item pg-order-2" align-self="center">
      <v-row no-gutters justify="center">
        <v-icon :color="isActive == 2 ? 'black' : '#d9d9d9'" size="50">
          mdi-dots-horizontal
        </v-icon>
      </v-row>
    </v-col>
    <!-- 3 -->
    <v-col class="pg-item pg-order-3" align-self="center">
      <v-row no-gutters justify="center">
        <v-avatar size="50" :color="isActive == 3 ? '#673fb4' : '#d9d9d9'">
          <span
            :class="{
              'active-num': isActive == 3,
              'default-num': isActive != 3,
            }"
          >
            3
          </span>
        </v-avatar>
      </v-row>
      <v-row class="pg-word" justify="center">개인정보입력</v-row>
    </v-col>
    <!-- dot -->
    <v-col class="pg-item pg-order-3" align-self="center">
      <v-row no-gutters justify="center">
        <v-icon :color="isActive == 3 ? 'black' : '#d9d9d9'" size="50">
          mdi-dots-horizontal
        </v-icon>
      </v-row>
    </v-col>
    <!-- 4 -->
    <v-col class="pg-item pg-order-4" align-self="center">
      <v-row no-gutters justify="center">
        <v-avatar size="50" :color="isActive == 4 ? '#673fb4' : '#d9d9d9'">
          <span
            :class="{
              'active-num': isActive == 4,
              'default-num': isActive != 4,
            }"
          >
            4
          </span>
        </v-avatar>
      </v-row>
      <v-row class="pg-word" justify="center">가입완료</v-row>
    </v-col>
  </v-row>
</template>

<script>
export default {
  data: function() {
    return {
      isActive: 1, // 1단계:1,  2단계:2, 3단계:3, 4단계:4
    };
  },
  methods: {
    moveStep(value) {
      this.isActive = value;
    },
  },
};
</script>

<style>
.pg-container {
  padding-left: 200px;
  padding-right: 200px;
  padding-top: 80px;
  padding-bottom: 20px;
}
.pg-word {
  color: #b2b2b2;
  letter-spacing: -1px;
  font-size: 0.8rem;
  margin: 0;
  margin-top: 10px;
}
.active-num {
  color: white;
  font-size: 1.7rem;
  /* font-weight: bold; */
}
.default-num {
  color: #673fb4;
  font-size: 1.7rem;
  /* font-weight: bold; */
}
</style>
