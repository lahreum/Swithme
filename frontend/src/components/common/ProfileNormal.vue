<template>
  <div>
    <v-avatar size="70px">
      <img :src="src" />
    </v-avatar>
    {{ src }}
  </div>
</template>

<script>
export default {
  props: ["src"],
};
</script>

<style></style>
