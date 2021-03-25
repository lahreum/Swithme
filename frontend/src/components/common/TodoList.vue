<template>
  <v-container>
    <TodoInput @message="addTodo" />
    <v-list class="todoList">
      <Todo
        @delete="deleteTodo"
        @done="doneTodo"
        v-for="(todo, index) in list"
        :title="todo.message"
        :id="todo.id"
        :done="todo.done"
        :key="index"
      />
    </v-list>
    <ProgressBar :progressValue="progress" />
  </v-container>
</template>

<script>
import TodoInput from "@/components/common/TodoInput.vue";
import Todo from "@/components/common/Todo.vue";
import ProgressBar from "@/components/common/ProgressBar.vue";
export default {
  components: {
    TodoInput,
    Todo,
    ProgressBar,
  },
  data() {
    return {
      list: [],
      progress: 0,
    };
  },
  mounted() {
    const list = JSON.parse(localStorage.getItem("list"));
    this.list = list || [];
    this.UpdateProgress();
  },
  methods: {
    addTodo(message) {
      const todoObj = {
        id: Math.random(),
        message,
        done: false,
      };
      this.list.push(todoObj);
      this.setLs();
      this.UpdateProgress();
    },
    doneTodo(id) {
      const todoIndex = this.list.findIndex((todo) => todo.id === id);
      const doneProp = this.list[todoIndex].done;
      this.$set(this.list[todoIndex], "done", !doneProp);
      this.setLs();
      this.UpdateProgress();
    },
    deleteTodo(id) {
      const todoIndex = this.list.findIndex((todo) => todo.id === id);
      this.list.splice(todoIndex, 1);
      this.setLs();
      this.UpdateProgress();
    },
    setLs() {
      const jsonList = JSON.stringify(this.list);
      localStorage.setItem("list", jsonList);
    },
    UpdateProgress() {
      const lengthList = this.list.length;
      var doneTodos = 0;
      var i = 0;
      for (i = 0; i < lengthList; i++) {
        if (this.list[i].done) {
          doneTodos += 1;
        }
      }
      this.progress = parseInt((100 * doneTodos) / lengthList);
      console.log(typeof(this.progress));
    },
  },
};
</script>

<style>
.todoList {
  padding: 0;
  height: 330px;
  /* overflow: scroll; */
}
</style>
