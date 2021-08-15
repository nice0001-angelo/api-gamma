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
public interface BoardRepository extends JpaRepository<Board, Long>{

}
