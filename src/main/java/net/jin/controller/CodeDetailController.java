/**
 * 
 */
package net.jin.controller;

import org.springframework.web.bind.annotation.*;

import lombok.*;
import lombok.extern.slf4j.*;

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
	@RequestMapping(value="",method = RequestMethod.GET)
}
