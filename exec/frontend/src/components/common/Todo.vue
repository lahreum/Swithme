<template>
  <v-list-item class="todo">
    <v-checkbox @click="doneTodo(id)" v-model="doneData" color="#616161"></v-checkbox>
    <div :class="{ 'todo_title-done': doneData }" class="todo_title">
      {{ title }}
    </div>
    <v-spacer></v-spacer>
    <v-btn @click="deleteTodo(id)" class="todo_action" icon small>
      <v-icon>mdi-close</v-icon>
    </v-btn>
  </v-list-item>
</template>

<script>
export default {
  props: {
    title: {
      type: String,
      require: true,
    },
    id: {
      type: Number,
      require: true,
    },
    done: {
      type: Boolean,
      default: false,
    },
  },
  data() {
    return {
      doneData: false,
    };
  },
  methods: {
    doneTodo(id) {
      this.$emit("done", id);
      // console.log(id);
    },
    deleteTodo(id) {
      this.$emit("delete", id);
    },
  },

  mounted() {
    this.doneData = this.done;
  },
  computed: {
    controllDone: {
      get() {
        return this.doneData;
      },
      set(value) {
        this.doneData = value;
      },
    },
  },
};
</script>

<style>
.todo {
  display: flex;
  justify-content: space-between;
}
.todo_title-done {
  text-decoration: line-through;
}
</style>
