package com.demo.configuration.exceptionHandler;

import com.demo.common.FunctionCommonUtils;
import com.demo.configuration.exceptionHandler.exception.ErrorResponse;
import com.demo.configuration.exceptionHandler.exception.NoRollbackException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @Autowired
    private FunctionCommonUtils functionCommonUtils;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> mainExceptionHandler(Exception e, HandlerMethod handlerMethod, HttpServletRequest request) {
        logger.info("mainExceptionHandler\n", e);
        ErrorResponse response = ErrorResponse.builder()
                .timestamp(functionCommonUtils.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"))
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(e.getMessage())
                .path(request.getRequestURI())
                .build();
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);
    }

    @ExceptionHandler(NoRollbackException.class)
    public ResponseEntity<ErrorResponse> noRollbackExceptionHandler(NoRollbackException e, HandlerMethod handlerMethod, HttpServletRequest request) {
        logger.info("noRollbackExceptionHandler\n", e);
        ErrorResponse response = ErrorResponse.builder()
                .timestamp(functionCommonUtils.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"))
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(e.getMessage())
                .path(request.getRequestURI())
                .build();
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);
    }

}
