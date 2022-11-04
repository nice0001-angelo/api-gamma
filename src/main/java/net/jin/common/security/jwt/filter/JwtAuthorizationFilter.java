package net.jin.common.security.jwt.filter;

import java.io.*;
import java.util.List;
import java.util.stream.*;

import javax.servlet.*;
import javax.servlet.http.*;

import org.slf4j.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.*;
import org.springframework.security.core.authority.*;
import org.springframework.security.web.authentication.www.*;




import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import io.micrometer.core.instrument.util.StringUtils;
import net.jin.common.security.jwt.constants.*;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
	
	private static final Logger Log = org.slf4j.LoggerFactory.getLogger(JwtAuthenticationFilter.class);
	
	public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, 
			FilterChain filterChain) throws IOException, ServletException {
		Authentication authentication = getAuthentication(request);
		if(authentication == null) {
			filterChain.doFilter(request, response);
			return;
		}
		
	}

	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(SecurityConstants.TOKEN_HEADER);
		
		if(StringUtils.isNotEmpty(token) && token.startsWith(SecurityConstants.TOKEN_PREFIX)) {
			try {
				byte[] signingKey = SecurityConstants.JWT_SCRET.getBytes();
				
				Jws<Claims> parsedToken = Jwts.parser()
						.setSigningKey(signingKey)
						.parseClaimsJws(token.replace("Bearer ", ""));
				
				String username = parsedToken
						.getBody()
						.getSubject();
				
				Log.info("username: "+username);
				
				List<GrantedAuthority> authorities = ((List<?>) parsedToken.getBody()
						.get("rol")).stream()
						.map(authority -> new SimpleGrantedAuthority((String) authority))
						.collect(Collectors.toList());
				if(StringUtils.isNotEmpty(username)) {
					return new UsernamePasswordAuthenticationToken(username, null, authorities);
				}
			} catch (ExpiredJwtException exception) {
				Log.error("Request to parse expired JWT : {} failed : {}", token, exception.getMessage());
			} catch (UnsupportedJwtException exception) {
				Log.error("Request to parse unsupported JWT : {} failed : {}", token, exception.getMessage());
			} catch (MalformedJwtException exception) {
				Log.error("Request to parse Invalid JWT : {} failed : {}", token, exception.getMessage());
			} catch (SignatureException exception) {
				Log.error("Request to parse JWT with invalid signature : {} failed : {}", token, exception.getMessage());
			} catch (IllegalArgumentException exception) {
				Log.error("Request to parse empty or null JWT : {} failed : {}", token, exception.getMessage());
			}
		}
		return null;
	}
}
