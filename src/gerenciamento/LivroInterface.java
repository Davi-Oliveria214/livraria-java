package gerenciamento;

import livro.Livro;

import java.time.LocalDate;
import java.util.List;

public interface LivroInterface {
    public void addLivro(String titulo, String autor, double preco, int isbn, int estoque, LocalDate data);

    public void delLivro(int isbn);

    public void altTitulo(String titulo);

    public void altAltor(String autor);

    public void altPreco(double preco);

    public void altEstoque(int estoque);

    public void altIsbn(int isbn);

    public void altLancamento(LocalDate data);

    public List<Livro> buscarLivro(String titulo);

    public List<Livro> buscarLivro(int isbn);

    public List<Livro> buscarAutor(String autor);
}