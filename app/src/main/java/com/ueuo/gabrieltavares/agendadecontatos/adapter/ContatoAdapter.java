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

public class ContatoAdapter extends BaseAdapter{

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
        Contato contato = contatos.get(position);

//        A CONVERTVIEW é uma view já instanciada que está sendo reaproveitada
       View view = null;

        LayoutInflater layoutInflater = LayoutInflater.from(context);

        if (convertView == null){
//            Parâmetros
//                    O layout a ser inflado
//                    O item pai
//                    False para dizer que a view inflada não deve ser colocada dentro do item pai
            view = layoutInflater.inflate(R.layout.activity_act_linha_contato_foto, parent ,false);
        }else {
            view = convertView;
        }
        TextView nome = (TextView) view.findViewById(R.id.lbl_nome);
        nome.setText(contato.getNome());

        TextView telefone = (TextView) view.findViewById(R.id.lbl_telefone);
        telefone.setText(contato.getTelefone());

        ImageView foto = (ImageView) view.findViewById(R.id.layout_foto_img_foto) ;

//        foto.setImageResource(R.drawable.person);
//        String caminhoFoto;
//        caminhoFoto = contato.getCaminhoFoto();
//        if (caminhoFoto != null){
//            Bitmap bitmap = BitmapFactory.decodeFile(caminhoFoto);
//            Bitmap bitmapReduzido = Bitmap.createScaledBitmap(bitmap, 64, 64, true);
//            foto.setImageBitmap(bitmapReduzido);
//            foto.setScaleType(ImageView.ScaleType.CENTER_CROP);
//        }

        return view;
    }


}
