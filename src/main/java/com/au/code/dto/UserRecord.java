package com.au.code.dto;

public record UserRecord( String username, String password, String roles, boolean active) {

  public UserRecord(String username, String roles, boolean active) {
    this(username, null, roles, active);
  }
}
