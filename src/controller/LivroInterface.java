package controller;

import livro.Livro;

import java.time.LocalDate;
import java.util.List;

public interface LivroInterface {
    public void addLivro(String titulo, String autor, double preco, int isbn, int estoque, LocalDate data);

    public void delLivro(int isbn);

    public List<Livro> getLivros();

    public List<Livro> buscarTitulo(String titulo);

    public Livro buscarISBN(int isbn);

    public List<Livro> buscarAutor(String autor);

    public List<Livro> buscarPreco(double preco);

    public void altTitulo(int isbn, String novoTitulo);

    public void altAutor(int isbn, String novoAutor);

    public void altPreco(int isbn, double novoPreco);

    public void altEstoque(int isbn, int novoEstoque);

    public void altISBN(int isbn, int novaISBN);
}