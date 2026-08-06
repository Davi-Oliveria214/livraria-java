package com.livrariaJava.repository;

import com.livrariaJava.entity.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
    List<Livro> findByIsbn(String isbn);

    List<Livro> findByAutorAndTitulo(String autor, String titulo);

    List<Livro> findByTituloContainingIgnoreCase(String titulo);

    List<Livro> findByAutorContainingIgnoreCase(String autor);

    List<Livro> findByGenero(@Param("genero") String genero);

    @Query(value = "SELECT * FROM tb_livros WHERE preco BETWEEN :min AND :max;", nativeQuery = true)
    List<Livro> findPreco(@Param("min") Double min, @Param("max") Double max);

    @Query(value = "SELECT * FROM tb_livros WHERE lancamento BETWEEN :anoInicio AND :anoFim;", nativeQuery = true)
    List<Livro> findLancamento(@Param("anoInicio") LocalDate anoInicio, @Param("anoFim") LocalDate anoFim);

    List<Livro> findAllByOrderByCriadoDesc();

    List<Livro> findAllByOrderByCriadoAsc();
}