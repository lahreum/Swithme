import Tutorial from '@/views/HomeTutorial.vue';
import MyStudy from '@/views/MyStudy.vue';
import Ranking from '@/views/Ranking.vue';
import GroupMain from '@/views/GroupMain.vue';
import Community from '@/views/Community.vue';
import Home from '@/views/Home.vue';
import Timer from '@/views/timer/Timer.vue';
import Join from '@/views/join/Join.vue';
import JoinAgree from '@/views/join/JoinAgree.vue';
import JoinAuth from '@/views/join/JoinAuth.vue';
import JoinCreate from '@/views/join/JoinCreate.vue';
import JoinComplete from '@/views/join/JoinComplete.vue';
import Kakao from '@/views/Kakao.vue';

export default [
  {
    path: '/',
    name: 'Home',
    component: Home,
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
    path: '/community',
    name: 'Community',
    component: Community,
  },
  {
    path: '/kakao',
    name: 'Kakao',
    component: Kakao,
  },
];
