<template>
  <div></div>
</template>

<script>
const params = new URLSearchParams(window.location.search);
const isUser = params.get("is-user");
const token = params.get("jwt-auth-token");
const storage = window.sessionStorage;

export default {
  data() {
    return {
      userInfo: [],
    };
  },
  created() {
    if (isUser == "true") {
      storage.setItem("jwt-auth-token", token);
      this.$Axios
        .create({
          headers: {
            "jwt-auth-token": storage.getItem("jwt-auth-token"),
          },
        })
        .get("user")
        .then((res) => {
          this.userInfo = res.data.data;
          this.userInfo["profileImg"] = res.data.profileImg;
          this.userInfo["isLogin"] = true;
          this.$store.commit("LOGIN", this.userInfo);
        });
      this.$router.push("/");
    } else
      this.$router.push({
        name: "Nickname",
        params: { tmpToken: token, userInfo: this.userInfo },
      });
  },
};
</script>

<style></style>
