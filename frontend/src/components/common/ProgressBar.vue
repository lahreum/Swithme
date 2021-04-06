<template>
  <div>
    <v-progress-linear
      color="#673fb4"
      background-color="#DAD9FF"
      height="35"
      :value="progressValue"
      rounded
    >
      <strong v-if="isNaN(progressValue) === true"
        >투두리스트를 추가해주세요</strong
      >
      <strong v-else>{{ progressValue }}%</strong>
    </v-progress-linear>
  </div>
</template>

<script>
export default {
  props: ['progressValue'],
};
</script>

<style scoped>
.v-progress-linear {
  border-radius: 30px !important;
}
</style>
