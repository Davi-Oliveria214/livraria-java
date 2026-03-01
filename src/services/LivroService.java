package services;

import entity.LivroEntity;
import excecoes.BuscaLivros;
import excecoes.ExcecoesLivro;
import interfaces.LivroInterface;
import repository.LivroRepository;

import java.util.List;
import java.util.Objects;

public class LivroService implements LivroInterface {
    private final LivroRepository repository;

    public LivroService(LivroRepository livroRepository) {
        repository = livroRepository;
    }

    @Override
    public void criarLivro(LivroEntity livro) {

        if (repository.buscarISBN(livro.getIsbn()) != null) {
            throw new BuscaLivros("Essa ISBN já está cadastrada");
        }

        for (LivroEntity livros : repository.buscarTitulo(livro.getTitulo())) {
            if (Objects.equals(livros.getTitulo(), livro.getTitulo()) && Objects.equals(livros.getAutor(), livro.getAutor())) {
                throw new BuscaLivros("Esse titulo: " + livro.getTitulo() + ", desse autor: " + livro.getAutor() + ", já está cadastrado");
            }
        }

        repository.newLivro(livro);
    }

    @Override
    public void delLivro(int isbn) throws ExcecoesLivro {
        LivroEntity livroEntity = repository.buscarISBN(isbn);

        if (livroEntity == null) {
            throw new BuscaLivros("Nenhum livro encontrado");
        }

        repository.delLivro(livroEntity);
    }

    @Override
    public List<LivroEntity> getLivros() {
        return repository.todosLivros();
    }

    @Override
    public List<LivroEntity> buscarTitulo(String titulo) throws ExcecoesLivro {
        this.verificar();

        List<LivroEntity> livroEntities = repository.buscarTitulo(titulo);

        if (livroEntities.isEmpty()) {
            throw new BuscaLivros("Nenhum livro com o titulo: " + titulo + ", encontrado");
        }

        return livroEntities;
    }

    @Override
    public LivroEntity buscarISBN(int isbn) throws ExcecoesLivro {
        this.verificar();

        LivroEntity livroEntity = repository.buscarISBN(isbn);

        if (livroEntity == null) {
            throw new BuscaLivros("Nenhum livro com a ISBN: " + isbn + ", encontrado");
        }

        return livroEntity;
    }

    @Override
    public List<LivroEntity> buscarAutor(String autor) throws ExcecoesLivro {
        this.verificar();

        List<LivroEntity> livroEntityAutor = repository.buscarAutor(autor);

        if (livroEntityAutor.isEmpty()) {
            throw new BuscaLivros("Nenhum autor com esse nome encontrado: " + autor);
        }

        return livroEntityAutor;
    }

    @Override
    public List<LivroEntity> buscarPreco(double preco) throws ExcecoesLivro {
        this.verificar();

        List<LivroEntity> livroEntities = repository.buscarPreco(preco);

        if (livroEntities.isEmpty()) {
            throw new BuscaLivros("Nenhum livro encontrado com esse preço: " + preco);
        }

        return livroEntities;
    }

    @Override
    public void altTitulo(int isbn, String novoTitulo) throws ExcecoesLivro {
        this.verificar();

        LivroEntity livroEntity = this.buscarISBN(isbn);
        livroEntity.setTitulo(novoTitulo);

        this.repository.updateLivro(livroEntity);
    }

    @Override
    public void altAutor(int isbn, String novoAutor) throws ExcecoesLivro {
        this.verificar();

        LivroEntity livroEntity = this.buscarISBN(isbn);
        livroEntity.setAutor(novoAutor);

        this.repository.updateLivro(livroEntity);
    }

    @Override
    public void altPreco(int isbn, double novoPreco) throws ExcecoesLivro {
        this.verificar();

        LivroEntity livroEntity = this.buscarISBN(isbn);
        livroEntity.setPreco(novoPreco);

        repository.updateLivro(livroEntity);
    }

    @Override
    public void altEstoque(int isbn, int novoEstoque) throws ExcecoesLivro {
        this.verificar();

        LivroEntity livroEntity = this.buscarISBN(isbn);
        livroEntity.setEstoque(novoEstoque);

        this.repository.updateLivro(livroEntity);
    }

    @Override
    public void altISBN(int isbn, int novaISBN) throws ExcecoesLivro {
        this.verificar();

        LivroEntity livroEntity = this.buscarISBN(isbn);
        livroEntity.setIsbn(novaISBN);

        this.repository.updateLivro(livroEntity);
    }

    public void verificar() throws ExcecoesLivro {
        if (repository.todosLivros().isEmpty()) {
            throw new BuscaLivros("Nenhum livro cadastrado");
        }
    }
}