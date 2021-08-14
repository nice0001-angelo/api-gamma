/**
 * 
 */
package net.jin.controller;

import java.util.*;

import org.springframework.context.*;
import org.springframework.http.*;
import org.springframework.security.access.prepost.*;
import org.springframework.security.core.annotation.*;
import org.springframework.security.crypto.password.*;
import org.springframework.validation.annotation.*;
import org.springframework.web.bind.annotation.*;

import lombok.*;
import lombok.extern.slf4j.*;
import net.jin.common.security.domain.*;
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
	
	//인터페이스 호출을 위한 필드
	private final MemberService memberService;
	
	//비밀번호 암호 처리를 위한 필드
	private final PasswordEncoder passwordEncoder;
	
	//메시지소스 필드
	private final MessageSource messageSource;
	
	 
	//전체 목록 조회
	//관리자 권한을 가진 사용자만 접근이 가능하다
	@PreAuthorize("hasRole('ADMIN')")
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
		String inputPassword = member.getUserPw();
		String encryptedPassword = passwordEncoder.encode(inputPassword);
		member.setUserPw(encryptedPassword);
		return new ResponseEntity<Member>(memberService.insert(member), HttpStatus.OK);
	}
	
	//Member 정보 삭제
	//관리자 권한을 가진 사용자만 사용 가능하다
	@PreAuthorize("hasRohe('ADMIN')")
	@RequestMapping(value = "/{userNo}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable("userNo") Long userNo) throws Exception{
		memberService.delete(userNo);
		//여기서는 ResponseEntity<Void> 이므로 절대 (HttpStatus.NO_CONTENT) 안에 리턴값을 요하는 memberService.delete(userNo)을 넣으면 안된다
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	//Member 정보 수정: Front 에서 memberObject 로 보낸 항목에 userNo는 없으므로 별도로 PathVariable로 받아 와야함
	//관리자와 회원 권한을 가진 사용자만 사용 가능하다
	@PreAuthorize("hasRole('ADMIN','MEMBER')")
	@RequestMapping(value = "/{userNo}", method = RequestMethod.PUT)
	public ResponseEntity<Member> update(@PathVariable("userNo") Long userNo, @Validated @RequestBody Member member) throws Exception{
		
		member.setUserNo(userNo);
		
		return new ResponseEntity<Member>(memberService.update(member), HttpStatus.OK);
	}
	
	//회원테이블에 데이터가 없으면 최초관리자를 생성한다
	@RequestMapping(value = "/setup", produces = "text/plain; charset=UTF-8", method = RequestMethod.POST)
	public ResponseEntity<String> setupAdmin(@Validated @RequestBody Member member) throws Exception{
		
		//회원존재여부 확인
		if(memberService.countAll()==0) {
			
			//Password 세팅
			String inputPassword = member.getUserPw();
			String encryptedPassoword = passwordEncoder.encode(inputPassword);
			member.setUserPw(encryptedPassoword);
			
			//Job 세팅
			member.setJob("00");
			
			memberService.setupAdmin(member);
			
			return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);	
		}
		
		//최초관리자 생성 불가 메시지
		String message = messageSource.getMessage("common.cannotSetupAdmin", null, Locale.KOREAN);
		
		return new ResponseEntity<String>(message, HttpStatus.BAD_REQUEST);
	}
	
	
	//회원정보를 가져온다
	//관리자와 회원 권한을 가진 사용자만 사용 가능하다
	@PreAuthorize("hasRole('ADMIN','MEMBER')")
	@RequestMapping(value = "/myinfo", method = RequestMethod.GET)
	public ResponseEntity<Member> getMyInfo(@AuthenticationPrincipal CustomUser customUser) throws Exception{
		Long userNo = customUser.getUserNo();
		
		Member member = memberService.read(userNo);
		
		member.setUserPw("");
		
		return new ResponseEntity<Member>(member, HttpStatus.OK);
	}
	
}
