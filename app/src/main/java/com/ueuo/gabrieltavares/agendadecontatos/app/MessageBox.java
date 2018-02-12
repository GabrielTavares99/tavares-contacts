package com.ueuo.gabrieltavares.agendadecontatos.app;

import android.content.Context;
import android.support.v7.app.AlertDialog;

public class MessageBox {

    AlertDialog.Builder alertDialog;

    public MessageBox(Context context){
        alertDialog = new AlertDialog.Builder(context);
        alertDialog.setNeutralButton("OK", null);

    }

    public void showInfo(String title, String mensage){
        alertDialog.setMessage(mensage);
        alertDialog.setIcon(android.R.drawable.ic_dialog_alert);
        alertDialog.setTitle(title);
        alertDialog.show();
    }

    public void showAlert(String title, String mensage){
        alertDialog.setMessage(mensage);
        alertDialog.setIcon(android.R.drawable.ic_dialog_alert);
        alertDialog.setTitle(title);
        alertDialog.show();
    }

}
