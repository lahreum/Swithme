<template>
  <!-- 
<MiddleNav.vue를 사용하는 페이지에는 아래 사항을 따를 것>
1. 상위 컴포넌트 (=페이지)에서 data에 navInfo: ['이미지 이름', '페이지 이름', '첫번째 문장', '두번째 문장'] 추가
2. 속성 이름 propsdata 으로 해서 navInfo 배열을 props 해주기
 -->
  <v-img
    max-height="300"
    :src="require(`@/assets/img/nav/${propsdata[0]}`)"
    gradient="
    90deg,
    rgba(0, 0, 0, 0.3),
    rgba(0, 0, 0, 0.1),
    rgba(0, 0, 0, 0.3)"
    position="center 20%"
  >
    <v-row no-gutters style="height: 300px;">
      <v-col align="center" align-self="center">
        <div class="desc" style="font-size: 3rem; margin-bottom: 10px;">
          <span style="margin-right: 10px; color: #673FB4;">[</span>
          {{ propsdata[1] }}
          <span style="margin-left: 10px; color: #673FB4;">]</span>
        </div>
        <div class="desc">{{ propsdata[2] }}</div>
        <div class="desc">{{ propsdata[3] }}</div>
      </v-col>
    </v-row>
  </v-img>
</template>

<script>
export default {
  props: ['propsdata'],
  data: function() {
    return {
      pageName: '',
    };
  },
};
</script>

<style>
.desc {
  letter-spacing: -1px;
  color: white;
  font-size: 1.3rem;
}
</style>
