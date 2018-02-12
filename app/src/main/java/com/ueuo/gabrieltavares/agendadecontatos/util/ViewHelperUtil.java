package com.ueuo.gabrieltavares.agendadecontatos.util;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


public class ViewHelperUtil {

    private Context context;

    private ArrayAdapter<String> arrayAdapter;

    public ViewHelperUtil(Context context){
        this.context = context;
    }

    public ArrayAdapter<String> adicionarArrayAdapterSimples(Spinner spinner){

        arrayAdapter = new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item);

        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(arrayAdapter);

        return arrayAdapter;
    }

}
