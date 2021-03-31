<template>
    <v-select
            placeholder="정렬"
            :items="items"
            dense
            outlined
            ></v-select>
</template>

<script>
  export default {
    data: () => ({
      items: ['최신순', '좋아요 순', '조회수 순'],
    }),
  }
</script>

<style>

</style>