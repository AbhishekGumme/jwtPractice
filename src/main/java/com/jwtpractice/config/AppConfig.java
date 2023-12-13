package com.jwtpractice.config;

import java.security.Principal;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.bind.annotation.GetMapping;

@Configuration
public class AppConfig {

	@Bean
	public UserDetailsService userDetailsService()
	{
		UserDetails user=User.builder().username("abcd").password(passwordEncoder().encode("abcd")).roles("ADMIN").build();
		UserDetails user1=User.builder().username("abc").password(passwordEncoder().encode("abc")).roles("ADMIN").build();
		
		return new InMemoryUserDetailsManager(user,user1);
		
	}
	
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	
	@GetMapping("/current-user")
	public String getLoggedInUser(Principal principal)
	{
		return principal.getName();
	}
}
