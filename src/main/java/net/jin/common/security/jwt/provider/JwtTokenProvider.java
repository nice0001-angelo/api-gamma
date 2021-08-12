/**
 * 
 */
package net.jin.common.security.jwt.provider;

import java.util.*;
import java.util.stream.*;

import org.springframework.security.authentication.*;
import org.springframework.security.core.authority.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.*;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.*;
import lombok.*;
import lombok.extern.slf4j.*;
import net.jin.common.security.domain.*;
import net.jin.common.security.jwt.constants.*;
import net.jin.domain.*;
import net.jin.prop.*;

/**
 * @author njh
 *
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

private final ShopProperties shopProperties;
	
	public long getUserNo(String header) throws Exception {
		String token = header.substring(7);
        
		byte[] signingKey = getSigningKey();

		Jws<Claims> parsedToken = Jwts.parser()
			.setSigningKey(signingKey)
			.parseClaimsJws(token);
        
		String subject = parsedToken.getBody().getSubject();
        
		long userNo = Long.parseLong(subject);

		return userNo;
	}
	
	public String createToken(long userNo, String userId, List<String> roles) {
		byte[] signingKey = getSigningKey();

		String token = Jwts.builder()
			.signWith(Keys.hmacShaKeyFor(signingKey), SignatureAlgorithm.HS512)
			.setHeaderParam("typ", SecurityConstants.TOKEN_TYPE)
			.setExpiration(new Date(System.currentTimeMillis() + 864000000))
			.claim("uno", "" + userNo)
			.claim("uid", userId)
			.claim("rol", roles)
			.compact();
		
		return token;
	}
	
	public UsernamePasswordAuthenticationToken getAuthentication(String tokenHeader) {
        if (isNotEmpty(tokenHeader)) {
            try {
            	byte[] signingKey = getSigningKey();

                Jws<Claims> parsedToken = Jwts.parser()
                    .setSigningKey(signingKey)
                    .parseClaimsJws(tokenHeader.replace("Bearer ", ""));
                
                Claims claims = parsedToken.getBody();

                String userNo = (String)claims.get("uno");
                String userId = (String)claims.get("uid");
                
                List<SimpleGrantedAuthority> authorities = ((List<?>) claims.get("rol"))
                	.stream()
                    .map(authority -> new SimpleGrantedAuthority((String) authority))
                    .collect(Collectors.toList());
                
                if (isNotEmpty(userId)) {
                	Member member = new Member();
                	member.setUserNo(Long.parseLong(userNo));
                	member.setUserId(userId);
                	member.setUserPw("");
                	
                	UserDetails userDetails = new CustomUser(member, authorities);
                	
                    return new UsernamePasswordAuthenticationToken(userDetails, null, authorities);
                }
            } catch (ExpiredJwtException exception) {
                log.warn("Request to parse expired JWT : {} failed : {}", tokenHeader, exception.getMessage());
            } catch (UnsupportedJwtException exception) {
                log.warn("Request to parse unsupported JWT : {} failed : {}", tokenHeader, exception.getMessage());
            } catch (MalformedJwtException exception) {
                log.warn("Request to parse invalid JWT : {} failed : {}", tokenHeader, exception.getMessage());
            } catch (IllegalArgumentException exception) {
                log.warn("Request to parse empty or null JWT : {} failed : {}", tokenHeader, exception.getMessage());
            }
        }

        return null;
    }
	
	private byte[] getSigningKey() {
		return shopProperties.getSecretKey().getBytes();
	}
	
	private boolean isNotEmpty(final CharSequence cs) {
		return !isEmpty(cs);
	}

	private boolean isEmpty(final CharSequence cs) {
		return cs == null || cs.length() == 0;
	}

	public boolean validateToken(String jwtToken) {
	    try {
	        Jws<Claims> claims = Jwts.parser().setSigningKey(shopProperties.getSecretKey()).parseClaimsJws(jwtToken);
	        return !claims.getBody().getExpiration().before(new Date());
	    } catch (ExpiredJwtException exception) {
            log.error("Token Expired");
            return false;
        } catch (JwtException exception) {
            log.error("Token Tampered");
            return false;
        } catch (NullPointerException exception) {
            log.error("Token is null");
            return false;
        } catch (Exception e) {
	        return false;
	    }
	}
}
