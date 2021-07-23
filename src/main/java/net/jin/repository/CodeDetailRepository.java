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
public interface CodeDetailRepository extends JpaRepository<CodeDetail, CodeDetailId>{

	@Query("SELECT max("cd.sortSeq") FROM CodeDetail cd WHERE cd.groupcode = ?1)
	
}
