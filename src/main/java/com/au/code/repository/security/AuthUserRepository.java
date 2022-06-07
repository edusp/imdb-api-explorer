package com.au.code.repository.security;

import com.au.code.model.AuthUser;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;


public interface AuthUserRepository extends ReactiveCrudRepository<AuthUser, Long> {
  Mono<AuthUser> findByUsername(String username);
}
