package com.ueuo.gabrieltavares.agendadecontatos;

import android.content.Context;
import android.text.InputFilter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ueuo.gabrieltavares.agendadecontatos.dominio.entidades.Contato;

import java.util.List;

/**
 * Created by gabri on 07/10/2017.
 */

public class ContatoAdapter extends BaseAdapter {

    private final List<Contato> contatos;
    private final Context context;

    public ContatoAdapter(Context context, List<Contato> contatos){
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
        TextView textView = new TextView(context);
        textView.setText(contatos.get(position).getId()+""+contatos.get(position).getNome());
        return textView;
    }


}
