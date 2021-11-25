/**
 * 
 */
package net.jin.controller;

import org.springframework.context.*;
import org.springframework.http.*;
import org.springframework.security.access.prepost.*;
import org.springframework.security.core.annotation.*;
import org.springframework.web.bind.annotation.*;

import lombok.*;
import net.jin.common.security.domain.*;
import net.jin.service.*;

/**
 * @author njh
 *
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/coins")
public class CoinController {
	
	private final CoinService coinService;
	
	private final MessageSource messageSource;
	
	//코인충전 처리
	@PreAuthorize("hasRole('MEMBER')")
	@PostMapping(value = "/charge/{amount}", produces = "text/plain;charset=UTF-8")
	public ResponseEntity<String> charge(@PathVariable("amount") int amount, @AuthenticationPrincipal CustomUser customUser) throws Exception {
		return ResponseEntity<String>;
	}

}
