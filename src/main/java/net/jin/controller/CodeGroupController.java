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
		System.out.println("insert Controller");
		return new ResponseEntity<CodeGroup>(codeGroupService.insert(codeGroup), HttpStatus.OK);
	}

	//코드그룹 삭제 처리
	@RequestMapping(value="{groupCode}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable("groupCode") String groupCode) throws Exception{
		codeGroupService.delete(groupCode);
		//여기서는 ResponseEntity<Void> 이므로 절대 (HttpStatus.NO_CONTENT) 안에 리턴값을 요하는 codeGroupService.delete(groupCode) 을 넣으면 안된다
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);  
	}
	
	//코드그룹 수정 처리
	@RequestMapping(value="{groupCode}", method=RequestMethod.PUT)
	//@RequestBody는 Http 요청몸체를 Java 객체로 변환
	//@ReponseEntity는 Java 객체를 Http 요청몸체로 변환
	public ResponseEntity<CodeGroup> update(@RequestBody CodeGroup codeGroup) throws Exception{
		
		return new ResponseEntity<CodeGroup>(codeGroupService.update(codeGroup), HttpStatus.OK);
	}
	

}
