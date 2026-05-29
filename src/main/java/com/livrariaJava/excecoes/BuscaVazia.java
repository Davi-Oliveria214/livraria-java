package com.livrariaJava.excecoes;

public class BuscaVazia extends ExcecoesLivro {
    public BuscaVazia() {
        super("Nenhum Livro encontrado");
    }

    public BuscaVazia(String mensagem) {
        super(mensagem);
    }
}