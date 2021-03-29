package com.studywithme.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.studywithme.entity.User;

public interface UserRepository extends JpaRepository<User,String>{
	Optional<User> findByUserNickname(String userNickname);
	Optional<User> findByUserIdAndUserPassword(String userId,String userPassword);
}
