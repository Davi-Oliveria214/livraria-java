package com.livrariaJava.services;

import com.livrariaJava.entity.Generos;
import com.livrariaJava.entity.Livro;
import com.livrariaJava.entity.enums.GenerosEnum;
import com.livrariaJava.exception.BuscaVazia;
import com.livrariaJava.exception.LivroExcecao;
import com.livrariaJava.interfaces.LivroServiceInterface;
import com.livrariaJava.repository.LivroRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

@Service
public class LivroService implements LivroServiceInterface {
    private final LivroRepository repository;

    public LivroService(LivroRepository livroRepository) {
        this.repository = livroRepository;
    }

    @Override
    public Livro cadastrarLivro(Livro livro) {
        if (!this.repository.isIsbn(livro.getIsbn()))
            throw new LivroExcecao("Essa ISBN já está cadastrada", HttpStatus.CONFLICT);

        if (!this.repository.autorAndTitulo(livro.getAutor(), livro.getTitulo()))
            throw new LivroExcecao("Esse titulo: " + livro.getTitulo() + ", desse autor: " + livro.getAutor() + ", já está cadastrado",
                    HttpStatus.CONFLICT);

        return this.repository.cadastrarLivro(livro);
    }

    @Override
    public String deletarLivro(Long id) {
        Livro livro = this.buscarId(id);

        this.repository.deletarLivro(id);
        return "Livro: " + livro.getTitulo() + " do autor " + livro.getAutor() + " deletado com sucesso";
    }

    @Override
    public List<Livro> todosLivros(int limit, int off) {
        this.isLivros();

        return this.repository.todosLivros(limit, off);
    }

    @Override
    public List<Livro> historicoLivro(boolean ordem, int limit, int off) {
        this.isLivros();

        return this.repository.historicoLivro(ordem, limit, off);
    }

    @Override
    public Livro buscarId(Long id) {
        this.isLivros();

        return Optional.ofNullable(this.repository.porId(id)).orElseThrow(() -> new BuscaVazia("Nenhum livro encontrado com o ID: " + id));
    }

    @Override
    public List<Livro> filtroLivro(String filtro, String valor) {
        this.isLivros();

        return switch (filtro) {
            case "titulo" -> validarRetorno(this.repository.porTitulo(valor));
            case "autor" -> validarRetorno(this.repository.porAutor(valor));
            case "isbn" -> validarRetorno(this.repository.porIsbn(valor));
            case "preco" -> validarRetorno(this.repository.porPreco(Double.parseDouble(valor)));
            case "lancamento" -> validarRetorno(this.repository.porLancamento(anoInicioFim(valor)));
            case "genero" -> validarRetorno(this.repository.porGeneros(validarGenero(valor)));
            default -> throw new BuscaVazia("Opção de busca inválida ou não existe: " + filtro);
        };
    }

    @Override
    public Livro atualizarLivro(Long id, String tabela, String novoValor) {
        this.isLivros();
        this.buscarId(id);

        return switch (tabela) {
            case "lancamento" -> this.repository.atualizarLivro(id, tabela, LocalDate.parse(novoValor));
            case "preco" -> this.repository.atualizarLivro(id, tabela, Double.parseDouble(novoValor));
            case "estoque" -> this.repository.atualizarLivro(id, tabela, Integer.parseInt(novoValor));
            case "genero" -> this.repository.atualizarLivro(id, tabela, validarGenero(novoValor));
            default -> this.repository.atualizarLivro(id, tabela, novoValor);
        };
    }

    public List<Generos> todosGeneros(int limit, int off) {
        return this.repository.todosGeneros(limit, off);
    }

    private List<Livro> validarRetorno(List<Livro> livros) {
        if (livros.isEmpty())
            throw new BuscaVazia("Nanhum livro encontrado com");

        return livros;
    }

    private String validarGenero(String genero) {
        GenerosEnum g = GenerosEnum.buscarGenero(genero);

        if (g == null)
            throw new BuscaVazia("Gênero de livro não disponível", HttpStatus.BAD_REQUEST);

        return g.getCodigo();
    }

    private void isLivros() {
        if (this.repository.isTabelaVazia())
            throw new BuscaVazia("Nenhum livro cadastrado");
    }

    private List<LocalDate> anoInicioFim(String valor) {
        DateTimeFormatter formatar = new DateTimeFormatterBuilder()
                .appendPattern("yyyy")
                .parseDefaulting(ChronoField.MONTH_OF_YEAR, 1)
                .parseDefaulting(ChronoField.DAY_OF_MONTH, 1)
                .toFormatter();

        List<LocalDate> datas = new ArrayList<>(2);
        datas.add(LocalDate.parse(valor, formatar));
        datas.add(datas.get(0).withMonth(12).withDayOfMonth(31));

        return datas;
    }
}