package net.jin.service.impl;

import java.util.*;

import javax.transaction.*;

import org.springframework.data.domain.*;
import org.springframework.data.domain.Sort.*;
import org.springframework.stereotype.*;

import lombok.*;
import lombok.extern.slf4j.*;
import net.jin.domain.*;
import net.jin.repository.*;
import net.jin.service.*;

@Slf4j
@RequiredArgsConstructor //이게 없으면 private final UserItemRepository userItemRepository; 오류 난다. 
//@RequiredArgsConstructor 는 필드에서 final 이 붙어있는 것들만 생성자 주입을 해준다.
//이렇게 하지 않으면 private UserItemRepository userItemRepository 로 작성하고 생성자 인젝션을 통해서 필드 초기화 해야 한다.
//https://blog.naver.com/tnwnsrla/222676955788 참고
@Service
public class UserItemServiceImpl implements UserItemService{
	
	private final UserItemRepository userItemRepository;
	
	private final PayCoinRepository payCoinRepository;
	
	private final MemberRepository memberRepository; 
	

	//등록
	@Transactional
	@Override
	public void register(Member member, Item item) throws Exception {
		Long userNo = member.getUserNo();
		Long itemId = item.getItemId();
		int price = item.getPrice();
		
		UserItem userItem = new UserItem();
		userItem.setUserNo(userNo);
		userItem.setItemId(itemId);
		
		PayCoin payCoin = new PayCoin();
		payCoin.setUserNo(userNo);
		payCoin.setItemId(itemId);
		payCoin.setAmount(price);
		
		Member memberEntity =  memberRepository.getById(userNo);
		
		int coin = memberEntity.getCoin();
		int amount = payCoin.getAmount();
		
		//멤버 객체에서 갖고 있는 코인을 가져와서 amout를 빼서 차감
		memberEntity.setCoin(coin-amount);
		
		userItemRepository.save(userItem);
		payCoinRepository.save(payCoin);
		memberRepository.save(memberEntity);
		
	}

	
	//사용자 구매 상품 전체 목록
	@Override
	public List<UserItem> list(Long userNo) throws Exception {
		//List<Object[]> valueArrays =  userItemRepository.listUserItem(userNo);
		List<UserItem> userItemList = new ArrayList<UserItem>();
		System.out.println();
		System.out.println();
		
		
		return userItemRepository.findAll(Sort.by(Direction.DESC,"userNo"));
			}


	//사용자 구매상품 단품 보기
	@Override
	public UserItem read(Long userItemNo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	
}
