<template>
  <div>
    <v-avatar size="200px">
      <img :src="src" />
    </v-avatar>
  </div>
</template>

<script>
export default {
  props: ['src'],
};
</script>

<style></style>
