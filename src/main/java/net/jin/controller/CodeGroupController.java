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
@RequestMapping(value="/codegroups")
public class CodeGroupController {

	private final CodeGroupService codeGroupService;
	

	//코드그룹 목록조회
	@RequestMapping(value="", method=RequestMethod.GET)
	public ResponseEntity<List<CodeGroup>> list() throws Exception{
		//codeGroupService.list(); 아래에 직접 넣어도 된다
		return new ResponseEntity<List<CodeGroup>>(codeGroupService.list(),HttpStatus.OK);
	}
	
	//코드그룹 상세조회
	@RequestMapping(value="{groupCode}", method=RequestMethod.GET)
	public ResponseEntity<CodeGroup> read(@PathVariable("groupCode") String groupCode) throws Exception{
		//CodeGroup codeGroup = codeGroupService.read(groupCode); //서비스에서 정한 리턴 타입을 정의 후 변수에 담는다
		return new ResponseEntity<CodeGroup>(codeGroupService.read(groupCode), HttpStatus.OK); //변수를 리턴한다
	}
	
	//코드그룹 등록 처리
	@RequestMapping(value="", method=RequestMethod.POST)
	public ResponseEntity<CodeGroup> insert(@Validated @RequestBody CodeGroup codeGroup) throws Exception{
		System.out.println("Controller");
		return new ResponseEntity<CodeGroup>(codeGroupService.insert(codeGroup), HttpStatus.OK);
	}

	//코드그룹 삭제 처리
	@RequestMapping(value="{groupCode}", method=RequestMethod.DELETE)
	public void delete(@PathVariable("groupCode") String groupCode) throws Exception{
		
	}
	

}
