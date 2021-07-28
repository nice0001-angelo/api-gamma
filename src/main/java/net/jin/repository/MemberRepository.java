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
public interface MemberRepository extends JpaRepository<Member, Long>{
	
	@Query("SELECT m.userNo, m.userId, m.userPw, m.userName, cd.codeName, m.coin, m.regDate"
			+ "FROM Member m"
			+ "INNER JOIN CodeDetail cd ON cd.codeValue = m.job"
			+ "INNER JOIN CodeGroup cg ON cg.groupCode = cd.groupCode"
			+ "WHERE cg.groupCode = 'A01' ORDERY BY m.regDate DESC"
			)
	public List<Object[]> listAllmember();
}
