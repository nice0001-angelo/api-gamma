/**
 * 
 */
package net.jin.common.security.jwt.constants;

/**
 * @author njh
 *
 */
public final class SecurityConstants {
	
	//로그인 인증 URL 정의
	public static final String AUTH_LOGIN_URL="/api/authenticate";
	
	//JWT 토큰 기본 상수값 정의
	public static final String TOKEN_HEADER = "Authorization";
	public static final String TOKEN_PREFIX = "Bearer";
	public static final String TOKEN_TYPE = "JWT";

}
