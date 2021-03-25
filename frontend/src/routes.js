import StreamTest from '@/views/StreamTest.vue';
import Tutorial from '@/views/HomeTutorial.vue';
import Study from '@/views/Timer.vue';
import MyStudy from '@/views/MyStudy.vue';
import Ranking from '@/views/Ranking.vue';
import GroupMain from '@/views/GroupMain.vue';
import Community from '@/views/Community.vue';
import Home from '@/views/Home.vue';

export default [
  {
    path: '/',
    name: 'Home',
    component: Home,
  },
  {
    path: '/tutorial',
    name: 'Tutorial',
    component: Tutorial,
  },
  {
    path: '/study',
    name: 'Study',
    component: Study,
  },
  {
    path: '/mystudy',
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
    path: '/stream',
    name: 'StreamTest',
    component: StreamTest,
  },
];
