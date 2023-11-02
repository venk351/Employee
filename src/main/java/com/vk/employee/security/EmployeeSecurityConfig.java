package com.vk.employee.security;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class EmployeeSecurityConfig {

	// Adding roles using spring security and using jdbc connectivity with default
	// table
	/*
	 * @Bean public UserDetailsManager userDetailsManager(DataSource datasource) {
	 * 
	 * return new JdbcUserDetailsManager(datasource); }
	 */

	// Adding roles using spring security and using jdbc connectivity with custom
	// table
	@Bean
	public UserDetailsManager userDetailsManager(DataSource datasource) {

		JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(datasource);
		jdbcUserDetailsManager
				.setAuthoritiesByUsernameQuery("select user_id, pword, enabled from members where user_id=?");
		jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("select user_id, role from roles where user_id=?");

		return jdbcUserDetailsManager;
	}

	/*
	 * Adding roles for the users using InMemory
	 * 
	 * @Bean public InMemoryUserDetailsManager userDetailsmanager() {
	 * 
	 * UserDetails dhinesh = User.builder() .username("dhinesh")
	 * .password("{noop}test123") .roles("Employee") .build(); UserDetails vicky =
	 * User.builder() .username("vicky") .password("{noop}test123")
	 * .roles("Employee","Manager") .build(); UserDetails venky = User.builder()
	 * .username("venky") .password("{noop}test123")
	 * .roles("Employee","Manager","Admin") .build();
	 * 
	 * return new InMemoryUserDetailsManager(dhinesh,vicky,venky);
	 * 
	 * }
	 */

	@Bean
	public SecurityFilterChain filerChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(configurer -> configurer.requestMatchers(HttpMethod.GET, "/api/employees")
				.hasRole("EMPLOYEE").requestMatchers(HttpMethod.GET, "/api/employees/**").hasRole("EMPLOYEE")
				.requestMatchers(HttpMethod.POST, "/api/employees").hasRole("MANAGER")
				.requestMatchers(HttpMethod.PUT, "/api/employees/**").hasRole("MANAGER")
				.requestMatchers(HttpMethod.DELETE, "/api/employees/**").hasRole("ADMIN")
				.requestMatchers(HttpMethod.GET, "/api/getdate").hasRole("EMPLOYEE")
				.requestMatchers(HttpMethod.GET, "/css/**").hasRole("EMPLOYEE")
				.requestMatchers(HttpMethod.GET, "/leaders/**").hasRole("MANAGER")
				.requestMatchers(HttpMethod.GET, "/systems/**").hasRole("ADMIN").anyRequest().authenticated())
				.exceptionHandling(configurer -> configurer.accessDeniedPage("/accessDenied"))
				.formLogin(form -> form.loginPage("/showLogin").loginProcessingUrl("/authenticateTheUser").permitAll())
				.logout(logout -> logout.permitAll());

		/*
		 * http.authorizeHttpRequests(configurer ->
		 * configurer.requestMatchers(HttpMethod.GET,"/api/employees").hasRole(
		 * "EMPLOYEE")
		 * .requestMatchers(HttpMethod.GET,"/api/employees/**").hasRole("EMPLOYEE")
		 * .requestMatchers(HttpMethod.POST,"/api/employees").hasRole("MANAGER")
		 * .requestMatchers(HttpMethod.PUT,"/api/employees/**").hasRole("MANAGER")
		 * .requestMatchers(HttpMethod.DELETE,"/api/employees/**").hasRole("ADMIN")
		 * .requestMatchers(HttpMethod.GET,"/api/getdate").hasRole("EMPLOYEE")
		 * .requestMatchers(HttpMethod.GET,"/css/**").hasRole("EMPLOYEE") );
		 * 
		 * http.httpBasic(Customizer.withDefaults()); http.csrf(csrf->csrf.disable());
		 */

		return http.build();
	}

}
