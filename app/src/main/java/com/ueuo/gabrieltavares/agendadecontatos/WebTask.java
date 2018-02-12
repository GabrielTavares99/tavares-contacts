package com.ueuo.gabrieltavares.agendadecontatos;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.ueuo.gabrieltavares.agendadecontatos.converter.ContatoConverter;
import com.ueuo.gabrieltavares.agendadecontatos.dominio.DaoContato;
import com.ueuo.gabrieltavares.agendadecontatos.dominio.entidades.Contato;

import java.util.List;

//PRIMEIRO PARAMETRO É O PARAMETRO DO DO IM BACKGROUND
public class WebTask extends AsyncTask {

    private Context context;
    ProgressDialog dialog;

    public WebTask(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
//        se nao tem fim determinado
//        cancelable
        dialog = ProgressDialog.show(context, "Aguarde", "Sincronizando", true, false);
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        DaoContato daoContato = new DaoContato(context);
        List<Contato> contatos = daoContato.getTodosContato();
        daoContato.close();

        ContatoConverter conversor = new ContatoConverter();
        String json = conversor.converterToJson(contatos);

        WebClient webClient = new WebClient();
        String respostaRequisicao = webClient.post(json);
        return respostaRequisicao;
    }

    @Override
    protected void onPostExecute(Object resposta) {
//        executado na thread principal
        //parametro é a resposta do doinbackground
        Toast.makeText(context, resposta.toString(), Toast.LENGTH_SHORT).show();
        dialog.dismiss();
    }
}
