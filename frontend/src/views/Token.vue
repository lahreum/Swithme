<template>
  <div></div>
</template>

<script>
const params = new URLSearchParams(window.location.search);
const token = params.get('jwt-auth-token');
const storage = window.sessionStorage;

export default {
  created() {
    storage.setItem('jwt-auth-token', token);
    this.$router.push('/');
  },
};
</script>

<style></style>
