/**
 * 
 */
package net.jin.repository;

import java.util.*;

import org.springframework.data.jpa.repository.*;

import net.jin.domain.*;

/**
 * @author njh
 *
 */
public interface PayCoinRepository extends JpaRepository<List<PayCoin>, Long>{
	
}
