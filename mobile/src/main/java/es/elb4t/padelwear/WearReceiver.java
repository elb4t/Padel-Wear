package es.elb4t.padelwear;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.RemoteInput;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by eloy on 31/5/17.
 */

public class WearReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(MainActivity.ACTION_DEMAND)) {
            String extras = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
            Log.v("Notificaciones", "Se recibe ACTION_DEMAND; extras = " + extras);
            Bundle remoteInput = RemoteInput.getResultsFromIntent(intent);
            CharSequence reply = remoteInput.getCharSequence(MainActivity.EXTRA_RESPUESTA_POR_VOZ);
            Log.v("Notificaciones", "Respuesta dictada desde el wearable: " + reply);
            //((TextView) ((AppCompatActivity)context.getApplicationContext()).findViewById(R.id.textViewRespuesta)).setText(reply);
        }
    }
}
