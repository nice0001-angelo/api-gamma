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
	
	
	@Query
	public List<Object[]> listUserItem(Long userNo);
}
