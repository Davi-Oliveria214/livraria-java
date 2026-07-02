package com.livrariaJava.repository;

import com.livrariaJava.entity.Generos;
import com.livrariaJava.entity.Livro;
import com.livrariaJava.entity.enums.GenerosEnum;
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

    public Livro newLivro(Livro livro) {
        String sql = "INSERT INTO tb_livros(titulo, autor, isbn, preco, genero, estoque, lancamento) VALUES (:titulo, :autor, :isbn, :preco, :genero, :estoque, :lancamento)";

        KeyHolder key = new GeneratedKeyHolder();
        SqlParameterSource params = new BeanPropertySqlParameterSource(livro);

        this.conn.update(sql, params, key);
        return livro;
    }

    public void delLivro(Long id) {
        String sql = "DELETE FROM tb_livros WHERE id = :id";

        Map<String, Object> map = new HashMap<>();
        map.put("id", id);

        this.conn.update(sql, map);
    }

    public Livro updateLivro(Livro livro) {
        String sql = "UPDATE tb_livros SET titulo = :titulo, autor = :autor, isbn = :isbn, preco = :preco, genero = :genero, estoque = :estoque, lancamento = :lancamento WHERE id = :id";

        this.conn.update(sql, new BeanPropertySqlParameterSource(livro));

        return livro;
    }

    public List<Livro> historicoLivro() {
        String sql = "SELECT * FROM tb_livros ORDER BY criado_em DESC";

        return this.conn.query(sql, mapear());
    }

    public List<Livro> todosLivros() {
        String sql = "SELECT * FROM tb_livros";

        return this.conn.query(sql, mapear());
    }

    public Livro buscarId(Number id) {
        String sql = "SELECT * FROM tb_livros WHERE id = :id";

        List<Livro> l = this.conn.query(sql, params("id", id), mapear());

        return (!l.isEmpty()) ? l.get(0) : null;
    }

    public List<Livro> buscarIsbn(String isbn) {
        String sql = "SELECT * FROM tb_livros WHERE isbn::text LIKE CONCAT('%', :isbn, '%')";

        return this.conn.query(sql, params("isbn", isbn), mapear());
    }

    public List<Livro> buscarTitulo(String titulo) {
        String sql = "SELECT * FROM tb_livros WHERE LOWER(titulo) LIKE LOWER(CONCAT('%', :titulo, '%'))";

        return this.conn.query(sql, params("titulo", titulo), mapear());
    }

    public List<Livro> buscarAutor(String autor) {
        String sql = "SELECT * FROM tb_livros WHERE LOWER(autor) LIKE LOWER(CONCAT('%', :autor, '%'))";

        return this.conn.query(sql, params("autor", autor), mapear());
    }

    public List<Livro> buscarLancamento(List<LocalDate> datas) {
        String sql = "SELECT * FROM tb_livros WHERE lancamento BETWEEN :inicio AND :fim";

        Map<String, Object> map = new HashMap<>();
        map.put("inicio", datas.get(0));
        map.put("fim", datas.get(1));
        return this.conn.query(sql, map, mapear());
    }

    public List<Livro> buscarPreco(Double preco) {
        double margem = preco * 0.15;
        double minPreco = preco - margem;
        double maxPreco = preco + margem;
        String sql = "SELECT * FROM tb_livros WHERE preco BETWEEN :min AND :max";

        Map<String, Object> map = new HashMap<>();
        map.put("min", minPreco);
        map.put("max", maxPreco);
        return this.conn.query(sql, map, mapear());
    }

    public List<Livro> porGeneros(String valor) {
        String sql = "SELECT * FROM tb_livros WHERE genero = :g";

        return this.conn.query(sql, params("g", valor), mapear());
    }

    public Livro isIsbn(String isbn){
        String sql = "SELECT * FROM tb_livros WHERE isbn = :isbn";
        return this.conn.queryForObject(sql, params("isbn", isbn), mapear());
    }

    public boolean isTabelaVazia() {
        String sql = "SELECT EXISTS (SELECT 1 FROM tb_livros)";

        return this.conn.query(sql, mapear()).isEmpty();
    }

    private SqlParameterSource params(String tipo, Object valor) {
        return new MapSqlParameterSource().addValue(tipo, valor);
    }

    private BeanPropertyRowMapper<Livro> mapear() {
        return new BeanPropertyRowMapper<>(Livro.class);
    }
}