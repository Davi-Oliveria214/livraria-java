package com.livrariaJava.services;

import com.livrariaJava.entity.Livro;
import com.livrariaJava.excecoes.BuscaVazia;
import com.livrariaJava.excecoes.ExcecoesLivro;
import com.livrariaJava.interfaces.LivroServiceInterface;
import com.livrariaJava.repository.LivroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class LivroServiceService implements LivroServiceInterface {
    private final LivroRepository repository;

    public LivroServiceService(LivroRepository livroRepository) {
        this.repository = livroRepository;
    }

    @Override
    public Livro criarLivro(Livro livro) throws ExcecoesLivro {
        Optional.ofNullable(this.repository.buscarExataISBN(livro.getIsbn())).ifPresent(l -> {
            throw new ExcecoesLivro("Essa ISBN já está cadastrada");
        });

        for (Livro l : this.repository.buscarTitulo(livro.getTitulo())) {
            if (Objects.equals(l.getTitulo(), livro.getTitulo()) && Objects.equals(l.getAutor(), livro.getAutor())) {
                throw new ExcecoesLivro("Esse titulo: " + livro.getTitulo() + ", desse autor: " + livro.getAutor() + ", já está cadastrado");
            }
        }

        return this.repository.newLivro(livro);
    }

    @Override
    public String delLivro(int id) throws ExcecoesLivro {
        Livro livro = this.buscarId(id);

        this.repository.delLivro(id);
        return "Livro: " + livro.getTitulo() + " do autor " + livro.getAutor() + " deletado com sucesso";
    }

    @Override
    public List<Livro> getLivros() throws ExcecoesLivro {
        this.verificar();

        return this.repository.todosLivros();
    }

    @Override
    public List<Livro> buscarTitulo(String titulo) throws ExcecoesLivro {
        this.verificar();

        return Optional.ofNullable(this.repository.buscarTitulo(titulo)).filter(l -> !l.isEmpty()).orElseThrow(() -> new BuscaVazia("Nenhum livro encontrado"));
    }

    @Override
    public List<Livro> buscarISBN(int isbn) throws ExcecoesLivro {
        this.verificar();

        return Optional.ofNullable(this.repository.buscarISBN(isbn)).filter(l -> !l.isEmpty()).orElseThrow(() -> new BuscaVazia("Nenhum livro com a ISBN: " + isbn + ", encontrado"));
    }

    @Override
    public Livro buscarId(int id) throws ExcecoesLivro {
        return Optional.ofNullable(this.repository.buscarId(id)).orElseThrow(() -> new BuscaVazia("Nenhum livro encontrado"));
    }

    @Override
    public List<Livro> buscarAutor(String autor) throws ExcecoesLivro {
        this.verificar();

        return Optional.ofNullable(this.repository.buscarAutor(autor)).filter(l -> !l.isEmpty()).orElseThrow(() -> new BuscaVazia("Nenhum autor com esse nome encontrado: " + autor));
    }

    @Override
    public List<Livro> buscarPreco(double preco) throws ExcecoesLivro {
        this.verificar();

        return Optional.ofNullable(this.repository.buscarPreco(preco)).filter(l -> !l.isEmpty()).orElseThrow(() -> new BuscaVazia("Nenhum livro encontrado com esse preço: " + preco));
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

        this.repository.updateLivro(livro);
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
        if (this.repository.isTabelaVazia()) {
            throw new BuscaVazia("Nenhum livro cadastrado");
        }
    }
}