package com.demo.configuration;

import com.demo.configuration.exception.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> mainExceptionHandler(Exception e, HandlerMethod handlerMethod, HttpServletRequest request) {
        e.printStackTrace();
        ErrorResponse response = ErrorResponse.builder()
                .setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .setMessage(e.getMessage())
                .setPath(request.getRequestURI())
                .build();
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);
    }
}
