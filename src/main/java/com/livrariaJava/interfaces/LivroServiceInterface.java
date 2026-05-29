package com.livrariaJava.interfaces;

import com.livrariaJava.entity.Livro;

import java.util.List;
import java.util.Optional;

public interface LivroServiceInterface {
    public Livro criarLivro(Livro livro);

    public void delLivro(int isbn);

    public List<Livro> getLivros();

    public List<Livro> buscarTitulo(String titulo);

    public List<Livro> buscarISBN(int isbn);

    public Optional<Livro> buscarId(int id);

    public List<Livro> buscarAutor(String autor);

    public List<Livro> buscarPreco(double preco);

    public void altTitulo(int isbn, String novoTitulo);

    public void altAutor(int isbn, String novoAutor);

    public void altPreco(int isbn, double novoPreco);

    public void altEstoque(int isbn, int novoEstoque);

    public void altISBN(int isbn, int novaISBN);
}