<template>
  <div>
    <v-select
      :items="items"
      :value="items"
      placeholder="이메일 주소"
      outlined
      v-model="selected"
      @change="passEvent()"
    ></v-select>
  </div>
</template>

<script>
export default {
  data: () => ({
    items: [
      '직접 입력',
      'gmail.com',
      'hanmail.net',
      'hotmail.com',
      'korea.com',
      'nate.com',
      'naver.com',
      'yahoo.com',
    ],
    selected: '',
  }),
  methods: {
    passEvent: function() {
      this.$emit('pass-input', this.selected);
    },
  },
};
</script>

<style></style>
