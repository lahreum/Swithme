package com.studywithme.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.studywithme.entity.Liked;

public interface LikedRepository extends JpaRepository<Liked,Integer>{
	Optional<Liked> findByLikedBoardIdAndLikedUserNickname(int likedBoardId,String userNickname);
}
