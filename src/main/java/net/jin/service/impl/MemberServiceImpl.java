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
	@Override
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
	@Override
	public Member read(Long userNo) throws Exception{
		return memberRepository.getById(userNo);
	}
	
	//Member 상세 저장
	@Override
	public Member insert(Member member) throws Exception{
		
		//member.js 에서 data 로 넘긴 values 를  memberEntity 변수에 세팅
		Member memberEntity = new Member();
		memberEntity.setUserId(member.getUserId());
		memberEntity.setUserPw(member.getUserPw());
		memberEntity.setUserName(member.getUserName());
		memberEntity.setJob(member.getJob());
		
		//MemberAuth 도메인의 auth 변수에 "ROLE_MEMBER" 세팅
		MemberAuth memberAuth = new MemberAuth();
		memberAuth.setAuth("ROLE_MEMBER");
		
		//memberEntity 변수에 auth 값을 할당
		//Member domain 의 authList 변수(MemberAuth 타입의 List)에 memberAuth 할당
		memberEntity.addAuth(memberAuth);
		
		//memberRepository에 memberEntity 값을 save
		memberRepository.save(memberEntity);
		
		//member에 userNo를 Set ==> 이부분을 이해못함
		member.setUserNo(memberEntity.getUserNo());
		
		//member 결과값 return
		return member;
	}
	
	//Member 삭제
	@Override
	public void delete(Long userNo) throws Exception{
		memberRepository.deleteById(userNo);
	}
	
	//Member 수정
	@Override
	public Member update(Member member) throws Exception{
		//Member 타입으로 Controller로 부터 userNo, userId, userPw, userName, job, authList 받아옴
		
		//memberEntity 변수를 Member 타입으로 생성 후 Name 와 job 만 세팅
		Member memberEntity = new Member();
		memberEntity.setUserName(member.getUserName());
		memberEntity.setJob(member.getJob());
		
		//MemberAuth 타입의 List인 AuthList를 memberEntity로부터 가져다가 memberAuthList 변수에 할당
		List<MemberAuth> memberAuthList = memberEntity.getAuthList(); 
		
		//MemberAuth 타입의 List인 AuthList를 member로부터 가져다가 authList 변수에 할당
		List<MemberAuth> authList = member.getAuthList();
		
		for(int i =0; i<authList.size(); i++) {
			MemberAuth auth = authList.get(i);
			
			if(i < memberAuthList.size()) {
				MemberAuth memberAuth = memberAuthList.get(i);
				memberAuth.setAuth(auth.getAuth());
			}
			
		}
		
		return memberRepository.save(memberEntity);
		
	}
}

