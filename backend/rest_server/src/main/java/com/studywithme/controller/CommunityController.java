package com.studywithme.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.studywithme.config.CommonMethods;
import com.studywithme.entity.Board;
import com.studywithme.entity.Liked;
import com.studywithme.entity.Reply;
import com.studywithme.entity.UserInfo;
import com.studywithme.repository.BoardRepository;
import com.studywithme.repository.LikedRepository;
import com.studywithme.repository.ReplyRepository;
import com.studywithme.repository.TodoRepository;
import com.studywithme.repository.UserRepository;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/community")
public class CommunityController {
	
	@Autowired
	CommonMethods commonMethods;
	
	@Autowired
	BoardRepository boardRepository;
	
	@Autowired
	ReplyRepository replyRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	LikedRepository likedRepository;
	
	@Autowired
	TodoRepository todoRepository;
	
	
	@GetMapping("/board")
	@ApiOperation(value="게시글 목록",notes="파라미터로 받은 카테고리 번호에 맞는 게시글들의 목록 반환")
	public Object getBoardList(@RequestParam("categoryId") int categoryId) {
		Map<String,Object> result=new HashMap<>();
		
		Optional<List<Board>> list=boardRepository.findByBoardCategory(categoryId);
		if(list.isPresent()) 
			result.put("boardList",list.get());
		else
			result.put("boardList",null);
		
		return result;
	}
	
	@GetMapping("/board/{boardId}")
	@ApiOperation(value="게시글 상세",notes="url로 받은 게시글 번호에 따라 게시글 상세와 사용자의 좋아요 표시 여부, 해당 사용자가 글쓴이인지 반환")
	public Object getBoardDetail(@PathVariable("boardId") Integer boardId,HttpServletRequest req) {
		Map<String,Object> result=new HashMap<>();
		
		Optional<Board> board=boardRepository.findById(boardId);
		if(board.isPresent()) {
			String nickname=commonMethods.getUserNickname(req.getHeader("jwt-auth-token"));
			Optional<UserInfo> user=userRepository.findByUserNickname(nickname);
			if(user.isPresent()) {
				board.get().setBoardView(board.get().getBoardView()+1);
				boardRepository.save(board.get());
				Optional<Liked> liked=likedRepository.findByLikedBoardIdAndLikedUserNickname(boardId, nickname);
				if(liked.isPresent()) 
					result.put("didILiked",true);
				else
					result.put("didILiked",false);
				
				if(nickname.equals(board.get().getBoardWriter()))
					result.put("isWriter",true);
				else
					result.put("isWriter",false);
				
			}
			result.put("boardDetail",board.get());
		}
		else
			result.put("boardDetail",null);
		
		return result;
	}
	
	@GetMapping("/reply")
	@ApiOperation(value="댓글 목록",notes="파라미터로 받은 게시글 번호에 달린 댓글들의 리스트 반환")
	public Object getReplyList(@RequestParam("boardId") int boardId) {
		Map<String,Object> result=new HashMap<>();
		
		Optional<List<Reply>> list=replyRepository.findByReplyBoardId(boardId);
		if(list.isPresent())
			result.put("replyList",list.get());
		else
			result.put("replyList",null);
		
		return result;
	}
	
	@PostMapping("/board")
	@ApiOperation(value="게시글 작성",notes="바디로 받은 board를 db에 저장하는 데 성공했다면 true 반환")
	public Object writeBoard(@RequestBody Board board,HttpServletRequest req) {
		Map<String,Object> result=new HashMap<>();
		
		result.put("success",false);
		String nickname=commonMethods.getUserNickname(req.getHeader("jwt-auth-token"));
		Optional<UserInfo> user=userRepository.findByUserNickname(nickname);
		if(user.isPresent()) {
			board.setBoardWriter(nickname);
			boardRepository.save(board);
			
			result.clear();
			result.put("success",true);
		}
		return result;
	}
	
	@PutMapping("/board")
	@ApiOperation(value="게시글 수정",notes="바디로 받은 board를 수정하는 데 성공했다면 true 반환")
	public Object modifyBoard(@RequestBody Board boardAfter,HttpServletRequest req) {
		Map<String,Object> result=new HashMap<>();
		
		result.put("success",false);
		
		Optional<Board> boardBefore=boardRepository.findById(boardAfter.getBoardId());
		if(boardBefore.isPresent()) {
			String nickname=commonMethods.getUserNickname(req.getHeader("jwt-auth-token"));
			Optional<UserInfo> user=userRepository.findByUserNickname(nickname);
			if(user.isPresent()&&boardBefore.get().getBoardWriter().equals(nickname)) {
				boardAfter.setBoardWriter(nickname);
				boardRepository.save(boardAfter);
				
				result.clear();
				result.put("success",true);
			}
		}
		return result;
	}
	
	@DeleteMapping("/board/{boardId}")
	@ApiOperation(value="게시글 삭제",notes="파라미터로 받은 게시글 번호로 삭제에 성공하면 true 반환")
	public Object deleteBoard(@PathVariable("boardId") Integer boardId,HttpServletRequest req) {
		Map<String,Object> result=new HashMap<>();
		
		String nickname=commonMethods.getUserNickname(req.getHeader("jwt-auth-token"));
		
		Optional<Board> board=boardRepository.findById(boardId);
		result.put("success",false);
		
		Optional<UserInfo> user=userRepository.findByUserNickname(nickname);
		if(board.isPresent()&&user.isPresent()&&board.get().getBoardWriter().equals(nickname)) {
			boardRepository.delete(board.get());
			
			result.clear();
			result.put("success",true);
		}
		
		return result;
	}
	
	@PostMapping("/reply")
	@ApiOperation(value="댓글 작성",notes="바디로 받은 reply를 저장하는 데 성공했다면 true 반환")
	public Object writeReply(@RequestParam("boardId") int boardId,@RequestParam("content") String content,HttpServletRequest req) {
		Map<String,Object> result=new HashMap<>();
		
		result.put("success",false);
		String nickname=commonMethods.getUserNickname(req.getHeader("jwt-auth-token"));
		
		Optional<UserInfo> user=userRepository.findByUserNickname(nickname);
		if(user.isPresent()) {
			Reply reply=new Reply();
			reply.setReplyBoardId(boardId);
			reply.setReplyContent(content);
			reply.setReplyWriter(nickname);
			replyRepository.save(reply);
			result.clear();
			result.put("success",true);
		}
		return result;
	}
	
	@PutMapping("/reply")
	@ApiOperation(value="댓글 수정",notes="파라미터로 받은 댓글 번호와 내용을 통해 수정에 성공하면 true 반환")
	public Object modifyReply(@RequestParam("replyId") int replyId,@RequestParam("content") String content,HttpServletRequest req) {
		Map<String,Object> result=new HashMap<>();
		
		result.put("success",false);
		
		String nickname=commonMethods.getUserNickname(req.getHeader("jwt-auth-writer"));
		
		Optional<Reply> reply=replyRepository.findById(replyId);
		Optional<UserInfo> user=userRepository.findByUserNickname(nickname);
		if(reply.isPresent()&&user.isPresent()&&reply.get().getReplyWriter().equals(nickname)) {
			reply.get().setReplyContent(content);
			replyRepository.save(reply.get());
			
			result.clear();
			result.put("success",true);
		}
		return result;
	}
	
	@DeleteMapping("/reply")
	@ApiOperation(value="댓글 삭제",notes="파라미터로 받은 댓글 번호를 통해 삭제에 성공하면 true 반환")
	public Object deleteReply(@RequestParam("replyId") int replyId,HttpServletRequest req) {
		Map<String,Object> result=new HashMap<>();
		
		result.put("success",false);
		
		String nickname=commonMethods.getUserNickname(req.getHeader("jwt-auth-token"));
		
		Optional<Reply> reply=replyRepository.findById(replyId);
		Optional<UserInfo> user=userRepository.findByUserNickname(nickname);
		if(reply.isPresent()&&user.isPresent()&&reply.get().getReplyWriter().equals(nickname)) {
			replyRepository.delete(reply.get());
			
			result.clear();
			result.put("success",true);
		}
		return result;
	}
	
	@PostMapping("/liked")
	@ApiOperation(value="게시글 좋아요",notes="파라미터로 받은 게시글 번호의 게시글 좋아요를 누르는 데에 성공하면 true 반환")
	public Object likeBoard(@RequestParam("boardId") int boardId,HttpServletRequest req) {
		Map<String,Object> result=new HashMap<>();
		
		result.put("success",false);
		
		String nickname=commonMethods.getUserNickname(req.getHeader("jwt-auth-token"));
		
		Optional<Liked> liked=likedRepository.findByLikedBoardIdAndLikedUserNickname(boardId, nickname);
		Optional<UserInfo> user=userRepository.findByUserNickname(nickname);
		if(!liked.isPresent()&&user.isPresent()) {
			Optional<Board> board=boardRepository.findById(boardId);
			if(board.isPresent()) {
				Liked newLiked=new Liked();
				newLiked.setLikedBoardId(boardId);
				newLiked.setLikedUserNickname(nickname);
				
				likedRepository.save(newLiked);
				board.get().setBoardLiked(board.get().getBoardLiked()+1);
				boardRepository.save(board.get());
				
				result.clear();
				result.put("success",true);
			}
		}
		return result;
	}
	
	@DeleteMapping("/liked")
	@ApiOperation(value="게시글 좋아요취소",notes="파라미터로 받은 게시글 번호의 게시글 좋아요를 취소하는 데에 성공하면 true 반환")
	public Object unLikeBoard(@RequestParam("boardId") int boardId,HttpServletRequest req) {
		Map<String,Object> result=new HashMap<>();
		
		result.put("success",false);
		
		String nickname=commonMethods.getUserNickname(req.getHeader("jwt-auth-token"));
		
		Optional<Liked> liked=likedRepository.findByLikedBoardIdAndLikedUserNickname(boardId, nickname);
		Optional<UserInfo> user=userRepository.findByUserNickname(nickname);
		if(liked.isPresent()&&user.isPresent()) {
			Optional<Board> board=boardRepository.findById(boardId);
			if(board.isPresent()) {
				likedRepository.delete(liked.get());
				board.get().setBoardLiked(board.get().getBoardLiked()-1);
				boardRepository.save(board.get());
				
				result.clear();
				result.put("success",true);
			}
		}
		return result;
	}
	
}
