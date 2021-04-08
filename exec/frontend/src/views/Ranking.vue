<template>
  <div>
    <middle-nav v-bind:propsdata="navInfo"></middle-nav>
    <v-container style="padding-top: 3%; ">
      <!-- 상단 정보 -->
      <v-row no-gutters style="font-size: 1.8rem;" justify="center">
        <v-col cols="4" align="end">
          <v-icon large>mdi-chevron-left</v-icon>
        </v-col>
        <v-col cols="4" align="center">{{ year }}년 {{ month }}월</v-col>
        <v-col cols="4" align="start">
          <v-icon large>mdi-chevron-right</v-icon>
        </v-col>
      </v-row>
      <v-row no-gutters justify="center" style="margin-top: 5px; ">
        <hr
          style="width: 80%; border: 0; height: 2px; background-color: black;"
        />
      </v-row>
      <!-- 내 정보 -->
      <v-row justify="center" no-gutters style="margin-top: 50px;">
        <div style="width: 800px; min-width: 800px;">
          <my-rank></my-rank>
        </div>
      </v-row>
      <!-- 테이블 -->
      <v-row no-gutters justify="center" style="margin-top: 70px;">
        <div style="width: 800px; min-width: 800px;">
          <v-simple-table fixed-header>
            <thead>
              <tr>
                <th>순위</th>
                <th>공부시간</th>
                <th>닉네임</th>
                <th>좌우명</th>
              </tr>
            </thead>
            <tbody>
              <!-- 1등 -->
              <tr>
                <td>1</td>
                <td>{{ userList[0].studyTime }}</td>
                <td>
                  <profile-small :src="userList[0].profile"></profile-small>
                  {{ userList[0].username }}
                </td>
                <td>{{ userList[0].message }}</td>
              </tr>
              <!-- 2등 -->
              <tr>
                <td>2</td>

                <td>{{ userList[1].studyTime }}</td>
                <td>
                  <profile-small :src="userList[1].profile"></profile-small>
                  {{ userList[1].username }}
                </td>
                <td>{{ userList[1].message }}</td>
              </tr>
              <!-- 3등 -->
              <tr>
                <td>3</td>
                <td>{{ userList[2].studyTime }}</td>
                <td>
                  <profile-small :src="userList[2].profile"></profile-small>
                  {{ userList[2].username }}
                </td>
                <td>{{ userList[2].message }}</td>
              </tr>
              <!-- 그 외 -->
              <tr v-for="(user, index) in userList" :key="index">
                <template v-if="index > 2 && index < 5 + addedNum">
                  <td>{{ user.rank }}</td>
                  <td>{{ user.studyTime }}</td>
                  <td>
                    <profile-small :src="user.profile"></profile-small>
                    {{ user.username }}
                  </td>
                  <td>{{ user.message }}</td>
                </template>
              </tr>
            </tbody>
          </v-simple-table>
        </div>
      </v-row>
      <v-row
        no-gutters
        justify="center"
        style="letter-spacing: -1px; margin-top: 70px;"
      >
        2개 더 보기
      </v-row>
      <v-row no-gutters justify="center">
        <v-icon size="70" @click="loadMore" color="#999999"
          >mdi-arrow-down-thin-circle-outline</v-icon
        >
      </v-row>
    </v-container>
  </div>
</template>

<script>
import MiddleNav from '../components/include/MiddleNav.vue';
import MyRank from '@/components/common/MyRank.vue';
import ProfileSmall from '../components/common/ProfileSmall.vue';

export default {
  components: {
    'middle-nav': MiddleNav,
    'my-rank': MyRank,
    'profile-small': ProfileSmall,
  },
  data: function() {
    return {
      navInfo: [
        'nav4.jpg',
        '랭킹',
        '첫번째 문장입니다. 첫번째 문장입니다. 첫번째 문장입',
        '두번째 문장입니다~! 두번째 문장입니다~! 두번째 문장입니다~! 두번째',
      ],
      month: 3,
      year: 2021,
      addedNum: 0,
      userList: [
        {
          rank: 1,
          studyTime: '9:56:23',
          username: 'hana1',
          profile: require(`@/assets/img/avatars/iu.jpg`),
          message: '오늘 걷지 않으면 어쩌고',
        },
        {
          rank: 2,
          studyTime: '8:56:12',
          username: 'hana2',
          profile: require(`@/assets/img/avatars/iu.jpg`),
          message: '오늘 걷지 않으면 어쩌고',
        },
        {
          rank: 3,
          studyTime: '8:24:11',
          username: 'hana3',
          profile: require(`@/assets/img/avatars/iu.jpg`),
          message: '오늘 걷지 않으면 어쩌고',
        },
        {
          rank: 4,
          studyTime: '3:56:09',
          username: 'hana4',
          profile: require(`@/assets/img/avatars/iu.jpg`),
          message: '오늘 걷지 않으면 어쩌고',
        },
        {
          rank: 5,
          studyTime: '1:56:02',
          username: 'hana5',
          profile: require(`@/assets/img/avatars/iu.jpg`),
          message: '오늘 걷지 않으면 어쩌고',
        },
        {
          rank: 6,
          studyTime: '1:56:02',
          username: 'hana5',
          profile: require(`@/assets/img/avatars/iu.jpg`),
          message: '오늘 걷지 않으면 어쩌고',
        },
        {
          rank: 7,
          studyTime: '1:56:02',
          username: 'hana5',
          profile: require(`@/assets/img/avatars/iu.jpg`),
          message: '오늘 걷지 않으면 어쩌고',
        },
        {
          rank: 8,
          studyTime: '1:56:02',
          username: 'hana5',
          profile: require(`@/assets/img/avatars/iu.jpg`),
          message: '오늘 걷지 않으면 어쩌고',
        },
        {
          rank: 9,
          studyTime: '1:56:02',
          username: 'hana5',
          profile: require(`@/assets/img/avatars/iu.jpg`),
          message: '오늘 걷지 않으면 어쩌고',
        },
        {
          rank: 10,
          studyTime: '1:56:02',
          username: 'hana5',
          profile: require(`@/assets/img/avatars/iu.jpg`),
          message: '오늘 걷지 않으면 어쩌고',
        },
        {
          rank: 11,
          studyTime: '1:56:02',
          username: 'hana5',
          profile: require(`@/assets/img/avatars/iu.jpg`),
          message: '오늘 걷지 않으면 어쩌고',
        },
      ],
    };
  },
  methods: {
    loadMore: function() {
      this.addedNum += 2;
    },
  },
};
</script>

<style scoped>
th {
  text-align: center !important;
  font-size: 1.2rem !important;
  letter-spacing: -1px;
}
td {
  text-align: center !important;
}
</style>
