package com.au.code.exception.custom;

import com.au.code.exception.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.postgresql.util.PSQLException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpServerErrorException;

import java.sql.SQLException;
import java.util.Date;

@Slf4j
@ControllerAdvice
public class CustomExceptionHandler {

  @ExceptionHandler(value = {UsernameNotFoundException.class})
  public ResponseEntity<ErrorResponse> userNotFoundExceptionHandler(Exception e) {
    return buildResponse(e, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(value = {HttpServerErrorException.InternalServerError.class})
  public ResponseEntity<ErrorResponse> internalServerErrorExceptionHandler(Exception e) {
    return buildResponse(e, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(value = {HttpRequestMethodNotSupportedException.class})
  public ResponseEntity<ErrorResponse> methodNotSupportedExceptionHandler(Exception e) {
    return buildResponse(e, HttpStatus.METHOD_NOT_ALLOWED);
  }

  @ExceptionHandler(value = {PSQLException.class})
  public ResponseEntity<ErrorResponse> PSQLExceptionHandler(SQLException e) {
    return buildResponse(e, HttpStatus.BAD_REQUEST);
  }


  private ResponseEntity<ErrorResponse> buildResponse(Exception e, HttpStatus status) {
    return new ResponseEntity<>(
            ErrorResponse.builder()
                    .code(status.value())
                    .timestamp(new Date())
                    .status(status)
                    .message(e.getMessage())
                    .build(),
            status);
  }
}
