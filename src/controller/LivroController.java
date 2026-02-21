package controller;

import entity.LivroEntity;
import excecoes.ExcecoesLivro;
import services.LivroService;

import java.time.LocalDate;

public class LivroController {
    private LivroService service;
    private Long id = 1L;

    public LivroController(LivroService livroService) {
        service = livroService;
    }

    public void addLivro(String titulo, String autor, double preco, int isbn, int estoque, LocalDate lancamento) {
        try {
            LivroEntity livroEntity = new LivroEntity(id++, titulo, autor, preco, isbn, estoque, lancamento);
            service.criarLivro(livroEntity);

            System.out.println("Sucesso: Livro cadastrado!!!");
        } catch (ExcecoesLivro e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}