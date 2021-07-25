/**
 * 
 */
package net.jin.controller;

import java.util.*;

import org.springframework.http.*;
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
@RequestMapping(value="/codegroups")
public class CodeGroupController {

	private final CodeGroupService codeGroupService;
	
	//코드그룹 상세조회
	@RequestMapping(value="{groupCode}", method=RequestMethod.GET)
	public ResponseEntity<CodeGroup> read(@PathVariable("groupCode") String groupCode) throws Exception{
		//CodeGroup codeGroup = codeGroupService.read(groupCode); //서비스에서 정한 리턴 타입을 정의 후 변수에 담는다
		
		return new ResponseEntity<CodeGroup>(codeGroupService.read(groupCode), HttpStatus.OK); //변수를 리턴한다
	}
	
	//코드그룹 목록보기
	@RequestMapping(value="/", method=RequestMethod.GET)
	public ResponseEntity<List<CodeGroup>> list() throws Exception{
		//codeGroupService.list(); 아래에 직접 넣어도 된다
		return new ResponseEntity<List<CodeGroup>>(codeGroupService.list(),HttpStatus.OK);
	}
	
	

}
