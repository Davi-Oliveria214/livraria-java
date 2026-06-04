package com.livrariaJava.infra;

import java.util.LinkedHashMap;
import java.util.Map;

public class MessageErrorGlobal {
    private final Map<String, Object> body;

    public MessageErrorGlobal() {
        this.body = new LinkedHashMap<>();
    }

    public Map<String, Object> getBody() {
        return body;
    }

    public void mensagem(String tipo, Object item){
        body.put(tipo, item);
    }
}