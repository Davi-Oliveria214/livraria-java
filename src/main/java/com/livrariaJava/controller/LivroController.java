package com.livrariaJava.controller;

import com.livrariaJava.entity.Livro;
import com.livrariaJava.excecoes.ExcecoesLivro;
import com.livrariaJava.services.LivroServiceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
    public ResponseEntity<?> delLivro(@PathVariable Long id) {
        try {
            return ResponseEntity.status(200).body(this.service.delLivro(id));
        } catch (ExcecoesLivro el) {
            return ResponseEntity.status(400).body(el.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> mostarLivros() {
        try {
            List<Livro> l = this.service.getLivros();
            return ResponseEntity.status(200).body(l);
        } catch (ExcecoesLivro el) {
            return ResponseEntity.status(404).body(el.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarId(@PathVariable Long id) {
        try {
            Livro l = this.service.buscarId(id);
            return ResponseEntity.status(200).body(l);
        } catch (ExcecoesLivro el) {
            return ResponseEntity.status(404).body(el.getMessage());
        }
    }

    @GetMapping("/titulo")
    public ResponseEntity<?> buscarTitulo(@RequestParam("titulo") String titulo) {
        try {
            List<Livro> l = this.service.buscarTitulo(titulo);
            return ResponseEntity.status(200).body(l);
        } catch (ExcecoesLivro el) {
            return ResponseEntity.status(404).body(el.getMessage());
        }
    }

    @GetMapping("/isbn")
    public ResponseEntity<?> buscarISBN(@RequestParam("isbn") Integer isbn) {
        try {
            List<Livro> l = this.service.buscarISBN(isbn);
            return ResponseEntity.status(200).body(l);
        } catch (ExcecoesLivro el) {
            return ResponseEntity.status(404).body(el.getMessage());
        }
    }

    @GetMapping("/autor")
    public ResponseEntity<?> buscarAutor(@RequestParam("autor") String autor) {
        try {
            List<Livro> l = this.service.buscarAutor(autor);
            return ResponseEntity.status(200).body(l);
        } catch (ExcecoesLivro el) {
            return ResponseEntity.status(404).body(el.getMessage());
        }
    }

    @GetMapping("/preco")
    public ResponseEntity<?> buscarPreco(@RequestParam("preco") Double preco) {
        try {
            List<Livro> l = this.service.buscarPreco(preco);
            return ResponseEntity.status(200).body(l);
        } catch (ExcecoesLivro el) {
            return ResponseEntity.status(404).body(el.getMessage());
        }
    }

    @PatchMapping("/{id}/titulo")
    public ResponseEntity<?> altTitulo(@PathVariable("id") Long id, @RequestParam("titulo") String novoTitulo) {
        try {
            return ResponseEntity.status(200).body(this.service.altTitulo(id, novoTitulo));
        } catch (ExcecoesLivro e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PatchMapping("/{id}/autor")
    public ResponseEntity<?> altAutor(@PathVariable("id") Long id, @RequestParam("autor") String novoAutor) {
        try {
            return ResponseEntity.status(200).body(this.service.altAutor(id, novoAutor));
        } catch (ExcecoesLivro e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PatchMapping("/{id}/isbn")
    public ResponseEntity<?> altISBN(@PathVariable("id") Long id, @RequestParam("isbn") Integer novaISBN) {
        try {
            return ResponseEntity.status(200).body(this.service.altISBN(id, novaISBN));
        } catch (ExcecoesLivro e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PatchMapping("/{id}/preco")
    public ResponseEntity<?> altPreco(@PathVariable("id") Long id, @RequestParam("preco") Double preco) {
        try {
            return ResponseEntity.status(200).body(this.service.altPreco(id, preco));
        } catch (ExcecoesLivro e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PatchMapping("/{id}/estoque")
    public ResponseEntity<?> altEstoque(@PathVariable("id") Long id, @RequestParam("estoque") Integer estoque) {
        try {
            return ResponseEntity.status(200).body(this.service.altEstoque(id, estoque));
        } catch (ExcecoesLivro e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PatchMapping("/{id}/data")
    public ResponseEntity<?> altData(@PathVariable("id") Long id, @RequestParam("data") LocalDate novaData) {
        try {
            return ResponseEntity.status(200).body(this.service.altData(id, novaData));
        } catch (ExcecoesLivro e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}