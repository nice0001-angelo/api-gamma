/**
 * 
 */
package net.jin.repository;

import org.springframework.data.jpa.repository.*;

import net.jin.domain.*;

/**
 * @author njh
 *
 */
public interface UserItemRepository extends JpaRepository<UserItem, Long> {
	
	

}
