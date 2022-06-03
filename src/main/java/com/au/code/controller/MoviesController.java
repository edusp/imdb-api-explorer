package com.au.code.controller;

import com.au.code.dto.MovieRecord;
import com.au.code.service.MoviesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
@RequestMapping("movies")
public class MoviesController {

  private final MoviesService moviesService;

  @GetMapping(path = "/top250")
  @ResponseBody
  public MovieRecord top250Movies() {

    try {
      return moviesService.getTop250Movies();
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }

  }



}
