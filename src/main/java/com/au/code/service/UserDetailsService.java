package com.au.code.service;

import com.au.code.dto.UserRecord;
import com.au.code.model.AuthUser;
import com.au.code.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
@Slf4j
@RequiredArgsConstructor
@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
  private final UserRepository repository;

  @Override
  public AuthUser loadUserByUsername(String username) throws UsernameNotFoundException {
    AuthUser byUsername = repository.findByUsername(username);
    log.info("Authenticating user::{}", byUsername.getUsername());
    return byUsername;
  }

  public UserRecord save(UserRecord user) {
    AuthUser persistedUser = repository.save(AuthUser.builder()
            .username(user.username())
            .password(user.password())
            .active(user.active())
            .roles(user.roles())
            .build()
    );
    return persistedUser.mapToRecord();
  }
}
