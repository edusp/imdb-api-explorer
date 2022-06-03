package com.au.code.service;

import com.au.code.dto.MovieRecord;
import com.au.code.gateway.ApiGateway;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.http.HttpResponse;

@Slf4j
@RequiredArgsConstructor
@Service
public class MoviesService {

  @Value("${imdb.api.top-250-movies-url}")
  private String TOP_250_MOVIES_URL;

  private final ApiGateway gateway;

  public MovieRecord getTop250Movies() throws IOException, InterruptedException {
    HttpResponse<String> response = gateway.sendGET(TOP_250_MOVIES_URL);
    return new ObjectMapper().readValue(response.body(), MovieRecord.class);
  }

}
