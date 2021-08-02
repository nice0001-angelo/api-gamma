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
		
	
	

}
