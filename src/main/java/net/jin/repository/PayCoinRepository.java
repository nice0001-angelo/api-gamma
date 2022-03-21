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
		@Query
		("select a.historyNo, a.userNo, a.itemId, a.itemName, a.amount, a.regDate, a.updDate"
				+ "from PayCoin a inner join Item b on a.itemId = b.itemId"
				+ "where a.historyNo > 0 and a.userNo = ?1"
				+ "order by a.hostoryNo DESC")
		public List<Object[]> listPayHistory(Long userNo);
}
