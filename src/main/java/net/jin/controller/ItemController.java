/**
 * 
 */
package net.jin.controller;

import java.io.*;
import java.util.*;

import org.apache.commons.io.*;
import org.springframework.context.*;
import org.springframework.http.*;
import org.springframework.security.access.prepost.*;
import org.springframework.security.core.annotation.*;
import org.springframework.util.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.*;

import com.fasterxml.jackson.databind.*;

import lombok.*;
import lombok.extern.slf4j.*;
import net.jin.common.security.domain.*;
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
	
	//업무로직을 처리할 서비스 객체를 필드로 선언한다
	private final MemberService memberService;
	
	private final UserItemService userItemService;
	
	//메시지를 처리할 MessageSource를 필드로 선언한다
	private final MessageSource messageSource;
	 
	
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
		
		System.out.println();
		//pictureFile, previewFile 에서 이름, byte수 가져오기 uploadFile method 정의 필요
		String createdPictureFilename = uploadFile(pictureFile.getOriginalFilename(),pictureFile.getBytes());
		String createdPreviewFilename = uploadFile(previewFile.getOriginalFilename(),previewFile.getBytes());
		
		//생성된 picture, preview 파일네임을 세팅
		item.setPictureUrl(createdPictureFilename);
		item.setPreviewUrl(createdPreviewFilename);
		
		System.out.println();
		System.out.println();
		
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
		
		//originalImageFile이 null이 아니고 사이즈도 0이상이면 새로운 값을 세팅하고 새로운게 없으면 과거의 값을 세팅한다
		if(originalImageFile != null || originalImageFile.getSize()> 0 ) {
			String createdFilename = uploadFile(originalImageFile.getOriginalFilename(), originalImageFile.getBytes());
			item.setPictureUrl(createdFilename);
		}else {
			Item oldItem = itemService.read(itemId);
			item.setPictureUrl(oldItem.getPictureUrl());
		}
		
		//previewImageFile이 null이 아니고 사이즈도 0이상이면 새로운 값을 세팅하고 새로운게 없으면 과거의 값을 세팅한다
		if(previewImageFile != null || previewImageFile.getSize()>0) {
			String createdFilename = uploadFile(previewImageFile.getOriginalFilename(), previewImageFile.getBytes());
			item.setPreviewUrl(createdFilename);
		}else {
			Item oldItem = itemService.read(itemId);
			item.setPreviewUrl(oldItem.getPreviewUrl());
		}
			
	
		return new ResponseEntity<Item>(itemService.update(item),HttpStatus.OK);
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
	@RequestMapping(value = "/preview", method = RequestMethod.GET)
	public ResponseEntity<byte[]> previewFile(@RequestParam("itemId") Long itemId) throws Exception{
		InputStream in = null;
		ResponseEntity<byte[]> entity = null;
		
		String fileName = itemService.getPreview(itemId);
		
		try {
			String formatName = fileName.substring(fileName.lastIndexOf(".")+1);
			
			MediaType mType = getMediaType(formatName);
			
			HttpHeaders httpHeaders = new HttpHeaders();
			
			in = new FileInputStream(shopProperties.getUploadPath()+File.separator+fileName);
			
			if(mType != null) {
				httpHeaders.setContentType(mType);
			}
			
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), httpHeaders, HttpStatus.CREATED);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		}finally {
			in.close();
		}
		return entity;
	}
	
	//이미지 형식 확인
	private MediaType getMediaType(String formatName) {
		if(formatName != null) {
			if(formatName.equals("JPG")) {
				return MediaType.IMAGE_JPEG;
			}
			
			if(formatName.equals("GIF")) {
				return MediaType.IMAGE_GIF;
			}
			
			if(formatName.equals("PNG")) {
				return MediaType.IMAGE_PNG;
			}
		}
		return null;
	}
	
	//이미지 다운로드
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/download/{itemId}", method = RequestMethod.GET)
	public ResponseEntity<byte[]> downloadFile(@PathVariable("itemId") Long itemId) throws Exception{
		InputStream in = null;
		ResponseEntity<byte[]> entity= null;
		
		String fullName = itemService.getPicture(itemId);
		
		try {
			HttpHeaders httpHeaders = new HttpHeaders();
			
			in = new FileInputStream(shopProperties.getUploadPath()+File.separator+fullName);
			
			String fileName = fullName.substring(fullName.indexOf("_")+1);
			
			httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			
			httpHeaders.add("Content-Disposition", "attachment: filename=\"" + new String(fileName.getBytes("UTF-8"),"ISO-8859-1")+"\"");
			
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), httpHeaders, HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		} finally {
			in.close();
		}
		return entity;
	}
	
	//상품 구매 요청을 처리한다
	@GetMapping(value = "/buy/{itemid}", produces = "text/plain;charset=UTF-8")
	public ResponseEntity<String> buy(@PathVariable("itemid") Long itemId, @AuthenticationPrincipal CustomUser customUser) throws Exception{
		
		Long userNo = customUser.getUserNo();
		
		Member member = memberService.read(userNo);
		
		member.setCoin(memberService.getCoin(userNo));
		
		Item item = itemService.read(itemId);
		
		userItemService.register(member, item);
		
		System.out.println();
		
		String message = messageSource.getMessage("item.purchaseComplete", null, Locale.KOREAN);
		
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}

}
