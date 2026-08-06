package com.livrariaJava.entity;

import com.livrariaJava.entity.enums.GenerosEnum;
import jakarta.persistence.*;

@Table(name = "generos")
@Entity
public class Generos {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private String codigo;

    public Generos() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        GenerosEnum g = GenerosEnum.buscarGenero(nome);
        return g != null ? g.getGenero() : GenerosEnum.INDEFINIDO.getGenero();
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}