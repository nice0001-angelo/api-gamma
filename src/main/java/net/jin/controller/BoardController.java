/**
 * 
 */
package net.jin.controller;

import java.util.*;

import org.springframework.http.*;
import org.springframework.security.access.prepost.*;
import org.springframework.validation.annotation.*;
import org.springframework.web.bind.annotation.*;

import lombok.*;
import lombok.extern.slf4j.*;
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
	public ResponseEntity<Board> insert(@Validated @RequestBody Board board) throws Exception{
		return new ResponseEntity<Board>(boardService.insert(board), HttpStatus.OK);
	}
	
	
	//삭제
	
	
	//수정
	
	
	

}
