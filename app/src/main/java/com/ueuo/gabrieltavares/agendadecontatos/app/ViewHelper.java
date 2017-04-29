package com.ueuo.gabrieltavares.agendadecontatos.app;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

/**
 * Created by gabri on 27/04/2017.
 */

public class ViewHelper {

    private Context context;

    private ArrayAdapter<String> arrayAdapter;

    public ViewHelper(Context context){
        this.context = context;
    }

    public ArrayAdapter<String> adicionarArrayAdapterSimples(Spinner spinner){

        arrayAdapter = new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item);

        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(arrayAdapter);

        return arrayAdapter;
    }

}
