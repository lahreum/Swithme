import HelloWorld from '@/components/HelloWorld.vue';
import StreamTest from '@/views/StreamTest.vue';

export default [
  {
    path: '/',
    name: 'HelloWorld',
    component: HelloWorld,
  },
  {
    path: '/stream',
    name: 'StreamTest',
    component: StreamTest,
  },
];
