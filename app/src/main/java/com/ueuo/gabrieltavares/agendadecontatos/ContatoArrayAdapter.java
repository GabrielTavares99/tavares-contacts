package com.ueuo.gabrieltavares.agendadecontatos;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ueuo.gabrieltavares.agendadecontatos.dominio.entidades.Contato;

/**
 * Created by gabri on 28/04/2017.
 */

public class ContatoArrayAdapter extends ArrayAdapter<Contato>{

    private int resouce = 0;
    private LayoutInflater inflater;
    ViewHolder viewHolder = null;

    public ContatoArrayAdapter(Context context,int resource) {

        super(context, resource);
        //VAI CHAMAR UMA CLASSE RESPONSÁVEL POR CHAMAR A INTERFACE PARA CADA LINHA DO OBJETO LIST VIEW
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.resouce = resource;

    }

    //VISUALIZA OS ITEMS DO LISTVIEW
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = null;

            if (convertView == null){
                viewHolder = new ViewHolder();

                //CRIANDO A LINHA DO OBJETO PARA SER PREENCHIDO CADA ITEM DO LISTVIEW
                //FALSE - NÃO ASSOCIADO A NENHUM GRUPO
                view = inflater.inflate(resouce,parent,false);

                //CHAMANDO CADA ITEM DO LAYOUT
                viewHolder.lbl_cor = (TextView) view.findViewById(R.id.lbl_cor);
                viewHolder.lbl_nome = (TextView) view.findViewById(R.id.lbl_nome);
                viewHolder.lbl_telefone = (TextView) view.findViewById(R.id.lbl_telefone);

                //ASSOCIANDO OS ITENS A MINHA VIEW - OBJETO JÁ FICA ARMAZENADO PQ É NUMA CLASSA
                view.setTag(viewHolder);

                convertView = view;
            }else {
                viewHolder = (ViewHolder) convertView.getTag();
                view = convertView;
            }

        Contato contato = getItem(position);

       // viewHolder.lbl_cor = contato
        viewHolder.lbl_nome.setText(contato.getNome());
        viewHolder.lbl_telefone.setText(contato.getTelefone());

        return view;
    }

    //CRIANDO UMA CLASSE ESTÁTICA PARA NÃO FICAR
    //RECUPERANDO O TEMPO TODO OS VALORES DOS CAMPOS QUE
    //DESEJAMOS
    public class ViewHolder{
        TextView lbl_cor  ;
        TextView lbl_nome;
        TextView lbl_telefone;

    }

}
