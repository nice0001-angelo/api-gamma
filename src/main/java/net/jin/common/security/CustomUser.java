/**
 * 
 */
package net.jin.common.security;

import java.util.*;

import org.springframework.security.core.*;
import org.springframework.security.core.userdetails.*;

import net.jin.domain.*;

/**
 * @author njh
 *
 */
public class CustomUser extends User{
	
	private static final long serialVersionUID = 1L;
	
	private Member member;
	
	public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}
	

}
