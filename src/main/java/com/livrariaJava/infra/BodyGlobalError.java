package com.livrariaJava.infra;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class BodyGlobalError {
    private final LocalDateTime timestamp;
    private HttpStatus status;
    private String error;
    private String message;

    public BodyGlobalError() {
        this.timestamp = LocalDateTime.now();
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public String statusName() {
        return this.status.name();
    }
}