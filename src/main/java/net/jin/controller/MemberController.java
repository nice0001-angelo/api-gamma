/**
 * 
 */
package net.jin.controller;

import java.util.*;

import org.springframework.http.*;
import org.springframework.security.crypto.password.*;
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
@RequestMapping(value="/users")
public class MemberController {
	
	private final MemberService memberService;
	
	//비밀번호 암호 처리기
//	private final PasswordEncoder passwordEncoder;
	
	//전체 목록 조회
	@RequestMapping(value="", method = RequestMethod.GET)
	public ResponseEntity<List<Member>> list() throws Exception{
		
		return new ResponseEntity<List<Member>>(memberService.list(), HttpStatus.OK);
	}

	//상세 목록 조회
	@RequestMapping(value = "/{userNo}", method = RequestMethod.GET)
	public ResponseEntity<Member> read(@PathVariable("userNo") Long userNo) throws Exception{
		
		return new ResponseEntity<Member>(memberService.read(userNo), HttpStatus.OK);
	}
	
	//정보 저장
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<Member> insert(@Validated @RequestBody Member member) throws Exception{
		
	}
}
