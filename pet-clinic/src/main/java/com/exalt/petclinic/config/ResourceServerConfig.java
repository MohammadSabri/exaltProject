package com.exalt.petclinic.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * The @EnableResourceServer annotation adds a filter of type
 * OAuth2AuthenticationProcessingFilter automatically to the Spring Security
 * filter chain.
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/", "/api/v1/pets/**", "/register", "/login").permitAll()
                .antMatchers("/private/**", "/api/v1/admins/**").hasRole("Admin").and().formLogin().successForwardUrl("https://oauth.pstmn.io/v1/callback")
                .permitAll();

       //  .successForwardUrl("/oauth/token")
    }

}