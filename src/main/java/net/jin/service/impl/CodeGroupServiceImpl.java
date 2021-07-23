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
public class CodeGroupServiceImpl implements CodeGroupService{
	
	//원래 인터페이스를 DI 할때는 @Autowired를 써야한다. 다만 final 일경우는 @RequiredArgsConstructor을 쓸수 밖에없다
	private final CodeGroupRepository repository; //@RequiredArgsConstructor 로 쉽게 의존성 주입(Dependency Injection), final keyword를 쓸때는 @Autowired 못씀
		
	@Override
	public CodeGroup read(String groupCode) throws Exception{
		return repository.getById(groupCode); //getOne(groupCode) 대신에 썼음
	}
}
