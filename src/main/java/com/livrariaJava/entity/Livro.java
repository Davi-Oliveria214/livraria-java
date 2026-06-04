package com.livrariaJava.entity;

import java.sql.Timestamp;
import java.time.LocalDate;

public class Livro {
    private Long id;
    private String titulo;
    private String autor;
    private Double preco;
    private Integer isbn;
    private Integer estoque;
    private LocalDate lancamento;
    private Timestamp criado_em;

    public Livro() {

    }

    public Livro(Long id, String titulo, String autor, Double preco, Integer isbn, Integer estoque, LocalDate lancamento, Timestamp criado_em) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.preco = preco;
        this.isbn = isbn;
        this.estoque = estoque;
        this.lancamento = lancamento;
        this.criado_em = criado_em;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Integer getIsbn() {
        return isbn;
    }

    public void setIsbn(Integer isbn) {
        this.isbn = isbn;
    }

    public Integer getEstoque() {
        return estoque;
    }

    public void setEstoque(Integer estoque) {
        this.estoque = estoque;
    }

    public LocalDate getLancamento() {
        return lancamento;
    }

    public void setLancamento(LocalDate lancamento) {
        this.lancamento = lancamento;
    }

    public Timestamp getCriado_em() {
        return criado_em;
    }

    public void setCriado_em(Timestamp criado_em) {
        this.criado_em = criado_em;
    }

    @Override
    public String toString() {
        return "Livro{" +
                "ID: " + id +
                ", Titulo='" + titulo + '\'' +
                ", Autor='" + autor + '\'' +
                ", Preço=" + preco +
                ", ISBN=" + isbn +
                ", Estoque=" + estoque +
                ", Lançamento=" + lancamento +
                '}';
    }
}