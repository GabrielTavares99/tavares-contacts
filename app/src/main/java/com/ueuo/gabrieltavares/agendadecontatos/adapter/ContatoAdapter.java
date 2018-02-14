package com.ueuo.gabrieltavares.agendadecontatos.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ueuo.gabrieltavares.agendadecontatos.R;
import com.ueuo.gabrieltavares.agendadecontatos.dominio.entidades.Contato;

import java.util.List;

public class ContatoAdapter extends BaseAdapter {

    private final List<Contato> contatos;
    private final Context context;

    public ContatoAdapter(Context context, List<Contato> contatos) {
        this.context = context;
        this.contatos = contatos;
    }

    @Override
    public int getCount() {
        return contatos.size();
    }

    @Override
    public Object getItem(int position) {
        return contatos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return contatos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Contato contato = contatos.get(position);

//        A CONVERTVIEW é uma view já instanciada que está sendo reaproveitada
        View view;

        LayoutInflater layoutInflater = LayoutInflater.from(context);

        if (convertView == null) {
//            Parâmetros
//                    O layout a ser inflado
//                    O item pai
//                    False para dizer que a view inflada não deve ser colocada dentro do item pai
            view = layoutInflater.inflate(R.layout.activity_act_linha_contato_foto, parent, false);
        } else {
            view = convertView;
        }
        TextView nome = view.findViewById(R.id.lbl_nome);
        nome.setText(contato.getNome());

        TextView telefone = view.findViewById(R.id.lbl_telefone);
        telefone.setText(contato.getTelefone());

        ImageView foto = view.findViewById(R.id.layout_foto_img_foto);

        foto.setImageResource(R.drawable.person);

//        O CAMPO GRUPO SÓ EXISTE NO LAYOUT EM LANDSCAPE
//        QUANDO O LAYOUT ESTÁ EM RETRATO, O COMPONENTE NÃO EXISTE(NULL)
//        POR ISSO É NECESSÁRIO ESSE 'IF' DE VERIFICAÇÃO
        TextView grupo = view.findViewById(R.id.act_linha_contato_foto_lbl_grupo);
        if (grupo != null) {
            grupo.setText("- " + contato.getGrupo());
        }

//        String caminhoFoto = contato.getCaminhoFoto();
//        if (caminhoFoto != null) {
//            ImagemUtil.getImagemByCaminho(caminhoFoto, foto);
//        }

        return view;
    }


}
