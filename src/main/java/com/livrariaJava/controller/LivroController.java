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
    public ResponseEntity<?> delLivro(@PathVariable int id) {
        try {
            return ResponseEntity.status(200).body(this.service.delLivro(id));
        } catch (ExcecoesLivro el) {
            return ResponseEntity.status(400).body(el.getMessage());
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> mostarLivros() {
        try {
            List<Livro> l = this.service.getLivros();
            return ResponseEntity.status(200).body(l);
        } catch (ExcecoesLivro el) {
            return ResponseEntity.status(404).body(el.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarId(@PathVariable int id) {
        try {
            Livro l = this.service.buscarId(id);
            return ResponseEntity.status(200).body(l);
        } catch (ExcecoesLivro el) {
            return ResponseEntity.status(404).body(el.getMessage());
        }
    }

    @GetMapping("/buscar/titulo")
    public ResponseEntity<?> buscarTitulo(@RequestParam("titulo") String titulo) {
        try {
            List<Livro> l = this.service.buscarTitulo(titulo);
            return ResponseEntity.status(200).body(l);
        } catch (ExcecoesLivro el) {
            return ResponseEntity.status(404).body(el.getMessage());
        }
    }

    @GetMapping("/buscar/isbn")
    public ResponseEntity<?> buscarISBN(@RequestParam("isbn") int isbn) {
        try {
            List<Livro> l = this.service.buscarISBN(isbn);
            return ResponseEntity.status(200).body(l);
        } catch (ExcecoesLivro el) {
            return ResponseEntity.status(404).body(el.getMessage());
        }
    }

    @GetMapping("/buscar/autor")
    public ResponseEntity<?> buscarAutor(@RequestParam("autor") String autor) {
        try {
            List<Livro> l = this.service.buscarAutor(autor);
            return ResponseEntity.status(200).body(l);
        } catch (ExcecoesLivro el) {
            return ResponseEntity.status(404).body(el.getMessage());
        }
    }

    @GetMapping("/buscar/preco")
    public ResponseEntity<?> buscarPreco(@RequestParam("preco") double preco) {
        try {
            List<Livro> l = this.service.buscarPreco(preco);
            return ResponseEntity.status(200).body(l);
        } catch (ExcecoesLivro el) {
            return ResponseEntity.status(404).body(el.getMessage());
        }
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