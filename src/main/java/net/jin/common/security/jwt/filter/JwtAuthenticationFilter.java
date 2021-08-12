/**
 * 
 */
package net.jin.common.security.jwt.filter;

import java.util.*;
import java.util.stream.*;

import javax.servlet.*;
import javax.servlet.http.*;

import org.springframework.security.authentication.*;
import org.springframework.security.core.*;
import org.springframework.security.web.authentication.*;

import net.jin.common.security.domain.*;
import net.jin.common.security.jwt.constants.*;
import net.jin.common.security.jwt.provider.*;

/**
 * @author njh
 *
 */
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter{

	private final AuthenticationManager authenticationManager;
    
    private final JwtTokenProvider jwtTokenProvider;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        
        this.jwtTokenProvider = jwtTokenProvider;

        setFilterProcessesUrl(SecurityConstants.AUTH_LOGIN_URL);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        
        return authenticationManager.authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain, Authentication authentication) {
        CustomUser user = ((CustomUser) authentication.getPrincipal());
    	long userNo = user.getUserNo();
    	String userId = user.getUserId();
    	
        List<String> roles = user.getAuthorities()
        	.stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.toList());    	

        String token = jwtTokenProvider.createToken(userNo, userId, roles);

        response.addHeader(SecurityConstants.TOKEN_HEADER, SecurityConstants.TOKEN_PREFIX + token);
    }
}
