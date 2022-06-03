package com.au.code.gateway;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class ApiGateway {

  public HttpResponse<String> sendGET(String uri) throws IOException, InterruptedException {
    URI requestUrl = URI.create(uri);
    HttpRequest request = HttpRequest.newBuilder()
            .GET()
            .uri(requestUrl)
            .build();

    return HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
  }



}
