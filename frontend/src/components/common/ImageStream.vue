<template>
  <img id="stream" />
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      track: null,
      interval: null,
      awayCnt: 0,
      phoneCnt: 0,
    };
  },
  mounted() {
    // Element 생성
    let img = document.querySelector('#stream');
    let canvas = document.createElement('canvas');

    // 제약 조건 정의
    let constaints = {
      width: { min: 640, max: 640 },
      height: { min: 480, max: 480 },
    };

    // canvas 크기 설정
    canvas.width = 640;
    canvas.height = 480;

    // 클라이언트의 카메라를 녹화할 수 있도록 요청
    // 허용할 경우 MediaStream 객체를 얻어 Stream 가능
    navigator.mediaDevices
      .getUserMedia({ video: true })
      .then((stream) => {
        // 클라이언트의 카메라(track) 정보 불러오기
        this.track = stream.getVideoTracks()[0];

        // track에 제약 조건을 적용
        this.track
          .applyConstraints(constaints)
          .then(() => {
            // 이미지 캡처 주기 설정(0.05초)
            let period = 50;

            // 1초마다 서버 요청을 하기 위한 카운트 생성
            let cnt = 0;

            // 이미지를 캡처하기 위한 객체 생성
            let imageCapture = new ImageCapture(this.track);

            // canvas의 컨텍스트 불러오기
            let ctx = canvas.getContext('2d');

            // 설정한 주기마다 이미지를 캡처
            this.interval = setInterval(() => {
              imageCapture
                .grabFrame()
                .then((imageBitmap) => {
                  // 서버로 데이터를 전송할 폼 생성
                  let data = new FormData();

                  // canvas에 이미지 등록
                  ctx.drawImage(imageBitmap, 0, 0);

                  // 카운트가 1초가 되었을 경우 서버 요청
                  if (cnt != 1000) {
                    // 이미지 출력
                    img.src = canvas.toDataURL();

                    // 0.005초씩 카운트
                    cnt += period;
                  } else {
                    cnt = 0;

                    // canvas에서 이미지를 데이터로 변환
                    canvas.toBlob((blob) => {
                      // 폼에 데이터 추가
                      data.append('image', blob);

                      // 객체 감지를 위해 이미지를 인공지능 서버로 전송
                      axios
                        .post('https://j4b103.p.ssafy.io/aipredict', data)
                        // .post('http://localhost:8000/predict', data)
                        .then((response) => {
                          // 객체 감지 결과
                          let detectResult = response.data;
                          let isPerson = false;
                          let isPhone = false;

                          for (let i = 0; i < detectResult.length; i++) {
                            if (detectResult[i] == 'person') isPerson = true;
                            else if (detectResult[i] == 'phone') {
                              isPhone = true;
                              this.phoneCnt++;
                            }
                          }

                          if (!isPerson) this.awayCnt++;
                          else {
                            if (this.awayCnt >= 20) this.$emit('resumeTimer');
                            this.awayCnt = 0;
                          }

                          if (!isPhone) {
                            if (this.phoneCnt >= 5) this.$emit('resumeTimer');
                            this.phoneCnt = 0;
                          }

                          console.log(
                            '자리비움 카운트: ' +
                              this.awayCnt +
                              ' 핸드폰 카운트: ' +
                              this.phoneCnt
                          );

                          if (this.awayCnt == 20) {
                            // 20초동안 자리를 비워 타이머 중지
                            this.$store.commit('setAwayTime');
                            this.$emit('pauseTimer');
                          } else if (this.awayCnt > 20) {
                            // 이후부터는 자리비움 시간 누적
                            this.$store.commit('setAwayTime');
                          }

                          // 5초 이상 핸드폰이 감지되었을 경우
                          if (this.phoneCnt == 5) {
                            // 5초동안 자리를 비워 타이머 중지
                            this.$store.commit('setPhoneTime');
                            this.$emit('pauseTimer');
                          } else if (this.phoneCnt > 5) {
                            // 이후부터는 핸드폰 시간 누적
                            this.$store.commit('setPhoneTime');
                          }

                          console.log(
                            '자리비움 누적 시간: ' +
                              this.$store.getters.getAwayTime
                          );

                          console.log(
                            '핸드폰 누적 시간: ' +
                              this.$store.getters.getPhoneTime
                          );

                          // 이미지 출력
                          img.src = canvas.toDataURL();

                          // 0.005초씩 카운트
                          cnt += period;
                        })
                        .catch((error) => {
                          console.log(error);
                        });
                    });
                  }
                })
                .catch(() => {});
            }, period);
          })
          .catch((error) => {
            console.log(error);
          });
      })
      .catch((error) => {
        console.log(error);
      });
  },
};
</script>
<style>
#stream {
  width: 100%;
  height: 100%;
  object-fit: contain;
}
</style>
