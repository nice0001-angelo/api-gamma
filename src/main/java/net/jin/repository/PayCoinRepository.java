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
public interface PayCoinRepository extends JpaRepository<PayCoin, Long>{
	/*
	 * @Query("select a.historyNo, a.userNo, a.itemId, a.itemName, a.amount, a.regDate"
	 * + "from PayCoin a inner join Item b on a.itemId = b.itemId" +
	 * "where a.historyNo > 0 and a.userNo = ?1" + "order by a.historyNo DESC")
	 * public List<Object[]> listPayHistory(Long userNo);
	 */
	
	@Query("SELECT a.historyNo, a.userNo, a.itemId, b.itemName, a.amount, a.regDate "
			+ "FROM PayCoin a INNER JOIN Item b ON a.itemId = b.itemId "
			+ "WHERE a.historyNo > 0 AND a.userNo = ?1 "
			+ "ORDER BY a.historyNo DESC")
	public List<Object[]> listPayHistory(Long userNo);
}
