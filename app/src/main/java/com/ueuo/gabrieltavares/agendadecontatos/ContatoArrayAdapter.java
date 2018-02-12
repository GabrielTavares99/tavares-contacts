package com.ueuo.gabrieltavares.agendadecontatos;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ueuo.gabrieltavares.agendadecontatos.Util.SeletorCores;
import com.ueuo.gabrieltavares.agendadecontatos.dominio.entidades.Contato;

public class ContatoArrayAdapter extends ArrayAdapter<Contato>{

    private int resouce = 0;
    private LayoutInflater inflater;
    ViewHolder viewHolder = null;
    private Context context;

    boolean cor_Tabela = true;

    SeletorCores seletorCores;

    public ContatoArrayAdapter(Context context,int resource) {

        super(context, resource);
        //VAI CHAMAR UMA CLASSE RESPONSÁVEL POR CHAMAR A INTERFACE PARA CADA LINHA DO OBJETO LIST VIEW
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.resouce = resource;
        this.context = context;
        seletorCores = new SeletorCores();
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
                viewHolder.linearLayout = (LinearLayout) view.findViewById(R.id.layout_fundo);

                //ASSOCIANDO OS ITENS A MINHA VIEW - OBJETO JÁ FICA ARMAZENADO PQ É NUMA CLASSE
                view.setTag(viewHolder);

                convertView = view;
            }else {
                viewHolder = (ViewHolder) convertView.getTag();
                view = convertView;
            }

        Contato contato = getItem(position);

        String letraInicial = String.valueOf( contato.getNome().charAt(0));
        letraInicial = letraInicial.toUpperCase();

        //viewHolder.lbl_cor.setBackgroundColor(context.getResources().getColor(seletorCores.setarCor(letraInicial)));
        viewHolder.lbl_cor.setBackground(context.getResources().getDrawable(seletorCores.setarCor(letraInicial)));

        viewHolder.lbl_cor.setText(letraInicial);

        viewHolder.lbl_nome.setText(contato.getNome());
        viewHolder.lbl_telefone.setText(contato.getTelefone());

        if (cor_Tabela) {
            viewHolder.linearLayout.setBackgroundColor(context.getResources().getColor(R.color.branco_linha));
            cor_Tabela=false;
        }else{
            viewHolder.linearLayout.setBackgroundColor(context.getResources().getColor(R.color.cinza_linha));
            cor_Tabela = true;
        }

        return view;
    }

    //CRIANDO UMA CLASSE ESTÁTICA PARA NÃO FICAR
    //RECUPERANDO O TEMPO TODO OS VALORES DOS CAMPOS QUE
    //DESEJAMOS
    public class ViewHolder{
        TextView lbl_cor  ;
        TextView lbl_nome;
        TextView lbl_telefone;
        LinearLayout linearLayout;

    }

}
