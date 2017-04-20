package com.ueuo.gabrieltavares.agendadecontatos.database;

/**
 * Created by gabri on 17/04/2017.
 */

import android.content.Context;
import android.database.sqlite.*;

public class DataBase extends SQLiteOpenHelper{


    //Utilizar quatro parâmetros
    public DataBase(Context context){
        //Contrutor da classe abstrata pai
        super(context,"db_tavares_agenda",null,1);

        //Obs. Se mudar o banco, mudar o numero da versar(ultimo parametro)
    }

    //RESPONSÁVEL POR CRIAR AS TABELAS
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ScriptSql.getCreateContato());
    }

    //REALIZAR ALTERAÇÃO NAS TABELAS
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
