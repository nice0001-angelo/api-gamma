/**
 * 
 */
package net.jin.service;

import net.jin.domain.*;

/**
 * @author njh
 *
 */
public interface CodeGroupService {

	//코드그룹 상세조회
	public CodeGroup read(String groupCode) throws Exception;
	
	//코드그룹 리스트 조회
	public CodeGroup list() throws Exception;

}
