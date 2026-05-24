package com.livrariaJava.controller;

import com.livrariaJava.entity.LivroEntity;
import com.livrariaJava.excecoes.ExcecoesLivro;
import com.livrariaJava.services.LivroService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/livraria")
public class LivroController {
    private final LivroService service;

    public LivroController(LivroService livroService) {
        this.service = livroService;
    }

    @PostMapping("/")
    public void addLivro(@RequestBody LivroEntity livro) throws ExcecoesLivro {
        LivroEntity livroEntity = livro;
        this.service.criarLivro(livroEntity);
    }

    @DeleteMapping("/{id}")
    public void delLivro(@PathVariable("id") int isbn) throws ExcecoesLivro {
        this.service.delLivro(isbn);
    }

    @GetMapping("/")
    public List<LivroEntity> mostarLivros() throws ExcecoesLivro {
        return this.service.getLivros();
    }

    @GetMapping("/{titulo}")
    public List<LivroEntity> buscarTitulo(@PathVariable("titulo") String titulo) throws ExcecoesLivro {
        return this.service.buscarTitulo(titulo);
    }

    @GetMapping("/isbn/{isbn}")
    public LivroEntity buscarISBN(@PathVariable("isbn") int isbn) throws ExcecoesLivro {
        return this.service.buscarISBN(isbn);
    }

    @GetMapping("/autor/{autor}")
    public List<LivroEntity> buscarAutor(@PathVariable("autor") String autor) throws ExcecoesLivro {
        return this.service.buscarAutor(autor);
    }

    @GetMapping("/preco/{preco}")
    public List<LivroEntity> buscarPreco(@PathVariable("preco") double preco) throws ExcecoesLivro {
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