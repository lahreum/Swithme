<template>
  <div class="home-container">
    <!-- <study-btn style="position: fixed; left: 450px; top: 550px;"></study-btn> -->
    <div class="home-description-1">
      <div style="font-size: 7.5rem; font-weight: bold;">
        Study
      </div>
      <div style="font-size: 7.5rem; margin-top: -4rem; font-weight: bold;">
        With me
      </div>
      <div style="font-size: 1.5rem; margin-top: -1.5rem;">
        스윗미는 플랫폼 중심의 인공지능 학습관리 서비스입니다.
      </div>
    </div>
  </div>
</template>

<script>
// import StudyBtn from '../common/StudyBtn.vue';
export default {
  components: {
    // 'study-btn': StudyBtn,
  },
};
</script>

<style scoped>
.home-container {
  height: 100%;
  background-image: url(../../assets/img/bg/home1.jpg);
  background-position: top center;
}
.home-description-1 {
  position: relative;
  left: 10%;
  top: 18%;
  letter-spacing: -1px;
  color: white;
}
</style>
