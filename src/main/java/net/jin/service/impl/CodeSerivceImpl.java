/**
 * 
 */
package net.jin.service.impl;

import java.util.*;
import java.util.List;

import org.hibernate.mapping.*;
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
	
	//코드그룹 레파지토리 선언
	private final CodeGroupRepository codeGroupRepository;
	
	//코드상세 레파지토리 선언
	private final CodeDetailRepository codeDetailRepository;
	
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
	
	//지정된 코드그룹의 코드상세 목록 조회
	@Override
	public List<CodeLabelValue> getCodeList(String groupCode) throws Exception{
		
		List<CodeDetail> codeDetails = codeDetailRepository.getCodeList(groupCode);
		
		List<CodeLabelValue> codeList = new ArrayList<CodeLabelValue>();
		
		for(CodeDetail codeDetail : codeDetails) {
			codeList.add(new CodeLabelValue(codeDetail.getCodeValue(), codeDetail.getCodeName()));
		}
		
		return codeList;
	}

}
