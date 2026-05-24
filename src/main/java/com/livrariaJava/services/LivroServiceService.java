package com.livrariaJava.services;

import com.livrariaJava.entity.Livro;
import com.livrariaJava.excecoes.BuscaLivros;
import com.livrariaJava.excecoes.ExcecoesLivro;
import com.livrariaJava.interfaces.LivroServiceInterface;
import com.livrariaJava.repository.LivroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class LivroServiceService implements LivroServiceInterface {
    private final LivroRepository repository;

    public LivroServiceService(LivroRepository livroRepository) {
        repository = livroRepository;
    }

    @Override
    public void criarLivro(Livro livro) {

        if (repository.buscarISBN(livro.getIsbn()) != null) {
            throw new BuscaLivros("Essa ISBN já está cadastrada");
        }

        for (Livro livros : repository.buscarTitulo(livro.getTitulo())) {
            if (Objects.equals(livros.getTitulo(), livro.getTitulo()) && Objects.equals(livros.getAutor(), livro.getAutor())) {
                throw new BuscaLivros("Esse titulo: " + livro.getTitulo() + ", desse autor: " + livro.getAutor() + ", já está cadastrado");
            }
        }

        repository.newLivro(livro);
    }

    @Override
    public void delLivro(int id) throws ExcecoesLivro {
        Livro livro = repository.buscarId(id);

        if (livro == null) {
            throw new BuscaLivros("Nenhum livro encontrado");
        }

        repository.delLivro(livro);
    }

    @Override
    public List<Livro> getLivros() {
        return repository.todosLivros();
    }

    @Override
    public List<Livro> buscarTitulo(String titulo) throws ExcecoesLivro {
        this.verificar();

        List<Livro> livroEntities = repository.buscarTitulo(titulo);

        if (livroEntities.isEmpty()) {
            throw new BuscaLivros("Nenhum livro com o titulo: " + titulo + ", encontrado");
        }

        return livroEntities;
    }

    @Override
    public List<Livro> buscarISBN(int isbn) throws ExcecoesLivro {
        this.verificar();

        List<Livro> livros = repository.buscarISBN(isbn);

        if (livros == null) {
            throw new BuscaLivros("Nenhum livro com a ISBN: " + isbn + ", encontrado");
        }

        return livros;
    }

    @Override
    public Livro buscarId(int id) {
        return repository.buscarId(id);
    }

    @Override
    public List<Livro> buscarAutor(String autor) throws ExcecoesLivro {
        this.verificar();

        List<Livro> livroAutor = repository.buscarAutor(autor);

        if (livroAutor.isEmpty()) {
            throw new BuscaLivros("Nenhum autor com esse nome encontrado: " + autor);
        }

        return livroAutor;
    }

    @Override
    public List<Livro> buscarPreco(double preco) throws ExcecoesLivro {
        this.verificar();

        List<Livro> livroEntities = repository.buscarPreco(preco);

        if (livroEntities.isEmpty()) {
            throw new BuscaLivros("Nenhum livro encontrado com esse preço: " + preco);
        }

        return livroEntities;
    }

    @Override
    public void altTitulo(int id, String novoTitulo) throws ExcecoesLivro {
        this.verificar();

        Livro livro = this.buscarId(id);
        livro.setTitulo(novoTitulo);

        this.repository.updateLivro(livro);
    }

    @Override
    public void altAutor(int id, String novoAutor) throws ExcecoesLivro {
        this.verificar();

        Livro livro = this.buscarId(id);
        livro.setAutor(novoAutor);

        this.repository.updateLivro(livro);
    }

    @Override
    public void altPreco(int id, double novoPreco) throws ExcecoesLivro {
        this.verificar();

        Livro livro = this.buscarId(id);
        livro.setPreco(novoPreco);

        repository.updateLivro(livro);
    }

    @Override
    public void altEstoque(int id, int novoEstoque) throws ExcecoesLivro {
        this.verificar();

        Livro livro = this.buscarId(id);
        livro.setEstoque(novoEstoque);

        this.repository.updateLivro(livro);
    }

    @Override
    public void altISBN(int id, int novaISBN) throws ExcecoesLivro {
        this.verificar();

        Livro livro = this.buscarId(id);
        livro.setIsbn(novaISBN);

        this.repository.updateLivro(livro);
    }

    public void verificar() throws ExcecoesLivro {
        if (repository.isTabelaVazia()) {
            throw new BuscaLivros("Nenhum livro cadastrado");
        }
    }
}