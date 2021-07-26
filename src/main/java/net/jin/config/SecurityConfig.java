/**
 * 
 */
package net.jin.config;

import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.config.annotation.web.configuration.*;

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
		
		//httpSecurity.formLogin().disable().httpBasic().disable();
		
		//CORS 사용자 설정
		//httpSecurity.cors();
		
		//csrf() Disable 이걸 하지 않으면 CUD를 할수 없다
		httpSecurity.csrf().disable();
	}	
}
