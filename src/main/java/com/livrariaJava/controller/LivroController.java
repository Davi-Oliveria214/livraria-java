package com.livrariaJava.controller;

import com.livrariaJava.entity.Livro;
import com.livrariaJava.services.LivroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/livraria")
public class LivroController {
    private final LivroService service;

    public LivroController(LivroService livroService) {
        this.service = livroService;
    }

    @PostMapping
    public ResponseEntity<Livro> cadastrarLivro(@RequestBody Livro livro) {
        return ResponseEntity.status(201).body(this.service.cadastrarLivro(livro));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarLivro(@PathVariable Long id) {
        return ResponseEntity.status(200).body(this.service.deletarLivro(id));
    }

    @GetMapping
    public ResponseEntity<?> todosLivros() {
        return ResponseEntity.ok(this.service.todosLivros());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livro> buscarId(@PathVariable("id") Long id) {
        return ResponseEntity.status(200).body(this.service.buscarId(id));
    }

    @GetMapping("/filtro/{filtro}")
    public ResponseEntity<?> filtroLivro(@PathVariable("filtro") String filtro, @RequestParam("valor") String valor) {
        return ResponseEntity.status(200).body(this.service.filtroLivro(filtro, valor));
    }

    @GetMapping("/historico")
    public ResponseEntity<?> historico(@RequestParam(value = "ordem", defaultValue = "true") boolean ordem) {
        return ResponseEntity.status(200).body(this.service.historicoLivro(ordem));
    }

    @GetMapping("/generos")
    public ResponseEntity<?> generos() {
        return ResponseEntity.status(200).body(this.service.todosGeneros());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable("id") Long id, @RequestBody Livro livro) {
        return ResponseEntity.status(200).body(this.service.atualizarLivro(id, livro));
    }
}