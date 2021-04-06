<template>
  <v-sheet class="mx-auto" max-width="100%">
    <v-slide-group show-arrows mandatory v-model="model">
      <v-slide-item 
        v-for="i in items"
        :key="i.value"
        v-slot:default="{ active, toggle }"
      >
        <div @click="toggle"><div
          class="category-item"
          :class="{ 'category-active': active, 'category-default': !active }"
          @click="sendCategory(i)"
        >
          {{ i.name }}
        </div></div>
      </v-slide-item>
    </v-slide-group>
  </v-sheet>
</template>

<script>
export default {
  data: function() {
    return {
      model: 1,
      items: [
        {name: '초중고', value: 1},
        {name: '수능', value: 2},
        {name: '대학교', value: 3},
        {name: '대학원', value: 4},
        {name: '취업', value: 5},
        {name: '공무원시험', value: 6},
        {name: '자격증',value: 7},
        {name: '어학',value: 8},
        {name: '기타', value: 9},
      ],
    };
  },
  created() {
    this.model =`${this.$store.state.categoryId}`-1;
  },
  methods: {
    sendCategory(i) {
        // 부모로 카테고리 정보 보냄. 
        this.$emit('categoryName',i);
    }
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
