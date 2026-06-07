package com.livrariaJava.services;

import com.livrariaJava.entity.Livro;
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
import java.util.Objects;
import java.util.Optional;

@Service
public class LivroService implements LivroServiceInterface {
    private final LivroRepository repository;

    public LivroService(LivroRepository livroRepository) {
        this.repository = livroRepository;
    }

    @Override
    public Livro criarLivro(Livro livro) {
        Optional.ofNullable(this.repository.buscarExataIsbn(livro.getIsbn())).ifPresent(l -> {
            throw new LivroExcecao("Essa ISBN já está cadastrada", HttpStatus.CONFLICT);
        });

        for (Livro l : this.repository.buscarTitulo(livro.getTitulo())) {
            if (Objects.equals(l.getTitulo(), livro.getTitulo()) && Objects.equals(l.getAutor(), livro.getAutor())) {
                throw new LivroExcecao("Esse titulo: " + livro.getTitulo() + ", desse autor: " + livro.getAutor() + ", já está cadastrado",
                        HttpStatus.CONFLICT);
            }
        }

        return this.repository.newLivro(livro);
    }

    @Override
    public String delLivro(Long id) {
        Livro livro = this.buscarId(id);

        this.repository.delLivro(id);
        return "Livro: " + livro.getTitulo() + " do autor " + livro.getAutor() + " deletado com sucesso";
    }

    @Override
    public List<Livro> getLivros() {
        this.verificar();

        return this.repository.todosLivros();
    }

    @Override
    public List<Livro> historico() {
        this.verificar();

        return this.repository.historicoLivro();
    }

    @Override
    public Livro buscarId(Long id) {
        return Optional.ofNullable(this.repository.buscarId(id)).orElseThrow(() -> new BuscaVazia("Nenhum livro encontrado com o ID: " + id));
    }

    @Override
    public List<Livro> busca(String filtro, String valor) {
        return switch (filtro) {
            case "titulo" -> Optional.ofNullable(this.repository.buscarTitulo(valor))
                    .filter(l -> !l.isEmpty())
                    .orElseThrow(BuscaVazia::new);
            case "autor" -> Optional.ofNullable(this.repository.buscarAutor(valor))
                    .filter(l -> !l.isEmpty())
                    .orElseThrow(BuscaVazia::new);
            case "isbn" -> Optional.ofNullable(this.repository.buscarIsbn(Integer.parseInt(valor)))
                    .filter(l -> !l.isEmpty())
                    .orElseThrow(BuscaVazia::new);
            case "preco" -> Optional.ofNullable(this.repository.buscarPreco(Double.parseDouble(valor)))
                    .filter(l -> !l.isEmpty())
                    .orElseThrow(BuscaVazia::new);
            case "lancamento" -> Optional.ofNullable(this.repository.buscarLancamento(converter(valor)))
                    .filter(l -> !l.isEmpty())
                    .orElseThrow(BuscaVazia::new);
            default -> throw new BuscaVazia("Opção de busca inválida ou não existe: " + filtro);
        };
    }

    @Override
    public Livro altTitulo(Long id, String novoTitulo) {
        this.verificar();

        Livro livro = this.buscarId(id);
        livro.setTitulo(novoTitulo);

        return this.repository.updateLivro(livro);
    }

    @Override
    public Livro altAutor(Long id, String novoAutor) {
        this.verificar();

        Livro livro = this.buscarId(id);
        livro.setAutor(novoAutor);

        return this.repository.updateLivro(livro);
    }

    @Override
    public Livro altPreco(Long id, Double novoPreco) {
        this.verificar();

        Livro livro = this.buscarId(id);
        livro.setPreco(novoPreco);

        return this.repository.updateLivro(livro);
    }

    @Override
    public Livro altEstoque(Long id, Integer novoEstoque) {
        this.verificar();

        Livro livro = this.buscarId(id);
        livro.setEstoque(novoEstoque);

        return this.repository.updateLivro(livro);
    }

    @Override
    public Livro altISBN(Long id, Integer novaISBN) {
        this.verificar();

        Livro livro = this.buscarId(id);
        livro.setIsbn(novaISBN);

        return this.repository.updateLivro(livro);
    }

    @Override
    public Livro altData(Long id, LocalDate novaData) {
        this.verificar();

        Livro livro = this.buscarId(id);
        livro.setLancamento(novaData);

        return this.repository.updateLivro(livro);
    }

    private void verificar() {
        if (this.repository.isTabelaVazia()) {
            throw new BuscaVazia("Nenhum livro cadastrado");
        }
    }

    private List<LocalDate> converter(String valor) {
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