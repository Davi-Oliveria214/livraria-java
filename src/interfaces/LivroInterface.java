package interfaces;

import entity.LivroEntity;

import java.util.List;

public interface LivroInterface {
    public void criarLivro(LivroEntity livroEntity);

    public void delLivro(int isbn);

    public List<LivroEntity> getLivros();

    public List<LivroEntity> buscarTitulo(String titulo);

    public LivroEntity buscarISBN(int isbn);

    public List<LivroEntity> buscarAutor(String autor);

    public List<LivroEntity> buscarPreco(double preco);

    public void altTitulo(int isbn, String novoTitulo);

    public void altAutor(int isbn, String novoAutor);

    public void altPreco(int isbn, double novoPreco);

    public void altEstoque(int isbn, int novoEstoque);

    public void altISBN(int isbn, int novaISBN);
}