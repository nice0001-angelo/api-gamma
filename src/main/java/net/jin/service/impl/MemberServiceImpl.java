/**
 * 
 */
package net.jin.service.impl;

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
	
	//Member 전체목록 조회
	public List<Member> list() throws Exception{
		return memberRepository.findAll();
	}

	//Member 상세목록 조회
	public Member read(Long userNo) throws Exception{
		return memberRepository.getById(userNo);
	}
	
	//Member 상세 저장
	public Member insert(Member member) throws Exception{
		return memberRepository.save(member);
	}
}

