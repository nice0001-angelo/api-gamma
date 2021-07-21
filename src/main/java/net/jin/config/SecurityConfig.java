/**
 * 
 */
package net.jin.config;

import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.config.annotation.web.configuration.*;

import lombok.extern.slf4j.*;

/**
 * @author njh
 *
 */
@Slf4j
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception{
		log.info("Security Configuring....");

	//폼기반 인증 기능을 사용한다 이걸해야  http://localhost:8080/login 으로 redirect 되지 않는다
	httpSecurity.formLogin();
	}	
}
