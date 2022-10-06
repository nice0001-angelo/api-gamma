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
	
	//Signing key for HS512 algorithm
	//You can use the page http://www.allkeysgenerator.com/ to generate all kinds of keys
	public static final String JWT_SCRET = "";
	
	//JWT 토큰 기본 상수값 정의
	public static final String TOKEN_HEADER = "Authorization";
	public static final String TOKEN_PREFIX = "Bearer";
	public static final String TOKEN_TYPE = "JWT";
	public static final String TOKEN_ISSUER = "secure-api";
	public static final String TOKEN_AUDIENCE = "secure-app";
	public static final String TOKEN_ALG = "HS512";
	
	//Username
	public static final String USER_MP = "raptor_memportal";
	public static final String USER_EP = "raptor_empportal";
	public static final String USER_AM = "raptor_snow";
	public static final String USER_DV = "raptor_dev";
	
	public static final String MP ="MP";
	public static final String EP ="EP";
	public static final String AM ="AM";
	public static final String IT ="IT";
	public static final String DV ="DV";
	public static final String SW ="Swagger UI";

	private SecurityConstants() {
		throw new IllegalStateException("Cannot create instance of static util class");
	}
}

