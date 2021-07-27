/**
 * 
 */
package net.jin.controller;

import org.springframework.security.crypto.password.*;
import org.springframework.web.bind.annotation.*;

import lombok.*;
import lombok.extern.slf4j.*;
import net.jin.service.*;

/**
 * @author njh
 *
 */
@Slf4j
//@RequiredArgsConstructor
@RestController
@RequestMapping(value="/users")
public class MemberController {

	private final MemberService memberService;
	
	//비밀번호 암호처리기 SecurityConfig.java 에 세팅한 PasswordEncoder와는 별개의 인터페이스이다
	private final PasswordEncoder passwordEncoder;
	
	

}
