<template>
  <div>
    <MiddleNav v-bind:propsdata="navInfo" />
    <v-container>
      <v-row>
        <v-col cols="2"></v-col>
        <v-col>
          <h1 style="text-align:center">그룹 정보 수정</h1>
          <hr />
          <v-row align-content="center">
            <v-col style="margin-bottom:50px;" align="center">
              <v-img
                class="groupImgUpload"
                :src="'data:image/png;base64,' + imageUrl"
              ></v-img>

              <v-btn type="button" @click="onClickImageUpload"
                >이미지 업로드</v-btn
              >
              <input
                ref="imageInput"
                type="file"
                hidden
                @change="onChangeImages"
              />
            </v-col>
            <v-row
              style="height:150px; width:100%; border-bottom: 1px solid black"
              align="center"
            >
              <v-col cols="3" style="font-size: 2rem;">
                그룹이름
              </v-col>
              <v-col cols="9" style="font-size: 2rem;">
                {{ groupInfo.groupName }}
              </v-col>
            </v-row>

            <v-row
              style="height:150px;width:100%;border-bottom: 1px solid black"
              align="center"
            >
              <v-col cols="3" style="font-size: 2rem;">
                카테고리
              </v-col>
              <v-col cols="9" style="font-size: 2rem;">
                {{ category[groupInfo.groupCategory - 1].title }}
              </v-col>
            </v-row>

            <v-row
              style="height:150px;width:100%;border-bottom: 1px solid black"
              align="center"
            >
              <v-col cols="3" style="font-size: 2rem;">
                최대인원
              </v-col>
              <v-col cols="9">
                <v-text-field
                  type="number"
                  outlined
                  label="최대그룹원수를 지정하세요"
                  required
                  :min="minNum"
                  v-model="groupInfo.groupMaxMemberCount"
                ></v-text-field>
              </v-col>
            </v-row>

            <v-row
              style="height:150px;width:100%;border-bottom: 1px solid black"
              align="center"
            >
              <v-col cols="3" style="font-size: 2rem;">
                공개범위
                <v-menu open-on-hover rounded="xl" offset-y>
                  <template v-slot:activator="{ on, attrs }">
                    <v-btn icon v-bind="attrs" v-on="on">
                      <v-icon>mdi-help-circle-outline</v-icon>
                    </v-btn>
                  </template>
                  <v-card style="width:300px; height:170px; padding:20px">
                    <h3 style="margin-bottom:10px">공개범위란?</h3>
                    <p>
                      기본적으로 그룹은 모두에게 공개됩니다. 비공개 설정 후
                      비밀번호를 등록하면 비밀번호를 입력해야 가입할 수
                      있습니다.
                    </p>
                  </v-card>
                </v-menu>
              </v-col>
              <v-col cols="3">
                <v-radio-group v-model="radios" mandatory row>
                  <v-radio label="전체공개" value="전체공개"></v-radio>
                  <v-radio label="비공개" value="비공개"></v-radio>
                </v-radio-group>
              </v-col>
              <v-col cols="6"
                ><v-text-field
                  v-if="radios === '비공개'"
                  :counter="10"
                  outlined
                  label="그룹 비밀번호를 입력하세요"
                  required
                  v-model="groupPassword"
                ></v-text-field
              ></v-col>
            </v-row>

            <v-row
              style="height:250px;width:100%; border-bottom: 1px solid black"
              align="center"
            >
              <v-col cols="3" style="font-size: 2rem;">
                소개
              </v-col>
              <v-col cols="9">
                <v-textarea
                  outlined
                  :counter="50"
                  placeholder="이 그룹을 나타내는 소개글을 멋지게 써주세요."
                  :value="groupInfo.groupNotice"
                  v-model="groupInfo.groupNotice"
                ></v-textarea>
              </v-col>
            </v-row>
            <v-row
              style="height:200px;width:100%; border-bottom: 1px solid black"
              align="center"
            >
              <v-col cols="3" style="font-size: 2rem;">
                D-DAY 설정
              </v-col>
              <v-col cols="9">
                <v-text-field
                  type="date"
                  outlined
                  label="날짜를 등록하세요"
                  required
                  v-model="groupGoalDate"
                ></v-text-field>
                <v-text-field
                  :counter="20"
                  outlined
                  color="black"
                  label="D-Day제목을 등록하세요"
                  required
                  v-model="groupGoalTitle"
                ></v-text-field>
              </v-col>
            </v-row>
            <v-row style="margin-top:230px" justify="end"
              ><v-btn @click="deleteGroup" icon
                ><v-icon color="red">mdi-delete-forever</v-icon>그룹삭제</v-btn
              ></v-row
            >
          </v-row>
          <div style="text-align:center;">
            <div
              style="display:inline-block; margin:5%"
              @click="$router.go(-1)"
            >
              <app-btn-middle
                :btnColor="'#FAFAFA'"
                :btnName="'취소'"
                :btnNameColor="'#424242'"
              ></app-btn-middle>
            </div>
            <div style="display:inline-block;margin:5%" @click="groupModify">
              <app-btn-middle
                :btnColor="'#424242'"
                :btnName="'수정하기'"
                :btnNameColor="'white'"
              ></app-btn-middle>
            </div>
          </div>
        </v-col>

        <v-col cols="2"></v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>
import AppBtnMiddle from "@/components/common/AppBtnMiddle.vue";
import MiddleNav from "@/components/include/MiddleNav.vue";
import axios from "axios";
const storage = window.sessionStorage;
export default {
  components: {
    MiddleNav,
    AppBtnMiddle,
  },
  data() {
    return {
      navInfo: [
        "sample2.png",
        "그룹",
        "목표가 같은 사람들끼리 모여 달려보세요.",
        "목표로 가는 길이 덜 힘들고, 더욱 든든해질 거예요",
      ],
      groupMaxMemberCount: 0,
      groupPassword: "",
      groupNotice: "",
      groupGoalDate: "",
      groupGoalTitle: "",
      minNum: 0,
      fileList: [],
      imageUrl: null,
      items: ["정보처리기사", "토익", "임용고시", "공무원"],
      radios: null,
      groupInfo: { groupGoalDate: "", groupGoalTitle: "" },
      category: [
        { title: "초중고", icon: "mdi-baby-face-outline" },
        { title: "수능", icon: "mdi-pen" },
        { title: "대학교", icon: "mdi-school-outline" },
        { title: "대학원", icon: "mdi-school" },
        { title: "취업", icon: "mdi-domain" },
        { title: "공무원시험", icon: "mdi-briefcase" },
        { title: "자격증", icon: "mdi-medal-outline" },
        { title: "어학", icon: "mdi-alphabetical" },
        { title: "기타", icon: "mdi-guitar-acoustic" },
      ],
    };
  },
  created() {
    let today = new Date();
    let year = today.getFullYear();
    let month = today.getMonth() + 1;
    let date = today.getDate();
    let hours = today.getHours();
    let min = today.getMinutes();
    let sec = today.getSeconds();
    let datetime =
      year + "-" + month + "-" + date + " " + hours + ":" + min + ":" + sec;
    console.log(datetime);

    this.$Axios
      .create({
        headers: {
          "jwt-auth-token": storage.getItem("jwt-auth-token"),
        },
      })
      .get(`group/${this.$route.query.groupId}?datetime=${datetime}`)
      .then((res) => {
        console.log("수정페이지만들어질때", res);
        this.groupInfo = res.data.groupInfo;
        this.imageUrl = res.data.groupProfileImg;
        this.minNum = this.groupInfo.groupMaxMemberCount;
        console.log(res.data.groupInfo.groupGoalTitle);
        if (this.groupInfo.groupGoalDate !== undefined) {
          // this.groupInfo["groupGoalDate"] = "";
          // this.groupInfo["groupGoalTitle"] = "";
          this.groupGoalDate = this.groupInfo.groupGoalDate.slice(0, 10);
          this.groupGoalTitle = this.groupInfo.groupGoalTitle;
        }
        if (this.groupInfo.groupPassword !== undefined) {
          // this.groupInfo["groupPassword"] = "";
          this.groupPassword = this.groupInfo.groupPassword;
        }
        console.log(this.groupInfo);
      })
      .catch((err) => {
        console.log(err);
      });
    // console.log("받아왓니", this.$route.params.groupId);
    // console.log("dfasdf", this.studying);
    // console.log(this.notStudying);
  },
  methods: {
    onClickImageUpload() {
      this.$refs.imageInput.click();
    },
    onChangeImages(e) {
      console.log(e.target.files);
      const file = e.target.files[0]; // Get first index in files
      this.imageUrl = URL.createObjectURL(file); // Create File URL
      this.fileList = e.target.files;
    },
    groupModify() {
      if (this.groupGoalDate === "") {
        this.groupGoalDate = null;
      }
      if (this.groupGoalTitle === "") {
        this.groupGoalTitle = null;
      }
      if (this.groupPassword === "") {
        this.groupPassword = null;
      }
      console.log("============");
      console.log(this.groupInfo);
      console.log(this.groupInfo.groupMaxMemberCount);
      console.log(this.groupGoalDate);
      console.log(this.groupGoalTitle);
      console.log(this.groupInfo.groupNotice);
      console.log(this.groupPassword);
      axios
        .create({
          headers: {
            "jwt-auth-token": storage.getItem("jwt-auth-token"),
          },
        })
        .put("group", {
          groupId: this.groupInfo.groupId,
          groupMaxMemberCount: this.groupInfo.groupMaxMemberCount,
          groupGoalDate: this.groupGoalDate,
          groupGoalTitle: this.groupGoalTitle,
          groupNotice: this.groupInfo.groupNotice,
          groupPassword: this.groupPassword,
        })
        .then((res) => {
          console.log("put보냄", res);

          // console.log("파일리스트", this.fileList);
          // console.log("받아온그룹아이디", res.data.createdGroupId);
          // console.log(this.fileList);
          if (this.fileList.length !== 0) {
            var params = new FormData();
            params.append("groupId", res.data.createdGroupId);
            params.append("file", this.fileList[0]);

            // console.log(this.fileList[0]);
            axios
              .create({
                headers: {
                  "Content-Type": "multipart/form-data",
                  "jwt-auth-token": storage.getItem("jwt-auth-token"),
                },
              })
              .put("group/profile-img", params)
              .then((res) => {
                console.log("해치웠나??", res);
                this.$router.push("/group");
              });
          }
          this.$router.push("/group");
          console.log("수정한다", this.groupInfo);
        });
    },
    deleteGroup() {
      axios
        .create({
          headers: {
            "Content-Type": "multipart/form-data",
            "jwt-auth-token": storage.getItem("jwt-auth-token"),
          },
        })
        .delete(`group?groupId=${this.groupInfo.groupId}`)
        .then((res) => {
          console.log(res);
          this.$router.push("/group");
        });
    },
  },
};
</script>

<style>
.groupImgUpload {
  width: 600px;
  height: 400px;
  margin: 50px;
}
</style>
