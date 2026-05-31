package com.livrariaJava.controller;

import com.livrariaJava.entity.Livro;
import com.livrariaJava.excecoes.BuscaVazia;
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
    public ResponseEntity<?> addLivro(@RequestBody Livro livro) {
        try {
            Livro l = this.service.criarLivro(livro);
            return ResponseEntity.status(201).body(l);
        } catch (ExcecoesLivro el) {
            return ResponseEntity.status(409).body(el.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public String delLivro(@PathVariable int id) {
        try {
            this.service.delLivro(id);
            return "Livro deletado com sucesso";
        } catch (ExcecoesLivro el) {
            return el.getMessage();
        }
    }

    @GetMapping("/")
    public List<Livro> mostarLivros() {
        return this.service.getLivros();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarId(@PathVariable int id) {
        try {
            Livro l = this.service.buscarId(id);
            return ResponseEntity.status(200).body(l);
        } catch (BuscaVazia el) {
            return ResponseEntity.status(404).body(el.getMessage());
        }
    }

    @GetMapping("/buscar/titulo")
    public ResponseEntity<?> buscarTitulo(@RequestParam("titulo") String titulo) {
        try {
            List<Livro> l = this.service.buscarTitulo(titulo);
            return ResponseEntity.status(200).body(l);
        } catch (BuscaVazia el) {
            return ResponseEntity.status(404).body(el.getMessage());
        }
    }

    @GetMapping("/buscar/isbn")
    public ResponseEntity<?> buscarISBN(@RequestParam("isbn") int isbn) {
        try {
            List<Livro> l = this.service.buscarISBN(isbn);
            return ResponseEntity.status(200).body(l);
        } catch (BuscaVazia el) {
            return ResponseEntity.status(404).body(el.getMessage());
        }
    }

    @GetMapping("/buscar/autor")
    public ResponseEntity<?> buscarAutor(@PathVariable String autor) {
        try {
            List<Livro> l = this.service.buscarAutor(autor);
            return ResponseEntity.status(200).body(l);
        } catch (BuscaVazia el) {
            return ResponseEntity.status(404).body(el.getMessage());
        }
    }

    @GetMapping("/preco/param")
    public List<Livro> buscarPreco(@PathVariable double preco) {
        return this.service.buscarPreco(preco);
    }

    public void altTitulo(int id, String novoTitulo) {
        this.service.altTitulo(id, novoTitulo);
    }

    public void altAutor(int id, String novoAutor) {
        this.service.altAutor(id, novoAutor);
    }

    public void altISBN(int id, int novaISBN) {
        this.service.altISBN(id, novaISBN);
    }

    public void altPreco(int id, double preco) {
        this.service.altPreco(id, preco);
    }

    public void altEstoque(int id, int estoque) {
        this.service.altEstoque(id, estoque);
    }

    public void verificar() throws ExcecoesLivro {
        this.service.verificar();
    }
}