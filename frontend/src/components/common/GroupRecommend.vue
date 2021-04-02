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
        v-for="(group, i) in groupList"
        :index="i"
        :key="i"
        :style="{ backgroundColor: 'transparent' }"
      >
        <figure>
          <group-recommend-card
            :groupImg="group.groupImg"
            :groupName="group.groupName"
            :groupDesc="group.groupDesc"
            :groupMemberCnt="group.groupMemberCnt"
            :groupMaster="group.groupMaster"
          ></group-recommend-card>
        </figure>
      </slide>
    </carousel-3d>
  </div>
</template>

<script>
import GroupRecommendCard from '@/components/common/GroupRecommendCard.vue';
import { Carousel3d, Slide } from 'vue-carousel-3d';

export default {
  components: {
    'group-recommend-card': GroupRecommendCard,
    'carousel-3d': Carousel3d,
    slide: Slide,
  },
  data: function() {
    return {
      groupList: [
        {
          groupImg: 'timer_background.jpg',
          groupName: '피자레인저',
          groupDesc: '일주일 목표를 다 이루면 피자를 먹는 모임입니다.',
          groupMaster: '이지금',
          groupMemberCnt: 29,
        },
        {
          groupImg: 'naver_long.png',
          groupName: '네이버롱',
          groupDesc: '일주일 목표를 다 이루면 피자를 먹는 모임입니다.',
          groupMaster: '이하연',
          groupMemberCnt: 27,
        },
        {
          groupImg: 'pattern.jpg',
          groupName: '패턴',
          groupDesc: '일주일 목표를 다 이루면 피자를 먹는 모임입니다.',
          groupMaster: '임찬찬',
          groupMemberCnt: 26,
        },
        {
          groupImg: 'timer_background.jpg',
          groupName: '피자레인저',
          groupDesc: '일주일 목표를 다 이루면 피자를 먹는 모임입니다.',
          groupMaster: '이지금',
          groupMemberCnt: 29,
        },
        {
          groupImg: 'timer_background.jpg',
          groupName: '피자레인저',
          groupDesc: '일주일 목표를 다 이루면 피자를 먹는 모임입니다.',
          groupMaster: '이지금',
          groupMemberCnt: 29,
        },
      ],
    };
  },
};
</script>

<style></style>
