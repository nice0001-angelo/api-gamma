/**
 * 
 */
package net.jin.service.impl;

import java.util.*;

import org.springframework.data.domain.*;
import org.springframework.data.domain.Sort.*;
import org.springframework.stereotype.*;

import lombok.*;
import net.jin.domain.*;
import net.jin.dto.*;
import net.jin.repository.*;
import net.jin.service.*;

/**
 * @author njh
 *
 */
@RequiredArgsConstructor
@Service
public class CodeSerivceImpl implements CodeService{
	
	private final CodeGroupRepository codeGroupRepository;
	
	//코드그룹 목록 조회
	@Override
	public List<CodeLabelValue> getCodeGroupList() throws Exception{
		List<CodeGroup> codeGroups = codeGroupRepository.findAll(Sort.by(Direction.ASC,"groupCode"));
		
		List<CodeLabelValue> codeGroupList = new ArrayList<CodeLabelValue>();
		
		for(CodeGroup codeGroup : codeGroups) {
			codeGroupList.add(new CodeLabelValue(codeGroup.getGroupCode(), codeGroup.getGroupName()));
		}
		
		return codeGroupList;
	}

}
