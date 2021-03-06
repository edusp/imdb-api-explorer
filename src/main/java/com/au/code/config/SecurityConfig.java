package com.au.code.config;

import com.au.code.exception.custom.CustomAccessDeniedExceptionHandler;
import com.au.code.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private static final String ADMIN = "ADMIN";
  private final UserService authService;

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(authService);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable();
    http.authorizeHttpRequests()
            .antMatchers("/actuator/**", "/users/**").hasRole(ADMIN)
            .antMatchers(HttpMethod.POST).hasRole(ADMIN)
            .antMatchers(HttpMethod.PUT).hasRole(ADMIN)

            .antMatchers("/movies/**").permitAll()

            .anyRequest().authenticated()
            .and()
            .httpBasic()

            .and()
            .exceptionHandling().accessDeniedHandler(accessDeniedHandler());
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }


  @Bean
  public AccessDeniedHandler accessDeniedHandler(){
    return new CustomAccessDeniedExceptionHandler();
  }

}
