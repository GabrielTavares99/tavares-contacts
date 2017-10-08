package com.ueuo.gabrieltavares.agendadecontatos.Converter;

import com.ueuo.gabrieltavares.agendadecontatos.dominio.entidades.Contato;

import org.json.JSONException;
import org.json.JSONStringer;

import java.util.List;

/**
 * Created by gabri on 14/10/2017.
 */

public class ContatoConverter {
    public String converterToJson(List<Contato> contatos) {

        String  json = null;

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
