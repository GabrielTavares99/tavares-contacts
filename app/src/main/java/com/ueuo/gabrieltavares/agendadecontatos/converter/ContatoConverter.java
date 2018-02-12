package com.ueuo.gabrieltavares.agendadecontatos.converter;

import com.ueuo.gabrieltavares.agendadecontatos.dominio.entidades.Contato;

import org.json.JSONException;
import org.json.JSONStringer;

import java.util.List;

public class ContatoConverter {
    public String converterToJson(List<Contato> contatos) {

        String  json;

        JSONStringer jsonStringer = new JSONStringer();

        try {
            jsonStringer.object().key("list").array().object().key("aluno").array();

            for (Contato contato: contatos){
                jsonStringer.object();
                jsonStringer.key("nome").value(contato.getNome());
                jsonStringer.key("telefone").value(contato.getTelefone());
                jsonStringer.endObject();
            }
            jsonStringer.endArray().endObject().endArray().endObject();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        json = jsonStringer.toString();

        return json;
    }
}
