/**
 * 
 */
package net.jin.controller;

import java.util.*;

import org.springframework.http.*;
import org.springframework.security.access.prepost.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.*;

import com.fasterxml.jackson.databind.*;

import lombok.*;
import lombok.extern.slf4j.*;
import net.jin.domain.*;
import net.jin.prop.*;
import net.jin.service.*;

/**
 * @author njh
 *
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/items")
public class ItemController {
	
	
	private final ItemService itemService;
	
	private final ShopProperties shopProperties;
	
	//전체목록조회
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<List<Item>> list() throws Exception{
		return new ResponseEntity<List<Item>>(itemService.list(), HttpStatus.OK);
	}
	
	
	//상세목록조회
	@RequestMapping(value = "{itemId}", method = RequestMethod.GET)
	public ResponseEntity<Item> read(@PathVariable("itemId") Long itemId) throws Exception{
		return new ResponseEntity<Item>(itemService.read(itemId), HttpStatus.OK);
	}
	//등록
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<Item> insert(@RequestPart("item") String itemString, 
			@RequestPart("file") MultipartFile originalImageFile, 
			@RequestPart("file2") MultipartFile previewImageFile) throws Exception{
		
		//Item타입의 item 변수에 객체주입하면서 초기화
		//Convert "JSON" to "Java Object" 할때 사용하는것이 아래타입의 문장임.(String to Object), JSON file 이나 JSON url을 convert 할때는 문장이 달라짐 
		Item item = new ObjectMapper().readValue(itemString, Item.class);
		
		String itemName = item.getItemName();
		String description = item.getDescription();
		
		//itemName 의 Null 체크
		if(itemName != null) {
			item.setItemName(itemName);
		}
		
		//description 의 Null 체크
		if(description != null) {
			item.setDescription(description);
		}
		
		//item object에 file 세팅
		item.setPicture(originalImageFile);
		item.setPreview(previewImageFile);
	}
	
	//삭제
	
	//수정
	
	//바이너리 데이터 파일 저장
	
	//원본 이미지 표시
	
	//미리보기 이미지 표시
	
	//이미지 다운로드

}
