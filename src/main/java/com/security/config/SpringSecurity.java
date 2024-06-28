package com.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration		//this annotation this calls provide configuration of application 
@EnableWebSecurity  //to enable web security features in the application 
public class SpringSecurity{
	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
		
		http.authorizeHttpRequests((request)->request.anyRequest().authenticated());  
		//any request will be authenticated 
		http.sessionManagement(session
		->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		
//		http.formLogin(); 
		http.httpBasic();   // basic authentication with default settings 
		return http.build();
		
	}


	@Bean
	public UserDetailsService userDetailsService(){

		return new InMemoryUserDetailsManager(user1,admin);
	}


}
