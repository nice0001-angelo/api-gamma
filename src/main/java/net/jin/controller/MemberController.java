/**
 * 
 */
package net.jin.controller;

import org.springframework.security.crypto.password.*;
import org.springframework.web.bind.annotation.*;

import lombok.*;
import lombok.extern.slf4j.*;
import net.jin.repository.*;

/**
 * @author njh
 *
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value="/users")
public class MemberController {
	
	private final MemberRepository memberRepository;
	
	//
	private final PasswordEncoder passwordEncoder;
	
	@RequestMapping(value="", method = RequestMethod.GET)
	

}
