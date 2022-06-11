package com.au.code.exception.custom;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class CustomAccessDeniedExceptionHandler implements AccessDeniedHandler {

  @Override
  public void handle(HttpServletRequest request,
                     HttpServletResponse response,
                     AccessDeniedException accessDeniedException) throws IOException {
    Authentication auth
            = SecurityContextHolder.getContext().getAuthentication();
    if (auth != null) {
      log.warn("User: " + auth.getName()
              + " attempted to access the protected URL: "
              + request.getRequestURI());

      response.setStatus(HttpStatus.FORBIDDEN.value());
      response.getWriter().write("No enough privileges to access this resource. User must be an Admin");
      response.getWriter().flush();
    }
  }

}
