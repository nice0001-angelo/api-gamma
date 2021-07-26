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
	public List<Object[]> getMaxSortSeq(String groupCode);
}
