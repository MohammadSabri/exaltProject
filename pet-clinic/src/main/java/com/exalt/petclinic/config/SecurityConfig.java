package com.exalt.petclinic.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import com.exalt.petclinic.security.UserDetailsServiceImpl;
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter  {
	@Bean
	public UserDetailsService userDetailsService() {
		return new UserDetailsServiceImpl();
	}

//	@Bean
//	public BCryptPasswordEncoder passwordEncoder() {
//		
//		return new BCryptPasswordEncoder();
//	}
	@Bean
	public PasswordEncoder encodePassword() {
		return NoOpPasswordEncoder.getInstance();
	}

//	@Bean
//	public DaoAuthenticationProvider authenticationProvider() {
//		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//		authProvider.setUserDetailsService(userDetailsService());
//		authProvider.setPasswordEncoder(encodePassword());
//
//		return authProvider;
//	}
//	@Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(authenticationProvider());
//    }
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.inMemoryAuthentication().withUser("my-trusted-client").password("secret").roles("Admin");
	}
//	 @Bean
//	    public UserDetailsService userDetailsService() {
//
//	        User.UserBuilder users = User.withDefaultPasswordEncoder();
//	        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//	        manager.createUser(users.username("my-trusted-client").password("secret").roles("Admin").build());
//	        
//	        return manager;
//
//	    }

//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.authenticationProvider(authenticationProvider());
//	}
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//
//		http.httpBasic().and().authorizeRequests().antMatchers("/api/v1/admin/**", "/api/v1/admins/**").hasRole("Admin")
//				.antMatchers("/api/v1/clients/**", "/api/v1/client/**").hasAnyRole("Admin", "Worker").antMatchers("/**")
//				.permitAll().and().formLogin().and().logout().permitAll();
//	}
	@Bean
	@Override
	 public AuthenticationManager authenticationManagerBean() throws Exception {
	      return super.authenticationManagerBean();
	}  
}
