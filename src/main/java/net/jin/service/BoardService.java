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

	//상세 목록조회
	public Board read(Long boardNo) throws Exception;
	
	//등록
	public Board insert(Board board) throws Exception;
	
	//삭제
	public void delete(Long boardNo) throws Exception;
	
	//수정
	public Board update(Board board) throws Exception;
}
