package com.ueuo.gabrieltavares.agendadecontatos.dominio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;

/**
 * Created by gabri on 20/04/2017.
 */

public class RepositorioContato {

    private SQLiteDatabase conn;

    public RepositorioContato(SQLiteDatabase conn){
        this.conn = conn;
    }

    public void inserirContatos(){

        // salva dados para ser cadastrados
        ContentValues values = new ContentValues();
        values.put("telefone","25557164");
        for (int i =0; i < 10; i++){
            //Cadastra se possivel ou diz se deu errado
            conn.insertOrThrow("tb_contato",null, values);

        }

    }

    public ArrayAdapter<String> buscaContatos( Context context){
        ArrayAdapter<String> adpContatos = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1);

        // Realiza a busca por meio da tabela e campos como parametros
        Cursor cursor = conn.query("tb_contato", null,null,null,null,null,null);

        // Responsavel por armazenar registros
        //Cursor

        //Vericiar se retornou dado
        if (cursor.getCount() > 0){
            //Estar na primera posição
           cursor.moveToFirst();
            do {
                String telefone = cursor.getString(1);
                adpContatos.add(telefone);
                //Repete até ter dados no cursos
            }while (cursor.moveToNext());

        }

        return adpContatos;
    };

}
