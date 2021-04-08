package com.studywithme.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.studywithme.entity.UserInfo;

public interface UserRepository extends JpaRepository<UserInfo,String>{
	Optional<UserInfo> findByUserNickname(String userNickname);
	Optional<UserInfo> findByUserIdAndUserPassword(String userId,String userPassword);
}
