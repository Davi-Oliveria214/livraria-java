package com.livrariaJava.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.livrariaJava.entity.enums.GenerosEnum;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.LocalDate;

@Table(name = "tb_livros")
@Entity
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String autor;
    private Double preco;
    private String isbn;
    private Integer estoque;
    private String sinopse;
    private String genero;
    private LocalDate lancamento;

    @Column(name = "criado_em")
    private Timestamp criado;

    public Livro() {

    }

    public Livro(Long id, String titulo, String autor, Double preco, String isbn, Integer estoque, String sinopse, String genero, LocalDate lancamento) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.preco = preco;
        this.isbn = isbn;
        this.estoque = estoque;
        this.sinopse = sinopse;
        this.genero = genero;
        this.lancamento = lancamento;
        this.criado = new Timestamp(System.currentTimeMillis());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
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

    @JsonProperty(value = "criado_em")
    public Timestamp getCriado() {
        return criado;
    }

    @JsonProperty(value = "criado_em", access = JsonProperty.Access.READ_ONLY)
    public void setCriado(Timestamp criado_em) {
        this.criado = criado_em;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    @JsonProperty(value = "genero", access = JsonProperty.Access.WRITE_ONLY)
    public String getGenero() {
        GenerosEnum g = GenerosEnum.buscarGenero(genero);
        return g != null ? g.getCodigo() : GenerosEnum.INDEFINIDO.getCodigo();
    }

    @JsonProperty(value = "genero", access = JsonProperty.Access.READ_ONLY)
    public String getGeneroExibir() {
        GenerosEnum g = GenerosEnum.buscarGenero(genero);
        return g != null ? g.getGenero() : GenerosEnum.INDEFINIDO.getGenero();
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
}