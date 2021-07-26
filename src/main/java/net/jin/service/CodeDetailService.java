/**
 * 
 */
package net.jin.service;

import org.springframework.stereotype.*;

import net.jin.domain.*;

/**
 * @author njh
 *
 */
public interface CodeDetailService {
	
	//코드 목록 조회
	
	//코드 상세 조회
	public CodeDetail read(CodeDetail codeDetail) throws Exception;

	//코드 등록 처리
	public CodeDetail insert(CodeDetail codeDetail) throws Exception;
}
