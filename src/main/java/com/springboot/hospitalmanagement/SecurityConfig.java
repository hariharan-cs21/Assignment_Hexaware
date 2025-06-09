package com.springboot.hospitalmanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration 
public class SecurityConfig {
	@Autowired
	private JwtFilter jwtFilter;
	

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.csrf((csrf) -> csrf.disable()) 
			.authorizeHttpRequests(authorize -> authorize
					.requestMatchers("/api/doctor/register","/api/patient/register").permitAll()
					.requestMatchers("/api/appointments/create").permitAll()
					.requestMatchers("/api/user/details").authenticated()
					
					.requestMatchers("api/patient/add/all").permitAll()
					
					
					
					.requestMatchers("/api/medicalhistory/add").hasAuthority("PATIENT")
					.requestMatchers("/api/medicalhistory/{patientId}").authenticated()
					
					 .requestMatchers("/api/doctor/patients").hasAuthority("DOCTOR")
					.anyRequest().authenticated()
			)
			 .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class) 
		 .httpBasic(Customizer.withDefaults()); 
		return http.build();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {  
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	AuthenticationManager getAuthManager(AuthenticationConfiguration auth) 
			throws Exception {
		  return auth.getAuthenticationManager();
	 }
}
