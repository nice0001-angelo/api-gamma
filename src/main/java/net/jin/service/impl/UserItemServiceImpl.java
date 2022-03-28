package net.jin.service.impl;

import java.util.*;

import org.springframework.stereotype.*;

import lombok.extern.slf4j.*;
import net.jin.domain.*;
import net.jin.repository.*;
import net.jin.service.*;

@Slf4j
@Service
public class UserItemServiceImpl implements UserItemService{
	
	private final UserItemRepository userItemRepository;
	
	private final PayCoinRepository payCoinRepository;
	
	private final MemberRepository memberRepository; 
	
	@Override
	public List<UserItem> list(Long userNo) throws Exception {
		// TODO Auto-generated method stub
		System.out.println();
		return null;
	}

	//등록
	@Override
	public void register(Member member, Item item) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
