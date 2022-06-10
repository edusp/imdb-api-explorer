package com.au.code.repository;

import com.au.code.model.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<AuthUser, Long> {
  AuthUser findByUsername(String username);
}
