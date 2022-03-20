/**
 * 
 */
package net.jin.common.security.domain;

import java.util.*;
import java.util.stream.*;

import org.springframework.security.core.*;
import org.springframework.security.core.authority.*;
import org.springframework.security.core.userdetails.*;

import net.jin.domain.*;

/**
 * @author njh
 * Custom 한 유저인데 고민 해볼것 세션에서 받나? User를 상속
 *
 */
public class CustomUser extends User{
	
	//필드
	private static final long serialVersionUID = 1L;
	
	//필드
	private Member member;
	
	//생성자
	public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}
	
	//생성자
	public CustomUser(Member member) {
		super(member.getUserId(), member.getUserPw(), member.getAuthList().stream()
				.map(auth->new SimpleGrantedAuthority(auth.getAuth())).collect(Collectors.toList()));
		
		this.member = member;
	}

	//생성자
	public CustomUser(Member member, Collection<? extends GrantedAuthority> authorities) {
		super(member.getUserId(), member.getUserPw(), authorities);
		this.member = member;
	}
	
	public Long getUserNo(){
		return member.getUserNo();
	}
	
	public String getUserId() {
		return member.getUserId();
	}
}
