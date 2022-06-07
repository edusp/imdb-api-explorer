package com.au.code.config;

import com.au.code.service.security.AuthUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
@Configuration
public class SecurityConfig {


  @Bean
  public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
    return http
            .csrf().disable()
            .authorizeExchange()
              .pathMatchers(HttpMethod.POST).hasRole("ADMIN")
              .pathMatchers(HttpMethod.PUT).hasRole("ADMIN")
              .pathMatchers(HttpMethod.GET, "/actuator/**").hasRole("ADMIN")
            .pathMatchers("/movies/**").permitAll()
            .anyExchange().authenticated()
            .and()
              .httpBasic()
            .and()
              .build();
  }

  @Bean
  public ReactiveAuthenticationManager authenticationManager(AuthUserDetailsService userDetailsService) {
    return new UserDetailsRepositoryReactiveAuthenticationManager(userDetailsService);
  }

}
