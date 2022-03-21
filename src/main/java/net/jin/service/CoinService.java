/**
 * 
 */
package net.jin.service;

import java.util.*;

import net.jin.common.security.domain.*;
import net.jin.domain.*;

/**
 * @author njh
 *
 */
public interface CoinService {
	
	//코인충전처리
	public void charge(ChargeCoin chargeCoin) throws Exception;
	
	//코인화면처리
	public List<ChargeCoin> list(Long userNo) throws Exception;
	
	//사용자의 상품 구매내역을 반환한다
	public List<PayCoin> listPayHistory(Long userNo) throws Exception;
}