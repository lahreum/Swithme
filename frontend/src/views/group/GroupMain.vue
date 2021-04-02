<template>
  <div>
    <middle-nav v-bind:propsdata="navInfo"></middle-nav>
    <v-container>
      <v-row>
        <v-col cols="2"></v-col>
        <v-col>
          <h1 style="text-align:center">3월의 추천 그룹</h1>
          <hr />

          <div style="height:430px; margin:5% 0;">
            <group-recommend></group-recommend>
          </div>

          <div>
            <v-row align="end">
              <v-col cols="3"><h1>그룹 목록</h1></v-col>
              <v-col cols="5" style="font-size: 1rem;">
                지금
                <span style="font-size:1.2rem;color:#673fb4">{{
                  groups.length
                }}</span>
                개의 그룹이 활동중
              </v-col>
              <v-col cols="4" style="font-size: 1rem;" align="end">
                관심카테고리설정
                <GroupSelect />
              </v-col>
            </v-row>
            <hr />

            <v-row align="center" style="margin:10px 0;">
              <v-col cols="8">
                <AppBtnSmall
                  :btnColor="caseNum === 1 ? '#673fb4' : 'white'"
                  :btnName="'전체'"
                  :btnNameColor="caseNum === 1 ? 'white' : 'black'"
                  @realClick="caseNum = 1"/>
                <AppBtnSmall
                  :btnColor="caseNum === 2 ? '#673fb4' : 'white'"
                  :btnName="'내가 있는 그룹'"
                  :btnNameColor="caseNum === 2 ? 'white' : 'black'"
                  @realClick="MakeMyGroup"/>
                <AppBtnSmall
                  :btnColor="caseNum === 3 ? '#673fb4' : 'white'"
                  :btnName="'뜨거운 그룹'"
                  :btnNameColor="caseNum === 3 ? 'white' : 'black'"
                  @realClick="MakeHotGroup"/>
                <AppBtnSmall
                  :btnColor="caseNum === 4 ? '#673fb4' : 'white'"
                  :btnName="'신규 그룹'"
                  :btnNameColor="caseNum === 4 ? 'white' : 'black'"
                  @realClick="MakeNewGroup"
              /></v-col>
              <v-col cols="4"><SearchBar @searchThing="search"/></v-col>
            </v-row>
          </div>
          <v-row v-if="caseNum === 1">
            <v-col
              cols="12"
              sm="3"
              v-for="(group, index) in AllGroup"
              :key="index"
              ><GroupInfo
                :src="group.src"
                :groupName="group.groupName"
                :groupDesc="group.groupDesc"
                :groupCnt="group.groupCnt"
                :groupTotalCnt="group.groupTotalCnt"
            /></v-col>
          </v-row>
          <v-row v-else-if="caseNum === 2">
            <v-col
              cols="12"
              sm="3"
              v-for="(group, index) in MyGroup"
              :key="index"
              ><GroupInfo
                :src="group.src"
                :groupName="group.groupName"
                :groupDesc="group.groupDesc"
                :groupCnt="group.groupCnt"
                :groupTotalCnt="group.groupTotalCnt"
            /></v-col>
          </v-row>
          <v-row v-else-if="caseNum === 3">
            <v-col
              cols="12"
              sm="3"
              v-for="(group, index) in HotGroup"
              :key="index"
              ><GroupInfo
                :src="group.src"
                :groupName="group.groupName"
                :groupDesc="group.groupDesc"
                :groupCnt="group.groupCnt"
                :groupTotalCnt="group.groupTotalCnt"
            /></v-col>
          </v-row>
          <v-row v-else-if="caseNum === 4">
            <v-col
              cols="12"
              sm="3"
              v-for="(group, index) in NewGroup"
              :key="index"
              ><GroupInfo
                :src="group.src"
                :groupName="group.groupName"
                :groupDesc="group.groupDesc"
                :groupCnt="group.groupCnt"
                :groupTotalCnt="group.groupTotalCnt"
            /></v-col>
          </v-row>
          <v-row v-else-if="caseNum === 5">
            <v-col
              cols="12"
              sm="3"
              v-for="(group, index) in SearchedGroup"
              :key="index"
              ><GroupInfo
                :src="group.src"
                :groupName="group.groupName"
                :groupDesc="group.groupDesc"
                :groupCnt="group.groupCnt"
                :groupTotalCnt="group.groupTotalCnt"
            /></v-col>
          </v-row>
          <v-row justify="center" v-else-if="caseNum === 6">
            <v-col align-self="center" align="end" cols="5"
              ><span style="font-size:2rem">...이런</span
              ><span>검색어와 일치하는 결과가 없네요</span>
              <h2>다른검색어로 검색해보시겠어요?</h2></v-col
            >
            <v-col cols="5"
              ><v-img src="https://ifh.cc/g/yLHO83.png"></v-img
            ></v-col>
          </v-row>
          <v-col
            v-if="
              groups.length > 12 &&
                caseNum === 1 &&
                AllGroup.length < groups.length
            "
            align="center"
            style="margin-top:30px;"
          >
            <hr />
            <v-btn
              @click="moreSee"
              color="white"
              rounded
              style="font-size:20px;width:150px;height:50px;position:relative; top:-25px;"
              >더보기</v-btn
            ></v-col
          >
        </v-col>
        <v-btn
          class="createGroupBtn"
          fab
          width="100"
          height="100"
          color="#673fb4"
          @click="toCreateGroup"
          ><v-icon color="white" size="50">mdi-plus</v-icon></v-btn
        >

        <v-col cols="2"></v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>
import GroupInfo from "@/components/common/GroupInfo.vue";
import MiddleNav from "@/components/include/MiddleNav.vue";
import GroupSelect from "@/components/common/GroupSelect.vue";
import SearchBar from "@/components/common/SearchBar.vue";
import AppBtnSmall from "@/components/common/AppBtnSmall.vue";
import GroupRecommend from "@/components/common/GroupRecommend.vue";

export default {
  components: {
    "group-recommend": GroupRecommend,
    "middle-nav": MiddleNav,
    GroupInfo,
    GroupSelect,
    SearchBar,
    AppBtnSmall,
  },
  data: function() {
    return {
      caseNum: 1,
      nickName: "dldkfma",
      moreSeeIdx: 12,
      AllGroup: [],
      MyGroup: [],
      HotGroup: [],
      NewGroup: [],
      SearchedGroup: [],

      navInfo: [
        "sample2.png",
        "그룹",
        "목표가 같은 사람들끼리 모여 달려보세요.",
        "목표로 가는 길이 덜 힘들고, 더욱 든든해질 거예요",
      ],
      groups: [
        {
          src: "https://ifh.cc/g/wyakuA.jpg",
          groupName: "자면 안돼",
          groupDesc:
            "잠을 자는 사람은 꿈을 꾸지만, 잠을 자지 않는 사람은 꿈을 이룬다",
          groupCnt: 7,
          groupTotalCnt: 13,
          grouper: [
            "zasdfasdf",
            "빛봉현",
            "별빛지현",
            "정처기out",
            "녹용파는사슴",
          ],
          date: "2021-03-22 14:21:46",
        },
        {
          src: "https://ifh.cc/g/qJkaF3.png",
          groupName: "싸피모임",
          groupDesc: "싸피 1~4기 아무나 모여~!",
          groupCnt: 17,
          groupTotalCnt: 26,
          grouper: [
            "ffdfdw",
            "빛봉현",
            "별빛지현",
            "정처기out",
            "녹용파는사슴",
          ],
          date: "2021-03-22 14:21:46",
        },
        {
          src: "https://ifh.cc/g/GAyQK3.jpg",
          groupName: "피자레인저",
          groupDesc: "일주일목표를 다이루면 피자를사먹는그룹입니다",
          groupCnt: 8,
          groupTotalCnt: 12,
          grouper: [
            "dldkfma",
            "빛봉현",
            "별빛지현",
            "정처기out",
            "녹용파는사슴",
          ],
          date: "2021-03-22 14:21:46",
        },

        {
          src: "https://ifh.cc/g/Zp0ZYA.jpg",
          groupName: "합격할거야!",
          groupDesc: "정처기 같이 공부하실분!!",
          groupCnt: 5,
          groupTotalCnt: 12,
          grouper: [
            "dldkfma",
            "빛봉현",
            "별빛지현",
            "정처기out",
            "녹용파는사슴",
          ],
          date: "2021-03-23 14:21:46",
        },
        {
          src: "https://ifh.cc/g/Zp0ZYA.jpg",
          groupName: "공무원 자율스터디",
          groupDesc: "공무원, 자율스터디",
          groupCnt: 7,
          groupTotalCnt: 13,
          grouper: [
            "dldkfma",
            "빛봉현",
            "별빛지현",
            "정처기out",
            "녹용파는사슴",
          ],
          date: "2021-03-24 14:21:46",
        },
        {
          src: "https://ifh.cc/g/ojNXEJ.jpg",
          groupName: "예비 선생님들 스터디~",
          groupDesc: "임용고시 같이 준비해요~",
          groupCnt: 6,
          groupTotalCnt: 18,
          grouper: [
            "dldkfma",
            "빛봉현",
            "별빛지현",
            "정처기out",
            "녹용파는사슴",
          ],
          date: "2021-03-25 14:21:46",
        },
        {
          src: "https://ifh.cc/g/JPassm.jpg",
          groupName: "토익 가즈아",
          groupDesc: "토익 스터디 환영합니다",
          groupCnt: 5,
          groupTotalCnt: 11,
          grouper: [
            "dldkfma",
            "빛봉현",
            "별빛지현",
            "정처기out",
            "녹용파는사슴",
          ],
          date: "2021-03-26 14:21:46",
        },
        {
          src: "https://ifh.cc/g/Zp0ZYA.jpg",
          groupName: "온라인 스터디룸",
          groupDesc: "누구나 환영~~~",
          groupCnt: 3,
          groupTotalCnt: 10,
          grouper: [
            "dldkfma",
            "빛봉현",
            "별빛지현",
            "정처기out",
            "녹용파는사슴",
          ],
          date: "2021-03-29 14:21:46",
        },
        {
          src: "https://ifh.cc/g/Zp0ZYA.jpg",
          groupName: "어서오세요",
          groupDesc: "자율 스터디 방입니다!",
          groupCnt: 7,
          groupTotalCnt: 13,
          grouper: [
            "dldkfma",
            "빛봉현",
            "별빛지현",
            "정처기out",
            "녹용파는사슴",
          ],
          date: "2021-03-30 14:21:46",
        },
        {
          src: "https://ifh.cc/g/Zp0ZYA.jpg",
          groupName: "하이하이요",
          groupDesc: "자율 스터디 방입니다람쥐!",
          groupCnt: 0,
          groupTotalCnt: 13,
          grouper: [
            "zxmcvnkjc",
            "빛봉현",
            "별빛지현",
            "정처기out",
            "녹용파는사슴",
          ],
          date: "2021-03-30 14:21:46",
        },
        {
          src: "https://ifh.cc/g/Zp0ZYA.jpg",
          groupName: "하이하이요",
          groupDesc: "자율 스터디 방입니다람쥐!",
          groupCnt: 0,
          groupTotalCnt: 13,
          grouper: [
            "zxmcvnkjc",
            "빛봉현",
            "별빛지현",
            "정처기out",
            "녹용파는사슴",
          ],
          date: "2021-03-30 14:21:46",
        },
        {
          src: "https://ifh.cc/g/Zp0ZYA.jpg",
          groupName: "하이하이요",
          groupDesc: "자율 스터디 방입니다람쥐!",
          groupCnt: 0,
          groupTotalCnt: 13,
          grouper: [
            "zxmcvnkjc",
            "빛봉현",
            "별빛지현",
            "정처기out",
            "녹용파는사슴",
          ],
          date: "2021-03-30 14:21:46",
        },
        {
          src: "https://ifh.cc/g/Zp0ZYA.jpg",
          groupName: "하이하이요",
          groupDesc: "자율 스터디 방입니다람쥐!",
          groupCnt: 0,
          groupTotalCnt: 13,
          grouper: [
            "zxmcvnkjc",
            "빛봉현",
            "별빛지현",
            "정처기out",
            "녹용파는사슴",
          ],
          date: "2021-03-30 14:21:46",
        },
        {
          src: "https://ifh.cc/g/Zp0ZYA.jpg",
          groupName: "하이하이요",
          groupDesc: "자율 스터디 방입니다람쥐!",
          groupCnt: 0,
          groupTotalCnt: 13,
          grouper: [
            "zxmcvnkjc",
            "빛봉현",
            "별빛지현",
            "정처기out",
            "녹용파는사슴",
          ],
          date: "2021-03-30 14:21:46",
        },
        {
          src: "https://ifh.cc/g/Zp0ZYA.jpg",
          groupName: "하이하이요",
          groupDesc: "자율 스터디 방입니다람쥐!",
          groupCnt: 0,
          groupTotalCnt: 13,
          grouper: [
            "zxmcvnkjc",
            "빛봉현",
            "별빛지현",
            "정처기out",
            "녹용파는사슴",
          ],
          date: "2021-03-30 14:21:46",
        },
        {
          src: "https://ifh.cc/g/Zp0ZYA.jpg",
          groupName: "하이하이요",
          groupDesc: "자율 스터디 방입니다람쥐!",
          groupCnt: 0,
          groupTotalCnt: 13,
          grouper: [
            "zxmcvnkjc",
            "빛봉현",
            "별빛지현",
            "정처기out",
            "녹용파는사슴",
          ],
          date: "2021-03-30 14:21:46",
        },
        {
          src: "https://ifh.cc/g/Zp0ZYA.jpg",
          groupName: "하이하이요",
          groupDesc: "자율 스터디 방입니다람쥐!",
          groupCnt: 0,
          groupTotalCnt: 13,
          grouper: [
            "zxmcvnkjc",
            "빛봉현",
            "별빛지현",
            "정처기out",
            "녹용파는사슴",
          ],
          date: "2021-03-30 14:21:46",
        },
        {
          src: "https://ifh.cc/g/Zp0ZYA.jpg",
          groupName: "하이하이요",
          groupDesc: "자율 스터디 방입니다람쥐!",
          groupCnt: 0,
          groupTotalCnt: 13,
          grouper: [
            "zxmcvnkjc",
            "빛봉현",
            "별빛지현",
            "정처기out",
            "녹용파는사슴",
          ],
          date: "2021-03-30 14:21:46",
        },
        {
          src: "https://ifh.cc/g/Zp0ZYA.jpg",
          groupName: "하이하이요",
          groupDesc: "자율 스터디 방입니다람쥐!",
          groupCnt: 0,
          groupTotalCnt: 13,
          grouper: [
            "asdlfkjlew",
            "빛봉현",
            "별빛지현",
            "정처기out",
            "녹용파는사슴",
          ],
          date: "2021-03-30 14:21:46",
        },
        {
          src: "https://ifh.cc/g/Zp0ZYA.jpg",
          groupName: "하이하이요",
          groupDesc: "자율 스터디 방입니다람쥐!",
          groupCnt: 0,
          groupTotalCnt: 13,
          grouper: [
            "asdlfkjlew",
            "빛봉현",
            "별빛지현",
            "정처기out",
            "녹용파는사슴",
          ],
          date: "2021-03-30 14:21:46",
        },
        {
          src: "https://ifh.cc/g/Zp0ZYA.jpg",
          groupName: "하이하이요",
          groupDesc: "자율 스터디 방입니다람쥐!",
          groupCnt: 0,
          groupTotalCnt: 13,
          grouper: [
            "asdlfkjlew",
            "빛봉현",
            "별빛지현",
            "정처기out",
            "녹용파는사슴",
          ],
          date: "2021-03-30 14:21:46",
        },
        {
          src: "https://ifh.cc/g/Zp0ZYA.jpg",
          groupName: "하이하이요",
          groupDesc: "자율 스터디 방입니다람쥐!",
          groupCnt: 0,
          groupTotalCnt: 13,
          grouper: [
            "asdlfkjlew",
            "빛봉현",
            "별빛지현",
            "정처기out",
            "녹용파는사슴",
          ],
          date: "2021-03-30 14:21:46",
        },
        {
          src: "https://ifh.cc/g/Zp0ZYA.jpg",
          groupName: "하이하이요",
          groupDesc: "자율 스터디 방입니다람쥐!",
          groupCnt: 0,
          groupTotalCnt: 13,
          grouper: [
            "asdlfkjlew",
            "빛봉현",
            "별빛지현",
            "정처기out",
            "녹용파는사슴",
          ],
          date: "2021-03-30 14:21:46",
        },
        {
          src: "https://ifh.cc/g/Zp0ZYA.jpg",
          groupName: "하이하이요",
          groupDesc: "자율 스터디 방입니다람쥐!",
          groupCnt: 0,
          groupTotalCnt: 13,
          grouper: [
            "asdlfkjlew",
            "빛봉현",
            "별빛지현",
            "정처기out",
            "녹용파는사슴",
          ],
          date: "2021-03-30 14:21:46",
        },
        {
          src: "https://ifh.cc/g/Zp0ZYA.jpg",
          groupName: "하이하이요",
          groupDesc: "자율 스터디 방입니다람쥐!",
          groupCnt: 0,
          groupTotalCnt: 13,
          grouper: [
            "asdlfkjlew",
            "빛봉현",
            "별빛지현",
            "정처기out",
            "녹용파는사슴",
          ],
          date: "2021-03-30 14:21:46",
        },
        {
          src: "https://ifh.cc/g/Zp0ZYA.jpg",
          groupName: "하이하이요",
          groupDesc: "자율 스터디 방입니다람쥐!",
          groupCnt: 0,
          groupTotalCnt: 13,
          grouper: [
            "asdlfkjlew",
            "빛봉현",
            "별빛지현",
            "정처기out",
            "녹용파는사슴",
          ],
          date: "2021-03-30 14:21:46",
        },
        {
          src: "https://ifh.cc/g/Zp0ZYA.jpg",
          groupName: "하이하이요",
          groupDesc: "자율 스터디 방입니다람쥐!",
          groupCnt: 0,
          groupTotalCnt: 13,
          grouper: [
            "asdlfkjlew",
            "빛봉현",
            "별빛지현",
            "정처기out",
            "녹용파는사슴",
          ],
          date: "2021-03-30 14:21:46",
        },
        {
          src: "https://ifh.cc/g/Zp0ZYA.jpg",
          groupName: "하이하이요",
          groupDesc: "자율 스터디 방입니다람쥐!",
          groupCnt: 0,
          groupTotalCnt: 13,
          grouper: [
            "asdlfkjlew",
            "빛봉현",
            "별빛지현",
            "정처기out",
            "녹용파는사슴",
          ],
          date: "2021-03-30 14:21:46",
        },
        {
          src: "https://ifh.cc/g/Zp0ZYA.jpg",
          groupName: "하이하이요",
          groupDesc: "자율 스터디 방입니다람쥐!",
          groupCnt: 0,
          groupTotalCnt: 13,
          grouper: [
            "qqqweeeer",
            "빛봉현",
            "별빛지현",
            "정처기out",
            "녹용파는사슴",
          ],
          date: "2021-03-30 14:21:46",
        },
        {
          src: "https://ifh.cc/g/Zp0ZYA.jpg",
          groupName: "하이하이요",
          groupDesc: "자율 스터디 방입니다람쥐!",
          groupCnt: 0,
          groupTotalCnt: 13,
          grouper: [
            "qqqweeeer",
            "빛봉현",
            "별빛지현",
            "정처기out",
            "녹용파는사슴",
          ],
          date: "2021-03-30 14:21:46",
        },
        {
          src: "https://ifh.cc/g/Zp0ZYA.jpg",
          groupName: "하이하이요",
          groupDesc: "자율 스터디 방입니다람쥐!",
          groupCnt: 0,
          groupTotalCnt: 13,
          grouper: [
            "qqqweeeer",
            "빛봉현",
            "별빛지현",
            "정처기out",
            "녹용파는사슴",
          ],
          date: "2021-03-30 14:21:46",
        },
        {
          src: "https://ifh.cc/g/Zp0ZYA.jpg",
          groupName: "하이하이요",
          groupDesc: "자율 스터디 방입니다람쥐!",
          groupCnt: 0,
          groupTotalCnt: 13,
          grouper: [
            "qqqweeeer",
            "빛봉현",
            "별빛지현",
            "정처기out",
            "녹용파는사슴",
          ],
          date: "2021-03-30 14:21:46",
        },
        {
          src: "https://ifh.cc/g/Zp0ZYA.jpg",
          groupName: "하이하이요",
          groupDesc: "자율 스터디 방입니다람쥐!",
          groupCnt: 0,
          groupTotalCnt: 13,
          grouper: [
            "qqqweeeer",
            "빛봉현",
            "별빛지현",
            "정처기out",
            "녹용파는사슴",
          ],
          date: "2021-03-30 14:21:46",
        },
      ],
    };
  },
  methods: {
    toCreateGroup() {
      this.$router.push("/group-create");
      window.scrollTo(0, 0);
    },
    moreSee() {
      this.moreSeeIdx += 8;
      this.AllGroup = this.groups.slice(0, this.moreSeeIdx);
    },
    MakeMyGroup() {
      this.caseNum = 2;
      this.MyGroup = [];
      for (var i = 0; i < this.groups.length; i++) {
        for (var j = 0; j < this.groups[i].grouper.length; j++) {
          if (this.groups[i].grouper[j] === this.nickName) {
            this.MyGroup.push(this.groups[i]);
          }
        }
      }
      console.log(this.MyGroup);
      console.log(this.caseNum);
    },
    MakeHotGroup() {
      this.caseNum = 3;
      this.HotGroup = this.groups.slice(); //배열 복사
      this.HotGroup.sort(function(a, b) {
        return b.groupTotalCnt - a.groupTotalCnt;
      });
      this.HotGroup = this.HotGroup.slice(0, 8);
      console.log(this.HotGroup);
    },
    MakeNewGroup() {
      this.caseNum = 4;
      this.NewGroup = [];
      var Today = new Date();
      console.log(Today);
      // var year = date.getFullYear();
      // var month = ("0" + (1 + date.getMonth())).slice(-2);
      // var day = ("0" + date.getDate()).slice(-2);
      for (var i = 0; i < this.groups.length; i++) {
        var groupMadeDate = this.groups[i].date;
        var groupDate = new Date(groupMadeDate.substring(0, 10));
        console.log("그룹만들어진날짜==", groupDate);
        console.log(
          "오늘날짜 빼기 그룹만들어진날짜",
          (Today.getTime() - groupDate.getTime()) / 1000 / 60 / 60 / 24
        );
        if ((Today.getTime() - groupDate.getTime()) / 1000 / 60 / 60 / 24 < 3) {
          this.NewGroup.push(this.groups[i]);
        }
        console.log("=============================");
      }
    },
    search(s) {
      console.log("넘어온거", s);
      this.caseNum = 5;
      this.SearchedGroup = [];
      for (var i = 0; i < this.groups.length; i++) {
        if (this.groups[i].groupName.includes(s)) {
          this.SearchedGroup.push(this.groups[i]);
        }
      }
      if (this.SearchedGroup.length === 0) {
        this.caseNum = 6;
      }
    },
  },
  created() {
    // const nickName = storage.getItem('nickName')
    this.AllGroup = this.groups.slice(0, 12);
  },
};
</script>

<style>
.createGroupBtn {
  position: fixed;
  bottom: 5%;
  right: 5%;
}
</style>
