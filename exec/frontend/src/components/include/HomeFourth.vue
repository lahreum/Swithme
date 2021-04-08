<template>
  <div class="home-container" style="background-color: black;">
    <!-- 제일 왼쪽 설명 부분 -->
    <div class="home-description-4">
      <div style="font-size: 3.2rem;">스윗미는</div>
      <div style="font-size: 3.2rem; margin-top: -1rem;">
        <strong>행동분석</strong>을 통해
      </div>
      <div style="font-size: 3.2rem; margin-top: -1rem;">
        <strong>공부패턴과 학습</strong>을
      </div>
      <div style="font-size: 3.2rem; margin-top: -1rem;">
        관리해드립니다.
      </div>
    </div>
    <!-- 비디오 부분 -->
    <div class="home-video-4" style="z-index: 100;">
      <video
        src="../../assets/img/bg/home4.mp4"
        width="50%"
        autoplay
        muted
        loop
        data-keepplaying
      ></video>
    </div>
    <!-- Manage your time! -->
    <div
      style="z-index: 1000; color: #ffffff85; font-size: 2.5rem; position: relative; left: 70%; top: -30%; font-style: italic;"
    >
      Manage your time!
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style scoped>
.home-container {
  height: 100%;
}
.home-video-4 {
  position: relative;
  left: 50%;
  top: 5%;
}
.home-description-4 {
  position: relative;
  left: 10%;
  top: 25%;
  letter-spacing: -1px;
  color: #ffffff;
  z-index: 1000;
}
.big-word {
  font-size: 4.2rem;
  letter-spacing: -1px;
  font-weight: 300;
  margin-top: -1.2rem;
  margin-bottom: 1.7rem;
}
.small-word {
  font-size: 1.5rem;
  letter-spacing: -1px;
  color: #434343;
  font-weight: lighter;
}
</style>
