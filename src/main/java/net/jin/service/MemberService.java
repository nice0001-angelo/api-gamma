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

}
