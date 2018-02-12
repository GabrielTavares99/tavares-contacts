package com.ueuo.gabrieltavares.agendadecontatos.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.telephony.SmsMessage;
import android.widget.Toast;

import com.ueuo.gabrieltavares.agendadecontatos.R;
import com.ueuo.gabrieltavares.agendadecontatos.dominio.DaoContato;

public class SMSReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {

        Object[] pdus = (Object[]) intent.getSerializableExtra("pdus");
        byte[] pdu = (byte[]) pdus[0];
        String formato = (String) intent.getSerializableExtra("format");

        SmsMessage sms = SmsMessage.createFromPdu(pdu, formato);

        String telefone = sms.getDisplayOriginatingAddress();

        DaoContato daoContato = new DaoContato(context);
        String mensagem;
        if (daoContato.isContatoSalvo(telefone)){
            mensagem = "Mensagem de aluno";
        }else {
            mensagem = "Desconhecido";
        }

        MediaPlayer mediaPlayer = MediaPlayer.create(context, R.raw.msg);
        mediaPlayer.start();
        Toast.makeText(context, mensagem, Toast.LENGTH_SHORT).show();

    }
}
