<template>
  <div>
    <middle-nav v-bind:propsdata="navInfo"></middle-nav>
    <br><br><br>
    <div style="width:60%; margin:auto;">
      <div>
        <div data-aos="fade-down"
          data-aos-easing="linear"
          data-aos-duration="1000">
          <h1>AI로 정확하게.</h1>
          <h3>인공지능이 사물을 감지하여<br>방해 요인, 집중 여부를<br>파악해줍니다</h3><br>
        </div>
        <div data-aos="fade-right" data-aos-duration="1500" style="float:left;">
          <v-img src="@/assets/img/tutorial1.png" width="600"></v-img>
        </div>
        <div data-aos="fade-left" data-aos-duration="1500" style="float:right;">
          <v-img src="@/assets/img/tutorial2.png" width="600"></v-img>
        </div>
      </div>
      <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
      <div>
        <div data-aos="fade-down"
          data-aos-easing="linear"
          data-aos-duration="1000">
          <h1>24시간 언제나.</h1>
          <h3>공부 외에는 SWITHME에게 맡겨주세요.<br>계속 타이머를 켜놓으면<br>자동으로 공부시간 측정 및 패턴 분석까지</h3><br>
        </div>
        <div data-aos="fade-right" data-aos-duration="1500" style="float:left;">
          <v-img src="@/assets/img/tutorial3.png" width="400"></v-img>
        </div>
        <div data-aos="fade-left" data-aos-duration="1500" style="float:right;padding-top:50px;">
          <v-img src="@/assets/img/tutorial4.png" width="400"></v-img>
        </div>
      </div>
      <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
      <div data-aos="fade-down"
        data-aos-easing="linear"
        data-aos-duration="1000">
        <h1>원하는 사람들과.</h1>
        <h3>스터디그룹을 생성해서<br>같은 시험을 준비하는 사람들과<br>함께 공부해보세요.</h3><br>
      </div>
      <div data-aos="fade-right" data-aos-duration="1500" style="float:left;">
        <v-img src="@/assets/img/tutorial5.png" width="400"></v-img>
      </div>
      <div data-aos="fade-left" data-aos-duration="1500" style="float:right;padding-top:50px;">
        <v-img src="@/assets/img/tutorial6.png" width="400"></v-img>
      </div>
      <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
      <div data-aos="fade-down"
        data-aos-easing="linear"
        data-aos-duration="1000">
        <h1>공부가 필요한 모든 순간,</h1>
        <h1>SWITHME</h1><br>
      </div>
      <div data-aos="fade-zoom-in"
          data-aos-easing="ease-in-back"
          data-aos-delay="300"
          data-aos-offset="0" style="text-align:center;">
        <v-img src="@/assets/img/nav/nav1.jpg" width="1000"></v-img>
      </div>
      <div style="text-align:center;padding-top:20px;">
        <a href="/join" id="ddd">회원가입하러가기→</a>
        <a href="/timer" id="ddd"> 공부하러가기→</a>
      </div>
    </div>
  </div>
</template>

<script>
import MiddleNav from '../components/include/MiddleNav.vue';

export default {
  components: {
    'middle-nav': MiddleNav,
  },
  data: function() {
    return {
      navInfo: [
        'nav1.jpg',
        '튜토리얼',
        '공부가 필요한 모든 순간, SWITHME와 함께하세요. ',
        '당신의 밝은 미래를 응원합니다.',
      ],
    };
  },

};
</script>

<style>
#ddd {
  color: black;
  font-size: 25px;
  padding: 30px;
  font-weight: 600;
}

</style>
