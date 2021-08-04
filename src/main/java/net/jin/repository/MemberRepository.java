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
public interface MemberRepository extends JpaRepository<Member, Long>{
	
	// Object 타입의 Array 임. 각각의 Array는 자리수에 구애받지 않음 char[]는 char가 1자리이므로 각 array 자리수는 1, 아래의 컬럼 하나하나가 java의 Object
	@Query("SELECT m.userNo, m.userId, m.userPw, m.userName, cd.codeName, m.coin, m.regDate "
			+ "FROM Member m "
			+ "INNER JOIN CodeDetail cd ON cd.codeValue = m.job "
			+ "INNER JOIN CodeGroup cg ON cg.groupCode = cd.groupCode "
			+ "WHERE cg.groupCode = 'A01' ORDER BY m.regDate DESC")
	public List<Object[]> listAllMember(); 

}
