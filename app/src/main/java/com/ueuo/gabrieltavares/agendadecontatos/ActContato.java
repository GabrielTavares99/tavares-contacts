package com.ueuo.gabrieltavares.agendadecontatos;

import android.content.Intent;
import android.icu.util.ULocale;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.database.sqlite.*;
import android.database.*;

import java.sql.SQLData;

public class ActContato extends AppCompatActivity implements View.OnClickListener{

    private ImageButton imgBtnCadastrar;

    private DataBase dataBase;
    private SQLiteDatabase conexao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_contato);
        imgBtnCadastrar = (ImageButton) findViewById(R.id.imgBtn_cadastrar);

        imgBtnCadastrar.setOnClickListener(this);

        AlertDialog.Builder dlg = new AlertDialog.Builder(this);
        dlg.setNeutralButton("OK",null);

        try {
            dataBase = new DataBase(this);
            conexao = dataBase.getReadableDatabase();
            dlg.setMessage("Banco criado com sucesso");
        } catch (Exception e){
            dlg.setMessage("Erro ao criar banco!"+e.getMessage());
        }
        dlg.show();

    }


    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.imgBtn_cadastrar:
                Intent intent = new Intent(this,act_cadastroContato.class);
                startActivity(intent);
                break;
            default:
                break;
        }

    }
}
