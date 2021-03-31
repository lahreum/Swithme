import Tutorial from '@/views/HomeTutorial.vue';
import MyStudy from '@/views/MyStudy.vue';
import MyPageAccess from '@/views/MyPageAccess.vue';
import MyPageModify from '@/views/MyPageModify.vue';
import MyPage from '@/views/MyPage.vue';
import Ranking from '@/views/Ranking.vue';
import GroupMain from '@/views/GroupMain.vue';
import GroupCreate from '@/views/GroupCreate.vue';
import GroupDetail from '@/views/GroupDetail.vue';
import GroupRanking from '@/views/GroupRanking.vue';
import GroupAttendance from '@/views/GroupAttendance.vue';
import GroupModify from '@/views/GroupModify.vue';
import Home from '@/views/Home.vue';
import Timer from '@/views/timer/Timer.vue';
import Join from '@/views/user/Join.vue';
import JoinAgree from '@/views/user/JoinAgree.vue';
import JoinAuth from '@/views/user/JoinAuth.vue';
import JoinCreate from '@/views/user/JoinCreate.vue';
import JoinComplete from '@/views/user/JoinComplete.vue';
import Token from '@/views/social/Token.vue';
import Nickname from '@/views/social/Nickname.vue';
import NoAccess from '@/views/NoAccess.vue';
import FindPw from '@/views/user/FindPw.vue';
import Community from '@/views/community/Community.vue';
import CommunityCreate from '@/views/community/CommunityCreate.vue';
import CommunityDetail from '@/views/community/CommunityDetail.vue';
import CommunityModify from '@/views/community/CommunityModify.vue';

export default [
  {
    path: '/',
    name: 'Home',
    component: Home,
  },
  {
    path: '/find-pw',
    name: 'FindPw',
    component: FindPw,
  },
  {
    path: '/community',
    name: 'Community',
    component: Community,
    children: [
      {
        path: 'community-create',
        name: 'CommunityCreate',
        component: CommunityCreate,
      },
      {
        path: 'community-detail',
        name: 'CommunityDetail',
        component: CommunityDetail,
      },
      {
        path: 'community-modify',
        name: 'CommunityModify',
        component: CommunityModify,
      },
    ]
  },
  {
    path: '/join',
    name: 'Join',
    component: Join,
    children: [
      {
        path: '/',
        name: 'JoinAgree',
        component: JoinAgree,
      },
      {
        path: 'join-auth',
        name: 'JoinAuth',
        component: JoinAuth,
      },
      {
        path: 'join-create',
        name: 'JoinCreate',
        component: JoinCreate,
      },
      {
        path: 'join-complete',
        name: 'JoinComplete',
        component: JoinComplete,
      },
    ],
  },
  {
    path: '/tutorial',
    name: 'Tutorial',
    component: Tutorial,
  },
  {
    path: '/timer',
    name: 'Timer',
    component: Timer,
  },
  {
    path: '/my-study',
    name: 'MyStudy',
    component: MyStudy,
  },
  {
    path: '/ranking',
    name: 'Ranking',
    component: Ranking,
  },
  {
    path: '/group',
    name: 'GroupMain',
    component: GroupMain,
  },
  {
    path: '/group-create',
    name: 'GroupCreate',
    component: GroupCreate,
  },
  {
    path: '/group-detail',
    name: 'GroupDetail',
    component: GroupDetail,
  },
  {
    path: '/group-ranking',
    name: 'GroupRanking',
    component: GroupRanking,
  },
  {
    path: '/group-attendance',
    name: 'GroupAttendance',
    component: GroupAttendance,
  },
  {
    path: '/group-modify',
    name: 'GroupModify',
    component: GroupModify,
  },
  {
    path: '/community',
    name: 'Community',
    component: Community,
  },
  {
    path: '/my-page',
    name: 'MyPage',
    component: MyPage,
  },
  {
    path: '/my-page-modify',
    name: 'MyPageModify',
    component: MyPageModify,
  },
  {
    path: '/my-page-access',
    name: 'MyPageAccess',
    component: MyPageAccess,
  },
  {
    path: '/token',
    name: 'Token',
    component: Token,
    beforeEnter: (to, from, next) => {
      if (from.name == null) next();
    },
  },
  {
    path: '/nickname',
    name: 'Nickname',
    component: Nickname,
    beforeEnter: (to, from, next) => {
      if (to.params.tmpToken != null) next();
    },
  },
  {
    path: '/no-access/:error',
    name: 'NoAccess',
    component: NoAccess,
  },
];
