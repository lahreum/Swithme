<template>
  <div>
    <MiddleNav v-bind:propsdata="navInfo" />
    <v-container>
      <v-row>
        <v-col cols="2"></v-col>
        <v-col>
          <h1 style="text-align:center">그룹 만들기</h1>
          <hr />
          <v-row align-content="center">
            <v-col style="margin-bottom:50px;" align="center">
              <v-img
                class="groupImgUpload"
                v-if="imageUrl"
                :src="imageUrl"
              ></v-img>
              <v-img
                class="groupImgUpload"
                v-else
                src="https://ifh.cc/g/3qRFoS.png"
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
              <v-col cols="9">
                <v-text-field
                  :counter="10"
                  outlined
                  color="black"
                  label="그룹이름을 입력하세요"
                  required
                  v-model="groupName"
                ></v-text-field>
              </v-col>
            </v-row>

            <v-row
              style="height:150px;width:100%;border-bottom: 1px solid black"
              align="center"
            >
              <v-col cols="3" style="font-size: 2rem;">
                카테고리
              </v-col>
              <v-col cols="9">
                <!-- :error-messages="errors" -->
                <!-- v-model="select" -->
                <!-- data-vv-name="select" -->
                <v-select
                  :items="items"
                  outlined
                  color="black"
                  label="스터디를 대표할수 있는 카테고리를 선택하세요."
                  required
                  v-model="groupCategory"
                ></v-select>
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
                  min="1"
                  v-model="groupMaxMemberCount"
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
                  v-model="groupNotice"
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
            <div style="display:inline-block;margin:5%" @click="ToGroupMain">
              <app-btn-middle
                :btnColor="'#424242'"
                :btnName="'만들기'"
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
      groupName: "",
      groupCategory: "",
      groupMaxMemberCount: 0,
      groupPassword: "",
      groupNotice: "",
      groupGoalDate: "",
      groupGoalTitle: "",
      navInfo: [
        "sample2.png",
        "그룹",
        "목표가 같은 사람들끼리 모여 달려보세요.",
        "목표로 가는 길이 덜 힘들고, 더욱 든든해질 거예요",
      ],
      imageUrl: null,
      items: [
        "초중고",
        "수능",
        "대학교",
        "대학원",
        "취업",
        "공무원시험",
        "자격증",
        "어학",
        "기타",
      ],
      radios: null,
    };
  },
  methods: {
    onClickImageUpload() {
      this.$refs.imageInput.click();
    },
    onChangeImages(e) {
      console.log(e.target.files);
      const file = e.target.files[0]; // Get first index in files
      this.imageUrl = URL.createObjectURL(file); // Create File URL
    },
    ToGroupMain() {
      var params = new URLSearchParams();
      params.append("groupCategory", this.items.indexOf(this.groupCategory));
      params.append("groupMaxMemberCount", this.groupMaxMemberCount);
      params.append("groupGoalDate", this.groupGoalDate);
      params.append("groupNotice", this.groupNotice);
      params.append("groupName", this.groupName);
      params.append("groupPassword", this.groupPassword);
      axios
        .create({
          headers: {
            "jwt-auth-token": storage.getItem("jwt-auth-token"),
          },
        })
        .post("group", params)
        .then((res) => {
          console.log(res);
          this.$router.push("/");
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
