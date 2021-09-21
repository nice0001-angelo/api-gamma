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
	@Override
	public Item update(Item item) throws Exception{
		Item itemEntity = itemRepository.getById(item.getItemId());
		
		itemEntity.setItemName(item.getItemName());
		itemEntity.setDescription(item.getDescription());
		itemEntity.setPicture(item.getPicture());
		itemEntity.setPreview(item.getPreview());
		itemEntity.setPictureUrl(item.getPictureUrl());
		itemEntity.setPreviewUrl(item.getPictureUrl());
		itemEntity.setPrice(item.getPrice());
		
		itemRepository.save(itemEntity);
		
		return itemEntity;
	}
	
	
	
	//원본 이미지 표시
	@Override
	public String getPicture(Long itemId) throws Exception{
		Item item = itemRepository.getById(itemId);
		return item.getPictureUrl();
	}
	
	//미리보기 이미지 표시
	@Override
	public String getPreview(Long itemId) throws Exception{
		Item item = itemRepository.getById(itemId);
		System.out.println();
		System.out.println();
		return item.getPreviewUrl();	

	}
	

}
