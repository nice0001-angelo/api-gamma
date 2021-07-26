/**
 * 
 */
package net.jin.service;

import java.util.*;

import org.springframework.stereotype.*;

import net.jin.domain.*;

/**
 * @author njh
 *
 */
public interface CodeDetailService {
	
	//코드 목록 조회
	public List<CodeDetail> list() throws Exception; 
	
	//코드 상세 조회
	public CodeDetail read(CodeDetail codeDetail) throws Exception;

	//코드 등록 처리
	public CodeDetail insert(CodeDetail codeDetail) throws Exception;
	
	//코드 삭제 처리
	public void delete(String groupCode, String codeValue) throws Exception;
}
