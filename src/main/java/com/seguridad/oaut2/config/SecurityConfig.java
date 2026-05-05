package com.seguridad.oaut2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests(auth -> auth
				.requestMatchers("/public/**", "/", "/error", "/login").permitAll()
				.requestMatchers("/admin/**").hasRole("ADMIN")
				.anyRequest().authenticated()
			)
			.oauth2Login(oauth2 -> oauth2
				.loginPage("/login")
				.defaultSuccessUrl("/dashboard", true)
				.failureUrl("/login?error=true")
			)
			.logout(logout -> logout
				.logoutSuccessUrl("/")
				.invalidateHttpSession(true)
				.clearAuthentication(true)
			);

		return http.build();
	}
}
