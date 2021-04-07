<template>
  <div>
    <v-avatar :color="IsStudying ? '#673fb4' : 'white'" size="170px"
      ><v-avatar size="150px">
        <img :src="'data:image/png;base64,' + src" /> </v-avatar
    ></v-avatar>
  </div>
</template>

<script>
export default {
  props: ["src", "IsStudying"],
};
</script>

<style></style>
