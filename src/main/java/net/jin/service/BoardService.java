/**
 * 
 */
package net.jin.service;

import java.util.*;

import net.jin.domain.*;

/**
 * @author njh
 *
 */
public interface BoardService {
	
	//전체 목록조회
	public List<Board> list() throws Exception;

}
