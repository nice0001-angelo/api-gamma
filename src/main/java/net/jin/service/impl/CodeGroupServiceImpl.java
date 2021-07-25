/**
 * 
 */
package net.jin.service.impl;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.*;
import org.springframework.data.domain.Sort.*;
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
	private final CodeGroupRepository codeGroupRepository; //@RequiredArgsConstructor 로 쉽게 의존성 주입(Dependency Injection), final keyword를 쓸때는 @Autowired 못씀
		
	//코드그룹 목록조회
	@Override
	public List<CodeGroup> list() throws Exception{
		return codeGroupRepository.findAll(Sort.by(Direction.DESC, "groupCode")); //JPA 메소드를 잘 알아야 함
	}
	
	//코드그룹 상세조회
	@Override
	public CodeGroup read(String groupCode) throws Exception{
		return codeGroupRepository.getById(groupCode); //getOne(groupCode) 대신에 썼음
	}

	//코드그룹 등록처리
	public CodeGroup insert(CodeGroup codeGroup) throws Exception{
		System.out.println("SerivceImpl");
		codeGroupRepository.save(codeGroup);
		return codeGroupRepository.getById(codeGroup.getGroupCode()); 
	}
}
