/**
 * 
 */
package net.jin.service.impl;

import java.util.*;

import org.springframework.jca.cci.core.support.*;
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
	@Override
	public List<CodeDetail> list(){
		return codeDetailRepository.findAll(); 
	}
	
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
		
		
//		List<Object[]> rsList = codeDetailRepository.getMaxSortSeq(codeDetail.getGroupCode());
//		
//		Integer maxSortSeq = 0;
//		if(rsList.size()>0) {
//			Object[] maxValues = rsList.get(0);
//			if(maxValues!=null && maxValues.length>0) {
//				maxSortSeq = (Integer)maxValues[0];
//			}
//		}
		
		//위에 처럼 어렵게 할것 없이 Integer 값으로 치환하여 최대값을 가져다가 처리하면된다
		Integer maxSortSeq = codeDetailRepository.getMaxSortSeq(codeDetail.getGroupCode());
		codeDetail.setSortSeq(maxSortSeq+1);
		
		return codeDetailRepository.save(codeDetail);
	}
	
	//코드 삭제 처리
	@Override
	public void delete(String groupCode, String codeValue) throws Exception{
		CodeDetailId codeDetailID = new CodeDetailId();
		codeDetailID.setGroupCode(groupCode);
		codeDetailID.setCodeValue(codeValue);
		codeDetailRepository.deleteById(codeDetailID);
		
	}
	
	//코드 수정 처리
	@Override
	public CodeDetail update(CodeDetail codeDetail) throws Exception{
		
		
		System.out.println("CodeDetailService Impl====> "+codeDetail);
		
		CodeDetailId codeDetailId = new CodeDetailId();
		codeDetailId.setGroupCode(codeDetail.getGroupCode());
		codeDetailId.setCodeValue(codeDetail.getCodeValue());
		
		CodeDetail afterGetCodeDetailID = codeDetailRepository.getById(codeDetailId);
		
		
		afterGetCodeDetailID.setCodeName(codeDetail.getCodeName());
		
		return codeDetailRepository.save(afterGetCodeDetailID);
	}

}
