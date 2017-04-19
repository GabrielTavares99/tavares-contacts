package com.ueuo.gabrieltavares.agendadecontatos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class act_cadastroContato extends AppCompatActivity {

    private Spinner spin_tipoTelefone,spin_tipoEmail,spin_tipoEndereco,spin_tipoDataEspecial,spin_tipoGrupo;
    private EditText txt_nome,txt_telefone,txt_email,txt_endereco,dataEspecial,txt_grupo;

    private ArrayAdapter<String> adpTelefone,adpEmail,adpEndereco,adpDataEspecial,adpGrupo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_cadastro_contato);

        spin_tipoDataEspecial = (Spinner) findViewById(R.id.spin_tipoDataEspecial);
        spin_tipoEmail = (Spinner) findViewById(R.id.spin_tipoEmail);
        spin_tipoEndereco = (Spinner) findViewById(R.id.spin_tipoEndereco);
        spin_tipoGrupo = (Spinner) findViewById(R.id.spin_tipoGrupo);
        spin_tipoTelefone = (Spinner) findViewById(R.id.spin_tipoTelefone);

        txt_email = (EditText) findViewById(R.id.txt_email);
        txt_endereco = (EditText) findViewById(R.id.txt_endereco);
        txt_grupo = (EditText) findViewById(R.id.txt_grupo);
        txt_nome = (EditText) findViewById(R.id.txt_Nome);
        txt_telefone = (EditText) findViewById(R.id.txt_numeroTelefone);

        //Dou a forma/layout para o spinner
       // adpOpcoes = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        // Como vai ficar quando o spinner é clicado
       // adpOpcoes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Relacionando o spinner com o arrayAdapter
      //  spn_Opcoes.setAdapter(adpOpcoes);
       // adpOpcoes.add("1 unidade");

        //Criando um adaptador de string para colocar no meu "combobox"
        adpEmail = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item);
        adpEmail.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_tipoEmail.setAdapter(adpEmail);
        adpEmail.add("Pessoal");
        adpEmail.add("Comercial");

        adpTelefone = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        adpTelefone.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_tipoTelefone.setAdapter(adpTelefone);
        adpTelefone.add("Fax");
        adpTelefone.add("Celular");

        adpDataEspecial = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        adpDataEspecial.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_tipoDataEspecial.setAdapter(adpDataEspecial);
        adpDataEspecial.add("Aniversário");
        adpDataEspecial.add("Nascimento");

        adpEndereco = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        adpEndereco.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_tipoEndereco.setAdapter(adpTelefone);
        adpEndereco.add("Casa");
        adpEndereco.add("Trabalho");

    }

    //Associando menu ao activity


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflate = getMenuInflater();
        inflate.inflate(R.menu.menu_act_cadastro_contato,menu);
        return super.onCreateOptionsMenu(menu);
    }

    //Vai verificar qual item foi selecionado
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){

            case R.id.item_menu_salvar:
                break;
            case R.id.item_menu_excluir:
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}
