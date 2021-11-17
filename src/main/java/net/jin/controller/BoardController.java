/**
 * 
 */
package net.jin.controller;

import java.util.*;

import org.springframework.http.*;
import org.springframework.security.access.prepost.*;
import org.springframework.security.core.annotation.*;
import org.springframework.validation.annotation.*;
import org.springframework.web.bind.annotation.*;

import lombok.*;
import lombok.extern.slf4j.*;
import net.jin.common.security.domain.*;
import net.jin.domain.*;
import net.jin.service.*;

/**
 * @author njh
 *
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/boards")
public class BoardController {
	
	private final BoardService boardService;
	
	//전체목록 조회
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<List<Board>> list() throws Exception{
		return new ResponseEntity<List<Board>>(boardService.list(), HttpStatus.OK);
	}
	
	//상세목록조회
	@RequestMapping(value = "/{boardNo}", method = RequestMethod.GET)
	public ResponseEntity<Board> read(@PathVariable("boardNo") Long boardNo) throws Exception{
		return new ResponseEntity<Board>(boardService.read(boardNo), HttpStatus.OK);
	}
	
	
	//등록
	@PreAuthorize("hasRole('MEMBER')")
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<Board> insert(@Validated @RequestBody Board board, @AuthenticationPrincipal CustomUser customUser) throws Exception{
		//customerUser로 부터 UserId를 가져다가 board 변수에 담고
		board.setWriter(customUser.getUserId());
		//등록하기 위해 boardService를 호출한다
		boardService.insert(board);
		System.out.println();
		
		//리턴 결과는 insert한 내용을 읽어온다 
		return new ResponseEntity<Board>(boardService.read(board.getBoardNo()), HttpStatus.OK);
	}
	
	
	//삭제
	@PreAuthorize("hasRole('MEMBER' and principal.username == #writer) or hasRole('ADMIN')")
	@RequestMapping(value = "/{boardNo}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Long boardNo, @RequestParam("writer") String writer) throws Exception{
		boardService.delete(boardNo);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	
	//수정
	@PreAuthorize("hasRole('MEMBER' and principal.username == #board.writer) or hasRole('ADMIN')")
	@RequestMapping(value = "/{boardNo}", method = RequestMethod.PUT)
	public ResponseEntity<Board> update(@PathVariable("boardNo") Long boardNo, @Validated @RequestBody Board board) throws Exception{
		
		return new ResponseEntity<Board>(boardService.update(board), HttpStatus.OK);
	}
	
	

}
