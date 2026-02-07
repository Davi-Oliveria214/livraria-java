package gerenciamento;

import excecoes.BuscaLivros;
import excecoes.ExcecoesLivro;
import livro.Livro;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LivroConfig implements LivroInterface {
    public List<Livro> listaLivros;
    private Long id = 1L;

    public LivroConfig() {
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
        if (listaLivros.isEmpty()) {
            throw new BuscaLivros("Nenhum livro cadastrado");
        }

        boolean remover = listaLivros.removeIf(livro -> livro.getIsbn() == isbn);

        if (!remover) {
            throw new BuscaLivros("Nenhum livro encontrado com a ISBN: " + isbn);
        }
    }

    @Override
    public void altTitulo(String titulo) {

    }

    @Override
    public void altAltor(String autor) {

    }

    @Override
    public void altPreco(double preco) {

    }

    @Override
    public void altEstoque(int estoque) {

    }

    @Override
    public void altIsbn(int isbn) {

    }

    @Override
    public void altLancamento(LocalDate data) {

    }

    @Override
    public List<Livro> buscarLivro(String titulo) throws ExcecoesLivro {
        List<Livro> livros = new ArrayList<>();

        if (listaLivros.isEmpty()) {
            throw new BuscaLivros("Nenhum livro cadastrado");
        }

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
    public List<Livro> buscarLivro(int isbn) throws ExcecoesLivro {
        List<Livro> livros = new ArrayList<>();

        if (listaLivros.isEmpty()) {
            throw new BuscaLivros("Nenhum livro cadastrado");
        }

        for (Livro livro : listaLivros) {
            if (livro.getIsbn() == isbn) {
                livros.add(livro);
            }
        }

        if (livros.isEmpty()) {
            throw new BuscaLivros("Nenhum livro a ISBN: " + isbn + ", encontrado");
        }

        return livros;
    }

    @Override
    public List<Livro> buscarAutor(String autor) throws ExcecoesLivro {
        List<Livro> livroAutor = new ArrayList<>();

        if (listaLivros.isEmpty()) {
            throw new BuscaLivros("Nenhum livro cadastrado");
        }

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
}