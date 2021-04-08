<template>
  <img id="stream" />
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      constaints: {
        width: { min: 640, max: 640 },
        height: { min: 402, max: 402 },
      },
      awayCnt: 0,
      phoneCnt: 0,
      sleepCnt: 0,
      talkCnt: 0,
    };
  },
  mounted() {
    // Element 생성
    let img = document.querySelector('#stream');
    let canvas = document.createElement('canvas');

    // canvas 크기 설정
    canvas.width = 640;
    canvas.height = 402;

    // 클라이언트의 카메라를 녹화할 수 있도록 요청
    // 허용할 경우 MediaStream 객체를 얻어 Stream 가능
    navigator.mediaDevices
      .getUserMedia({ video: true })
      .then((stream) => {
        // 클라이언트의 카메라(track) 정보 불러오기
        const track = stream.getVideoTracks()[0];

        // track에 제약 조건을 적용
        track
          .applyConstraints(this.constaints)
          .then(() => {
            // 이미지 캡처 주기 설정(0.05초)
            let period = 50;

            // 1초마다 서버 요청을 하기 위한 카운트 생성
            let cnt = 0;

            // 이미지를 캡처하기 위한 객체 생성
            const imageCapture = new ImageCapture(track);

            // canvas의 컨텍스트 불러오기
            let ctx = canvas.getContext('2d');

            // 서버로 데이터를 전송할 폼 생성
            let firstData = new FormData();

            imageCapture
              .grabFrame()
              .then((imageBitmap) => {
                ctx.drawImage(imageBitmap, 0, 0);

                canvas.toBlob((blob) => {
                  firstData.append('image', blob);

                  axios
                    .post('https://j4b103.p.ssafy.io/aipredict', firstData)
                    .then(() => {
                      track.stop();

                      // 설정한 주기마다 이미지를 캡처
                      this.capture(img, canvas, period, cnt, ctx);
                    })
                    .catch((error) => {
                      console.log(error);
                    });
                });
              })
              .catch(() => {});
          })
          .catch((error) => {
            console.log(error);
          });
      })
      .catch((error) => {
        console.log(error);
      });
  },
  methods: {
    capture(img, canvas, period, cnt, ctx) {
      navigator.mediaDevices.getUserMedia({ video: true }).then((stream) => {
        // 클라이언트의 카메라(track) 정보 불러오기
        const track = stream.getVideoTracks()[0];

        // track에 제약 조건을 적용
        track.applyConstraints(this.constaints).then(() => {
          // 카메라가 켜졌다는 신호
          this.$store.commit('setIsStartCam', true);
          this.$emit('startCam');

          // 이미지를 캡처하기 위한 객체 생성
          const imageCapture = new ImageCapture(track);

          let interval = setInterval(() => {
            imageCapture
              .grabFrame()
              .then((imageBitmap) => {
                if (!this.$store.state.isStartCam) {
                  clearInterval(interval);
                  track.stop();
                }

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
                      .then((response) => {
                        // 객체 감지 결과
                        let detectResult = response.data;
                        let isPerson = false;
                        let isPhone = false;
                        let isFace = false;
                        let personNum = 0;
                        let faceNum = 0;

                        for (let i = 0; i < detectResult.length; i++) {
                          if (detectResult[i] == 'person') {
                            isPerson = true;
                            personNum++;
                          } else if (detectResult[i] == 'phone') isPhone = true;
                          else if (detectResult[i] == 'face') {
                            isFace = true;
                            faceNum++;
                          }
                        }

                        if (!isPerson) {
                          this.awayCnt++;
                          this.$emit('awayState');
                        } else {
                          if (
                            this.awayCnt >= 5 &&
                            !this.$store.state.user.isStudying
                          )
                            this.$emit('resumeTimer');
                          this.awayCnt = 0;
                          this.$emit('awayEnd');
                        }

                        if (!isPhone) {
                          if (
                            this.phoneCnt >= 5 &&
                            !this.$store.state.user.isStudying
                          )
                            this.$emit('resumeTimer');
                          this.phoneCnt = 0;
                          this.$emit('phoneEnd');
                        } else {
                          this.phoneCnt++;
                          this.$emit('phoneState');
                        }

                        if (!isFace && isPerson) {
                          this.sleepCnt++;
                          this.$emit('sleepState');
                        } else {
                          if (
                            this.sleepCnt >= 5 &&
                            !this.$store.state.user.isStudying
                          )
                            this.$emit('resumeTimer');
                          this.sleepCnt = 0;
                          this.$emit('sleepEnd');
                        }

                        if (personNum > 1 || faceNum > 1) {
                          this.talkCnt++;
                          this.$emit('talkState');
                        } else if (personNum == 1 && faceNum == 1) {
                          if (
                            this.talkCnt >= 5 &&
                            !this.$store.state.user.isStudying
                          )
                            this.$emit('resumeTimer');
                          this.talkCnt = 0;
                          this.$emit('talkEnd');
                        }

                        console.log(
                          '자리비움 카운트: ' +
                            this.awayCnt +
                            ', 핸드폰 카운트: ' +
                            this.phoneCnt +
                            ', 졸음 카운트: ' +
                            this.sleepCnt +
                            ', 잡담 카운트: ' +
                            this.talkCnt
                        );

                        if (this.awayCnt == 5) {
                          // 5초동안 자리를 비워 타이머 중지
                          this.$store.commit('setAwayTime');
                          this.$emit('pauseTimer');
                        } else if (this.awayCnt > 5) {
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

                        if (this.sleepCnt == 5) {
                          // 5초동안 졸아 타이머 중지
                          this.$store.commit('setSleepTime');
                          this.$emit('pauseTimer');
                        } else if (this.sleepCnt > 5) {
                          // 이후부터는 졸음 시간 누적
                          this.$store.commit('setSleepTime');
                        }

                        if (this.talkCnt == 5) {
                          // 5초동안 잡담하여 타이머 중지
                          this.$store.commit('setTalkTime');
                          this.$emit('pauseTimer');
                        } else if (this.talkCnt > 5) {
                          // 이후부터는 잡담 시간 누적
                          this.$store.commit('setTalkTime');
                        }

                        console.log(
                          '자리비움 누적 시간: ' +
                            this.$store.getters.getAwayTime
                        );

                        console.log(
                          '핸드폰 누적 시간: ' +
                            this.$store.getters.getPhoneTime
                        );

                        console.log(
                          '졸음 누적 시간: ' + this.$store.getters.getSleepTime
                        );

                        console.log(
                          '잡담 누적 시간: ' + this.$store.getters.getTalkTime
                        );

                        // 이미지 출력
                        img.src = canvas.toDataURL();

                        // 공부 중일 경우 타이머 1초 증가
                        if (this.$store.state.user.isStudying)
                          this.$emit('runningTimer');

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
        });
      });
    },
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
