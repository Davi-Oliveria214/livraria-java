package controller;

import entity.LivroEntity;
import excecoes.ExcecoesLivro;
import services.LivroService;

import java.time.LocalDate;
import java.util.List;

public class LivroController {
    private final LivroService service;
    private Long id = 1L;

    public LivroController(LivroService livroService) {
        this.service = livroService;
    }

    public void addLivro(String titulo, String autor, double preco, int isbn, int estoque, LocalDate lancamento) throws ExcecoesLivro {
        LivroEntity livroEntity = new LivroEntity(id++, titulo, autor, preco, isbn, estoque, lancamento);
        this.service.criarLivro(livroEntity);
    }

    public void delLivro(int isbn) throws ExcecoesLivro {
        this.service.delLivro(isbn);
    }

    public List<LivroEntity> mostarLivros() throws ExcecoesLivro {
        return this.service.getLivros();
    }

    public List<LivroEntity> buscarTitulo(String titulo) throws ExcecoesLivro {
        return this.service.buscarTitulo(titulo);
    }

    public LivroEntity buscarISBN(int isbn) throws ExcecoesLivro {
        return this.service.buscarISBN(isbn);
    }

    public List<LivroEntity> buscarAutor(String autor) throws ExcecoesLivro {
        return this.service.buscarAutor(autor);
    }

    public List<LivroEntity> buscarPreco(double preco) throws ExcecoesLivro {
        return this.service.buscarPreco(preco);
    }

    public void altTitulo(int isbn, String novoTitulo) throws ExcecoesLivro {
        this.service.altTitulo(isbn, novoTitulo);
    }

    public void altAutor(int isbn, String novoAutor) throws ExcecoesLivro {
        this.service.altAutor(isbn, novoAutor);
    }

    public void altISBN(int isbn, int novaISBN) throws ExcecoesLivro {
        this.service.altISBN(isbn, novaISBN);
    }

    public void altPreco(int isbn, double preco) {
        this.service.altPreco(isbn, preco);
    }

    public void altEstoque(int isbn, int estoque) {
        this.service.altEstoque(isbn, estoque);
    }

    public void verificar() throws ExcecoesLivro {
        this.service.verificar();
    }
}