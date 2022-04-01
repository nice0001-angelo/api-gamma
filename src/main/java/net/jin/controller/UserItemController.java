/**
 * 
 */
package net.jin.controller;

import java.util.List;

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

/**
 * @author njh
 *
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value =  "/useritems")
public class UserItemController {
	
	private final UserItemService userItemService;
	
	private final ShopProperties shopProperties;
	
	//사용자 구매 상품 전체 목록
	@PreAuthorize("hasAnyRole('ADMIN','MEMBER')")
	@GetMapping
	public ResponseEntity<List<UserItem>> getUserItemList(@AuthenticationPrincipal CustomUser customerUser) throws Exception{
		
		Long userNo = customerUser.getUserNo();
		
		log.info("read: userNo"+userNo);
		
		return new ResponseEntity<>(userItemService.list(userNo), HttpStatus.OK);
		
	}
	
	//사용자 구매상품 단품 보기
	@PreAuthorize("hasAnyRole('ADMIN','MEMBER')")
	@GetMapping("/{userItemNo}")
	public ResponseEntity<UserItem> getUserItemRead(@PathVariable("{userItemNo") Long userItemNo) throws Exception{
		
		UserItem userItem = userItemService.read(userItemNo);
		System.out.println();
		return new ResponseEntity<UserItem>(userItem, HttpStatus.OK);
	}
	
	

}
