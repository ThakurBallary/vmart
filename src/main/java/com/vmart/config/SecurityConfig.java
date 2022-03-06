package com.vmart.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private static final String ADMIN = "ADMIN";
	private static final String USER = "USER";

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		String[] adminPaths = new String[] {
			"/add-vegetable",
			"/update-vegetable",
			"/delete-vegetable"
		};
		String[] userPaths = new String[] {
			"/add-to-cart",
			"/get-cart-items",
			"/update-cart-item",
			"/remove-from-cart",
			"/empty-cart",
			"/create-order",
			"/get-orders",
			"/get-order-items"
		};
		http.authorizeRequests()
		.antMatchers("/get-vegetable").hasAnyRole(ADMIN, USER)
		.antMatchers(adminPaths).hasRole(ADMIN)
		.antMatchers(userPaths).hasRole(USER)
		.and().formLogin();
	}

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

}
