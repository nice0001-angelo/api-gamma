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
public class CodeGroupServiceImpl implements CodeGroupService{
	
	private final CodeGroupRepository repository;
		
	@Override
	public CodeGroup read(String groupCode) throws Exception{
		return repository.getById(groupCode); //getOne(groupCode) 대신에 썼음
	}
}
