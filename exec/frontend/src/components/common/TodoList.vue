<template>
  <v-container>
    <TodoInput @message="addTodo" />
    <v-list class="todoList">
      <Todo
        @delete="deleteTodo"
        @done="doneTodo"
        v-for="todo in todoList"
        :title="todo.todoContent"
        :id="todo.todoId"
        :done="todo.todoFinish"
        :key="todo.todoId"
      />
    </v-list>
    <ProgressBar :progressValue="progress" />
  </v-container>
</template>

<script>
import TodoInput from '@/components/common/TodoInput.vue';
import Todo from '@/components/common/Todo.vue';
import ProgressBar from '@/components/common/ProgressBar.vue';
const storage = window.sessionStorage;

export default {
  props:["todoList", "date"],
  components: {
    TodoInput,
    Todo,
    ProgressBar,
  },
  data() {
    return {
      // list: [],
      progress: 0,
      changed: false,
      // todoList: [],
    };
  },
  watch: {
    changed: function() {
      this.$emit('updateTodoList', this.changed);
      this.changed = false;
    }
  },
  created(){
    this.todoList = this.todoList || [];
  },
  mounted() {
    // const list = JSON.parse(localStorage.getItem('list'));
    // this.list = list || [];
    this.UpdateProgress();
    // this.UpdateProgress();
  },
  methods: {
    addTodo(message) {
      // const todoObj = {
      //   id: Math.random(),
      //   message,
      //   done: false,
      // };
      // this.list.push(todoObj);
      // this.setLs();
      let params = new URLSearchParams();
      params.append('content', message);
      params.append('datetime', this.date);

      this.$Axios
        .post('todo', params, {
          headers: {
            'jwt-auth-token': storage.getItem('jwt-auth-token'),
          },
        })
        .then((res) => {
          if (res.data.success) {
            console.log('todolist가 잘 추가되었음');
            this.changed = true;
            this.UpdateProgress();
          } else {
            console.log('todolist 추가 못함');
          }
        })
        .catch((error) => {
          console.log(error);
        });
    },
    // doneTodo(id) {
    //   const todoIndex = this.list.findIndex((todo) => todo.id === id);
    //   const doneProp = this.list[todoIndex].done;
    //   this.$set(this.list[todoIndex], 'done', !doneProp);
    //   this.setLs();
    //   this.UpdateProgress();
    // },
    doneTodo(id) {
      let params = new URLSearchParams();
      params.append('todoId', id);

      this.$Axios
        .put('todo', params, {
          headers: {
            'jwt-auth-token': storage.getItem('jwt-auth-token'),
          },
        })
        .then((res) => {
          if (res.data.success) {
            console.log('todolist done');
            this.changed = true;
            this.UpdateProgress();
          } else {
            console.log('todolist done fail');
          }
        })
        .catch((error) => {
          console.log(error);
        });
    },
    deleteTodo(id) {
      // const todoIndex = this.list.findIndex((todo) => todo.id === id);
      // this.list.splice(todoIndex, 1);
      // this.setLs();
      // this.UpdateProgress();
      this.$Axios
        .delete('todo?todoId=' + id, {
          headers: {
            'jwt-auth-token': storage.getItem('jwt-auth-token'),
          },
        })
        .then((res) => {
          if (res.data.success) {
            console.log('todolist 삭제 성공');
            this.changed = true;
            this.UpdateProgress();
          } else {
            console.log('todolist 삭제 실패');
          }
        })
        .catch((error) => {
          console.log(error);
        });
    },
    setLs() {
      const jsonList = JSON.stringify(this.list);
      localStorage.setItem('list', jsonList);
    },
    UpdateProgress() {
      const lengthList = Object.keys(this.todoList).length;
      var doneTodos = 0;
      var i = 0;
      for (i = 0; i < lengthList; i++) {
        if (this.todoList[i].todoFinish) {
          doneTodos += 1;
        }
      }
      this.progress = parseInt((100 * doneTodos) / lengthList);
      this.changed = true;
      console.log('updateProgress 하면서 부모로 emit 보냄');
    },
  },
};
</script>

<style>
.todoList {
  padding: 0;
  height: 18.2vw;
  overflow: scroll;
}
</style>
