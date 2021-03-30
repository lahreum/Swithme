<template>
  <div></div>
</template>

<script>
const params = new URLSearchParams(window.location.search);
const isUser = params.get('is-user');
const token = params.get('jwt-auth-token');
const storage = window.sessionStorage;

export default {
  created() {
    if (isUser == 'true') {
      storage.setItem('jwt-auth-token', token);
      this.$router.push('/');
    } else this.$router.push({ name: 'Nickname', params: { tmpToken: token } });
  },
};
</script>

<style></style>
