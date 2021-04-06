<template>
  <div>
    <carousel-3d
      :controls-visible="true"
      :controls-width="50"
      :controls-height="60"
      :clickable="true"
      :width="320"
      :height="420"
      :border="0"
      :space="200"
      :perspective="50"
      :inverseScaling="450"
    >
      <slide
        v-for="(group, i) in recommendGroup"
        :index="i"
        :key="i"
        :style="{ backgroundColor: 'transparent' }"
      >
        <figure>
          <group-recommend-card
            @clickRecommend="toGroupDetail(group.groupId)"
            :groupImg="'data:image/png;base64,' + group.src"
            :groupName="group.groupName"
            :groupDesc="group.groupNotice"
            :groupMemberCnt="group.groupMaxMemberCount"
            :groupMaster="group.groupMasterNickname"
          ></group-recommend-card>
        </figure>
      </slide>
    </carousel-3d>
  </div>
</template>

<script>
import GroupRecommendCard from "@/components/common/GroupRecommendCard.vue";
import { Carousel3d, Slide } from "vue-carousel-3d";
const storage = window.sessionStorage;
export default {
  components: {
    "group-recommend-card": GroupRecommendCard,
    "carousel-3d": Carousel3d,
    slide: Slide,
  },
  // props: ["groups"],
  created() {
    this.$Axios
      .create({
        headers: {
          "jwt-auth-token": storage.getItem("jwt-auth-token"),
        },
      })
      .get("group")
      .then((res) => {
        this.groups = res.data.groupList;
        for (var i = 0; i < this.groups.length; i++) {
          this.groups[i]["src"] = res.data.groupProfileList[i].groupProfileImg;
        }

        this.recommendGroup = this.groups.filter(
          (group) => group.groupName.length > 4
        );
        this.recommendGroup = this.recommendGroup.slice(0, 6);
        console.log(this.recommendGroup);
      })
      .catch((err) => {
        console.log(err);
      });

    // this.$Axios
    //   .create({
    //     headers: {
    //       "jwt-auth-token": storage.getItem("jwt-auth-token"),
    //     },
    //   })
    //   .get("group")
    //   .then((res) => {
    //     console.log("created될때", res);
    //     this.groups = res.data.groupList;
    //     for (var i = 0; i < this.groups.length; i++) {
    //       this.groups[i]["src"] = res.data.groupProfileList[i].groupProfileImg;
    //     }
    //     // console.log(this.groups);
    //     this.AllGroup = this.groups.slice(0, 12);
    //   })
    //   .catch((err) => {
    //     console.log(err);
    //   });
  },
  methods: {
    toGroupDetail(g) {
      this.$router.push({ name: "GroupDetail", query: { groupId: g } });
    },
  },
  data: function() {
    return {
      groups: [],
      recommendGroup: [],
    };
  },
};
</script>

<style></style>
