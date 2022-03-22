/**
 * 
 */
package net.jin.service.impl;

import java.time.*;
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
	
	private final PayCoinRepository payCoinRepository;
	
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
		return chargeCoinRepository.findAll(Sort.by(Direction.DESC, "historyNo"));
	}

	@Override
	public List<PayCoin> listPayHistory(Long userNo) throws Exception {
		
		//list array를 레파지토리에서 받아 온다(컬럼, 레코드)
		List<Object[]> listArr = payCoinRepository.listPayHistory(userNo);
		
		List<PayCoin> payCoinList = new ArrayList<PayCoin>();
		
		for(Object[] x : listArr) {
			
			PayCoin payCoin = new PayCoin();
			
			payCoin.setHistoryNo((Long)x[0]);
			payCoin.setUserNo((Long)x[1]);
			payCoin.setItemId((Long)x[2]);
			payCoin.setItemName((String)x[3]);
			payCoin.setAmount((int)x[4]);
			payCoin.setRegDate((LocalDateTime)x[5]);
			
			payCoinList.add(payCoin);
		}
		return payCoinList;
	}
	
}
