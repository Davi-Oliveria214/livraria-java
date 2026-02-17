package controller;

import excecoes.BuscaLivros;
import excecoes.ExcecoesLivro;
import livro.Livro;
import services.LivroInterface;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LivroController implements LivroInterface {
    private final List<Livro> listaLivros;
    private Long id = 1L;

    public LivroController() {
        listaLivros = new ArrayList<>();
    }

    @Override
    public void addLivro(String titulo, String autor, double preco, int isbn, int estoque, LocalDate data) throws ExcecoesLivro {

        for (Livro livro : listaLivros) {
            if (livro.getIsbn() == isbn) {
                throw new BuscaLivros("Essa ISBN já está cadastrada");
            }

            if (livro.getTitulo().equals(titulo) && livro.getAutor().equals(autor)) {
                throw new BuscaLivros("Esse titulo: " + titulo + ", desse autor: " + autor + ", já está cadastrado");
            }
        }

        listaLivros.add(new Livro(id++, titulo, autor, preco, isbn, estoque, data));
    }

    @Override
    public void delLivro(int isbn) throws ExcecoesLivro {
        this.verificar();

        boolean remover = listaLivros.removeIf(livro -> livro.getIsbn() == isbn);

        if (!remover) {
            throw new BuscaLivros("Nenhum livro encontrado com a ISBN: " + isbn);
        }
    }

    @Override
    public List<Livro> getLivros() {
        this.verificar();
        return listaLivros;
    }

    @Override
    public List<Livro> buscarTitulo(String titulo) throws ExcecoesLivro {
        List<Livro> livros = new ArrayList<>();

        this.verificar();

        for (Livro livro : listaLivros) {
            if (livro.getTitulo().equals(titulo)) {
                livros.add(livro);
            }
        }

        if (livros.isEmpty()) {
            throw new BuscaLivros("Nenhum livro com o titulo: " + titulo + ", encontrado");
        }

        return livros;
    }

    @Override
    public Livro buscarISBN(int isbn) throws ExcecoesLivro {
        Livro livro = null;

       this.verificar();

        for (Livro livros : listaLivros) {
            if (livros.getIsbn() == isbn) {
                livro = livros;
            }
        }

        if (livro == null) {
            throw new BuscaLivros("Nenhum livro com a ISBN: " + isbn + ", encontrado");
        }

        return livro;
    }

    @Override
    public List<Livro> buscarAutor(String autor) throws ExcecoesLivro {
        List<Livro> livroAutor = new ArrayList<>();

        this.verificar();

        for (Livro livro : listaLivros) {
            if (livro.getAutor().equals(autor)) {
                livroAutor.add(livro);
            }
        }

        if (livroAutor.isEmpty()) {
            throw new BuscaLivros("Nenhum autor com esse nome encontrado: " + autor);
        }

        return livroAutor;
    }

    @Override
    public List<Livro> buscarPreco(double preco) {
        List<Livro> livros = new ArrayList<>();

        this.verificar();

        for (Livro livro : listaLivros) {
            if (livro.getPreco() == preco) {
                livros.add(livro);
            }
        }

        if (livros.isEmpty()) {
            throw new BuscaLivros("Nenhum livro encontrado com esse preço: " + preco);
        }

        return livros;
    }

    @Override
    public void altTitulo(int isbn, String novoTitulo) throws ExcecoesLivro {
        this.verificar();

        this.buscarISBN(isbn).setTitulo(novoTitulo);
    }

    @Override
    public void altAutor(int isbn, String novoAutor) {
        this.verificar();

        this.buscarISBN(isbn).setAutor(novoAutor);
    }

    @Override
    public void altPreco(int isbn, double novoPreco) {
        this.verificar();

        this.buscarISBN(isbn).setPreco(novoPreco);
    }

    @Override
    public void altEstoque(int isbn, int novoEstoque) {
        this.verificar();

        this.buscarISBN(isbn).setEstoque(novoEstoque);
    }

    @Override
    public void altISBN(int isbn, int novaISBN) {
        this.verificar();

        this.buscarISBN(isbn).setIsbn(novaISBN);
    }

    public void verificar(){
        if(listaLivros.isEmpty()){
            throw new BuscaLivros("Nenhum livro cadastrado");
        }
    }
}