/**
 * 
 */
package net.jin.controller;

import org.springframework.http.*;
import org.springframework.security.access.prepost.*;
import org.springframework.security.core.annotation.*;
import org.springframework.web.bind.annotation.*;

import lombok.*;
import lombok.extern.slf4j.*;
import net.jin.common.security.domain.*;

/**
 * @author njh
 *
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value =  "/useritems")
public class UserItemController {
	
	//사용자 구매 상품 목록
	@PreAuthorize("hasAnyRole('ADMIN','MEMBER')")
	@GetMapping
	public ResponseEntity<T> getUserItemList(@AuthenticationPrincipal CustomUser customerUser) throws Exception{
		
	}
	
	

}
