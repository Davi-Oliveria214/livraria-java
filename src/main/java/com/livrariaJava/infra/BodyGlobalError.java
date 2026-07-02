package com.livrariaJava.infra;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import java.time.LocalDateTime;

public class BodyGlobalError {
    private final LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;

    public BodyGlobalError() {
        this.timestamp = LocalDateTime.now();
    }

    public void setStatusCode(HttpStatusCode status) {
        this.status = status.value();
    }

    public void setError(int error) {
        this.error = HttpStatus.valueOf(error).name();
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public int getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }
}