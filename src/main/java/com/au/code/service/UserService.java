package com.au.code.service;

import com.au.code.dto.UserRecord;
import com.au.code.model.AuthUser;
import com.au.code.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {
  private final UserRepository repository;

  @Override
  public AuthUser loadUserByUsername(String username) throws UsernameNotFoundException {
    AuthUser byUsername = repository.findByUsername(username);
    log.info("Authenticating user::{}", byUsername.getUsername());
    return byUsername;
  }

  @Transactional
  public UserRecord save(UserRecord user) {
    AuthUser persistedUser = repository.save(AuthUser.builder()
            .username(user.username())
            .password(encodePassword(user.password()))
            .active(user.active())
            .roles(user.roles())
            .build()
    );
    return persistedUser.mapToRecord();
  }

  private String encodePassword(String pass) {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(pass);
  }
}
