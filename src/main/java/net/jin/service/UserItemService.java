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
	
	//사용자 구매 상품 목록
	public List<UserItem> list(Long userNo) throws Exception; 
	
	//등록
	//public void register()

}
