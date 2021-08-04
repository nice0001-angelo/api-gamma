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
	private final PasswordEncoder passwordEncoder;
	
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
	
	//Member 정보 저장 Insert
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<Member> insert(@Validated @RequestBody Member member) throws Exception{
		String encryptedPassword = passwordEncoder.encode(member.getUserPw());
		member.setUserPw(encryptedPassword);
		return new ResponseEntity<Member>(memberService.insert(member), HttpStatus.OK);
	}
	
	//Member 정보 삭제
	@RequestMapping(value = "/{userNo}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable("userNo") Long userNo) throws Exception{
		memberService.delete(userNo);
		//여기서는 ResponseEntity<Void> 이므로 절대 (HttpStatus.NO_CONTENT) 안에 리턴값을 요하는 memberService.delete(userNo)을 넣으면 안된다
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	//Member 정보 수정: Front 에서 memberObject 로 보낸 항목에 userNo는 없으므로 별도로 PathVariable로 받아 와야함
	@RequestMapping(value = "/{userNo}", method = RequestMethod.PUT)
	public ResponseEntity<Member> update(@PathVariable("userNo") Long userNo, @Validated @RequestBody Member member) throws Exception{
		
		member.setUserNo(userNo);
		
		return new ResponseEntity<Member>(memberService.update(member), HttpStatus.OK);
	}
}
