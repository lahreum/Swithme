<template>
  <v-sheet class="mx-auto" max-width="700">
    <v-slide-group show-arrows>
      <v-slide-item
        v-for="i in items"
        :key="i"
        v-slot:default="{ active, toggle }"
      >
        <div
          class="category-item"
          :class="{ 'category-active': active, 'category-default': !active }"
          @click="toggle"
        >
          {{ i }}
        </div>
      </v-slide-item>
    </v-slide-group>
  </v-sheet>
</template>

<script>
export default {
  data: function() {
    return {
      items: [
        '초중고',
        '수능',
        '대학교',
        '편입',
        'PEET',
        'MEET',
        'LEET',
        '대학원',
        '공무원',
        '임용',
        '자격증',
        '전문자격시험',
        '취업',
        '이직',
        '어학',
      ],
    };
  },
  methods: {
    goRouting() {
      console.log('성공!!');
    },
  },
};
</script>

<style scoped>
.v-btn {
  border-radius: 7px !important;
}
.category-item {
  text-align: center;
  min-width: 150px;
  margin-left: 10px;
  margin-right: 10px;
  padding: 10px;
  font-size: 1.2rem;
  letter-spacing: -1px;
  font-weight: bold;
}
.category-active {
  background-color: #673fb4;
  border: 1px solid #673fb4;
  border-radius: 15px;
  color: white;
}
.category-default {
  border: 1px solid #999999;
  border-radius: 15px;
  color: #434343;
}
</style>
