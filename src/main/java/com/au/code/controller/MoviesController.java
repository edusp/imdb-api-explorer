package com.au.code.controller;

import com.au.code.dto.MovieRecord;
import com.au.code.metrics.CounterMetrics;
import com.au.code.service.MoviesService;
import io.prometheus.client.Summary;
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
  private final CounterMetrics counterMetrics;

  @GetMapping(path = "/top250")
  @ResponseBody
  public MovieRecord top250Movies() {
    counterMetrics.increment();

    Summary.Timer timer = CounterMetrics.requestLatency.startTimer();

    try {
      return moviesService.getTop250Movies();
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    } finally {
      timer.observeDuration();
    }

  }



}
