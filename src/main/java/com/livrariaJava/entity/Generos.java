package com.livrariaJava.entity;

import com.livrariaJava.entity.enums.GenerosEnum;

public class Generos {
    private GenerosEnum genero;

    public Generos() {
    }

    public String getNome() {
        return genero.getGenero();
    }

    public String getGenero() {
        return genero.getCodigo();
    }

    public void setCodigo(String genero) {
        this.genero = GenerosEnum.buscarGenero(genero);
    }
}