package com.jwtpractice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.jwtpractice.security.JwtAnthenticatinFilter;
import com.jwtpractice.security.JwtaunticationEntryPoint;

@Configuration
public class SecurityConfig {

	@Autowired
	private JwtaunticationEntryPoint point;
	
	@Autowired
	private JwtAnthenticatinFilter filter;
	
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
	{
		//congiguratation
		http.csrf(csrf->csrf.disable())
		.cors(cors->cors.disable())
		.authorizeHttpRequests(auth->auth.requestMatchers("/home/**").authenticated().requestMatchers("/auth/login")
				.permitAll().anyRequest().authenticated()).exceptionHandling(ex->ex.authenticationEntryPoint(point))
		.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		
		http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}
}
