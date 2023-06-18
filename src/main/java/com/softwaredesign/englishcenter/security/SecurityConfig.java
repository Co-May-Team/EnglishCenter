package com.softwaredesign.englishcenter.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(requests -> {
			try {
				requests.requestMatchers(
						HttpMethod.GET, "/static/**", 
						"/templates/**", 
						"/resources/**", 
						"/css/**",
						"/js/**", 
						"/assets/**").permitAll()
						.anyRequest().authenticated()
						.and().formLogin()
						.loginPage("/login").permitAll().loginProcessingUrl("/j_spring_security_check")
						.successHandler((request, response, authentication) -> {
							// Custom logic for redirection
							response.sendRedirect("/");
						}).failureUrl("/login?success=false")
						.and().logout().permitAll().and().httpBasic().disable();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).httpBasic().disable()
		.csrf().disable(); // Disable CSRF protection

		return http.build();
	}

	@Bean
	public InMemoryUserDetailsManager userDetailsService() {
		UserDetails user = User.withUsername("admin").password(passwordEncoder().encode("admin")).roles("ADMIN")
				.build();
		return new InMemoryUserDetailsManager(user);
	}
}
