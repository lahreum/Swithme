<template>
  <v-text-field
    class="TodoInput"
    placeholder="+ add to do list"
    solo
    v-model="message"
    @keydown.enter="sendMessage"
    @click:append="sendMessage"
  >
  </v-text-field>
</template>

<script>
export default {
  data() {
    return {
      message: "",
    };
  },
  methods: {
    sendMessage() {
      if (this.message === "") {
        this.message = "Todo item";
      }
      this.$emit("message", this.message);
      console.log(this.message);
      this.message = "";
    },
  },
};
</script>

<style>
.TodoInput {
  width: 100%;
};
</style>
