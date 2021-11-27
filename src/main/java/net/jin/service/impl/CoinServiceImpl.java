/**
 * 
 */
package net.jin.service.impl;

import javax.transaction.*;

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
	
	@Transactional
	@Override
	public void charge(ChargeCoin chargeCoin) throws Exception{
		Member memberEntity = memberRepository.getOne(chargeCoin.getUserNo());
		
		int coin = memberEntity.getCoin();
		int amount = chargeCoin.getAmount();
		
		memberEntity.setCoin(coin + amount);
		
		memberRepository.save(memberEntity);
		
		chargeCoinRepository.save(chargeCoin);
	}

}
