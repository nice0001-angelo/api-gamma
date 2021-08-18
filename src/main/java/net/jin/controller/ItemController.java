/**
 * 
 */
package net.jin.controller;

import java.util.*;

import org.springframework.http.*;
import org.springframework.security.access.prepost.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.*;

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
		
	}
	
	//삭제
	
	//수정
	
	//바이너리 데이터 파일 저장
	
	//원본 이미지 표시
	
	//미리보기 이미지 표시
	
	//이미지 다운로드

}
