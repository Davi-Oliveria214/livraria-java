package com.livrariaJava.infra;

import com.livrariaJava.exception.BuscaVazia;
import com.livrariaJava.exception.LivroExcecao;
import org.jspecify.annotations.Nullable;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BuscaVazia.class)
    private ResponseEntity<Object> livroNaoEncontrado(BuscaVazia buscaVazia) {
        BodyGlobalError body = createBodyHandler(buscaVazia);
        return ResponseEntity.status(body.getStatus()).body(body);
    }

    @ExceptionHandler(LivroExcecao.class)
    private ResponseEntity<Object> livroExistente(LivroExcecao livroExcecao) {
        BodyGlobalError body = createBodyHandler(livroExcecao);
        return ResponseEntity.status(body.getStatus()).body(body);
    }

    @ExceptionHandler(Exception.class)
    private ResponseEntity<Object> erroGenerico(Exception exception) {
        BodyGlobalError global = new BodyGlobalError();
        global.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        global.setError(global.getStatus().name());
        global.setMessage(exception.getMessage());

        return ResponseEntity.status(global.getStatus()).body(global);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    private ResponseEntity<Object> createBodyHandler(EmptyResultDataAccessException e) {
        BodyGlobalError global = new BodyGlobalError();
        global.setStatus(HttpStatus.NOT_FOUND);
        global.setError(global.getStatus().name());
        global.setMessage(new BuscaVazia().getMessage());

        return ResponseEntity.status(global.getStatus()).body(global);
    }

    @Override
    protected @Nullable ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        BodyGlobalError global = new BodyGlobalError();
        global.setStatus((HttpStatus) status);
        global.setError(global.getStatus().name());
        global.setMessage(ex.getMessage());

        return ResponseEntity.status(global.getStatus()).body(global);
    }

    private BodyGlobalError createBodyHandler(LivroExcecao e) {
        BodyGlobalError global = new BodyGlobalError();
        global.setStatus(e.getStatus());
        global.setError(global.statusName());
        global.setMessage(e.getMessage());

        return global;
    }
}