<template>
    <v-select
            placeholder="정렬"
            :items="items"
            dense
            outlined v-model="filter"
            ></v-select>
</template>

<script>
  export default {
    data: () => ({
      items: ['최신 순','오래된 순', '좋아요 순', '조회수 순'],
      filter: '',
    }),
    watch: {
      filter: function(){
        // 부모로 필터 정보 보냄.
        this.$emit('filterInfo', this.filter);
      }
    },
  }
</script>

<style>

</style>