package com.livrariaJava.interfaces;

import com.livrariaJava.entity.Livro;
import java.time.LocalDate;
import java.util.Deque;
import java.util.List;

public interface LivroServiceInterface {
    public Livro criarLivro(Livro livro);

    public String delLivro(Long id);

    public List<Livro> getLivros();

    public List<Livro> historico();

    public Livro buscarId(Long id);

    public List<Livro> busca(String filtro, String valor);

    public Livro altTitulo(Long id, String novoTitulo);

    public Livro altAutor(Long id, String novoAutor);

    public Livro altPreco(Long id, Double novoPreco);

    public Livro altEstoque(Long id, Integer novoEstoque);

    public Livro altISBN(Long id, Integer novaISBN);

    public Livro altData(Long id, LocalDate novaData);
}