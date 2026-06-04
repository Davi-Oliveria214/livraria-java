package com.livrariaJava.controller;

import com.livrariaJava.entity.Livro;
import com.livrariaJava.services.LivroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;

@RestController
@RequestMapping("/livraria")
public class LivroController {
    private final LivroService service;

    public LivroController(LivroService livroService) {
        this.service = livroService;
    }

    @PostMapping("/")
    public ResponseEntity<?> addLivro(@RequestBody Livro livro) {
        return ResponseEntity.status(201).body(this.service.criarLivro(livro));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delLivro(@PathVariable Long id) {
        return ResponseEntity.status(200).body(this.service.delLivro(id));
    }

    @GetMapping("")
    public ResponseEntity<?> mostarLivros() {
        return ResponseEntity.ok(this.service.getLivros());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarId(@PathVariable Long id) {
        return ResponseEntity.status(200).body(this.service.buscarId(id));
    }

    @GetMapping("/historico")
    public ResponseEntity<?> historico() {
        return ResponseEntity.status(200).body(this.service.historico());
    }

    @GetMapping("/titulo")
    public ResponseEntity<?> buscarTitulo(@RequestParam("titulo") String titulo) {
        return ResponseEntity.status(200).body(this.service.buscarTitulo(titulo));
    }

    @GetMapping("/isbn")
    public ResponseEntity<?> buscarISBN(@RequestParam("isbn") Integer isbn) {
        return ResponseEntity.status(200).body(this.service.buscarISBN(isbn));
    }

    @GetMapping("/autor")
    public ResponseEntity<?> buscarAutor(@RequestParam("autor") String autor) {
        return ResponseEntity.status(200).body(this.service.buscarAutor(autor));
    }

    @GetMapping("/preco")
    public ResponseEntity<?> buscarPreco(@RequestParam("preco") Double preco) {
        return ResponseEntity.status(200).body(this.service.buscarPreco(preco));
    }

    @PatchMapping("/{id}/titulo")
    public ResponseEntity<?> altTitulo(@PathVariable("id") Long id, @RequestParam("novoTitulo") String novoTitulo) {
        return ResponseEntity.status(200).body(this.service.altTitulo(id, novoTitulo));
    }

    @PatchMapping("/{id}/autor")
    public ResponseEntity<?> altAutor(@PathVariable("id") Long id, @RequestParam("novoAutor") String novoAutor) {
        return ResponseEntity.status(200).body(this.service.altAutor(id, novoAutor));
    }

    @PatchMapping("/{id}/isbn")
    public ResponseEntity<?> altISBN(@PathVariable("id") Long id, @RequestParam("novaIsbn") Integer novaISBN) {
        return ResponseEntity.status(200).body(this.service.altISBN(id, novaISBN));
    }

    @PatchMapping("/{id}/preco")
    public ResponseEntity<?> altPreco(@PathVariable("id") Long id, @RequestParam("novoPreco") Double preco) {
        return ResponseEntity.status(200).body(this.service.altPreco(id, preco));
    }

    @PatchMapping("/{id}/estoque")
    public ResponseEntity<?> altEstoque(@PathVariable("id") Long id, @RequestParam("novoEstoque") Integer estoque) {
        return ResponseEntity.status(200).body(this.service.altEstoque(id, estoque));
    }

    @PatchMapping("/{id}/data")
    public ResponseEntity<?> altData(@PathVariable("id") Long id, @RequestParam("novaData") LocalDate novaData) {
        return ResponseEntity.status(200).body(this.service.altData(id, novaData));
    }
}