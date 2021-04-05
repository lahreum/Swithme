<template>
  <div style="display:inline-block;">
    <v-menu left offset-y v-model="menu" :close-on-content-click="false">
      <template v-slot:activator="{ on, attrs }">
        <v-btn
          depressed
          outlined
          fab
          width="20"
          height="20"
          v-bind="attrs"
          v-on="on"
        >
          <v-icon>mdi-chevron-down</v-icon>
        </v-btn>
      </template>
      <v-row
        class="pa-5 ma-0"
        style="width:500px; padding:5px; background-color:white; opacity:0.8;max-height:700px"
      >
        <h1 style="width:100%;">나의 관심주제</h1>
        <h5 style="width:100%;">관심 주제 기반으로 그룹 목록을 제공합니다.</h5>
        <v-col
          class="pa-5"
          cols="12"
          sm="4"
          v-for="(category, index) in category"
          :key="index"
        >
          <GroupCategory
            :CategoryIcon="category.icon"
            :CategoryName="category.title"
            @selectLike="saveCategoryIdx"
          />
        </v-col>
        <v-btn
          @click="sendCategoryIdx"
          color="#673fb4"
          style="margin-left:5%; width:90%"
          ><h3 style="color:white">완료</h3></v-btn
        >
      </v-row>
    </v-menu>
  </div>
</template>

<script>
import GroupCategory from "@/components/common/GroupCategory";
export default {
  data() {
    return {
      menu: false,
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
      categoryIdxList: [],
    };
  },
  methods: {
    saveCategoryIdx(s1, s2) {
      for (var i = 0; i < 9; i++) {
        if (this.category[i].title === s1) {
          if (s2 === true) {
            this.categoryIdxList.push(i + 1);
          } else {
            var j = this.categoryIdxList.indexOf(i + 1);
            this.categoryIdxList.splice(j, 1);
          }
        }
      }
      console.log(this.categoryIdxList);
    },
    sendCategoryIdx() {
      console.log(this.categoryIdxList);
      this.$emit("filtering", this.categoryIdxList);
      this.menu = false;
    },
  },
  components: {
    GroupCategory,
  },
};
</script>

<style></style>
