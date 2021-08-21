/**
 * 
 */
package net.jin.controller;

import java.io.*;
import java.util.*;

import org.springframework.http.*;
import org.springframework.security.access.prepost.*;
import org.springframework.util.*;
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
		
		//item object에 세팅된 파일을 Multipartfile 타입의 변수로 세팅
		MultipartFile pictureFile = item.getPicture();
		MultipartFile previewFile = item.getPreview();
		
		//pictureFile, previewFile 에서 이름, byte수 가져오기 uploadFile method 정의 필요
		String createdPictureFilename = uploadFile(pictureFile.getOriginalFilename(),pictureFile.getBytes());
		String createdPreviewFilename = uploadFile(previewFile.getOriginalFilename(),previewFile.getBytes());
		
		//생성된 picture, preview 파일네임을 세팅
		item.setPictureUrl(createdPictureFilename);
		item.setPreviewUrl(createdPreviewFilename);
		
		itemService.insert(item);
		
//		Item createdItem = new Item();
//		createdItem.setItemId(item.getItemId());
		
		return new ResponseEntity<Item>(item , HttpStatus.OK);
	}
	
	//삭제
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/{itemId}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable("itemId") Long itemId) throws Exception{
		itemService.delete(itemId);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	//수정
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "{itemId}", method = RequestMethod.PUT)
	public ResponseEntity<Item> update(@PathVariable("itemId") Long itemId, 
			@RequestPart("item") String itemString, @RequestPart(name = "file", required = false) MultipartFile originalImageFile,
			@RequestPart(name = "file2", required = false) MultipartFile previewImageFile) throws Exception{
		
		//RequestPart를 이용해서 String 타입으로 가져왔기 때문에 Item 타입으로 변환 하는 것이 필요(아래)
		Item item =  new ObjectMapper().readValue(itemString, Item.class);

		item.setPicture(originalImageFile);
		item.setPreview(previewImageFile);
		
		itemService.update(item);
		
		return new ResponseEntity<Item>(HttpStatus.OK);
	}
	
	
	//바이너리 데이터 파일 저장
	public String uploadFile(String originalFilename, byte[] fileData) throws Exception{
		
		//UUID 클래스를 이용해서 유일한 식별자를 생성하기 위해 randomUUID() method를 통해서 유일한 식별자를 생성한다. 업로드 파일 관리를 위해 자주 쓰임
		UUID uid = UUID.randomUUID();
		
		String createdFileName = uid.toString() + "_" + originalFilename;
		
		File target = new File(shopProperties.getUploadPath(), createdFileName);
		
		FileCopyUtils.copy(fileData, target);
		
		return createdFileName;
		
	}
	
	//원본 이미지 표시
	
	//미리보기 이미지 표시
	
	//이미지 다운로드

}
