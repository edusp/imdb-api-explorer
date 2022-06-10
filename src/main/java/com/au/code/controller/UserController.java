package com.au.code.controller;

import com.au.code.dto.UserRecord;
import com.au.code.service.UserDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("users")
public class UserController {

  private final UserDetailsService service;

  @PostMapping
  @ResponseBody
  public UserRecord save(@RequestBody UserRecord user) {
    return service.save(user);
  }



}
