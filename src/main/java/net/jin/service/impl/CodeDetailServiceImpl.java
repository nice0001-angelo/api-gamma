/**
 * 
 */
package net.jin.service.impl;

import org.springframework.beans.factory.annotation.*;
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
public class CodeDetailServiceImpl implements CodeGroupService {
	
	private final CodeDetailRepository codeDetailRepository;
	
	@Override
	public CodeDetail read(CodeDetail codeDetail) {
		
	}
	
	

}
