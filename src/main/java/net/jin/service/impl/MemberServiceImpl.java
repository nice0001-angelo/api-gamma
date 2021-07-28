/**
 * 
 */
package net.jin.service.impl;

import java.util.*;

import org.springframework.stereotype.*;

import lombok.*;
import lombok.extern.slf4j.*;
import net.jin.domain.*;
import net.jin.repository.*;
import net.jin.service.*;

/**
 * @author njh
 *
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService{
	
	private final MemberRepository memberRepository;
	
	public List<Member> list() throws Exception{
		return memberRepository.findAll();
	}

}
