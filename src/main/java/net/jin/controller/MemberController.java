/**
 * 
 */
package net.jin.controller;

import java.util.*;

import org.springframework.http.*;
import org.springframework.security.crypto.password.*;
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
@RequestMapping(value="/users")
public class MemberController {
	
	private final MemberService memberService;
	
	//
	//private final PasswordEncoder passwordEncoder;
	
	@RequestMapping(value="", method = RequestMethod.GET)
	public ResponseEntity<List<Member>> list() throws Exception{
		
		return new ResponseEntity<List<Member>>(memberService.list(), HttpStatus.OK);
	}

}
