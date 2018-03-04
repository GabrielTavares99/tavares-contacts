package com.ueuo.gabrieltavares.agendadecontatos.view;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.ueuo.gabrieltavares.agendadecontatos.R;
import com.ueuo.gabrieltavares.agendadecontatos.adapter.ContatoAdapter;
import com.ueuo.gabrieltavares.agendadecontatos.database.DataBase;
import com.ueuo.gabrieltavares.agendadecontatos.dominio.DaoContato;
import com.ueuo.gabrieltavares.agendadecontatos.dominio.entidades.Contato;
import com.ueuo.gabrieltavares.agendadecontatos.util.MessageBoxUtil;
import com.ueuo.gabrieltavares.agendadecontatos.web.WebTaskContato;

import java.util.Date;
import java.util.List;

public class ActContato extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private static final String parametro_Contato = "CONTATO";
    private static final int CODIGO_SMS = 550;
    MessageBoxUtil alertUsuario;
    private FloatingActionButton imgBtnCadastrar;
    private DataBase dataBase;
    private SQLiteDatabase conexao;
    private ListView listaContatos;
    private EditText txt_pesquisa;
    private FiltrarDados filtrarDados;
    private ContatoAdapter adpContatos;
    private DaoContato daoContato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_contato);

        imgBtnCadastrar = findViewById(R.id.imgBtn_cadastrar);
        imgBtnCadastrar.setOnClickListener(this);

        alertUsuario = new MessageBoxUtil(this);

        txt_pesquisa = findViewById(R.id.txt_pesquisa);

        //Instanciando minha list view
        listaContatos = findViewById(R.id.list_contatos);

//        Criando um menu de contexto - menu em cima dos itens da lista
        registerForContextMenu(listaContatos);

        if (checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.WRITE_CONTACTS}, 007);
        } else {
            fetchContacts(this);
        }
//        allSIMContact();
        try {
            listaContatos.setOnItemClickListener(this);
        } catch (Exception e) {
            alertUsuario.showAlert(getString(R.string.lbl_erro), getString(R.string.lbl_erro_carregar_lista_contato) + e.getMessage());
        }

        try {

            preencherLista();

            //Mensagem se deu certo a conexão ao banco de dados
            //alertUsuario.showInfo("Sucesso!","Banco criado com sucesso");

            //Instancia do objeto repositorio que possui os metodos de manupulação do banco

            //Dentro do arrayAdapter da lista eu faço a busca na classe
            //que possui os métodos de banco de dados
            //e busco os contatos salvos

            //CLASSE NESTE ARQUIVO, RESPONSÁVEL POR IMPLEMENTAR UM TEXTCHANGEDLISTENER
            filtrarDados = new FiltrarDados();
            //ADICIONANDO O EVENTO À CAIXA DE TEXTO
            txt_pesquisa.addTextChangedListener(filtrarDados);

        } catch (Exception e) {
            //Caso dê erro na excução da conexao ao banco
            alertUsuario.showAlert(this.getString(R.string.lbl_erro), "Erro ao criar O banco de dados! " + e.getMessage());
        }

        if (checkSelfPermission(Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.RECEIVE_SMS}, CODIGO_SMS);
        }

    }

    private void preencherLista() {
        daoContato = new DaoContato(this);
        List<Contato> contatos = daoContato.getTodosContato();
        adpContatos = new ContatoAdapter(this, contatos);
        listaContatos.setAdapter(adpContatos);
        daoContato.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        Inflando o menu com o botão de sincronização
        getMenuInflater().inflate(R.menu.menu_act_contato, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_menu_sincronizar:
//                Toast.makeText(this, "Sincronizando...", Toast.LENGTH_SHORT).show();
                new WebTaskContato(this).execute();
//                DaoContato daoContato = new DaoContato(this);
//                List<Contato> contatos = daoContato.getTodosContato();
//                daoContato.close();
//
//                ContatoConverter conversor = new ContatoConverter();
//                String json = conversor.converterToJson(contatos);
//
//                WebClientContato webClient = new WebClientContato();
//                String respostaRequisicao = webClient.post(json);
//                Toast.makeText(this, respostaRequisicao, Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.imgBtn_cadastrar:

                Intent intent = new Intent(this, ActCadastroContato.class);
                startActivityForResult(intent, 0);
                //startActivity(intent);
                break;
            default:
                break;
        }

    }

    //Método que trata os retorno de outras activitys
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        preencherLista();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

//        //RECUPERANDO MEU OBJETO CONTATO SELECIONADO
//        Contato contato = adpContatos.getItem(position);
//
//        //INSTANCIO MINHA INTENT
//        Intent intent = new Intent(this,ActCadastroContato.class);
//
//        //PASSAGEM DO OBJETO CONTATO ENTRE TELAS
//        //É PRECISO IMPLEMENTAR SERIALIZABLE NA CLASSE DO OBJETO A INTERFACE PRA TRANSPOSIÇÃO DE OBJETOS
//        intent.putExtra(parametro_Contato,contato);
//
//        //ABRO A SEGUNDA ACTIVITY
//        startActivityForResult(intent,0);

        Contato contato = (Contato) listaContatos.getItemAtPosition(position);
        Toast.makeText(ActContato.this, "Contato: " + contato.getNome(), Toast.LENGTH_SHORT).show();
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
                if (ActivityCompat.checkSelfPermission(ActContato.this, Manifest.permission.CALL_PHONE)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(ActContato.this, new String[]{Manifest.permission.CALL_PHONE}, 123);
                } else {
                    Intent ligarIntent = new Intent(Intent.ACTION_CALL);
                    ligarIntent.setData(Uri.parse("tel:" + contato.getTelefone()));
                    startActivity(ligarIntent);
                }
                return false;
            }
        });


//      SMS
        MenuItem smsItem = menu.add("Enviar SMS");
        Intent smsIntent = new Intent(Intent.ACTION_VIEW);
        smsIntent.setData(Uri.parse("sms:" + contato.getTelefone()));
        smsItem.setIntent(smsIntent);

//        LOCALIZACAO
        MenuItem localizacaoItem = menu.add("Visualizar posição");
        Intent localizacaoIntent = new Intent(Intent.ACTION_VIEW);
        localizacaoIntent.setData(Uri.parse("geo:0,0?q=" + contato.getEndereco()));
        localizacaoItem.setIntent(localizacaoIntent);

//         PERFIL FACEBOOK
        MenuItem perfilFacebookItem = menu.add("Ir perfil");
//        Intent siteIntent = new Intent(ActContato.this, Browser.class);
//        Falo pra intente que quero abrir um recurso
        Intent siteIntent = new Intent(Intent.ACTION_VIEW);
        siteIntent.setData(Uri.parse("https://www.facebook.com/search/top/?q=" + contato.getNome() + "&init=public"));
        perfilFacebookItem.setIntent(siteIntent);

        detalheItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;

                //RECUPERANDO MEU OBJETO CONTATO SELECIONADO
                Contato contato = (Contato) adpContatos.getItem(((AdapterView.AdapterContextMenuInfo) menuInfo).position);

                //INSTANCIO MINHA INTENT
                Intent intent = new Intent(ActContato.this, ActCadastroContato.class);

                //PASSAGEM DO OBJETO CONTATO ENTRE TELAS
                //É PRECISO IMPLEMENTAR SERIALIZABLE NA CLASSE DO OBJETO A INTERFACE PRA TRANSPOSIÇÃO DE OBJETOS
                intent.putExtra(parametro_Contato, contato);

                //ABRO A SEGUNDA ACTIVITY
                startActivityForResult(intent, 0);
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

        if (conexao != null) {
            daoContato.close();
        }
    }

    public void fetchContacts(Context context) {
        daoContato = new DaoContato(context);
        String phoneNumber = null;
        String email = null;

        Uri CONTENT_URI = ContactsContract.Contacts.CONTENT_URI;
        String _ID = ContactsContract.Contacts._ID;
        String DISPLAY_NAME = ContactsContract.Contacts.DISPLAY_NAME;
        String HAS_PHONE_NUMBER = ContactsContract.Contacts.HAS_PHONE_NUMBER;

        Uri PhoneCONTENT_URI = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String Phone_CONTACT_ID = ContactsContract.CommonDataKinds.Phone.CONTACT_ID;
        String NUMBER = ContactsContract.CommonDataKinds.Phone.NUMBER;

        Uri EmailCONTENT_URI = ContactsContract.CommonDataKinds.Email.CONTENT_URI;
        String EmailCONTACT_ID = ContactsContract.CommonDataKinds.Email.CONTACT_ID;
        String DATA = ContactsContract.CommonDataKinds.Email.DATA;

        ContentResolver contentResolver = getContentResolver();

        Cursor cursor = contentResolver.query(CONTENT_URI, null, null, null, null);

        // Loop for every contact in the phone
        if (cursor.getCount() > 0) {

            while (cursor.moveToNext()) {
                Contato contato = new Contato();
                StringBuffer output = new StringBuffer();

                String contact_id = cursor.getString(cursor.getColumnIndex(_ID));
                String name = cursor.getString(cursor.getColumnIndex(DISPLAY_NAME));

                int hasPhoneNumber = Integer.parseInt(cursor.getString(cursor.getColumnIndex(HAS_PHONE_NUMBER)));

                if (hasPhoneNumber > 0) {

                    output.append("\n First Name:" + name);

                    // Query and loop for every phone number of the contact
                    Cursor phoneCursor = contentResolver.query(PhoneCONTENT_URI, null, Phone_CONTACT_ID + " = ?", new String[]{contact_id}, null);

                    while (phoneCursor.moveToNext()) {
                        phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(NUMBER));
                        output.append("\n Phone number:" + phoneNumber);
                        contato.setTelefone(phoneNumber);
                    }

                    phoneCursor.close();

                    // Query and loop for every email of the contact
                    Cursor emailCursor = contentResolver.query(EmailCONTENT_URI, null, EmailCONTACT_ID + " = ?", new String[]{contact_id}, null);

                    while (emailCursor.moveToNext()) {

                        email = emailCursor.getString(emailCursor.getColumnIndex(DATA));

                        output.append("\nEmail:" + email);

                    }

                    emailCursor.close();
                }

                output.append("\n");
                contato.setDataEspecial(new Date());
                contato.setNome(name);
                contato.setTelefone(contato.getTelefone() == null ? "po" : contato.getTelefone());
                boolean existeContato = daoContato.existeEsseContato(contato);
                if (!existeContato) {
                    daoContato.inserir(contato);
                }
            }

        }
    }

    public void fetchContactsOriginal(Context context) {

        String phoneNumber = null;
        String email = null;

        Uri CONTENT_URI = ContactsContract.Contacts.CONTENT_URI;
        String _ID = ContactsContract.Contacts._ID;
        String DISPLAY_NAME = ContactsContract.Contacts.DISPLAY_NAME;
        String HAS_PHONE_NUMBER = ContactsContract.Contacts.HAS_PHONE_NUMBER;

        Uri PhoneCONTENT_URI = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String Phone_CONTACT_ID = ContactsContract.CommonDataKinds.Phone.CONTACT_ID;
        String NUMBER = ContactsContract.CommonDataKinds.Phone.NUMBER;

        Uri EmailCONTENT_URI = ContactsContract.CommonDataKinds.Email.CONTENT_URI;
        String EmailCONTACT_ID = ContactsContract.CommonDataKinds.Email.CONTACT_ID;
        String DATA = ContactsContract.CommonDataKinds.Email.DATA;


        ContentResolver contentResolver = getContentResolver();

        Cursor cursor = contentResolver.query(CONTENT_URI, null, null, null, null);

        // Loop for every contact in the phone
        if (cursor.getCount() > 0) {

            while (cursor.moveToNext()) {
                StringBuffer output = new StringBuffer();

                String contact_id = cursor.getString(cursor.getColumnIndex(_ID));
                String name = cursor.getString(cursor.getColumnIndex(DISPLAY_NAME));

                int hasPhoneNumber = Integer.parseInt(cursor.getString(cursor.getColumnIndex(HAS_PHONE_NUMBER)));

                if (hasPhoneNumber > 0) {

                    output.append("\n First Name:" + name);

                    // Query and loop for every phone number of the contact
                    Cursor phoneCursor = contentResolver.query(PhoneCONTENT_URI, null, Phone_CONTACT_ID + " = ?", new String[]{contact_id}, null);

                    while (phoneCursor.moveToNext()) {
                        phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(NUMBER));
                        output.append("\n Phone number:" + phoneNumber);

                    }

                    phoneCursor.close();

                    // Query and loop for every email of the contact
                    Cursor emailCursor = contentResolver.query(EmailCONTENT_URI, null, EmailCONTACT_ID + " = ?", new String[]{contact_id}, null);

                    while (emailCursor.moveToNext()) {

                        email = emailCursor.getString(emailCursor.getColumnIndex(DATA));

                        output.append("\nEmail:" + email);

                    }

                    emailCursor.close();
                }

                output.append("\n");
                Toast.makeText(context, output.toString(), Toast.LENGTH_SHORT).show();
            }

        }
    }

    private void allSIMContact() {
        // TODO: 25/02/18 pedir permissão
        if (checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, 007);
        }

        try {
            String ClsSimPhonename = null;
            String ClsSimphoneNo = null;

            Uri simUri = Uri.parse("content://icc/adn");
            Cursor cursorSim = this.getContentResolver().query(simUri, null, null, null, null);

            Log.i("PhoneContact", "total: " + cursorSim.getCount());

            while (cursorSim.moveToNext()) {
                ClsSimPhonename = cursorSim.getString(cursorSim.getColumnIndex("name"));
                ClsSimphoneNo = cursorSim.getString(cursorSim.getColumnIndex("number"));
                ClsSimphoneNo.replaceAll("\\D", "");
                ClsSimphoneNo.replaceAll("&", "");
                ClsSimPhonename = ClsSimPhonename.replace("|", "");

                Log.i("PhoneContact", "name: " + ClsSimPhonename + " phone: " + ClsSimphoneNo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //Classe para implementação do listener de texto
    private class FiltrarDados implements TextWatcher {

        // ContatoArrayAdapter arrayAdapterContato;

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

}

