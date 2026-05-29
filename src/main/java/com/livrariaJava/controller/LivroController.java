package com.livrariaJava.controller;

import com.livrariaJava.entity.Livro;
import com.livrariaJava.excecoes.ExcecoesLivro;
import com.livrariaJava.services.LivroServiceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livraria")
public class LivroController {
    private final LivroServiceService service;

    public LivroController(LivroServiceService livroService) {
        this.service = livroService;
    }

    @PostMapping("/")
    public ResponseEntity<Livro> addLivro(@RequestBody Livro livro) {
        Livro l = this.service.criarLivro(livro);
        return ResponseEntity.status(201).body(l);
    }

    @DeleteMapping("/{id}")
    public void delLivro(@PathVariable int id) {
        this.service.delLivro(id);
    }

    @GetMapping("/")
    public List<Livro> mostarLivros() {
        return this.service.getLivros();
    }

    @GetMapping("/{id}")
    public Livro buscarId(@PathVariable int id) throws ExcecoesLivro {
        return this.service.buscarId(id).orElseThrow(() -> new ExcecoesLivro("Nenhum livro encontrado"));
    }

    @GetMapping("/titulo/{titulo}")
    public List<Livro> buscarTitulo(@PathVariable String titulo) throws ExcecoesLivro {
        return this.service.buscarTitulo(titulo);
    }

    @GetMapping("/isbn/{isbn}")
    public List<Livro> buscarISBN(@PathVariable int isbn) throws ExcecoesLivro {
        return this.service.buscarISBN(isbn);
    }

    @GetMapping("/autor/{autor}")
    public List<Livro> buscarAutor(@PathVariable String autor) throws ExcecoesLivro {
        return this.service.buscarAutor(autor);
    }

    @GetMapping("/preco/{preco}")
    public List<Livro> buscarPreco(@PathVariable double preco) throws ExcecoesLivro {
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