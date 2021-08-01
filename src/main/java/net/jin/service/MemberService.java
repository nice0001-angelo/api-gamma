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
}
