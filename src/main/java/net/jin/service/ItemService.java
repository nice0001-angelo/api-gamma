/**
 * 
 */
package net.jin.service;

import java.util.*;

import net.jin.domain.*;

/**
 * @author njh
 *
 */
public interface ItemService {
	
	//전체목록조회
	public List<Item> list() throws Exception;
	
	//상세목록조회
	public Item read(Long itemId) throws Exception;
	
	//등록
	public Item insert(Item item) throws Exception;
	
	//삭제
	public void delete(Long itemId) throws Exception;
	
	//수정
	public Item update(Item item) throws Exception;
	
	//바이너리 데이터 파일 저장
	
	//원본 이미지 표시
	public String getPicture(Long itemId) throws Exception;
	
	//미리보기 이미지 표시
	public String getPreview(Long itemId) throws Exception;
	
	//이미지 다운로드

}
