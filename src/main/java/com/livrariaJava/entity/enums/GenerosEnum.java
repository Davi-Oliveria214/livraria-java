package com.livrariaJava.entity.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Generos {
    INDEFINIDO("indefinido"),
    ACAO("acao", "ação"),
    ROMANCE("romance"),
    DRAMA("drama"),
    SUSPENSE("suspense"),
    TERROR("terror"),
    AVENTURA("aventura"),
    COMEDIA("comedia", "comédia"),
    MISTERIO("misterio", "mistério");

    private final String valorBanco;
    private final String genero;

    Generos(String genero) {
        this.valorBanco = genero;
        this.genero = genero;
    }

    Generos(String valorBanco, String genero) {
        this.valorBanco = valorBanco;
        this.genero = genero;
    }

    @JsonCreator
    public static Generos paraString(String valor) {
        for (Generos g : Generos.values()) {
            if (g.valorBanco.equalsIgnoreCase(valor) || g.name().equalsIgnoreCase(valor)) {
                return g;
            }
        }

        return null;
    }

    @JsonValue
    public String getGenero() {
        return genero;
    }

    public String getValorBanco() {
        return valorBanco;
    }
}