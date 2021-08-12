/**
 * 
 */
package net.jin.common.security;

import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.userdetails.*;

import lombok.extern.slf4j.*;
import net.jin.common.security.domain.*;
import net.jin.domain.*;
import net.jin.repository.*;

/**
 * @author njh
 *
 */
@Slf4j
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private MemberRepository memberRepository;
	
	//사용자정보 조회
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException{
		Member member = memberRepository.findByUserId(userName).get(0);
		return member == null?null:new CustomUser(member);
	}
}
