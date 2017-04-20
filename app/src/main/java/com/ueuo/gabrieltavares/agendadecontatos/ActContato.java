package com.ueuo.gabrieltavares.agendadecontatos;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.database.sqlite.*;
import android.widget.ListView;

import com.ueuo.gabrieltavares.agendadecontatos.database.DataBase;
import com.ueuo.gabrieltavares.agendadecontatos.dominio.RepositorioContato;

public class ActContato extends AppCompatActivity implements View.OnClickListener{

    private ImageButton imgBtnCadastrar;

    private DataBase dataBase;
    private SQLiteDatabase conexao;

    private ListView listaContatos;

    private ArrayAdapter<String> adpContatos;
    private RepositorioContato repositorioContato;

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
            conexao = dataBase.getWritableDatabase();
            dlg.setMessage("Banco criado com sucesso");

            repositorioContato = new RepositorioContato(conexao);

            repositorioContato.inserirContatos();

            adpContatos = repositorioContato.buscaContatos(this);

            listaContatos = (ListView) findViewById(R.id.list_contatos);

            listaContatos.setAdapter(adpContatos);

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
