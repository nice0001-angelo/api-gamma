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
public interface CodeGroupService {

	//코드그룹 목록조회
	public List<CodeGroup> list() throws Exception;
	
	//코드그룹 상세조회
	public CodeGroup read(String groupCode) throws Exception;
	
	//코드그룹 등록처리
	public CodeGroup insert(CodeGroup codeGroup) throws Exception;

}
