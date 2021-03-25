<template>
  <v-container style="">
    <v-text-field
      style="margin-top:7px; height:40px"
      outlined
      rounded
      placeholder="검색"
      dense
      prepend-inner-icon="mdi-magnify"
      v-on:keyup.enter="search"
      v-model="searchItem"
      color="#673fb4"
      clearable
    ></v-text-field>
  </v-container>
</template>

<script>
export default {
  data: function() {
    return {
      searchItem: "",
    };
  },
  methods: {
    search: function() {
      if (this.searchItem !== "") {
        // 무언가 입력되었을때
        console.log(this.searchItem);
        this.clearInput();
      } else {
        console.log("아무것도 입력되지 않았습니다");
      }
    },
    clearInput: function() {
      this.searchItem = "";
    },
  },
};
</script>
