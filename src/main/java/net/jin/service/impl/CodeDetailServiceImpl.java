/**
 * 
 */
package net.jin.service.impl;

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
	
	@Override
	public CodeDetail read(CodeDetail codeDetail) throws Exception{
		
		CodeDetailId codeDetailId = new CodeDetailId();
		
		codeDetailId.setGroupCode(codeDetail.getGroupCode());
		codeDetailId.setCodeValue(codeDetail.getCodeValue());
		
		//CodeDetailRepository를 인터페이스로 선언했다 JpaRepository를 상속했기 때문에 JpaRepository의 메소드를 쓸수 있다
		return codeDetailRepository.getById(codeDetailId);
		
	}
	
	

}
