/**
 * 
 */
package net.jin.repository;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.data.jpa.repository.*;

import net.jin.domain.*;

/**
 * @author njh
 *
 */
public interface CodeDetailRepository extends JpaRepository<CodeDetail, CodeDetailId>{

	@Query("SELECT max(cd.sortSeq) FROM CodeDetail cd WHERE cd.groupCode = ?1")//PQL: 해당 그룹코드중에 max sortSeq를 가져오는 쿼리
	//public List<Object[]> getMaxSortSeq(String groupCode);
	
	//굳이 위에처럼 List 값으로 처리할 필요가 없다. 왜냐하면 위의 쿼리에서 나오는 값은 어짜피 유일하게 1개만 나온다.
	public Integer getMaxSortSeq(String goupCode);
}
