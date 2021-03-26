<template>
  <div class="home-container">
    <!-- 제일 왼쪽 설명 부분 -->
    <div class="home-description-2">
      <div style="font-size: 3.2rem;">스윗미는</div>
      <div style="font-size: 3.2rem; font-weight: bold; margin-top: -1rem;">
        플랫폼 중심의
      </div>
      <div style="font-size: 3.2rem; font-weight: bold; margin-top: -1rem;">
        인공지능
      </div>
      <div style="font-size: 3.2rem; font-weight: bold; margin-top: -1rem;">
        학습 도우미
      </div>
      <div style="font-size: 3.2rem; margin-top: -1rem;">서비스 입니다.</div>
    </div>
    <!-- 가운데 회사 정보 부분 -->
    <div class="center-description-2">
      <div class="small-word">
        Founded in
      </div>
      <div class="big-word">
        2021
      </div>
      <hr
        style="width: 350px; position: relative; left: -200px; top: -4.5rem;"
      />
      <div class="small-word">
        Members
      </div>
      <div class="big-word">
        5+
      </div>
      <div class="small-word">
        Client
      </div>
      <div class="big-word">
        1,000+
      </div>
    </div>
    <!-- 비디오 부분 -->
    <div class="home-video-2">
      <video
        id="home-video"
        src="../../assets/img/bg/home2.mp4"
        width="800px"
        height="650px"
        autoplay
        muted
        loop
      ></video>
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
}
.home-video-2 {
  position: relative;
  left: 55%;
  top: -60%;
}
.home-description-2 {
  position: relative;
  left: 10%;
  top: 25%;
  letter-spacing: -1px;
  color: #333333;
}
.center-description-2 {
  position: relative;
  left: 40%;
  top: -15%;
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
