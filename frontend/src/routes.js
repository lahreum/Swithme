import StreamTest from '@/views/StreamTest.vue';
import Tutorial from '@/views/HomeTutorial.vue';

export default [
  {
    path: '/',
    name: 'Main'
  },
  {
    path: '/tutorial',
    name: 'Tutorial',
    component: Tutorial,
  },
  {
    path: '/stream',
    name: 'StreamTest',
    component: StreamTest,
  },
];
