/**
 * 
 */
package net.jin.service.impl;

import java.util.*;

import org.springframework.data.domain.*;
import org.springframework.data.domain.Sort.*;
import org.springframework.security.access.prepost.*;
import org.springframework.stereotype.*;

import lombok.extern.slf4j.*;
import net.jin.domain.*;
import net.jin.repository.*;
import net.jin.service.*;

/**
 * @author njh
 *
 */
@Slf4j
@Service
public class ItemServiceImpl implements ItemService{
	
	ItemRepository itemRepository;
	
	//전체목록조회
	@Override
	public List<Item> list() throws Exception{
		return itemRepository.findAll(Sort.by(Direction.DESC,"itemId"));
	}
	
	//상세목록조회
	@Override
	public Item read(Long itemId) throws Exception{
		return itemRepository.getById(itemId);
	}
	
	//등록
	@Override
	public Item insert(Item item) throws Exception{
		return itemRepository.save(item);
	}
	
	//삭제
	@Override
	public void delete(Long itemId) throws Exception{
		itemRepository.deleteById(itemId);
	}
	
	
	//수정
	
	
	//바이너리 데이터 파일 저장
	
	//원본 이미지 표시
	
	//미리보기 이미지 표시
	
	//이미지 다운로드

}
