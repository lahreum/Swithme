<template>
  <div>
    <v-avatar color="#673fb4" size="170px"
      ><v-avatar size="150px"> <img :src="src" /> </v-avatar
    ></v-avatar>
  </div>
</template>

<script>
export default {
  props: ["src"],
};
</script>

<style></style>
