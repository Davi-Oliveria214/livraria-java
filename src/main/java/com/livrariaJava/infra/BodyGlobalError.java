package com.livrariaJava.infra;

import org.springframework.http.HttpStatusCode;

import java.time.LocalDateTime;

public class BodyGlobalError {
    private final LocalDateTime timestamp;
    private HttpStatusCode status;
    private String error;
    private String message;

    public BodyGlobalError() {
        this.timestamp = LocalDateTime.now();
    }

    public void setStatusCode(HttpStatusCode status) {
        this.status = status;
    }

    public void setError(HttpStatusCode error) {
        this.error = error.getClass().getName();
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public HttpStatusCode getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }
}