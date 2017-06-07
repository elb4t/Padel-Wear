package es.elb4t.padelwear;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.view.CurvedChildLayoutManager;
import android.support.wearable.view.WatchViewStub;
import android.support.wearable.view.WearableRecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.attr.tag;

public class MainActivity extends Activity {
    // Elementos a mostrar en la lista
    String[] elementos = {"Partida", "Contador2", "Historial",
            "Jugadores", "Pasos","Pasos2", "Pulsaciones", "Terminar partida"};

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
                Log.e("LLISTA-----", tag.toString());
                switch (tag) {
                    case 0:
                        startActivity(new Intent(MainActivity.this, Contador.class));
                        break;
                    case 1:
                        startActivity(new Intent(MainActivity.this, Contador2.class));
                        break;
                    case 2:
                        startActivity(new Intent(MainActivity.this, Historial.class));
                        break;
                    case 3:
                        startActivity(new Intent(MainActivity.this, Jugadores.class));
                        break;
                    case 4:
                        startActivity(new Intent(MainActivity.this, Pasos.class));
                        break;
                    case 5:
                        startActivity(new Intent(MainActivity.this, Pasos2.class));
                        break;
                    case 6:
                        startActivity(new Intent(MainActivity.this, Cardiaco.class));
                        break;
                    case 7:
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
