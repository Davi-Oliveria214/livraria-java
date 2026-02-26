package repository;

import connection.LivroConnection;
import entity.LivroEntity;
import excecoes.ExcecoesLivro;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LivroRepository {
    private final List<LivroEntity> listaLivroEntities;
    private final LivroConnection conn;

    private Statement stm;
    private ResultSet res;

    public LivroRepository(LivroConnection livroConnection) {
        this.listaLivroEntities = new ArrayList<>();
        this.conn = livroConnection;
    }

    public void newLivro(LivroEntity livro) {
        String sql = "INSERT INTO livro(titulo, autor, isbn, preco, estoque, lancamento) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stm = conn.connection().prepareStatement(sql)) {

            stm.setString(1, livro.getTitulo());
            stm.setString(2, livro.getAutor());
            stm.setInt(3, livro.getIsbn());
            stm.setDouble(4, livro.getPreco());
            stm.setInt(5, livro.getEstoque());
            stm.setDate(6, Date.valueOf(livro.getLancamento()));

            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delLivro(LivroEntity livroEntity) {
        listaLivroEntities.remove(livroEntity);
    }

    public void updateLivro(LivroEntity livroEntity) {
        for (int i = 0; i < listaLivroEntities.size(); i++) {
            if (Objects.equals(listaLivroEntities.get(i).getId(), livroEntity.getId())) {
                listaLivroEntities.set(i, livroEntity);
            }
        }
    }

    public List<LivroEntity> buscarTitulo(String titulo) {
        List<LivroEntity> livroEntities = new ArrayList<>();

        for (LivroEntity livroEntity : listaLivroEntities) {
            if (livroEntity.getTitulo().equals(titulo)) {
                livroEntities.add(livroEntity);
            }
        }

        return livroEntities;
    }

    public List<LivroEntity> buscarAutor(String autor) {
        List<LivroEntity> livroEntities = new ArrayList<>();

        for (LivroEntity livroEntity : listaLivroEntities) {
            if (livroEntity.getAutor().equals(autor)) {
                livroEntities.add(livroEntity);
            }
        }

        return livroEntities;
    }

    public LivroEntity buscarISBN(int isbn) {
        for (LivroEntity livroEntity : listaLivroEntities) {
            if (livroEntity.getIsbn() == isbn) {
                return livroEntity;
            }
        }

        return null;
    }

    public List<LivroEntity> buscarPreco(double preco) {
        List<LivroEntity> livroEntities = new ArrayList<>();

        for (LivroEntity livroEntity : listaLivroEntities) {
            if (livroEntity.getPreco() == preco) {
                livroEntities.add(livroEntity);
            }
        }

        return livroEntities;
    }

    public List<LivroEntity> todosLivros() {
        return listaLivroEntities;
    }
}