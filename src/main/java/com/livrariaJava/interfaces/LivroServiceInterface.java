package com.livrariaJava.interfaces;

import com.livrariaJava.entity.Livro;

import java.util.List;

public interface LivroServiceInterface {
    public Livro cadastrarLivro(Livro livro);

    public Object deletarLivro(Long id);

    public List<Livro> todosLivros(int limit, int off);

    public List<Livro> historicoLivro(boolean ordem, int limit, int off);

    public Livro buscarId(Long id);

    public List<Livro> filtroLivro(String filtro, String valor);

    public Livro atualizarLivro(Long id, String tabela, String novoValor);
}