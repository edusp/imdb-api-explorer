package com.au.code.service.security;

import com.au.code.repository.security.AuthUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class AuthUserDetailsService implements ReactiveUserDetailsService {

  private final AuthUserRepository repository;

  @Override
  public Mono<UserDetails> findByUsername(String username) {
    return repository.findByUsername(username)
            .switchIfEmpty(
                    Mono.error(
                            () -> new UsernameNotFoundException("No user found for the given username: "+ username))
            )
            .cast(UserDetails.class);
  }

}
