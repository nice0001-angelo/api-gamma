/**
 * 
 */
package net.jin.controller;

import java.util.*;

import org.springframework.http.*;
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
@RequestMapping(value = "/notices")
public class NoticeController {
	
	private final NoticeService noticeService;
	
	//전체목록조회
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<List<Notice>> list() throws Exception{
		return new ResponseEntity<List<Notice>>(noticeService.list(),HttpStatus.OK);
	}
	
	//상세목록조회
	@RequestMapping(value = "/{noticeNo}", method = RequestMethod.GET)
	public ResponseEntity<Notice> read(@PathVariable("noticeNo") Long noticeNo) throws Exception{
		return new ResponseEntity<Notice>(noticeService.read(noticeNo), HttpStatus.OK);
	}
	
	//등록
	
	//삭제
	
	//수정

}
