package com.livrariaJava.services;

import com.livrariaJava.entity.Generos;
import com.livrariaJava.entity.Livro;
import com.livrariaJava.exception.BuscaVazia;
import com.livrariaJava.exception.LivroExcecao;
import com.livrariaJava.interfaces.LivroServiceInterface;
import com.livrariaJava.repository.GenerosRepository;
import com.livrariaJava.repository.LivroRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.beans.PropertyDescriptor;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.*;

@Service
public class LivroService implements LivroServiceInterface {
    private final LivroRepository repository;
    @Autowired
    private GenerosRepository generosRepository;

    public LivroService(LivroRepository livroRepository) {
        this.repository = livroRepository;
    }

    @Override
    public Livro cadastrarLivro(Livro livro) {
        if (!this.repository.findByIsbn(livro.getIsbn()).isEmpty())
            throw new LivroExcecao("Essa ISBN já está cadastrada", HttpStatus.CONFLICT);

        if (!this.repository.findByAutorAndTitulo(livro.getAutor(), livro.getTitulo()).isEmpty())
            throw new LivroExcecao("Esse titulo: " + livro.getTitulo() + ", desse autor: " + livro.getAutor() + ", já está cadastrado",
                    HttpStatus.CONFLICT);

        return this.repository.save(livro);
    }

    @Override
    public Object deletarLivro(Long id) {
        Livro livro = this.buscarId(id);

        this.repository.deleteById(id);
        Map<String, Object> map = new HashMap<>();
        map.put("status", HttpStatus.OK.value());
        map.put("message", livro.getTitulo() + " do autor " + livro.getAutor() + " deletado com sucesso");
        return map;
    }

    @Override
    public List<Livro> todosLivros() {
        return this.repository.findAll();
    }

    @Override
    public List<Livro> historicoLivro(boolean ordem) {
        return ordem ? this.repository.findAllByOrderByCriadoDesc() : this.repository.findAllByOrderByCriadoAsc();
    }

    @Override
    public Livro buscarId(Long id) {
        return this.repository.findById(id).orElseThrow(BuscaVazia::new);
    }

    @Override
    public List<Livro> filtroLivro(String filtro, String valor) {
        return switch (filtro) {
            case "titulo" -> this.repository.findByTituloContainingIgnoreCase(valor);
            case "autor" -> this.repository.findByAutorContainingIgnoreCase(valor);
            case "isbn" -> this.repository.findByIsbn(valor);
            case "preco" -> {
                Double[] margem = margemDePreco(valor);
                yield this.repository.findPreco(margem[0], margem[1]);
            }
            case "lancamento" -> {
                List<LocalDate> data = anoInicioFim(valor);
                yield this.repository.findLancamento(data.get(0), data.get(1));
            }
            case "genero" -> this.repository.findByGenero(valor);
            default -> throw new BuscaVazia("Opção de busca inválida ou não existe: " + filtro);
        };
    }

    @Override
    public Livro atualizarLivro(Long id, Livro novoValor) {
        Livro livro = this.buscarId(id);

        if (novoValor.getAutor() != null && !this.repository.findByAutorAndTitulo(novoValor.getAutor(), livro.getTitulo()).isEmpty())
            throw new LivroExcecao("Esse titulo: " + livro.getTitulo() + ", desse autor: " + livro.getAutor() + ", já está cadastrado",
                    HttpStatus.CONFLICT);

        if (novoValor.getTitulo() != null && !this.repository.findByAutorAndTitulo(livro.getAutor(), novoValor.getTitulo()).isEmpty())
            throw new LivroExcecao("Esse titulo: " + livro.getTitulo() + ", desse autor: " + livro.getAutor() + ", já está cadastrado",
                    HttpStatus.CONFLICT);

        BeanUtils.copyProperties(novoValor, livro, getCamposIgnorados(novoValor));
        return this.repository.save(livro);
    }

    public List<Generos> todosGeneros() {
        return this.generosRepository.findAll();
    }

    private Double[] margemDePreco(String valor) {
        Double preco = Double.parseDouble(valor);
        Double margem = (preco / 100) * 10;

        double min = preco - margem;
        double max = preco + margem;

        return new Double[]{min, max};
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

    private String[] getCamposIgnorados(Object source) {
        BeanWrapper src = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> camposIgnorados = new HashSet<>();
        camposIgnorados.add("id");

        for (PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) {
                camposIgnorados.add(pd.getName());
            }
        }

        return camposIgnorados.toArray(new String[0]);
    }
}