<template>
  <div>
    <group-recommend-card
      :groupImg="groupList[1].groupImg"
      :groupName="groupList[1].groupName"
      :groupDesc="groupList[1].groupDesc"
      :groupMemberCnt="groupList[1].groupMemberCnt"
      :groupMaster="groupList[1].groupMaster"
    ></group-recommend-card>
  </div>
</template>

<script>
import GroupRecommendCard from '@/components/common/GroupRecommendCard.vue';
export default {
  components: {
    'group-recommend-card': GroupRecommendCard,
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
