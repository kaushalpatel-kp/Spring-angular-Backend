package com.Spring.config;



import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class SecurityConfig {

	  @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
	  @Bean
	    public CorsConfigurationSource corsConfigurationSource() {
	        CorsConfiguration configuration = new CorsConfiguration();
	        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200")); // Angular frontend URL
	        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS")); // Allow methods
	        configuration.setAllowedHeaders(Arrays.asList("Content-Type", "Authorization")); // Allow headers
	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	        source.registerCorsConfiguration("/**", configuration); // Allow CORS for all paths
	        return source;
	    }

	  @Bean
	      public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		  http.csrf(csrf -> csrf.disable())
	        .authorizeHttpRequests(auth -> auth
	            .requestMatchers("/api/v1/register").permitAll()  // Permit access to register
	            .requestMatchers("/api/v1/employees/**").permitAll()  // Permit all employee-related requests
	            .anyRequest().authenticated())  // Secure other routes
	        .cors(); // Enable CORS
	    return http.build();	
	    }
	  
	
}
