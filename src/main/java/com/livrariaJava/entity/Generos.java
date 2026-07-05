package com.livrariaJava.entity;

import com.livrariaJava.entity.enums.GenerosEnum;

public class Generos {
    private String codigo;
    private GenerosEnum genero;

    public Generos() {
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo() {
        this.codigo = genero.getCodigo();
    }

    public String getGenero() {
        return genero.getGenero();
    }

    public void setGenero(String codigo) {
        this.genero = GenerosEnum.buscarGenero(codigo);
    }
}