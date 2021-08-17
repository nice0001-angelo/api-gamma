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
public interface ItemRepository extends JpaRepository<Item, Long>{

}
