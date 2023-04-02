package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurity  {
	
	 @Bean
	    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

	    	http
		    .cors().and()
		    .csrf().disable()
		    .authorizeRequests()
		    .requestMatchers(HttpMethod.POST, "/users")
		    .permitAll() 
			.anyRequest().authenticated();
	    	
	        return http.build();
	    }
}
