package entity;

import java.sql.Date;
import java.time.LocalDate;

public class LivroEntity {
    private int id;
    private String titulo;
    private String autor;
    private double preco;
    private int isbn;
    private int estoque;
    private LocalDate lancamento;

    public LivroEntity(String titulo, String autor, double preco, int isbn, int estoque, LocalDate lancamento) {
        this.titulo = titulo;
        this.autor = autor;
        this.preco = preco;
        this.isbn = isbn;
        this.estoque = estoque;
        this.lancamento = lancamento;
    }

    public LivroEntity(int id, String titulo, String autor, double preco, int isbn, int estoque, LocalDate lancamento) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.preco = preco;
        this.isbn = isbn;
        this.estoque = estoque;
        this.lancamento = lancamento;
    }

    public int getId() {
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

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    public LocalDate getLancamento() {
        return lancamento;
    }

    public void setLancamento(LocalDate lancamento) {
        this.lancamento = lancamento;
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