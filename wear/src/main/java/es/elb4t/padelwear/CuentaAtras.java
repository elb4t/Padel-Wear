package es.elb4t.padelwear;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.view.DelayedConfirmationView;
import android.view.View;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

/**
 * Created by eloy on 2/6/17.
 */

public class CuentaAtras extends Activity implements DelayedConfirmationView.DelayedConfirmationListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cuenta_atras);
        DelayedConfirmationView cuentaAtras =
                (DelayedConfirmationView) findViewById(R.id.cuenta_atras);
        cuentaAtras.setListener(this);
        cuentaAtras.setTotalTimeMs(2000);
        cuentaAtras.start();
    }

    @Override
    public void onTimerFinished(View view) {
        Intent i = getIntent();
        setResult(RESULT_OK, i);
        finish();
    }

    @Override
    public void onTimerSelected(View view) {
        Intent i = getIntent();
        setResult(RESULT_CANCELED, i);
        finish();
    }
}