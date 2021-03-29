package com.studywithme.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.studywithme.entity.Todo;

public interface TodoRepository extends JpaRepository<Todo,Integer>{
	Optional<List<Todo>> findByTodoUserNicknameAndTodoDateStartingWith(String todoUserNickname,String todoDate);
}
