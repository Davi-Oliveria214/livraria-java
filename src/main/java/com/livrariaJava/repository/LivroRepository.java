package com.livrariaJava.repository;

import com.livrariaJava.entity.Generos;
import com.livrariaJava.entity.Livro;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.*;

@Repository
public class LivroRepository {
    private final NamedParameterJdbcTemplate conn;

    public LivroRepository(NamedParameterJdbcTemplate livroConnection) {
        this.conn = livroConnection;
    }

    public Livro cadastrarLivro(Livro livro) {
        String sql = "INSERT INTO tb_livros(titulo, autor, isbn, preco, genero, estoque, sinopse, lancamento) " +
                "VALUES (:titulo, :autor, :isbn, :preco, :genero, :estoque, :sinopse, :lancamento)";

        KeyHolder key = new GeneratedKeyHolder();
        SqlParameterSource params = new BeanPropertySqlParameterSource(livro);

        this.conn.update(sql, params, key, new String[]{"id"});

        return key.getKey() != null ? porId(key.getKey()) : livro;
    }

    public void deletarLivro(Long id) {
        String sql = "DELETE FROM tb_livros WHERE id = :id";

        Map<String, Object> map = new HashMap<>();
        map.put("id", id);

        this.conn.update(sql, map);
    }

    public Livro atualizarLivro(Long id, String tabela, String valor) {
        String sql = "UPDATE tb_livros SET " + tabela + " = :valor WHERE id = :id";

        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("valor", valor);

        this.conn.update(sql, map);

        return porId(id);
    }

    public Livro atualizarLivro(Long id, String opcao, Object valor) {
        String sql = "UPDATE tb_livros SET " + opcao + " = :valor WHERE id = :id";

        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("valor", valor);

        this.conn.update(sql, map);

        return porId(id);
    }

    public List<Generos> todosGeneros(int limit, int off) {
        String sql = "SELECT * FROM generos LIMIT :limit OFFSET :off";

        Map<String, Object> map = new HashMap<>();
        map.put("limit", limit);
        map.put("off", off);

        return this.conn.query(sql, map, new BeanPropertyRowMapper<>(Generos.class));
    }

    public List<Livro> historicoLivro(Boolean ordem, Integer limit, Integer off) {
        String sql = "SELECT * FROM tb_livros ORDER BY criado_em ";
        sql += ordem ? "DESC" : "ASC";
        sql += " LIMIT :limit OFFSET :off";

        Map<String, Object> map = new HashMap<>();
        map.put("limit", limit);
        map.put("off", off);

        return this.conn.query(sql, map, mapearLivro());
    }

    public List<Livro> todosLivros(int limit, int off) {
        String sql = "SELECT * FROM tb_livros ORDER BY id LIMIT :limit OFFSET :off";

        Map<String, Object> map = new HashMap<>();
        map.put("limit", limit);
        map.put("off", off);

        return this.conn.query(sql, map, mapearLivro());
    }

    public Livro porId(Number id) {
        String sql = "SELECT * FROM tb_livros WHERE id = :id";

        List<Livro> l = this.conn.query(sql, parametros("id", id), mapearLivro());

        return (!l.isEmpty()) ? l.get(0) : null;
    }

    public List<Livro> porIsbn(String isbn) {
        String sql = "SELECT * FROM tb_livros WHERE isbn::text LIKE CONCAT('%', :isbn, '%')";

        return this.conn.query(sql, parametros("isbn", isbn), mapearLivro());
    }

    public List<Livro> porTitulo(String titulo) {
        String sql = "SELECT * FROM tb_livros WHERE LOWER(titulo) LIKE LOWER(CONCAT('%', :titulo, '%'))";

        return this.conn.query(sql, parametros("titulo", titulo), mapearLivro());
    }

    public List<Livro> porAutor(String autor) {
        String sql = "SELECT * FROM tb_livros WHERE LOWER(autor) LIKE LOWER(CONCAT('%', :autor, '%'))";

        return this.conn.query(sql, parametros("autor", autor), mapearLivro());
    }

    public List<Livro> porLancamento(List<LocalDate> datas) {
        String sql = "SELECT * FROM tb_livros WHERE lancamento BETWEEN :inicio AND :fim";

        Map<String, Object> map = new HashMap<>();
        map.put("inicio", datas.get(0));
        map.put("fim", datas.get(1));
        return this.conn.query(sql, map, mapearLivro());
    }

    public List<Livro> porPreco(Double preco) {
        double margem = preco * 0.15;
        double minPreco = preco - margem;
        double maxPreco = preco + margem;
        String sql = "SELECT * FROM tb_livros WHERE preco BETWEEN :min AND :max";

        Map<String, Object> map = new HashMap<>();
        map.put("min", minPreco);
        map.put("max", maxPreco);
        return this.conn.query(sql, map, mapearLivro());
    }

    public List<Livro> porGeneros(String valor) {
        String sql = "SELECT * FROM tb_livros WHERE genero = :g";

        return this.conn.query(sql, parametros("g", valor), mapearLivro());
    }

    public boolean isIsbn(String isbn) {
        String sql = "SELECT * FROM tb_livros WHERE isbn = :isbn";
        return this.conn.query(sql, parametros("isbn", isbn), mapearLivro()).isEmpty();
    }

    public boolean autorAndTitulo(String autor, String titulo) {
        String sql = "SELECT * FROM tb_livros WHERE autor = :autor AND titulo = :titulo";

        Map<String, String> map = new HashMap<>();
        map.put("autor", autor);
        map.put("titulo", titulo);

        return this.conn.query(sql, map, mapearLivro()).isEmpty();
    }

    public boolean autorAndTitulo(Long id, String autor, String titulo) {
        String sql = "SELECT * FROM tb_livros WHERE autor = :autor AND titulo = :titulo AND id <> :id";

        Map<String, Object> map = new HashMap<>();
        map.put("autor", autor);
        map.put("titulo", titulo);
        map.put("id", id);

        return this.conn.query(sql, map, mapearLivro()).isEmpty();
    }

    public boolean isTabelaVazia() {
        String sql = "SELECT EXISTS (SELECT 1 FROM tb_livros)";

        return this.conn.query(sql, mapearLivro()).isEmpty();
    }

    private SqlParameterSource parametros(String tipo, Object valor) {
        return new MapSqlParameterSource().addValue(tipo, valor);
    }

    private BeanPropertyRowMapper<Livro> mapearLivro() {
        return new BeanPropertyRowMapper<>(Livro.class);
    }
}