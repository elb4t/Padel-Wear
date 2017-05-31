package es.elb4t.padelwear;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.view.CurvedChildLayoutManager;
import android.support.wearable.view.WatchViewStub;
import android.support.wearable.view.WearableRecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.attr.tag;

public class MainActivity extends Activity {
    // Elementos a mostrar en la lista
    String[] elementos = {"Partida", "Terminar partida", "Historial",
            "Notificación", "Pasos", "Pulsaciones", "Terminar partida"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WearableRecyclerView lista = (WearableRecyclerView) findViewById(R.id.lista);
        Adaptador adaptador = new Adaptador(this, elementos);
        adaptador.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer tag = (Integer) v.getTag();
                switch (tag) {
                    case 1:
                        startActivity(new Intent(MainActivity.this, Confirmacion.class));
                        break;
                }
            }
        });


        lista.setAdapter(adaptador);
        lista.setCenterEdgeItems(true);
        lista.setLayoutManager(new MyChildLayoutManager(this));
        lista.setCircularScrollingGestureEnabled(true);
        lista.setScrollDegreesPerScreen(180);
        lista.setBezelWidth(0.5f);
    }
}
