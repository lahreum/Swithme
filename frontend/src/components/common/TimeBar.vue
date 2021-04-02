<template>
  <div>
    <v-row no-gutters class="time-bar" justify="center" align="center">
      {{ realTime }}
    </v-row>
  </div>
</template>

<script>
export default {
  data: function() {
    return {
      realTime: '0:00:00',
    };
  },
};
</script>

<style>
.time-bar {
  width: 250px;
  height: 70px;
  background-color: #2b243a;
  font-size: 3.2rem;
  color: white;
}
</style>
