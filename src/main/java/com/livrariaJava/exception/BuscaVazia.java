package com.livrariaJava.exception;

public class BuscaVazia extends RuntimeException {
    public BuscaVazia() {
        super("Nenhum Livro encontrado");
    }

    public BuscaVazia(String mensagem) {
        super(mensagem);
    }
}