/**
 * 
 */
package net.jin.controller;

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
@RequestMapping(value="/codedetails")
public class CodeDetailController {

	private final CodeDetailService codeDetailService;

	//코드 목록 조회
	
	//코드 상세 조회
	@RequestMapping(value="/{groupCode}/{codeValue}",method = RequestMethod.GET)
	public ResponseEntity<CodeDetail> read(@PathVariable("groupCode") String groupCode,
			@PathVariable("codeValue") String codeValue) throws Exception{
		
		CodeDetail codeDetail = new CodeDetail();
		codeDetail.setGroupCode(groupCode);
		codeDetail.setCodeValue(codeValue);
		
		return new ResponseEntity<CodeDetail>(codeDetailService.read(codeDetail), HttpStatus.OK);
	}
	
	//코드 등록 처리
	@RequestMapping(value="", method=RequestMethod.POST)
	public ResponseEntity<CodeDetail> insert(@Validated @RequestBody CodeDetail codeDetail) throws Exception{
		return new ResponseEntity<CodeDetail>(codeDetailService.insert(codeDetail), HttpStatus.OK);
	}
}
