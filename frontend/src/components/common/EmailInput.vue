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
      items: ['@naver.com', '@google.com', '@daum.net', '@nate.com', '@hanmail.net','사용자 입력'],
    }),
  }
</script>

<style>

</style>