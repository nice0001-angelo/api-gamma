/**
 * 
 */
package net.jin.service.impl;

import java.util.*;

import org.springframework.stereotype.*;

import lombok.*;
import net.jin.domain.*;
import net.jin.repository.*;
import net.jin.service.*;

/**
 * @author njh
 *
 */
@RequiredArgsConstructor
@Service
public class CodeDetailServiceImpl implements CodeDetailService {
	
	private final CodeDetailRepository codeDetailRepository;
	
	
	//코드 목록 조회
	
	//코드 상세 조회
	@Override
	public CodeDetail read(CodeDetail codeDetail) throws Exception{
		
		CodeDetailId codeDetailId = new CodeDetailId();
		
		codeDetailId.setGroupCode(codeDetail.getGroupCode());
		codeDetailId.setCodeValue(codeDetail.getCodeValue());
		
		//CodeDetailRepository를 인터페이스로 선언했다 JpaRepository를 상속했기 때문에 JpaRepository의 메소드를 쓸수 있다
		return codeDetailRepository.getById(codeDetailId);
		
	}
	
	//코드 등록 처리
	public CodeDetail insert(CodeDetail codeDetail) throws Exception{
		
		
		List<Object[]> rsList = codeDetailRepository.getMaxSortSeq(codeDetail.getGroupCode());
		
		Integer maxSortSeq = 0;
		if(rsList.size()>0) {
			Object[] maxValues = rsList.get(0);
			System.out.println("maxValue======> "+maxValues);
			System.out.println("maxValue[0]======> "+maxValues[0]);
			System.out.println("(Integer)maxValue[0]======> "+(Integer)maxValues[0]);
			if(maxValues!=null && maxValues.length>0) {
				maxSortSeq = (Integer)maxValues[0];
			}
		}
		
		codeDetail.setSortSeq(maxSortSeq+1);
		
		return codeDetailRepository.save(codeDetail);
	}
	
	

}
