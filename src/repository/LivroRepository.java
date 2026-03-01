package repository;

import connection.LivroConnection;
import entity.LivroEntity;
import excecoes.ExcecoesLivro;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LivroRepository {
    private final LivroConnection conn;
    private ResultSet res;

    public LivroRepository(LivroConnection livroConnection) {
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
            throw new ExcecoesLivro("Erro ao adicionar livro", e);
        }
    }

    public void delLivro(LivroEntity livro) {
        String sql = "DELETE FROM livro WHERE id = ?";

        try (PreparedStatement stm = conn.connection().prepareStatement(sql)) {
            stm.setInt(1, livro.getId());
            stm.executeUpdate();
        } catch (SQLException e) {
            throw new ExcecoesLivro("Erro ao apagar livro", e);
        }
    }

    public void updateLivro(LivroEntity livro) {
        String sql = "UPDATE livro SET titulo = ?, autor = ?, isbn = ?, preco = ?, estoque = ?, lancamento = ? WHERE id = ?";

        try (PreparedStatement stm = conn.connection().prepareStatement(sql)) {
            stm.setString(1, livro.getTitulo());
            stm.setString(2, livro.getAutor());
            stm.setInt(3, livro.getIsbn());
            stm.setDouble(4, livro.getPreco());
            stm.setInt(5, livro.getEstoque());
            stm.setDate(6, Date.valueOf(livro.getLancamento()));
            stm.setInt(7, livro.getId());

            stm.executeUpdate();
        } catch (SQLException e) {
            throw new ExcecoesLivro("Erro ao atualizar o livro", e);
        }
    }

    public List<LivroEntity> buscarTitulo(String titulo) {
        List<LivroEntity> livros = new ArrayList<>();
        String sql = "SELECT * FROM livro WHERE titulo = ?";

        try (PreparedStatement stm = conn.connection().prepareStatement(sql)) {
            stm.setString(1, titulo);
            res = stm.executeQuery();

            while (res.next()) {
                LivroEntity livro = new LivroEntity(res.getInt("id"), res.getString("titulo"), res.getString("autor"), res.getDouble("preco"), res.getInt("isbn"), res.getInt("estoque"), res.getDate("lancamento").toLocalDate());
                livros.add(livro);
            }
        } catch (SQLException e) {
            throw new ExcecoesLivro("Erro ao buscar titulo", e);
        }

        return livros;
    }

    public List<LivroEntity> buscarAutor(String autor) {
        List<LivroEntity> livros = new ArrayList<>();
        String sql = "SELECT * FROM livro WHERE autor = ?";

        try (PreparedStatement stm = conn.connection().prepareStatement(sql)) {
            stm.setString(1, autor);
            res = stm.executeQuery();

            while (res.next()) {
                LivroEntity livro = new LivroEntity(res.getInt("id"), res.getString("titulo"), res.getString("autor"), res.getDouble("preco"), res.getInt("isbn"), res.getInt("estoque"), res.getDate("lancamento").toLocalDate());
                livros.add(livro);
            }
        } catch (SQLException e) {
            throw new ExcecoesLivro("Erro ao buscar autor", e);
        }

        return livros;
    }

    public LivroEntity buscarISBN(int isbn) {
        LivroEntity livro = null;
        String sql = "SELECT * FROM livro WHERE isbn = ?";

        try (PreparedStatement stm = conn.connection().prepareStatement(sql)) {
            stm.setInt(1, isbn);
            res = stm.executeQuery();

            if (res.next()) {
                livro = new LivroEntity(res.getInt("id"), res.getString("titulo"), res.getString("autor"), res.getDouble("preco"), res.getInt("isbn"), res.getInt("estoque"), res.getDate("lancamento").toLocalDate());
            }
        } catch (SQLException e) {
            throw new ExcecoesLivro("Erro ao buscar ISBN", e);
        }

        return livro;
    }

    public List<LivroEntity> buscarPreco(double preco) {
        List<LivroEntity> livros = new ArrayList<>();
        String sql = "SELECT * FROM livro WHERE preco = ?";

        try (PreparedStatement stm = conn.connection().prepareStatement(sql)) {
            stm.setDouble(1, preco);
            res = stm.executeQuery();

            while (res.next()) {
                LivroEntity livro = new LivroEntity(res.getInt("id"), res.getString("titulo"), res.getString("autor"), res.getDouble("preco"), res.getInt("isbn"), res.getInt("estoque"), res.getDate("lancamento").toLocalDate());
                livros.add(livro);
            }
        } catch (SQLException e) {
            throw new ExcecoesLivro("Erro ao buscar livro por preço", e);
        }

        return livros;
    }

    public List<LivroEntity> todosLivros() {
        List<LivroEntity> livros = new ArrayList<>();
        String sql = "SELECT * FROM livro";

        try (PreparedStatement stm = conn.connection().prepareStatement(sql)) {
            res = stm.executeQuery();

            while (res.next()) {
                LivroEntity livro = new LivroEntity(res.getInt("id"), res.getString("titulo"), res.getString("autor"), res.getDouble("preco"), res.getInt("isbn"), res.getInt("estoque"), res.getDate("lancamento").toLocalDate());
                livros.add(livro);
            }
        } catch (SQLException e) {
            throw new ExcecoesLivro("Erro ao buscar livros", e);
        }

        return livros;
    }

    public boolean isTabelaVazia() {
        String sql = "SELECT EXISTS (SELECT 1 FROM livro)";

        try (PreparedStatement stm = conn.connection().prepareStatement(sql)) {
            res = stm.executeQuery();

            if (res.next()) {
                return !res.getBoolean(1);
            }
        } catch (SQLException e) {
            throw new ExcecoesLivro("Erro ao buscar livros", e);
        }

        return true;
    }
}