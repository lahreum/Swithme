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
                <GroupSelect @filtering="filteringGroup" />
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
                @clickGroupInfo="toGroupDetail(group.groupId)"
                :src="'data:image/png;base64,' + group.src"
                :groupName="group.groupName"
                :groupDesc="group.groupNotice"
                :groupCnt="group.groupCurMemberCount"
                :groupTotalCnt="group.groupMaxMemberCount"
            /></v-col>
          </v-row>
          <v-row v-else-if="caseNum === 2">
            <v-col
              cols="12"
              sm="3"
              v-for="(group, index) in MyGroup"
              :key="index"
              ><GroupInfo
                @clickGroupInfo="toGroupDetail(group.groupId)"
                :src="'data:image/png;base64,' + group.src"
                :groupName="group.groupName"
                :groupDesc="group.groupNotice"
                :groupCnt="group.groupCurMemberCount"
                :groupTotalCnt="group.groupMaxMemberCount"
            /></v-col>
          </v-row>
          <v-row v-else-if="caseNum === 3">
            <v-col
              cols="12"
              sm="3"
              v-for="(group, index) in HotGroup"
              :key="index"
              ><GroupInfo
                @clickGroupInfo="toGroupDetail(group.groupId)"
                :src="'data:image/png;base64,' + group.src"
                :groupName="group.groupName"
                :groupDesc="group.groupNotice"
                :groupCnt="group.groupCurMemberCount"
                :groupTotalCnt="group.groupMaxMemberCount"
            /></v-col>
          </v-row>
          <v-row v-else-if="caseNum === 4">
            <v-col
              cols="12"
              sm="3"
              v-for="(group, index) in NewGroup"
              :key="index"
              ><GroupInfo
                @clickGroupInfo="toGroupDetail(group.groupId)"
                :src="'data:image/png;base64,' + group.src"
                :groupName="group.groupName"
                :groupDesc="group.groupNotice"
                :groupCnt="group.groupCurMemberCount"
                :groupTotalCnt="group.groupMaxMemberCount"
            /></v-col>
          </v-row>
          <v-row v-else-if="caseNum === 5">
            <v-col
              cols="12"
              sm="3"
              v-for="(group, index) in SearchedGroup"
              :key="index"
              ><GroupInfo
                @clickGroupInfo="toGroupDetail(group.groupId)"
                :src="'data:image/png;base64,' + group.src"
                :groupName="group.groupName"
                :groupDesc="group.groupNotice"
                :groupCnt="group.groupCurMemberCount"
                :groupTotalCnt="group.groupMaxMemberCount"
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
import axios from "axios";

const storage = window.sessionStorage;

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
      groups: [],
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
      axios
        .create({
          headers: {
            "jwt-auth-token": storage.getItem("jwt-auth-token"),
          },
        })
        .get("group/that-i-am")
        .then((res) => {
          console.log("내가잇는그룹", res);
          this.MyGroup = res.data.groupListThatIAm;
          for (var i = 0; i < this.MyGroup.length; i++) {
            this.MyGroup[i]["src"] =
              res.data.groupProfileList[i].groupProfileImg;
          }
        });
      // console.log(this.MyGroup);
      // console.log(this.caseNum);
    },
    MakeHotGroup() {
      this.caseNum = 3;
      this.HotGroup = this.groups.slice(); //배열 복사
      this.HotGroup.sort(function(a, b) {
        return b.groupMaxMemberCount - a.groupMaxMemberCount;
      });
      this.HotGroup = this.HotGroup.slice(0, 8);
      // console.log(this.HotGroup);
    },
    MakeNewGroup() {
      this.caseNum = 4;
      this.NewGroup = [];
      var Today = new Date();
      // console.log(Today);
      // var year = date.getFullYear();
      // var month = ("0" + (1 + date.getMonth())).slice(-2);
      // var day = ("0" + date.getDate()).slice(-2);
      for (var i = 0; i < this.groups.length; i++) {
        var groupMadeDate = this.groups[i].groupCreatedDate;
        var groupDate = new Date(groupMadeDate.substring(0, 10));
        // console.log("그룹만들어진날짜==", groupDate);
        // console.log(
        //   "오늘날짜 빼기 그룹만들어진날짜",
        //   (Today.getTime() - groupDate.getTime()) / 1000 / 60 / 60 / 24
        // );
        if ((Today.getTime() - groupDate.getTime()) / 1000 / 60 / 60 / 24 < 3) {
          this.NewGroup.push(this.groups[i]);
        }
      }
    },
    search(s) {
      // console.log("넘어온거", s);
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
    filteringGroup() {},
    toGroupDetail(g) {
      this.$router.push({ name: "GroupDetail", params: { groupId: g } });
    },
  },
  created() {
    // const nickName = storage.getItem('nickName')

    axios
      .create({
        headers: {
          "jwt-auth-token": storage.getItem("jwt-auth-token"),
        },
      })
      .get("group")
      .then((res) => {
        // console.log("그룹메인created될때", res);
        this.groups = res.data.groupList;
        for (var i = 0; i < this.groups.length; i++) {
          this.groups[i]["src"] = res.data.groupProfileList[i].groupProfileImg;
        }
        // console.log(this.groups);
        this.AllGroup = this.groups.slice(0, 12);
      })
      .catch((err) => {
        console.log(err);
      });
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
