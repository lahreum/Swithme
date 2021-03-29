<template>
  <div>
    <v-btn
      class="study-button-main"
      fab
      dark
      large
      depressed
      width="200px"
      height="200px"
      :color="propsdata ? '#673FB4' : 'rgba(100, 100, 100, .6)'"
      @click="goRouting"
    >
      <div style="color: white; font-size: 22px; letter-spacing: -1px;">
        <div>
          공부하러가기
        </div>
        <div style="margin-top: 10%;">
          <v-icon dark size="60">
            mdi-arrow-right
          </v-icon>
        </div>
      </div>
    </v-btn>
  </div>
</template>

<script>
export default {
  props: ['propsdata'],
  methods: {
    goRouting() {
      this.$router.push('/timer');
    },
  },
};
</script>

<style></style>
