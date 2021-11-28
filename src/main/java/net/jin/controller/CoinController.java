/**
 * 
 */
package net.jin.controller;

import java.util.*;

import org.springframework.context.*;
import org.springframework.http.*;
import org.springframework.security.access.prepost.*;
import org.springframework.security.core.annotation.*;
import org.springframework.web.bind.annotation.*;

import lombok.*;
import net.jin.common.security.domain.*;
import net.jin.domain.*;
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

		Long userNo = customUser.getUserNo();
		
		ChargeCoin chargeCoin = new ChargeCoin();
		
		chargeCoin.setUserNo(userNo);
		chargeCoin.setAmount(amount);
		
		coinService.charge(chargeCoin);
		
		String message = messageSource.getMessage("coin.chargingComplete", null, Locale.KOREAN);
		
		return new ResponseEntity<String>(message, HttpStatus.OK);


	}
	
	//충전 내역 화면
	@PreAuthorize("hasRole('MEMBER')")
	@GetMapping
	public ResponseEntity<List<ChargeCoin>> list(@AuthenticationPrincipal CustomUser customUser) throws Exception{
		Long userNo = customUser.getUserNo();
		
		return new ResponseEntity<>(coinService.list(userNo), HttpStatus.OK);
	}

}
