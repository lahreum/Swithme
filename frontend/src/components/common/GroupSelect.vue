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
        style="width:500px; padding:5px; max-height:700px"
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
          />
        </v-col>
        <v-btn
          @click="menu = false"
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
        { title: "정보처리기사", icon: "mdi-desktop-tower-monitor" },
        { title: "정보처리기사", icon: "mdi-desktop-tower-monitor" },
        { title: "정보처리기사", icon: "mdi-desktop-tower-monitor" },
        { title: "정보처리기사", icon: "mdi-desktop-tower-monitor" },
        { title: "정보처리기사", icon: "mdi-desktop-tower-monitor" },
        { title: "정보처리기사", icon: "mdi-desktop-tower-monitor" },
        { title: "정보처리기사", icon: "mdi-desktop-tower-monitor" },
        { title: "정보처리기사", icon: "mdi-desktop-tower-monitor" },
        { title: "정보처리기사", icon: "mdi-desktop-tower-monitor" },
      ],
    };
  },
  components: {
    GroupCategory,
  },
};
</script>

<style></style>
