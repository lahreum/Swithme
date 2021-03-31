<template>
  <div>
      <v-select
          :items="items"
          placeholder="이메일 주소"
          outlined
        ></v-select>
  </div>
</template>

<script>
  export default {
    data: () => ({
      items: ['직접 입력','gmail.com','hanmail.net','hotmail.com','korea.com', 'nate.com','naver.com', 'yahoo.com'],
    }),
  }
</script>

<style>

</style>