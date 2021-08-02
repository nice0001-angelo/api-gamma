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
	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception{
		log.info("Security Configuring....");
		
		//폼로그인기능과 베이직인증 비활성화
		httpSecurity.formLogin().disable().httpBasic().disable();
		
		//CORS 사용자 설정
		//httpSecurity.cors();
		
		//csrf() Disable 이걸 하지 않으면 CUD를 할수 없다  CSRF 방지 지원 기능 비활서화
		httpSecurity.csrf().disable();
	}	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		
	}
	
	//비밀번호 암호 처리기 생성
	@Bean
	public PasswordEncoder passwordEncoder() {
		//- BCryptPasswordEncoder는 BCrypt 해싱 함수(BCrypt hashing function)를 사용해서 비밀번호를 인코딩해주는 메서드와 사용자의 의해 제출된 비밀번호와 저장소에 저장되어 있는 비밀번호의 일치 여부를 확인해주는 메서드를 제공합니다.
		return new BCryptPasswordEncoder();
	}
}
