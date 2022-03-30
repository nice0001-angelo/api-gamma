/**
 * 
 */
package net.jin.service;

import java.util.*;

import org.springframework.stereotype.*;

import net.jin.domain.*;

/**
 * @author njh
 *
 */
@Service
public interface UserItemService {
	
	//사용자 구매 상품 전체 목록
	public List<UserItem> list(Long userNo) throws Exception; 
	
	//사용자 구매상품 단품 보기
	public UserItem read(Long userItemNo) throws Exception;
	
	//등록
	public void register(Member member, Item item) throws Exception;
	
	//

}
