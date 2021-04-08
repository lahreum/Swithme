package com.studywithme.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.studywithme.entity.Reply;

public interface ReplyRepository extends JpaRepository<Reply,Integer>{
	Optional<List<Reply>> findByReplyBoardId(int replyBoardId);
}
