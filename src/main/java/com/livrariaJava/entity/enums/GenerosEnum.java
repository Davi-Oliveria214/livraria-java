package com.livrariaJava.entity.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.livrariaJava.entity.Generos;

public enum GenerosEnum {
    INDEFINIDO("ptI10", "indefinido"),
    ACAO("ptA04", "ação"),
    ROMANCE("ptR07", "romance"),
    DRAMA("ptD05", "drama"),
    SUSPENSE("ptS08", "suspense"),
    TERROR("ptT06", "terror"),
    AVENTURA("ptA08", "aventura"),
    COMEDIA("ptC07", "comédia"),
    MISTERIO("ptM08", "mistério");

    private final String codigo;
    private final String genero;

    GenerosEnum(String codigo, String genero) {
        this.codigo = codigo;
        this.genero = genero;
    }

    @JsonCreator
    public static GenerosEnum paraString(String codigo) {
        for (GenerosEnum g : GenerosEnum.values()) {
            if (g.codigo.equalsIgnoreCase(codigo) || g.name().equalsIgnoreCase(codigo)) {
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
        return codigo;
    }
}