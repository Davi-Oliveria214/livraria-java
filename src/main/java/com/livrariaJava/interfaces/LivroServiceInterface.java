package com.livrariaJava.interfaces;

import com.livrariaJava.entity.Livro;

import java.util.List;
import java.util.Optional;

public interface LivroServiceInterface {
    public Livro cadastrarLivro(Livro livro);

    public Object deletarLivro(Long id);

    public List<Livro> todosLivros();

    public List<Livro> historicoLivro(boolean ordem);

    public Livro buscarId(Long id);

    public List<Livro> filtroLivro(String filtro, String valor);

    public Livro atualizarLivro(Long id, Livro novoValor);
}