package com.demo.configuration.exceptionHandler.exception;

import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse implements Serializable {
    private String timestamp;
    private Integer status;
    private String message;
    private String path;
}
