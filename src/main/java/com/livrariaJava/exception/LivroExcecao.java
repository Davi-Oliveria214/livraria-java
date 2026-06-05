package com.livrariaJava.exception;

import org.springframework.http.HttpStatus;

public class LivroExcecao extends RuntimeException {
    private HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

    public LivroExcecao(String message) {
        super(message);
    }

    public LivroExcecao(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
