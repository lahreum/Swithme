<template>
  <div style="width:350px">
    <v-progress-linear
      color="#673fb4"
      height="30"
      :value="progressValue"
      rounded
    >
      <strong>{{ progressValue }}%</strong>
    </v-progress-linear>
  </div>
</template>

<script>
export default {
  props: ["progressValue"],
};
</script>

<style></style>
