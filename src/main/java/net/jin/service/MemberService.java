/**
 * 
 */
package net.jin.service;

import java.util.*;

import net.jin.domain.*;

/**
 * @author njh
 *
 */
public interface MemberService {
	//Member 전체목록 조회
	public List<Member> list() throws Exception;

	//Member 상세목록 조회
	public Member read(Long userNo) throws Exception;
	
	//Member 상세 저장 insert
	public Member insert(Member member) throws Exception;
	
	//Member 상세 삭제
	public void delete(Long userNo) throws Exception;
}
