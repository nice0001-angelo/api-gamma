/**
 * 
 */
package net.jin.repository;

import java.util.*;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import net.jin.domain.*;

/**
 * @author njh
 *
 */
public interface UserItemRepository extends JpaRepository<UserItem, Long> {
	
	public List<UserItem> findByUserNo(Long userNo);
	
	
	@Query("Select a.userItemNo, a.userNo, a.itemId, a.regDate, b.itemName, b.price, b.description, b.pictureUrl"
			+ "from UserItem a INNER JOIN Item b ON a.itemId = b.itemId"
			+ "where a.userItemNo = ?1")
	public List<Object[]> listUserItem(Long userNo);
}
