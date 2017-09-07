package com.ueuo.gabrieltavares.agendadecontatos;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.ueuo.gabrieltavares.agendadecontatos.Util.Mascaras;
import com.ueuo.gabrieltavares.agendadecontatos.app.MessageBox;
import com.ueuo.gabrieltavares.agendadecontatos.app.ViewHelper;
import com.ueuo.gabrieltavares.agendadecontatos.database.DataBase;
import com.ueuo.gabrieltavares.agendadecontatos.dominio.RepositorioContato;
import com.ueuo.gabrieltavares.agendadecontatos.dominio.entidades.Contato;

import java.io.File;
import java.net.URI;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import com.ueuo.gabrieltavares.agendadecontatos.Util.DateUtils;

public class act_cadastroContato extends AppCompatActivity{

    private Spinner spin_tipoTelefone,spin_tipoEmail,spin_tipoEndereco,spin_tipoDataEspecial,spin_tipoGrupo;
    private EditText txt_nome,txt_telefone,txt_email,txt_endereco,txt_dataEspecial,txt_grupo;

    private DataBase dataBase;
    private SQLiteDatabase conn;
    private RepositorioContato repositorioContato;
    private Contato contato;

    public Date date;

    //ObJETO RESPONSÁVEL POR EXIBIR TODAS AS MENSAGENS DO SISTEMA
    private MessageBox alertUsuario;

    private ArrayAdapter<String> adpTelefone,adpEmail,adpEndereco,adpDataEspecial,adpGrupo;

    private Button btn_camera;

    private ImageView img_foto;

    ExibeDataListener exibeDataListener;

    private String caminhoFoto;

    ViewHelper viewHelper;

    private static final int CODIGO_CAMERA = 567;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_cadastro_contato);

        viewHelper = new ViewHelper(this);

        spin_tipoDataEspecial = (Spinner) findViewById(R.id.spin_tipoDataEspecial);
        spin_tipoEmail = (Spinner) findViewById(R.id.spin_tipoEmail);
        spin_tipoEndereco = (Spinner) findViewById(R.id.spin_tipoEndereco);
        spin_tipoTelefone = (Spinner) findViewById(R.id.spin_tipoTelefone);

        txt_email = (EditText) findViewById(R.id.txt_email);
        txt_endereco = (EditText) findViewById(R.id.txt_endereco);
        txt_grupo = (EditText) findViewById(R.id.txt_grupo);
        txt_nome = (EditText) findViewById(R.id.txt_Nome);
        txt_telefone = (EditText) findViewById(R.id.txt_numeroTelefone);
        txt_dataEspecial = (EditText) findViewById(R.id.txt_dataEspecial);

        btn_camera = (Button) findViewById(R.id.btn_foto);

        //BLOQUANDO TECLADO VIRTUAL NO CAMPO DATAS ESPECIAIS
        txt_dataEspecial.setOnKeyListener(null);

        //Criando um adaptador de string para colocar no meu "list-box"
        adpEmail = viewHelper.adicionarArrayAdapterSimples(spin_tipoEmail);
        adpEmail.add(getString(R.string.lbl_tipo_email_comercial));
        adpEmail.add(getString(R.string.lbl_tipo_email_pessoal));

        adpTelefone = viewHelper.adicionarArrayAdapterSimples(spin_tipoTelefone);
        adpTelefone.add(getString(R.string.lbl_tipo_telefone_residencial));
        adpTelefone.add(getString(R.string.lbl_tipo_telefone_comercial));

        adpDataEspecial = viewHelper.adicionarArrayAdapterSimples(spin_tipoDataEspecial);
        adpDataEspecial.add(getString(R.string.lbl_tipo_data_especial_aniversario));
        adpDataEspecial.add(getString(R.string.lbl_tipo_data_especial_evento));

        adpEndereco =  viewHelper.adicionarArrayAdapterSimples(spin_tipoEndereco);
        adpEndereco.add(getString(R.string.lbl_tipo_endereco_pessoal));
        adpEndereco.add(getString(R.string.lbl_tipo_endereco_comercial));

        //Instancia objeto listener
        exibeDataListener = new ExibeDataListener();
        //Colocando Listener na caixa de data
        txt_dataEspecial.setOnFocusChangeListener(exibeDataListener);
        txt_dataEspecial.setOnClickListener(exibeDataListener);

        //RECUPERANDO O CONTATO RECEBIDO
        Bundle bundle = getIntent().getExtras();

        //INSTANCIO O OBJETO ALERTDIALOG
        alertUsuario = new MessageBox(this);

        img_foto = (ImageView) findViewById(R.id.img_foto);

        //VERIFICO SE VEIO ALGUM OBJETO DE OUTRA ACTIVITY
        if (bundle  != null && bundle.containsKey("CONTATO")){

            //CASO EXISTA, EU RECUPERO O SEU VALOR
            contato = (Contato) bundle.getSerializable("CONTATO");

            try {
                preencherDados(contato);
            }catch (Exception e){
                //ERRO AO PREENCHER OS CAMPOS
                //CHAMO UM MÉTODO ALERTDIALOG E PASSO OS PARÂMETROS
               alertUsuario.showAlert(getString(R.string.lbl_titulo_erro),getString(R.string.lbl_erro_preencher_dados)+e.getCause());
            }

           // MenuItem itemExcluir = (MenuItem) findViewById(R.id.item_menu_excluir);
           // itemExcluir.setVisible(true);
        }

        try {
            //Tentando vericiar se existe conexão
            dataBase = new DataBase(this);
            conn = dataBase.getWritableDatabase();
            repositorioContato = new RepositorioContato(conn);
        }catch (Exception e){
            alertUsuario.showAlert(getString(R.string.lbl_tituto_erro_conexao),getString(R.string.lbl_erro_conexao)+ e.getMessage());
        }

        btn_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                alertUsuario.showInfo("Sucesso!", "Esta função ainda está sendo implementada, espera até a proxima atualização");
                Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                caminhoFoto = getExternalFilesDir(null) + "/" + System.currentTimeMillis() + ".jpg";
                File file = new File(caminhoFoto);
                intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                startActivityForResult(intentCamera, CODIGO_CAMERA);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        O request code pega o código definido no startActivityForResult
//        O resultCode diz se a ação foi completada ou nao

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == CODIGO_CAMERA) {
                Bitmap bitmap = BitmapFactory.decodeFile(caminhoFoto);
                Bitmap bitmapreduzido = Bitmap.createScaledBitmap(bitmap, 400, 400, true);
                img_foto.setImageBitmap(bitmapreduzido);
                img_foto.setScaleType(ImageView.ScaleType.FIT_XY);
            }
        }
    }
    ///Método que preenche os campos com os atributos do objeto oriundo de outra classe
    public void preencherDados(Contato contato){

        txt_nome.setText(contato.getNome());
        txt_telefone.setText(contato.getTelefone());
        txt_endereco.setText(contato.getEndereco());
        txt_email.setText(contato.getEmail());
        txt_grupo.setText(contato.getGrupo());

        DateFormat dataFormat = DateFormat.getDateInstance(DateFormat.SHORT);
        String data = dataFormat.format(contato.getDataEspecial());
        txt_dataEspecial.setText(data);

        //SETANDO A DATA ORIUNDA DO OBJETO CONTATO PARA A VARIAVEL STATICA DE DATA
        date = contato.getDataEspecial();

        spin_tipoDataEspecial.setSelection(Integer.valueOf(contato.getTipoDataEspecial()));
        spin_tipoTelefone.setSelection(Integer.valueOf(contato.getTipoTelefone()));
        spin_tipoEmail.setSelection(Integer.valueOf(contato.getTipoEmail()));
        spin_tipoEndereco.setSelection(Integer.valueOf(contato.getTipoEndereco()));

    }

    //MÉTODO QUE PEGA OS VALORES DOS CAMPOS E MONTA UM OBJETO CONTATO
    public Contato montarContato(Contato contato){

        contato.setNome(txt_nome.getText().toString());

        contato.setTelefone(txt_telefone.getText().toString());
        contato.setTipoTelefone(spin_tipoTelefone.getSelectedItemPosition());

        contato.setEmail(txt_email.getText().toString());
        contato.setTipoEmail(spin_tipoEmail.getSelectedItemPosition());

        contato.setEndereco(txt_endereco.getText().toString());
        contato.setTipoEndereco(spin_tipoEndereco.getSelectedItemPosition());

        contato.setDataEspecial(date);
        contato.setTipoDataEspecial(spin_tipoDataEspecial.getSelectedItemPosition());

        contato.setGrupo(txt_grupo.getText().toString());

        return contato;
    }

    //Aparecer calendario pro usuario selecionar a data
    public void exibeData(){

        SelecionaDataListener selecionaDataListener = new SelecionaDataListener();

        Calendar calendario = Calendar.getInstance();
        int dia = calendario.get(Calendar.DAY_OF_MONTH);
        int mes = calendario.get(Calendar.MONTH);
        int ano = calendario.get(Calendar.YEAR);

        //1 contexto, 2 classe com o lister que vai setar dentro da caixa de texto a data formatada, 3 ano,4 mes,5 dia
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,selecionaDataListener,ano,mes,dia);
        //Faz aparecer o calendario pro usuario selecionar
        datePickerDialog.show();

    }

    //Associando menu ao activity
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflate = getMenuInflater();
        inflate.inflate(R.menu.menu_act_cadastro_contato,menu);

        //Deixando os itens acessíveis
        if (contato != null){
            //Indice do item do menu que irá ser alterado
            menu.getItem(1).setVisible(true);
        }

       return super.onCreateOptionsMenu(menu);
    }

    //Vai verificar qual item do menu que foi selecionado
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){

            case R.id.item_menu_salvar:

                //SE O OBJETO CONTATO ESTIVER NULO, SIGINIFICA QUE DEVE SER FEITO UM NOVO REGISTRO
                if (contato == null) {
                    try {
                        contato = new Contato();
                        contato = montarContato(contato);
                        repositorioContato.inserir(contato);
                        alertUsuario.showInfo(getString(R.string.lbl_titulo_sucesso), getString(R.string.lbl_contato_adicionado_sucesso));
                    } catch (Exception e) {
                        alertUsuario.showAlert(getString(R.string.lbl_titulo_erro), getString(R.string.lbl_erro_adicionar_contato)+e.getMessage());
                    }finally {
                        finish();
                    }
                    //CASO O OBJETO NÃO SEJA NULO, SIGINIFCA QUE ELE RECEBEU VALORES PARA EDIÇAÕ
                }else if (contato!=null){
                    try {
                        contato = montarContato(contato);
                        repositorioContato.alterar(contato);
                        alertUsuario.showInfo(getString(R.string.lbl_titulo_sucesso), getString(R.string.lbl_contato_editado_sucesso));
                    }catch (Exception e){
                        alertUsuario.showAlert(getString(R.string.lbl_titulo_erro), getString(R.string.lbl_erro_editar_contato)+e.getMessage());
                    }finally {
                        finish();
                    }
                }
                break;
            case R.id.item_menu_excluir:

                try {
                    repositorioContato.excluir(contato.getId());
                    alertUsuario.showInfo(getString(R.string.lbl_titulo_sucesso), getString(R.string.lbl_contato_excluido_sucesso));
                }catch (Exception e){
                    alertUsuario.showAlert(getString(R.string.lbl_titulo_erro), getString(R.string.lbl_erro_excluir_contato));
                }

                //FECHA A ACTIVITY POIS O CONTATO JÁ FOI EXCLUIDO
                finish();
                break;

            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    //Criando uma classe interna
    private class ExibeDataListener implements View.OnFocusChangeListener, View.OnClickListener{


        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if (hasFocus)
            exibeData();
        }

        @Override
        public void onClick(View v) {
            exibeData();
        }
    }

    private class SelecionaDataListener implements DatePickerDialog.OnDateSetListener{

        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

            DateUtils dataUtils = new DateUtils();

            date = dataUtils.data(year,month,dayOfMonth);

            txt_dataEspecial.setText(dataUtils.dataToString(date));

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (conn != null){
            conn.close();
        }
    }
}
