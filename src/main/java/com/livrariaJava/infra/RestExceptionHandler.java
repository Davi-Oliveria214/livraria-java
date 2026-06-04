package com.livrariaJava.infra;

import com.livrariaJava.exception.BuscaVazia;
import com.livrariaJava.exception.LivroExcecao;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BuscaVazia.class)
    private ResponseEntity<Object> livroNaoEncontrado(BuscaVazia buscaVazia) {
        MessageErrorGlobal global = new MessageErrorGlobal();
        global.mensagem("timestamp", LocalDateTime.now());
        global.mensagem("status", HttpStatus.NOT_FOUND);
        global.mensagem("error", "Recurso não encontrado");
        global.mensagem("message", buscaVazia.getMessage());

        return ResponseEntity.status(404).body(global.getBody());
    }

    @ExceptionHandler(LivroExcecao.class)
    private ResponseEntity<Object> livroExistente(LivroExcecao livroExcecao) {
        MessageErrorGlobal global = new MessageErrorGlobal();
        global.mensagem("timestamp", LocalDateTime.now());
        global.mensagem("status", HttpStatus.CONFLICT);
        global.mensagem("error", "Recurso existente");
        global.mensagem("message", livroExcecao.getMessage());

        return ResponseEntity.status(409).body(global.getBody());
    }

    @ExceptionHandler(Exception.class)
    private ResponseEntity<Object> erroGenerico(Exception exception) {
        MessageErrorGlobal global = new MessageErrorGlobal();
        global.mensagem("timestamp", LocalDateTime.now());
        global.mensagem("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        global.mensagem("error", "Erro interno no Servidor");
        global.mensagem("message", exception.getMessage());

        return ResponseEntity.status(500).body(global.getBody());
    }
}