<template>
  <v-sheet class="mx-auto" max-width="100%">
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
        '대학원',
        '취업',
        '공무원시험',
        '자격증',
        '어학',
        '기타',
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
  font-size: 0.9rem;
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
