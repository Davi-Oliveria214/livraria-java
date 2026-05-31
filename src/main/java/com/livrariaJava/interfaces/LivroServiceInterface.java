package com.livrariaJava.interfaces;

import com.livrariaJava.entity.Livro;
import java.time.LocalDate;
import java.util.List;

public interface LivroServiceInterface {
    public Livro criarLivro(Livro livro);

    public String delLivro(Long id);

    public List<Livro> getLivros();

    public List<Livro> buscarTitulo(String titulo);

    public List<Livro> buscarISBN(Integer isbn);

    public Livro buscarId(Long id);

    public List<Livro> buscarAutor(String autor);

    public List<Livro> buscarPreco(Double preco);

    public Livro altTitulo(Long id, String novoTitulo);

    public Livro altAutor(Long id, String novoAutor);

    public Livro altPreco(Long id, Double novoPreco);

    public Livro altEstoque(Long id, Integer novoEstoque);

    public Livro altISBN(Long id, Integer novaISBN);

    public Livro altData(Long id, LocalDate novaData);
}