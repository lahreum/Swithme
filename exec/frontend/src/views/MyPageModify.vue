<template>
  <div>
    <middle-nav v-bind:propsdata="navInfo"></middle-nav>
    <v-container style="padding-top: 3%; ">
      <!-- 상단 정보 -->
      <v-row no-gutters style="font-size: 1.8rem;" justify="center">
        회원정보 수정
      </v-row>
      <v-row no-gutters justify="center" style="margin-top: 5px;">
        <hr
          style="width: 80%; border: 0; height: 2px; background-color: black; "
        />
      </v-row>
      <!-- 프로필 사진 -->
      <v-row
        no-gutters
        justify="center"
        style="margin-top: 40px; margin-left: 40px;"
      >
        <div v-if="isDefault" @click="onClickImageUpload">
          <profile-large
            :src="'data:image/png;base64,' + user.profileImg"
          ></profile-large>
        </div>
        <div v-else @click="onClickImageUpload">
          <profile-large :src="tmpProfileImg"></profile-large>
        </div>
        <v-btn
          class="btn-profile-edit"
          depressed
          fab
          dark
          small
          color="#673fb4"
          @click="saveProfileImg"
        >
          <v-icon dark>mdi-content-save</v-icon>
        </v-btn>
        <input ref="imageInput" type="file" hidden @change="onChangeImages" />
      </v-row>
      <!-- 기타 정보 -->
      <div style="padding-left: 15%; padding-right: 15%; margin-top: 50px;">
        <!-- 닉네임 -->
        <v-row class="mypage-item" style="margin-top: 30px;">
          <v-col class="mypage-key" cols="3">
            <v-row no-gutters>
              닉네임
            </v-row>
            <v-row
              no-gutters
              v-show="warningNickname"
              style="letter-spacing: -1px; color: red; font-size: 0.8rem; margin-top: 5px;"
              >8자리 이하의 문자</v-row
            >
          </v-col>

          <v-col class="pa-0">
            <v-form ref="nickname">
              <v-row no-gutters>
                <v-col
                  class="mypage-value pa-0"
                  style="margin-right: 10px; margin-bottom: 10px;"
                  @mouseover="warningNickname = true"
                  @mouseleave="warningNickname = false"
                >
                  <input-bar
                    :rules="nicknameRules"
                    :placeholder="user.nickname"
                    @pass-input="getNewNickname"
                  ></input-bar>
                </v-col>
                <v-col class="pa-0" cols="2" align="center">
                  <div @click="nicknameRequest" style="width: 100px; ">
                    <app-btn-large
                      :btnColor="'#673fb4'"
                      :btnName="'저 장'"
                      :btnNameColor="'white'"
                    ></app-btn-large>
                  </div>
                </v-col>
              </v-row>
            </v-form>
          </v-col>
        </v-row>
        <hr style="border: 0; height: 1px; background-color: #d9d9d9; " />
        <!-- 아이디(이메일) -->
        <v-row class="mypage-item" style="margin-top: 30px;">
          <v-col class="mypage-key" cols="3">아이디(이메일)</v-col>
          <v-col class="mypage-value">
            {{ user.userId }}
          </v-col>
        </v-row>
        <hr style="border: 0; height: 1px; background-color: #d9d9d9; " />
        <!-- 비밀번호 -->
        <v-row class="mypage-item" style="margin-top: 30px;">
          <v-col class="mypage-key" cols="3">
            <v-row no-gutters>비밀번호</v-row>
            <v-row
              no-gutters
              v-if="warningPassword && isSocial"
              style="letter-spacing: -1px; color: red; font-size: 0.8rem; margin-top: 5px;"
            >
              소셜로그인 사용자는 비밀번호를 바꿀 수 없습니다.
            </v-row>
            <v-row
              no-gutters
              v-if="warningPassword && !isSocial"
              style="letter-spacing: -1px; color: red; font-size: 0.8rem; margin-top: 5px;"
              >8자리 이상의 문자와 숫자</v-row
            >
            <v-row
              no-gutters
              v-if="warningPassword && !isSocial"
              style="letter-spacing: -1px; font-weight: lighter; color: red; font-size: 0.8rem; margin-top: 5px;"
              >(변경을 원하지 않을 시 비워두세요)</v-row
            >
          </v-col>
          <v-col class="pa-0">
            <v-row no-gutters>
              <v-col
                class="mypage-value pa-0"
                style="margin-right: 10px; margin-bottom: 10px;"
                @mouseover="warningPassword = true"
                @mouseleave="warningPassword = false"
              >
                <input-bar
                  :type="'password'"
                  :placeholder="'새로운 비밀번호'"
                  :isDisabled="isSocial"
                  :rules="passwordRules"
                  @pass-input="getNewPassword"
                ></input-bar>
              </v-col>
              <v-col class="pa-0" cols="2" align="center">
                <div style="width: 100px; "></div>
              </v-col>
            </v-row>
            <v-form ref="password">
              <v-row no-gutters>
                <v-col
                  class="mypage-value pa-0"
                  style="margin-right: 10px;"
                  @mouseover="warningPassword = true"
                  @mouseleave="warningPassword = false"
                >
                  <input-bar
                    :isDisabled="isSocial"
                    :type="'password'"
                    :rules="passwordConfirmRules"
                    :placeholder="'비밀번호 확인'"
                  ></input-bar>
                </v-col>
                <v-col class="pa-0" cols="2" align="center">
                  <div style="width: 100px;" @click="passwordRequest">
                    <app-btn-large
                      :isDisabled="isSocial"
                      :btnColor="'#673fb4'"
                      :btnName="'저 장'"
                      :btnNameColor="'white'"
                    ></app-btn-large>
                  </div>
                </v-col>
              </v-row>
            </v-form>
          </v-col>
        </v-row>
        <hr style="border: 0; height: 1px; background-color: #d9d9d9; " />
        <!-- 상태 메시지 -->
        <v-row class="mypage-item" style="margin-top: 30px;">
          <v-col class="mypage-key" cols="3">상태 메시지</v-col>
          <v-col class="pa-0">
            <v-row no-gutters>
              <v-col class="mypage-value pa-0" style="margin-right: 10px;">
                <input-bar
                  :placeholder="this.user.message"
                  @pass-input="getNewMessage"
                ></input-bar>
              </v-col>
              <v-col class="pa-0" cols="2" align="center">
                <div @click="messageRequest" style="width: 100px; ">
                  <app-btn-large
                    :btnColor="'#673fb4'"
                    :btnName="'저 장'"
                    :btnNameColor="'white'"
                  ></app-btn-large>
                </div>
              </v-col>
            </v-row>
          </v-col>
        </v-row>
        <hr style="border: 0; height: 1px; background-color: #d9d9d9; " />
      </div>
      <v-row no-gutters justify="center" style="margin-top: 50px;">
        <v-col
          cols="2"
          @click="goRouting"
          @mouseover="warningClose = true"
          @mouseleave="warningClose = false"
        >
          <app-btn-large
            :btnColor="'#424242'"
            :btnName="'닫 기'"
            :btnNameColor="'white'"
          ></app-btn-large>
        </v-col>
      </v-row>
      <v-row
        no-gutters
        justify="center"
        style="height: 30px; letter-spacing: -1px; color: red; font-size: 0.8rem; margin-top: 5px;"
        v-show="warningClose"
      >
        변경사항을 각각 저장하고 닫기를 눌러주세요.
      </v-row>
      <v-row
        no-gutters
        style="height: 30px; margin-top: 5px;"
        v-show="!warningClose"
      ></v-row>
    </v-container>
  </div>
</template>

<script>
import MiddleNav from "../components/include/MiddleNav.vue";
import ProfileLarge from "@/components/common/ProfileLarge.vue";
import AppBtnLarge from "@/components/common/AppBtnLarge.vue";
import InputBar from "@/components/common/InputBar.vue";

const storage = window.sessionStorage;
export default {
  components: {
    "middle-nav": MiddleNav,
    "profile-large": ProfileLarge,
    "app-btn-large": AppBtnLarge,
    "input-bar": InputBar,
  },
  created: function() {
    this.$Axios
      .create({
        headers: { "jwt-auth-token": this.$route.query.jwt },
      })
      .get("user")
      .then((response) => {
        console.log("USER-INFO: ", response);
        this.user.nickname = response.data.data.userNickname;
        this.user.userId = response.data.data.userId;
        this.user.message = response.data.data.userMessage;
        this.user.profileImg = response.data.profileImg;
        this.tmpProfileImg = response.data.profileImg;
        if (response.data.data.userType != undefined) {
          this.user.userType = response.data.data.userType;
          // console.log('USER-TYPE: ', this.user.userType);
          // console.log('USER-TYPE: ', typeof response.data.data.userType);
          this.isSocial = true;
        } else {
          // console.log('USER-TYPE: ', this.user.userType);
          // console.log('USER-TYPE: ', typeof response.data.data.userType);
          this.isSocial = false;
        }
      })
      .catch((error) => {
        console.log(error);
      });
  },
  data: function() {
    return {
      navInfo: [
        "sample1.jpg",
        "마이페이지",
        "첫번째 문장입니다. 첫번째 문장입니다. 첫번째 문장입",
        "두번째 문장입니다~! 두번째 문장입니다~! 두번째 문장입니다~! 두번째",
      ],
      user: {
        nickname: "",
        userId: "",
        message: "",
        profileImg: "",
        userType: "",
      },
      new: {
        nickname: "",
        password: "",
        message: "",
        profileImg: "",
      },
      fileList: [],
      tmpProfileImg: "",
      warningClose: false,
      warningPassword: false,
      warningNickname: false,
      isDefault: true,
      isSocial: false,
      nicknameRules: [
        // (v) => v == null,
        (v) => !!v || "닉네임을 입력해주세요.",
        (v) => (v && v.length <= 8) || "닉네임은 8자 이하로 입력해주세요.",
      ],
      passwordRules: [
        (v) => !!v || "비밀번호를 입력해주세요.",
        (v) => (v && v.length >= 8) || "비밀번호는 8자 이상으로 입력해주세요.",
        (v) => /(?=.*[A-Za-z])/.test(v) || "문자와 숫자를 꼭 포함해주세요.",
        (v) => /(?=.*\d)/.test(v) || "문자와 숫자를 꼭 포함해주세요.",
      ],
      passwordConfirmRules: [
        (v) => !!v || "비밀번호를 입력해주세요.",
        (v) => v == this.new.password || "비밀번호와 일치해야해요.",
      ],
    };
  },
  methods: {
    goRouting: function() {
      this.$router.push("/my-page");
    },

    getNewNickname(value) {
      this.new.nickname = value;
    },
    getNewPassword(value) {
      this.new.password = value;
    },
    getNewMessage(value) {
      this.new.message = value;
    },
    getNewProfileImg(value) {
      this.new.profileImg = value;
    },
    messageRequest() {
      let params = new URLSearchParams();
      params.append("message", this.new.message);

      this.$Axios
        .create({
          headers: { "jwt-auth-token": storage.getItem("jwt-auth-token") },
        })
        .put("user/message", params)
        .then((response) => {
          if (response.data.success) {
            alert("새로운 상태메시지로 저장되었습니다.");
            this.user.message = this.new.message;
            window.location.reload();
            // this.new.message = '';
          } else {
            alert("상태메시지 저장 중 문제가 발생했습니다.");
          }
        })
        .catch((error) => {
          alert("상태메시지 저장 중 문제가 발생했습니다.");
          console.log(error);
        });
    },
    passwordRequest() {
      if (!this.isSocial) {
        if (this.$refs.password.validate()) {
          let params = new URLSearchParams();
          params.append("newPassword", this.new.password);
          params.append("jwt", this.$route.query.jwt);

          this.$Axios
            .put("user/password", params)
            .then((response) => {
              if (response.data.success) {
                alert("새로운 비밀번호로 변경되었습니다.");
                if (storage.getItem("jwt-auth-token") === null) {
                  alert("변경한 비밀번호로 로그인해주세요");
                  this.$router.push("/");
                }
                window.location.reload();
              } else {
                alert("비밀번호 변경 도중 오류가 발생했습니다.");
              }
            })
            .catch((error) => {
              console.log(error);
              alert("비밀번호 변경 도중 오류가 발생했습니다.");
            });
        }
      }
    },
    nicknameRequest() {
      if (this.$refs.nickname.validate()) {
        this.$Axios
          .get("user/nickname?userNickname=" + this.new.nickname)
          .then((response) => {
            if (!response.data.isPresent) {
              console.log("여기까진 들어옵니다.");
              this.nicknameRequest2();
            } else {
              alert("중복인 닉네임 입니다. 다른 닉네임을 입력해주세요.");
            }
          })
          .catch((error) => {
            console.log(error);
            alert("닉네임 변경 도중 오류가 발생했습니다. (1)");
          });
      }
    },
    nicknameRequest2() {
      console.log(this.new.nickname);
      let params = new URLSearchParams();
      params.append("newNickname", this.new.nickname);

      this.$Axios
        .create({
          headers: {
            "jwt-auth-token": storage.getItem("jwt-auth-token"),
          },
        })
        .put("user/nickname", params)
        .then((response) => {
          console.log("여기까지도 들어옵니다!");
          if (response.data.success) {
            alert("새로운 닉네임으로 변경되었습니다.");
            this.user.nickname = this.new.nickname;
            storage.setItem(
              "jwt-auth-token",
              response.headers["jwt-auth-token"]
            );
            window.location.reload();
            // this.new.nickname = '';
          } else {
            alert("닉네임 변경 도중 오류가 발생했습니다. (2)");
          }
        })
        .catch((error) => {
          console.log(error);
          alert("닉네임 변경 도중 오류가 발생했습니다. (3)");
        });
    },
    onClickImageUpload() {
      this.$refs.imageInput.click();
    },
    onChangeImages(e) {
      let file = e.target.files[0]; // Get first index in files
      this.fileList = e.target.files;
      if (e.target.files.length != 0) {
        this.isDefault = false;
        this.new.profileImg = URL.createObjectURL(file); // Create File URL
        this.tmpProfileImg = URL.createObjectURL(file);
      } else {
        this.isDefault = true;
        this.new.profileImg = this.user.profileImg;
        this.tmpProfileImg = this.user.profileImg;
      }
    },
    saveProfileImg: function() {
      if (!this.isDefault) {
        if (this.new.profileImg == "") {
          alert("프로필 이미지를 클릭해 파일을 업로드해주세요.");
        } else {
          let params = new FormData();
          params.append("file", this.fileList[0]);

          this.$Axios
            .create({
              headers: {
                "Content-Type": "multipart/form-data",
                "jwt-auth-token": storage.getItem("jwt-auth-token"),
              },
            })
            .put("user/profile-img", params)
            .then((response) => {
              if (response.data.result) {
                alert("프로필 사진이 성공적으로 변경되었습니다.");
                window.location.reload();
              } else {
                alert("프로필 변경 도중 오류가 발생했습니다.");
                window.location.reload();
              }
            })
            .catch((error) => {
              console.log(error);
              alert("에러 발생");
            });
        }
      } else {
        alert("기존 프로필 사진입니다. 변경된 사항이 없습니다.");
      }
    },
  },
};
</script>

<style scoped>
.mypage-item {
  margin-left: 0;
  margin-right: 0;
  margin-top: 20px;
  margin-bottom: 20px;
  letter-spacing: -1px;
}
.mypage-key {
  font-size: 1.1rem;
  font-weight: bold;
}
.mypage-value {
  font-size: 1rem;
  color: gray;
}
.btn-profile-edit {
  position: relative;
  left: -45px;
  top: 150px;
}
</style>
