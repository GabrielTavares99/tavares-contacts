package com.ueuo.gabrieltavares.agendadecontatos.dominio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;

import com.ueuo.gabrieltavares.agendadecontatos.ContatoArrayAdapter;
import com.ueuo.gabrieltavares.agendadecontatos.R;
import com.ueuo.gabrieltavares.agendadecontatos.dominio.entidades.Contato;

import java.util.Date;

/**
 * Created by gabri on 20/04/2017.
 */

public class DaoContato {

    private SQLiteDatabase conn;

    ContentValues contentValues;

    public DaoContato(SQLiteDatabase conn){
        this.conn = conn;
    }

    //CONSTANTE AO NOME DA TABELA
    private static final String NOME_TABELA = "tb_contato";
    private static final String CAMPO_ID = " _id = ?";
    private static final String CAMPO_NOME = "nome";
    private static final String CAMPO_TELEFONE = "telefone";
    private static final String CAMPO_TIPO_TELEFONE = "tipoTelefone";
    private static final String CAMPO_EMAIL = "email";
    private static final String CAMPO_TIPO_EMAIL = "tipoEmail";
    private static final String CAMPO_ENDERECO = "endereco";
    private static final String CAMPO_TIPO_ENDERECO = "tipoEndereco";
    private static final String CAMPO_DATA_ESPECIAL = "dataEspecial";
    private static final String CAMPO_TIPO_DATA_ESPECIAL= "tipoDataEspecial";
    private static final String CAMPO_GRUPO = "grupo";
    private static final String CAMPO_CAMINHO_FOTO = "caminhoFoto";

    //MÉTODO QUE PREENCHE O VALUES
    public ContentValues preencherValues(Contato contato){

        contentValues = new ContentValues();

        contentValues.put(CAMPO_NOME, contato.getNome());

        contentValues.put(CAMPO_TELEFONE, contato.getTelefone());
        contentValues.put(CAMPO_TIPO_TELEFONE, contato.getTipoTelefone());

        contentValues.put(CAMPO_EMAIL, contato.getEmail());
        contentValues.put(CAMPO_TIPO_EMAIL, contato.getTipoEmail());

        contentValues.put(CAMPO_ENDERECO, contato.getEndereco());
        contentValues.put(CAMPO_TIPO_ENDERECO, contato.getTipoEndereco());

        //Usar get time pra gerar um long que corresponde a data
        contentValues.put(CAMPO_TIPO_DATA_ESPECIAL, contato.getDataEspecial().getTime());
        contentValues.put(CAMPO_DATA_ESPECIAL, contato.getTipoDataEspecial());

        contentValues.put(CAMPO_GRUPO, contato.getGrupo());

        contentValues.put(CAMPO_CAMINHO_FOTO, contato.getCaminhoFoto());

        return contentValues;
    }

    public boolean excluir(long idContato){

        String idWhere [] = new String[]{String.valueOf(idContato)};

        conn.delete(NOME_TABELA,CAMPO_ID,idWhere);

        return true;
    }

    public boolean alterar(Contato contato){

        contentValues = preencherValues(contato);

        //ARGUMENTOS - TABELA - VALUES - CAMPOS DO WHERE, EXEMPLO
        // "_ID = ? AND nome = ?" - VETOR COM OS PARAMETROS DO WHERE
        String argumentosWhere[] = new String[]{String.valueOf(contato.getId())};

        conn.update(NOME_TABELA, contentValues,CAMPO_ID, argumentosWhere);

        return true;
    }

    public void inserir(Contato contato){

        contentValues = preencherValues(contato);

        conn.insertOrThrow(NOME_TABELA, null, contentValues);

    }

    public ContatoArrayAdapter buscarContatos(Context context){
        //Só me permite exibir textos simples
        //ArrayAdapter<Contato> adpContatos = new ArrayAdapter<Contato>(context, android.R.layout.simple_list_item_1);

        //IMPLEMENTANDO UM ARRAYADAPTER PERSONALIZADO
        ContatoArrayAdapter adpContatos = new ContatoArrayAdapter(context, R.layout.activity_act_linha_contato);

        // Realiza a busca por meio da tabela e campos como parametros
        Cursor cursor = conn.query(NOME_TABELA, null,null,null,null,null,CAMPO_NOME+ " ASC");

        // Responsavel por armazenar registros
        //Cursor

        //Vericiar se retornou dado
        if (cursor.getCount() > 0){
            //Estar na primera posição
           cursor.moveToFirst();
            do {
                //cursor.getColumnIndex("nome")

                Contato contato =  new Contato();

                contato.setId(Long.valueOf(cursor.getString(0)));
                contato.setNome(cursor.getString(1));

                contato.setTelefone(cursor.getString(2));
                contato.setTipoTelefone(cursor.getInt(3));

                contato.setEmail(cursor.getString(4));
                contato.setTipoEmail(cursor.getInt(5));

                contato.setEndereco(cursor.getString(6));
                contato.setTipoEndereco(cursor.getInt(7));

                contato.setDataEspecial(new Date(cursor.getLong(8)));
                contato.setTipoDataEspecial(cursor.getInt(9));

                contato.setGrupo(cursor.getString(10));

                contato.setCaminhoFoto(cursor.getString(11));

                adpContatos.add(contato);

                //Repete até ter dados no cursos
            }while (cursor.moveToNext());

        }


        return adpContatos;
    };

}
