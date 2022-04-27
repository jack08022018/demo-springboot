package com.demo.configuration.exception;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class ErrorResponse implements Serializable {
    private String timestamp;
    private Integer status;
    private String message;
    private String path;

    private ErrorResponse(ErrorResponseBuilder builder) {
        this.timestamp = Timestamp.valueOf(LocalDateTime.now()).toString();
        this.status = builder.status;
        this.message = builder.message;
        this.path = builder.path;
    }

    public static ErrorResponseBuilder builder() {
        return new ErrorResponseBuilder();
    }

    public static class ErrorResponseBuilder {
        private Integer status;
        private String message;
        private String path;

        public ErrorResponseBuilder setStatus(Integer status) {
            this.status = status;
            return this;
        }

        public ErrorResponseBuilder setMessage(String message) {
            this.message = message;
            return this;
        }

        public ErrorResponseBuilder setPath(String path) {
            this.path = path;
            return this;
        }

        public ErrorResponse build() {
            return new ErrorResponse(this);
        }
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
