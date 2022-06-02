package com.hb.blogwebapp.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception { // Controle d'accès (autorisation)

		http.authorizeHttpRequests().antMatchers("/**").permitAll()
				.antMatchers("/post/map").hasRole("ADMIN") // ROLE_ADMIN
				.anyRequest().authenticated().and().formLogin();

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception { // Authentification
//		auth.inMemoryAuthentication()
//			.withUser("admin")
//			.password(passwordEncoder().encode("admin"))
//			.roles("admin") // ROLE_ADMIN
//			.and()
//			.withUser("user")
//			.password(passwordEncoder().encode("user"))
//			.roles("user"); // ROLE_USER
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

}
