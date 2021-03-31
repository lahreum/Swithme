package com.studywithme.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.studywithme.config.CommonMethods;
import com.studywithme.entity.Todo;
import com.studywithme.entity.User;
import com.studywithme.repository.TodoRepository;
import com.studywithme.repository.UserRepository;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/todo")
public class TodoController {
	
	@Autowired
	CommonMethods commonMethods;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	TodoRepository todoRepository;

	@PostMapping("")
	@ApiOperation(value="투두리스트 추가",notes="파라미터로 받은 내용을 db에 저장하는 데 성공했다면 true 반환")
	public Object writeTodo(@RequestParam("content") String content,HttpServletRequest req) {
		Map<String,Object> result=new HashMap<>();
		
		result.put("success",false);
		
		String nickname=commonMethods.getUserNickname(req.getHeader("jwt-auth-token"));
		
		Optional<User> user=userRepository.findByUserNickname(nickname);
		if(user.isPresent()) {
			Todo todo=new Todo();
			todo.setTodoContent(content);
			todo.setTodoUserNickname(nickname);
			todoRepository.save(todo);			
			
			result.clear();
			result.put("success",true);
		}
		
		return result;
	}
	
	@DeleteMapping("")
	@ApiOperation(value="투두리스트 삭제",notes="파라미터로 받은 투두리스트 id로 삭제하는 데 성공했다면 true 반환")
	public Object deleteTodo(@RequestParam("todoId") int todoId,HttpServletRequest req) {
		Map<String,Object> result=new HashMap<>();
		
		result.put("success",false);
		
		String nickname=commonMethods.getUserNickname(req.getHeader("jwt-auth-token"));
		
		Optional<Todo> todo=todoRepository.findById(todoId);
		Optional<User> user=userRepository.findByUserNickname(nickname);
		if(todo.isPresent()&&user.isPresent()&&todo.get().getTodoUserNickname().equals(nickname)) {
			todoRepository.delete(todo.get());
			
			result.clear();
			result.put("success",true);
		}
		return result;
	}
	
	@PutMapping("")
	@ApiOperation(value="투두리스트 수정",notes="파라미터로 받은 투두리스트 id로 완료 상태를 수정하는 데 성공했다면 true 반환")
	public Object modifyTodo(@RequestParam("todoId") int todoId,HttpServletRequest req) {
		Map<String,Object> result=new HashMap<>();
		
		result.put("success",false);
		
		String nickname=commonMethods.getUserNickname(req.getHeader("jwt-auth-token"));
		
		Optional<Todo> todo=todoRepository.findById(todoId);
		Optional<User> user=userRepository.findByUserNickname(nickname);
		if(todo.isPresent()&&user.isPresent()&&todo.get().getTodoUserNickname().equals(nickname)) {
			todo.get().setTodoFinish(!todo.get().isTodoFinish());
			todoRepository.save(todo.get());
			result.put("success",true);
		}
		return result;
	}
	
	@GetMapping("")
	@ApiOperation(value="투두리스트 목록",notes="파라미터로 받은 날짜에 맞는 투두리스트를 불러온다")
	public Object getTodoList(@RequestParam("datetime") String datetime,HttpServletRequest req) {
		Map<String,Object> result=new HashMap<>();
		datetime=datetime.substring(0, 10);
		String nickname=commonMethods.getUserNickname(req.getHeader("jwt-auth-token"));

		result.put("todoList",null);
		
		Optional<User> user=userRepository.findByUserNickname(nickname);
		Optional<List<Todo>> todoList=todoRepository.findByTodoUserNicknameAndTodoDateStartingWith(nickname,datetime);
		if(user.isPresent()&&todoList.isPresent()) {
			result.clear();
			result.put("todoList",todoList.get());
		}
		return result;
	}
	
}
