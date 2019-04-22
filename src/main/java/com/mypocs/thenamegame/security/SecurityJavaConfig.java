/**
 * 
 */
package com.mypocs.thenamegame.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Raj Thuppanna
 *	Configure spring seurity 
 *	1.to use in memory auth provider / Hard coded users
 */
@Configuration
@EnableWebSecurity
public class SecurityJavaConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
	    auth.inMemoryAuthentication()
	        .withUser("user1").password(encoder().encode("user1")).roles("USER")
	        .and()
	        .withUser("user2").password(encoder().encode("user2")).roles("USER")
	        .and()
	        .withUser("user3").password(encoder().encode("user3")).roles("USER")
	        .and()
	        .withUser("user4").password(encoder().encode("user4")).roles("USER")
	        .and()
	        .withUser("user5").password(encoder().encode("user5")).roles("USER");
	}	

	
	@Override
	protected void configure(HttpSecurity http) throws Exception { 
	    http
	    .csrf().disable()
	    .exceptionHandling()
	    .authenticationEntryPoint(new RestAuthenticationEntryPoint())
	    .and()
	    .authorizeRequests()
	    .antMatchers("/v1/**").authenticated()
	    .and()
	    .formLogin()
	    .successHandler(new RESTAuthenticationSuccessHandler())
	    .and()
	    .httpBasic()
	    .and()
	    .logout();
	}

	
	@Bean
	public PasswordEncoder  encoder() {
	    return new BCryptPasswordEncoder();
	}
	
}
