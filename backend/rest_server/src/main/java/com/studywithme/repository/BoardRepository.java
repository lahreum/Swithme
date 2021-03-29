package com.studywithme.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.studywithme.entity.Board;

public interface BoardRepository extends JpaRepository<Board,Integer> {
	Optional<List<Board>> findByBoardCategory(int boardCategory);
}
