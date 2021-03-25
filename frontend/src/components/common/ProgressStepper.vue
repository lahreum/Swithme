<template>
  <!-- 뒤로가기에 따라서 isActive 변수 값들 처리해야 함 -->
  <v-row class="pg-container" no-gutters>
    <!-- 1 -->
    <v-col class="pg-item pg-order-1" align-self="center" @click="activate1">
      <v-row no-gutters justify="center" class="pg-order-1">
        <v-avatar size="60" :color="isActive1 == true ? '#673fb4' : '#d9d9d9'">
          <span :class="{ 'active-num': isActive1, 'default-num': !isActive1 }">
            1
          </span>
        </v-avatar>
      </v-row>
      <v-row class="pg-word" justify="center">약관동의</v-row>
    </v-col>
    <!-- dot -->
    <v-col class="pg-item pg-order-1" align-self="center">
      <v-row no-gutters justify="center">
        <v-icon :color="isActive1 == true ? 'black' : '#d9d9d9'" size="50">
          mdi-dots-horizontal
        </v-icon>
      </v-row>
    </v-col>
    <!-- 2 -->
    <v-col class="pg-item pg-order-2" align-self="center" @click="activate2">
      <v-row no-gutters justify="center">
        <v-avatar size="50" :color="isActive2 == true ? '#673fb4' : '#d9d9d9'">
          <span :class="{ 'active-num': isActive2, 'default-num': !isActive2 }">
            2
          </span>
        </v-avatar>
      </v-row>
      <v-row class="pg-word" justify="center">가입인증</v-row>
    </v-col>
    <!-- dot -->
    <v-col class="pg-item pg-order-2" align-self="center">
      <v-row no-gutters justify="center">
        <v-icon :color="isActive2 == true ? 'black' : '#d9d9d9'" size="50">
          mdi-dots-horizontal
        </v-icon>
      </v-row>
    </v-col>
    <!-- 3 -->
    <v-col class="pg-item pg-order-3" align-self="center" @click="activate3">
      <v-row no-gutters justify="center">
        <v-avatar size="50" :color="isActive3 == true ? '#673fb4' : '#d9d9d9'">
          <span :class="{ 'active-num': isActive3, 'default-num': !isActive3 }">
            3
          </span>
        </v-avatar>
      </v-row>
      <v-row class="pg-word" justify="center">개인정보입력</v-row>
    </v-col>
    <!-- dot -->
    <v-col class="pg-item pg-order-3" align-self="center">
      <v-row no-gutters justify="center">
        <v-icon :color="isActive3 == true ? 'black' : '#d9d9d9'" size="50">
          mdi-dots-horizontal
        </v-icon>
      </v-row>
    </v-col>
    <!-- 4 -->
    <v-col class="pg-item pg-order-4" align-self="center" @click="activate4">
      <v-row no-gutters justify="center">
        <v-avatar size="50" :color="isActive4 == true ? '#673fb4' : '#d9d9d9'">
          <span :class="{ 'active-num': isActive4, 'default-num': !isActive4 }">
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
      isActive1: true,
      isActive2: false,
      isActive3: false,
      isActive4: false,
    };
  },
  methods: {
    activate1: function() {
      this.isActive1 = true;
      this.isActive2 = false;
      this.isActive3 = false;
      this.isActive4 = false;
      //   회원가입1 페이지로 보내야 함
      //   this.$router.push('/');
    },
    activate2: function() {
      this.isActive1 = false;
      this.isActive2 = true;
      this.isActive3 = false;
      this.isActive4 = false;
      //   회원가입2 페이지로 보내야 함
      //   this.$router.push('/');
    },
    activate3: function() {
      this.isActive1 = false;
      this.isActive2 = false;
      this.isActive3 = true;
      this.isActive4 = false;
      //   회원가입3 페이지로 보내야 함
      //   this.$router.push('/');
    },
    activate4: function() {
      this.isActive1 = false;
      this.isActive2 = false;
      this.isActive3 = false;
      this.isActive4 = true;
      //   회원가입4 페이지로 보내야 함
      //   this.$router.push('/');
    },
  },
};
</script>

<style>
.pg-container {
  padding-left: 200px;
  padding-right: 200px;
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
