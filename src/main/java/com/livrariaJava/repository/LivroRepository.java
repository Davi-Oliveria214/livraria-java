package com.livrariaJava.repository;

import com.livrariaJava.connection.LivroConnection;
import com.livrariaJava.entity.Livro;
import com.livrariaJava.excecoes.ExcecoesLivro;
import org.springframework.stereotype.Repository;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class LivroRepository {
    private final LivroConnection conn;

    public LivroRepository(LivroConnection livroConnection) {
        this.conn = livroConnection;
    }

    public Livro newLivro(Livro livro) {
        String sql = "INSERT INTO livro(titulo, autor, isbn, preco, estoque, lancamento) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stm = conn.connection().prepareStatement(sql)) {
            stm.setString(1, livro.getTitulo());
            stm.setString(2, livro.getAutor());
            stm.setInt(3, livro.getIsbn());
            stm.setDouble(4, livro.getPreco());
            stm.setInt(5, livro.getEstoque());
            stm.setDate(6, Date.valueOf(livro.getLancamento()));

            stm.executeUpdate();
            return livro;
        } catch (SQLException e) {
            throw new ExcecoesLivro("Erro ao adicionar livro", e);
        }
    }

    public void delLivro(Long id) {
        String sql = "DELETE FROM livro WHERE id = ?";

        try (PreparedStatement stm = conn.connection().prepareStatement(sql)) {
            stm.setLong(1, id);
            stm.executeUpdate();
        } catch (SQLException e) {
            throw new ExcecoesLivro("Erro ao apagar livro", e);
        }
    }

    public Livro updateLivro(Livro livro) {
        String sql = "UPDATE livro SET titulo = ?, autor = ?, isbn = ?, preco = ?, estoque = ?, lancamento = ? WHERE id = ?";

        try (PreparedStatement stm = conn.connection().prepareStatement(sql)) {
            stm.setString(1, livro.getTitulo());
            stm.setString(2, livro.getAutor());
            stm.setInt(3, livro.getIsbn());
            stm.setDouble(4, livro.getPreco());
            stm.setInt(5, livro.getEstoque());
            stm.setDate(6, Date.valueOf(livro.getLancamento()));
            stm.setLong(7, livro.getId());
            stm.executeUpdate();

            return buscarId(livro.getId());
        } catch (SQLException e) {
            throw new ExcecoesLivro("Erro ao atualizar o livro", e);
        }
    }

    public List<Livro> buscarTitulo(String titulo) {
        List<Livro> livros = new ArrayList<>();
        String sql = "SELECT * FROM livro WHERE titulo LIKE CONCAT('%', ?, '%')";

        try (PreparedStatement stm = conn.connection().prepareStatement(sql)) {
            stm.setString(1, titulo);

            try (ResultSet res = stm.executeQuery()) {
                while (res.next()) {
                    Livro livro = new Livro(res.getLong("id"), res.getString("titulo"), res.getString("autor"), res.getDouble("preco"), res.getInt("isbn"), res.getInt("estoque"), res.getDate("lancamento").toLocalDate());
                    livros.add(livro);
                }
            }
        } catch (SQLException e) {
            throw new ExcecoesLivro("Erro ao buscar titulo", e);
        }

        return livros;
    }

    public List<Livro> buscarAutor(String autor) {
        List<Livro> livros = new ArrayList<>();
        String sql = "SELECT * FROM livro WHERE autor LIKE CONCAT('%', ?, '%')";

        try (PreparedStatement stm = conn.connection().prepareStatement(sql)) {
            stm.setString(1, autor);

            try (ResultSet res = stm.executeQuery()) {
                while (res.next()) {
                    Livro livro = new Livro(res.getLong("id"), res.getString("titulo"), res.getString("autor"), res.getDouble("preco"), res.getInt("isbn"), res.getInt("estoque"), res.getDate("lancamento").toLocalDate());
                    livros.add(livro);
                }
            }
        } catch (SQLException e) {
            throw new ExcecoesLivro("Erro ao buscar autor", e);
        }

        return livros;
    }

    public List<Livro> buscarISBN(Integer isbn) {
        List<Livro> livros = new ArrayList<>();
        String sql = "SELECT * FROM livro WHERE isbn LIKE CONCAT('%', ?, '%')";

        try (PreparedStatement stm = conn.connection().prepareStatement(sql)) {
            stm.setInt(1, isbn);

            try (ResultSet res = stm.executeQuery()) {
                while (res.next()) {
                    Livro livro = new Livro(res.getLong("id"), res.getString("titulo"), res.getString("autor"), res.getDouble("preco"), res.getInt("isbn"), res.getInt("estoque"), res.getDate("lancamento").toLocalDate());
                    livros.add(livro);
                }
            }
        } catch (SQLException e) {
            throw new ExcecoesLivro("Erro ao buscar ISBN", e);
        }

        return livros;
    }

    public Livro buscarExataISBN(Integer isbn) {
        Livro livro = null;
        String sql = "SELECT * FROM livro WHERE isbn = ?";

        try (PreparedStatement stm = conn.connection().prepareStatement(sql)) {
            stm.setInt(1, isbn);

            try (ResultSet res = stm.executeQuery()) {
                while (res.next()) {
                    livro = new Livro(res.getLong("id"), res.getString("titulo"), res.getString("autor"), res.getDouble("preco"), res.getInt("isbn"), res.getInt("estoque"), res.getDate("lancamento").toLocalDate());
                }
            }
        } catch (SQLException e) {
            throw new ExcecoesLivro("Erro ao buscar ISBN", e);
        }

        return livro;
    }

    public Livro buscarId(Long id) {
        Livro livro = null;
        String sql = "SELECT * FROM livro WHERE id = ?";

        try (PreparedStatement stm = conn.connection().prepareStatement(sql)) {
            stm.setLong(1, id);

            try (ResultSet res = stm.executeQuery()) {
                if (res.next()) {
                    livro = new Livro(res.getLong("id"), res.getString("titulo"), res.getString("autor"), res.getDouble("preco"), res.getInt("isbn"), res.getInt("estoque"), res.getDate("lancamento").toLocalDate());
                }
            }
        } catch (SQLException e) {
            throw new ExcecoesLivro("Erro ao buscar id", e);
        }

        return livro;
    }

    public List<Livro> buscarPreco(Double preco) {
        double margem = preco * 0.15;
        double minPreco = preco - margem;
        double maxPreco = preco + margem;
        List<Livro> livros = new ArrayList<>();
        String sql = "SELECT * FROM livro WHERE preco BETWEEN ? AND ?";

        try (PreparedStatement stm = conn.connection().prepareStatement(sql)) {
            stm.setDouble(1, minPreco);
            stm.setDouble(2, maxPreco);

            try (ResultSet res = stm.executeQuery()) {
                while (res.next()) {
                    Livro livro = new Livro(res.getLong("id"), res.getString("titulo"), res.getString("autor"), res.getDouble("preco"), res.getInt("isbn"), res.getInt("estoque"), res.getDate("lancamento").toLocalDate());
                    livros.add(livro);
                }
            }
        } catch (SQLException e) {
            throw new ExcecoesLivro("Erro ao buscar livro por preço", e);
        }

        return livros;
    }

    public List<Livro> todosLivros() {
        List<Livro> livros = new ArrayList<>();
        String sql = "SELECT * FROM livro";

        try (PreparedStatement stm = conn.connection().prepareStatement(sql)) {

            try (ResultSet res = stm.executeQuery()) {
                while (res.next()) {
                    Livro livro = new Livro(res.getLong("id"), res.getString("titulo"), res.getString("autor"), res.getDouble("preco"), res.getInt("isbn"), res.getInt("estoque"), res.getDate("lancamento").toLocalDate());
                    livros.add(livro);
                }
            }
        } catch (SQLException e) {
            throw new ExcecoesLivro("Erro ao buscar livros " + e, e);
        }

        return livros;
    }

    public boolean isTabelaVazia() {
        String sql = "SELECT EXISTS (SELECT 1 FROM livro)";

        try (PreparedStatement stm = conn.connection().prepareStatement(sql)) {

            try (ResultSet res = stm.executeQuery()) {
                if (res.next()) {
                    return !res.getBoolean(1);
                }
            }
        } catch (SQLException e) {
            throw new ExcecoesLivro("Erro ao buscar livros " + e, e);
        }

        return true;
    }
}