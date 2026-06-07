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

    @PostMapping
    public ResponseEntity<Livro> addLivro(@RequestBody Livro livro) {
        return ResponseEntity.status(201).body(this.service.criarLivro(livro));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delLivro(@PathVariable Long id) {
        return ResponseEntity.status(200).body(this.service.delLivro(id));
    }

    @GetMapping
    public ResponseEntity<?> mostarLivros() {
        return ResponseEntity.ok(this.service.getLivros());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livro> buscarId(@PathVariable("id") Long id) {
        return ResponseEntity.status(200).body(this.service.buscarId(id));
    }

    @GetMapping("/filtro/{filtro}")
    public ResponseEntity<?> busca(@PathVariable("filtro") String filtro, @RequestParam("valor") String valor){
        return ResponseEntity.status(200).body(this.service.busca(filtro, valor));
    }

    @GetMapping("/historico")
    public ResponseEntity<?> historico() {
        return ResponseEntity.status(200).body(this.service.historico());
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