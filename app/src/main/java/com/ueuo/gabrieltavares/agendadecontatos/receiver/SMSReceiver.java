package com.ueuo.gabrieltavares.agendadecontatos.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class SMSReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "SMS recebido", Toast.LENGTH_SHORT).show();
    }
}
