package com.livrariaJava.exception;

import org.springframework.http.HttpStatus;

public class BuscaVazia extends LivroExcecao {
    public BuscaVazia() {
        super("Nenhum Livro encontrado", HttpStatus.NOT_FOUND);
    }

    public BuscaVazia(String mensagem) {
        super(mensagem, HttpStatus.NOT_FOUND);
    }

    public BuscaVazia(String mensagem, HttpStatus status) {
        super(mensagem, status);
    }
}