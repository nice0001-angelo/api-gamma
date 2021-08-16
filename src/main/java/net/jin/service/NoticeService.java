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
public interface NoticeService {
	
	//전체목록조회
	public List<Notice> list() throws Exception;

	//상세목록조회
	public Notice read(Long noticeNo) throws Exception;
	
	//등록
	public Notice insert(Notice notice) throws Exception;
	
	//삭제
	public void delete(Long noticeNo) throws Exception;
	
	//수정
}
