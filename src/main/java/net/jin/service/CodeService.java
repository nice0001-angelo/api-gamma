/**
 * 
 */
package net.jin.service;

import java.util.*;

import net.jin.dto.*;

/**
 * @author njh
 *
 */
public interface CodeService {
	//코드그룹의 리스트를 가져오는것
	public List<CodeLabelValue> getCodeGroupList() throws Exception;

	//지정된 코드그룹의 상세 리스트를 가져오는것
	public List<CodeLabelValue> getCodeList(String groupCode) throws Exception;
}
