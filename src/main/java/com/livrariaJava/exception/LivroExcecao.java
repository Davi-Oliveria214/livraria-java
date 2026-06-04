package com.livrariaJava.exception;

public class LivroExcecao extends RuntimeException {
    public LivroExcecao() {
        super("Esse livro já está cadastrado");
    }

    public LivroExcecao(String message) {
        super(message);
    }
}
