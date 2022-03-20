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
public interface UserItemService {
	
	//사용자 구매 상품 목록
	public List<UserItem> list(Long userNo) throws Exception; 

}
