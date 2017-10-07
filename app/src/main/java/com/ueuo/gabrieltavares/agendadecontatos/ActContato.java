package com.ueuo.gabrieltavares.agendadecontatos;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.database.sqlite.*;
import android.widget.ListView;
import android.widget.Toast;

import com.ueuo.gabrieltavares.agendadecontatos.app.MessageBox;
import com.ueuo.gabrieltavares.agendadecontatos.database.DataBase;
import com.ueuo.gabrieltavares.agendadecontatos.dominio.DaoContato;
import com.ueuo.gabrieltavares.agendadecontatos.dominio.entidades.Contato;

public class ActContato extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener{

    private Button imgBtnCadastrar;

    private DataBase dataBase;
    private SQLiteDatabase conexao;

    private ListView listaContatos;

    private EditText txt_pesquisa;

    private FiltrarDados filtrarDados;

    private ContatoAdapter adpContatos;
    private DaoContato daoContato;

    private static final String parametro_Contato = "CONTATO";

    MessageBox alertUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_contato);
        imgBtnCadastrar = (Button) findViewById(R.id.imgBtn_cadastrar);

        imgBtnCadastrar.setOnClickListener(this);

        alertUsuario = new MessageBox(this);

        txt_pesquisa = (EditText) findViewById(R.id.txt_pesquisa);

        //Instanciando minha list view
        listaContatos = (ListView) findViewById(R.id.list_contatos);

//        Criando um menu de contexto - menu em cima dos itens da lista
        registerForContextMenu(listaContatos);

        try {
            listaContatos.setOnItemClickListener(this);
        }catch (Exception e){
            alertUsuario.showAlert("Erro!","Erro ao carregar lista de contato: "+e.getMessage());
          //  alertUsuario.show();
        }

        try {
            dataBase = new DataBase(this);
            conexao = dataBase.getWritableDatabase();

            //Mensagem se deu certo a conexão ao banco de dados
            //alertUsuario.showInfo("Sucesso!","Banco criado com sucesso");

            //Instancia do objeto repositorio que possui os metodos de manupulação do banco
            daoContato = new DaoContato(conexao);

            //Dentro do arrayAdapter da lista eu faço a busca na classe
            //que possui os métodos de banco de dados
            //e busco os contatos salvos
            adpContatos = new ContatoAdapter(this, daoContato.getTodosContato());

            conexao.close();
            //Setando o meu arrayAdapter na minha list view
            listaContatos.setAdapter(adpContatos);

            //CLASSE NESTE ARQUIVO, RESPONSÁVEL POR IMPLEMENTAR UM TEXTCHANGEDLISTENER
//            filtrarDados = new FiltrarDados(adpContatos);
            //ADICIONANDO O EVENTO À CAIXA DE TEXTO
            txt_pesquisa.addTextChangedListener(filtrarDados);

        } catch (Exception e){
            //Caso dê erro na excução da conexao ao banco
            alertUsuario.showAlert("Erro!","Erro ao criar banco!"+e.getMessage());
        }

    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.imgBtn_cadastrar:

                Intent intent = new Intent(this,act_cadastroContato.class);
                startActivityForResult(intent,0);
                //startActivity(intent);
                break;
            default:
                break;
        }

    }

    //Método que trata os retorno de outras activitys
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //Dentro do arrayAdapter da lista eu faço a busca na classe
        //que possui os métodos de banco de dados
        //e busco os contatos salvos
        dataBase = new DataBase(this);
        conexao = dataBase.getWritableDatabase();
        DaoContato daoContato = new DaoContato(conexao);
        adpContatos = new ContatoAdapter(this, daoContato.getTodosContato());
        conexao.close();
        //Setando o meu arrayAdapter na minha list view
        listaContatos.setAdapter(adpContatos);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

//        //RECUPERANDO MEU OBJETO CONTATO SELECIONADO
//        Contato contato = adpContatos.getItem(position);
//
//        //INSTANCIO MINHA INTENT
//        Intent intent = new Intent(this,act_cadastroContato.class);
//
//        //PASSAGEM DO OBJETO CONTATO ENTRE TELAS
//        //É PRECISO IMPLEMENTAR SERIALIZABLE NA CLASSE DO OBJETO A INTERFACE PRA TRANSPOSIÇÃO DE OBJETOS
//        intent.putExtra(parametro_Contato,contato);
//
//        //ABRO A SEGUNDA ACTIVITY
//        startActivityForResult(intent,0);

        Contato contato = (Contato) listaContatos.getItemAtPosition(position);
        Toast.makeText(ActContato.this, "Contato: " + contato.getNome(), Toast.LENGTH_LONG).show();
    }

    //Classe para implementação do listener de texto
    private class FiltrarDados implements TextWatcher{

       // ContatoArrayAdapter arrayAdapterContato;

        private FiltrarDados(ContatoArrayAdapter arrayAdapterContato){
          //  this.arrayAdapterContato = arrayAdapterContato;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            //O PRÓPRIO ARRAYADAPTER TEM UMA FUNÇÃO DE FILTRAGEM
            //O PARÁMETRO DE FILTO SÃO OS CAMPOS DO MÉTODO .TOSTRING()
//            adpContatos.getFilter().filter(s);

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }


//    Implemenando os listener de toque prolongado nos itens da lista
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {

//        if(!contato.site().startWith("http://"))
//            site = "http://"

        final Contato contato = (Contato) listaContatos.getItemAtPosition(((AdapterView.AdapterContextMenuInfo) menuInfo).position);
        MenuItem detalheItem = menu.add("Editar");

//      Ligação
        MenuItem ligarItem = menu.add("Ligar");
        ligarItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(ActivityCompat.checkSelfPermission(ActContato.this, Manifest.permission.CALL_PHONE)
                        != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(ActContato.this, new String[]{Manifest.permission.CALL_PHONE}, 123);
                }else {
                     Intent ligarIntent = new Intent(Intent.ACTION_CALL);
                    ligarIntent.setData(Uri.parse("tel:" + contato.getTelefone()));
                    startActivity(ligarIntent);
                }
                return false;
            }
        });


//      SMS
        MenuItem smsItem = menu.add("Enviar SMS");
        Intent smsIntent= new Intent(Intent.ACTION_VIEW);
        smsIntent.setData(Uri.parse("sms:"+contato.getTelefone()));
        smsItem.setIntent(smsIntent);

//        LOCALIZACAO
        MenuItem localizacaoItem = menu.add("Visualizar posição");
        Intent localizacaoIntent = new Intent(Intent.ACTION_VIEW);
        localizacaoIntent.setData(Uri.parse("geo:0,0?q="+contato.getEndereco()));
        localizacaoItem.setIntent(localizacaoIntent);

//         PERFIL FACEBOOK
        MenuItem perfilFacebookItem = menu.add("Ir perfil");
//        Intent siteIntent = new Intent(ActContato.this, Browser.class);
//        Falo pra intente que quero abrir um recurso
        Intent siteIntent = new Intent(Intent.ACTION_VIEW);
        siteIntent.setData(Uri.parse("https://www.facebook.com/search/top/?q="+contato.getNome()+"&init=public"));
        perfilFacebookItem.setIntent(siteIntent);

        detalheItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;

                //RECUPERANDO MEU OBJETO CONTATO SELECIONADO
                Contato contato = (Contato) adpContatos.getItem(((AdapterView.AdapterContextMenuInfo) menuInfo).position);

                //INSTANCIO MINHA INTENT
                Intent intent = new Intent(ActContato.this, act_cadastroContato.class);

                //PASSAGEM DO OBJETO CONTATO ENTRE TELAS
                //É PRECISO IMPLEMENTAR SERIALIZABLE NA CLASSE DO OBJETO A INTERFACE PRA TRANSPOSIÇÃO DE OBJETOS
                intent.putExtra(parametro_Contato, contato);

                //ABRO A SEGUNDA ACTIVITY
                startActivityForResult(intent,0);
//                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
//                Contato contato = (Contato) listaContatos.getItemAtPosition(info.position);
//                Toast.makeText(ActContato.this, "Contato: " + contato.getNome(), Toast.LENGTH_LONG).show();
                return false;
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (conexao != null){
            conexao.close();
        }
    }

}

