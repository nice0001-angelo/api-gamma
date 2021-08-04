/**
 * 
 */
package net.jin.service.impl;

import java.time.*;
import java.util.*;

import org.springframework.stereotype.*;

import lombok.*;
import lombok.extern.slf4j.*;
import net.jin.domain.*;
import net.jin.repository.*;
import net.jin.service.*;

/**
 * @author njh
 *
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService{
	
	private final MemberRepository memberRepository;
	
	//Member 전체목록 조회(Memeber 의 컬럼 value를 Member 구조체 형태로 record로 나열
	public List<Member> list() throws Exception{
		//Repository로 부터 object[] 타입으로 각각의 컬럼을 가져와서 각각의 행을 list로 반환하기 위해서 선언한다: listAllMember는 레파지토리에 선언
		List<Object[]> valueArrays = memberRepository.listAllMember();
		
		List<Member> memberList = new ArrayList<Member>();
		
		//Memeber 구조체를 선언하여 repository로부터 가져온 값을 형변화여 add한값을 list로 리턴한다
		for(Object[] valueArray : valueArrays) {
			Member member = new Member();
			
			member.setUserNo((Long)valueArray[0]);
			member.setUserId((String)valueArray[1]);
			member.setUserPw((String)valueArray[2]);
			member.setUserName((String)valueArray[3]);
			member.setJob((String)valueArray[4]);
			member.setCoin((int)valueArray[5]);
			member.setRegDate((LocalDateTime)valueArray[6]);
			
			memberList.add(member);
		}
		
		
		return memberList;
	}

	//Member 상세목록 조회
	public Member read(Long userNo) throws Exception{
		return memberRepository.getById(userNo);
	}
	
	//Member 상세 저장
	public Member insert(Member member) throws Exception{
		return memberRepository.save(member);
	}
	
	//Member 삭제
	public void delete(Long userNo) throws Exception{
		memberRepository.deleteById(userNo);
	}
	
	//Member 수정
	public Member update(Member member) throws Exception{
		String encryptedPassword = member.getUserPw();
		member.setUserPw(encryptedPassword);
		return memberRepository.save(member);
	}
}

