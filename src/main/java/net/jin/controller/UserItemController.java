///**
// * 
// */
//package net.jin.controller;
//
//import java.util.List;
//
//import org.springframework.http.*;
//import org.springframework.security.access.prepost.*;
//import org.springframework.security.core.annotation.*;
//import org.springframework.web.bind.annotation.*;
//
//import lombok.*;
//import lombok.extern.slf4j.*;
//import net.jin.common.security.domain.*;
//import net.jin.domain.*;
//import net.jin.prop.*;
//import net.jin.service.*;
//
///**
// * @author njh
// *
// */
//@Slf4j
//@RequiredArgsConstructor
//@RestController
//@RequestMapping(value =  "/useritems")
//public class UserItemController {
//	
//	private final UserItemService userItemService;
//	
//	private final ShopProperties shopProperties;
//	
//	//사용자 구매 상품 전체 목록
//	@PreAuthorize("hasAnyRole('ADMIN','MEMBER')")
//	@GetMapping
//	public ResponseEntity<List<UserItem>> getUserItemList(@AuthenticationPrincipal CustomUser customerUser) throws Exception{
//		
//		Long userNo = customerUser.getUserNo();
//		
//		log.info("read: userNo"+userNo);
//		
//		return new ResponseEntity<>(userItemService.list(userNo), HttpStatus.OK);
//		
//	}
//	
//	//사용자 구매상품 단품 보기
//	@PreAuthorize("hasAnyRole('ADMIN','MEMBER')")
//	@GetMapping("/{userItemNo}")
//	public ResponseEntity<UserItem> getUserItemRead(@PathVariable("{userItemNo") Long userItemNo) throws Exception{
//		
//		UserItem userItem = userItemService.read(userItemNo);
//		System.out.println();
//		return new ResponseEntity<UserItem>(userItem, HttpStatus.OK);
//	}
//	
//	
//
//}

package net.jin.controller;

import java.io.*;
import java.util.*;

import org.apache.commons.io.*;
import org.springframework.http.*;
import org.springframework.security.access.prepost.*;
import org.springframework.security.core.annotation.*;
import org.springframework.web.bind.annotation.*;

import lombok.*;
import lombok.extern.slf4j.*;
import net.jin.common.security.domain.*;
import net.jin.domain.*;
import net.jin.prop.*;
import net.jin.service.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/useritems")
public class UserItemController {

	private final UserItemService service;

	private final ShopProperties shopProperties;

	@PreAuthorize("hasAnyRole('ADMIN','MEMBER')")
	@GetMapping
	public ResponseEntity<List<UserItem>> list(@AuthenticationPrincipal CustomUser customUser) throws Exception {
		Long userNo = customUser.getUserNo();
		
		log.info("read : userNo " + userNo);
		
		return new ResponseEntity<>(service.list(userNo), HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN','MEMBER')")
	@GetMapping("/{userItemNo}")
	public ResponseEntity<UserItem> read(@PathVariable("userItemNo") Long userItemNo) throws Exception {
		UserItem userItem = service.read(userItemNo);
		
		return new ResponseEntity<>(userItem, HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN','MEMBER')")
	@GetMapping("/download/{userItemNo}")
	public ResponseEntity<byte[]> download(@PathVariable("userItemNo") Long userItemNo) throws Exception {
		log.info("download userItemNo = " + userItemNo);
		
		UserItem userItem = service.read(userItemNo);
		
		String fullName = userItem.getPictureUrl();

		InputStream in = null;
		ResponseEntity<byte[]> entity = null;

		try {
			HttpHeaders headers = new HttpHeaders();

			in = new FileInputStream(shopProperties.getUploadPath() + File.separator + fullName);

			String fileName = fullName.substring(fullName.indexOf("_") + 1);

			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			headers.add("Content-Disposition", "attachment; filename=\"" + new String(fileName.getBytes("UTF-8"), "ISO-8859-1") + "\"");

			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		} finally {
			in.close();
		}

		return entity;
	}
	
}
