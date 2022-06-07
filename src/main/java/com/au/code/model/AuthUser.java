package com.au.code.model;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Id;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
@Setter
@Table("auth_user")
public class AuthUser implements UserDetails {

  @Id
  private Long id;
  private String username;
  private String password;
  private String roles;
  private boolean active;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return Arrays.stream(roles.split(","))
            .map(SimpleGrantedAuthority::new)
            .collect(Collectors.toList());
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return this.isActive();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof AuthUser authUser)) return false;
    return getId().equals(authUser.getId()) && getUsername().equals(authUser.getUsername());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getUsername());
  }
}
