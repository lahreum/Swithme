<template>
  <div class="home-container">
    <!-- 제일 왼쪽 설명 부분 -->
    <div class="home-description-3">
      <div style="font-size: 3.2rem;">스윗미는</div>
      <div style="font-size: 3.2rem; margin-top: -1rem;">
        <strong>인공지능</strong>을 통해
      </div>
      <div style="font-size: 3.2rem; margin-top: -1rem;">
        <strong>정확한 집중 시간</strong>을
      </div>
      <div style="font-size: 3.2rem; margin-top: -1rem;">
        측정해줍니다.
      </div>
      <div style="font-size: 1.2rem; font-weight: bold; margin-top: -0.2rem;">
        <u>튜토리얼 보러가기</u>
      </div>
    </div>
    <!-- 비디오 부분 -->
    <div class="home-video-3-1">
      <video
        src="../../assets/img/bg/home3-1.mp4"
        width="50%"
        autoplay
        muted
        loop
        data-keepplaying
      ></video>
    </div>
    <hr style="width: 700px; position: relative; left: 25%;" />
    <div class="home-video-3-2">
      <video
        src="../../assets/img/bg/home3-2.mp4"
        width="40%"
        autoplay
        muted
        loop
        data-keepplaying
      ></video>
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
.home-video-3-1 {
  position: relative;
  left: 40%;
  top: -20%;
}
.home-video-3-2 {
  position: relative;
  left: 60%;
  top: -40%;
}
.home-description-3 {
  position: relative;
  left: 10%;
  top: 25%;
  letter-spacing: -1px;
  color: #333333;
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
