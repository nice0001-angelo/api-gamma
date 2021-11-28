/**
 * 
 */
package net.jin.service.impl;

import java.util.*;

import javax.transaction.*;

import org.springframework.data.domain.*;
import org.springframework.data.domain.Sort.*;
import org.springframework.stereotype.*;

import lombok.*;
import net.jin.domain.*;
import net.jin.repository.*;
import net.jin.service.*;

/**
 * @author njh
 *
 */
@RequiredArgsConstructor
@Service
public class CoinServiceImpl implements CoinService {
	
	private final ChargeCoinRepository chargeCoinRepository;
	
	private final MemberRepository memberRepository;
	
	//코인충전처리
	@Transactional
	@Override
	public void charge(ChargeCoin chargeCoin) throws Exception{
		Member memberEntity = memberRepository.getById(chargeCoin.getUserNo());
		
		int coin = memberEntity.getCoin();
		int amount = chargeCoin.getAmount();
		
		memberEntity.setCoin(coin + amount);
		
		memberRepository.save(memberEntity);
		
		chargeCoinRepository.save(chargeCoin);
	}
	
	//충전내역처리화면
	@Override
	public List<ChargeCoin> list(Long userNo) throws Exception{
		chargeCoinRepository.findAll(Sort.by(Direction.DESC, "historyNo"));
	}

}
