<template>
  <div>
    <middle-nav v-bind:propsdata="navInfo"></middle-nav>
    <v-container>
      <v-row>
        <v-col cols="2"></v-col>
        <v-col>
          <h1 style="text-align:center">3월의 추천 그룹</h1>
          <hr />

          <div
            style="height:500px; margin:5% 0; font-size:100px; background-color:blue"
          >
            추천그룹 컴포넌트 자리...
          </div>

          <div>
            <v-row align="end">
              <v-col cols="3"><h1>그룹 목록</h1></v-col>
              <v-col cols="5" style="font-size: 1rem;">
                지금 1,351 개의 그룹이 활동중
              </v-col>
              <v-col cols="4" style="font-size: 1rem;" align="end">
                관심카테고리설정
                <GroupSelect />
              </v-col>
            </v-row>
            <hr />
            <v-row align="center" style="margin:10px 0;">
              <v-col cols="8"
                ><AppBtnSmall
                  :btnColor="'#673fb4'"
                  :btnName="'전체'"
                  :btnNameColor="'white'"/>

                <AppBtnSmall
                  :btnColor="'white'"
                  :btnName="'내가 있는 그룹'"
                  :btnNameColor="'black'"/>
                <AppBtnSmall
                  :btnColor="'white'"
                  :btnName="'뜨거운 그룹'"
                  :btnNameColor="'black'"/>
                <AppBtnSmall
                  :btnColor="'white'"
                  :btnName="'신규 그룹'"
                  :btnNameColor="'black'"
              /></v-col>
              <v-col cols="4"><SearchBar /></v-col>
            </v-row>
          </div>
          <v-row>
            <v-col
              cols="12"
              sm="3"
              v-for="(group, index) in groups"
              :key="index"
              ><GroupInfo
                :src="group.src"
                :groupName="group.groupName"
                :groupDesc="group.groupDesc"
                :groupCnt="group.groupCnt"
                :groupTotalCnt="group.groupTotalCnt"
            /></v-col>
          </v-row>
        </v-col>

        <v-col cols="2"></v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>
import GroupInfo from "../components/common/GroupInfo.vue";
import MiddleNav from "../components/include/MiddleNav.vue";
import GroupSelect from "../components/common/GroupSelect.vue";
import SearchBar from "../components/common/SearchBar.vue";
import AppBtnSmall from "../components/common/AppBtnSmall.vue";

export default {
  components: {
    "middle-nav": MiddleNav,
    GroupInfo,
    GroupSelect,
    SearchBar,
    AppBtnSmall,
  },
  data: function() {
    return {
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
        },
        {
          src: "https://ifh.cc/g/qJkaF3.png",
          groupName: "싸피모임",
          groupDesc: "싸피 1~4기 아무나 모여~!",
          groupCnt: 17,
          groupTotalCnt: 26,
        },
        {
          src: "https://ifh.cc/g/GAyQK3.jpg",
          groupName: "피자레인저",
          groupDesc: "일주일목표를 다이루면 피자를사먹는그룹입니다",
          groupCnt: 8,
          groupTotalCnt: 12,
        },

        {
          src: "https://ifh.cc/g/Zp0ZYA.jpg",
          groupName: "합격할거야!",
          groupDesc: "정처기 같이 공부하실분!!",
          groupCnt: 5,
          groupTotalCnt: 12,
        },
        {
          src: "https://ifh.cc/g/Zp0ZYA.jpg",
          groupName: "공무원 자율스터디",
          groupDesc: "공무원, 자율스터디",
          groupCnt: 7,
          groupTotalCnt: 13,
        },
        {
          src: "https://ifh.cc/g/ojNXEJ.jpg",
          groupName: "예비 선생님들 스터디~",
          groupDesc: "임용고시 같이 준비해요~",
          groupCnt: 6,
          groupTotalCnt: 18,
        },
        {
          src: "https://ifh.cc/g/JPassm.jpg",
          groupName: "토익 가즈아",
          groupDesc: "토익 스터디 환영합니다",
          groupCnt: 5,
          groupTotalCnt: 11,
        },
        {
          src: "https://ifh.cc/g/Zp0ZYA.jpg",
          groupName: "온라인 스터디룸",
          groupDesc: "누구나 환영~~~",
          groupCnt: 3,
          groupTotalCnt: 10,
        },
        {
          src: "https://ifh.cc/g/Zp0ZYA.jpg",
          groupName: "어서오세요",
          groupDesc: "자율 스터디 방입니다!",
          groupCnt: 7,
          groupTotalCnt: 13,
        },
      ],
    };
  },
};
</script>

<style></style>
