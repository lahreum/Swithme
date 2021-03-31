<template>
  <div></div>
</template>

<script>
export default {
  data() {
    return {
      error: this.$router.currentRoute.params.error,
    };
  },
  created() {
    if (this.error == 'invalid_account')
      alert('잘못된 계정으로 접근하였습니다.');
    this.$router.push('/');
  },
};
</script>

<style></style>
