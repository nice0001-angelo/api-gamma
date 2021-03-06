/**
 * 
 */
package net.jin.controller;

import java.util.*;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import lombok.*;
import lombok.extern.slf4j.*;
import net.jin.dto.*;
import net.jin.service.*;

/**
 * @author njh
 *
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value="/codes")
public class CodeController {
	
	private final CodeService codeService;
	
	//코드그룹 목록조회
	@RequestMapping(value = "/codeGroup")
	public ResponseEntity<List<CodeLabelValue>> codeGroupList() throws Exception{
		return new ResponseEntity<List<CodeLabelValue>>(codeService.getCodeGroupList(), HttpStatus.OK);
	}
		
	//직업코드 목록조회
	@RequestMapping(value = "/job", method = RequestMethod.GET)
	public ResponseEntity<List<CodeLabelValue>> jobList() throws Exception{
		String groupCode = "A01";
		return new ResponseEntity<List<CodeLabelValue>>(codeService.getCodeList(groupCode), HttpStatus.OK);
	}
	

}
