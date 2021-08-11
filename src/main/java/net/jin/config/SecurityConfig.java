/**
 * 
 */
package net.jin.config;

import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.authentication.builders.*;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.security.crypto.password.*;
import org.springframework.security.web.authentication.*;

import lombok.*;
import lombok.extern.slf4j.*;

/**
 * @author njh
 *
 */
@Slf4j
//인자있는 생성자 자동생성
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	//JWT 토큰을 제공하는 JwtTokenProvider 필드 선언
	private final JwtTokenProvider jwtTokenProvider;
	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception{
		log.info("Security Configuring....");
		
		//폼로그인기능과 베이직인증 비활성화
		httpSecurity.formLogin().disable().httpBasic().disable();
		
		//CORS 사용자 설정
		httpSecurity.cors();
		
		//csrf() Disable 하지 않으면 CUD를 할수 없다  CSRF 방지 지원 기능 비활성화
		httpSecurity.csrf().disable();
		
		//JWT 관련 필터 보안 컨텍스트에 추가
		httpSecurity.addFilterAt(new JwtAuthenticationFilter(authenticationManager(), jwtTokenProvider), UsernamePasswordAuthenticationFilter.class)
		.addFilterBefore(new JwtRequestFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class)
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	
	}	
	
	//CustomUserDetailsService 빈을 인증 제공자에게 지정하고 비밀번호 암호처리기를 등록한다
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(customUserDetailsService())
		.passwordEncoder(passwordEncoder());
	}
	
	//비밀번호 암호 처리기 생성
	@Bean
	public PasswordEncoder passwordEncoder() {
		//- BCryptPasswordEncoder는 BCrypt 해싱 함수(BCrypt hashing function)를 사용해서
		//비밀번호를 인코딩해주는 메서드와 사용자의 의해 제출된 비밀번호와 저장소에 저장되어 있는 비밀번호의 일치 여부를 확인해주는 메서드를 제공합니다.
		return new BCryptPasswordEncoder();
	}
	
	
}
