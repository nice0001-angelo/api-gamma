/**
 * 
 */
package net.jin.controller;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import lombok.*;
import lombok.extern.slf4j.*;
import net.jin.domain.*;

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
	
	//상세조회
	@RequestMapping(value="/{groupCode}/{codeValue}",method = RequestMethod.GET)
	public ResponseEntity<CodeDetail> read(@PathVariable("groupCode") String groupCode,
			@PathVariable("codeValue") String codeValue) throws Exception{
		
		CodeDetail codeDetail = codeDetailService.read(groupCode, codeValue);
		
		return ResponseEntity<CodeDetail>(HttpStatus.OK);
	}
}
