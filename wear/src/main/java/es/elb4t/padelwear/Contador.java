package es.elb4t.padelwear;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.wearable.view.DismissOverlayView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import es.elb4t.comun.DireccionesGestureDetector;
import es.elb4t.comun.Partida;

/**
 * Created by eloy on 2/6/17.
 */

public class Contador extends Activity {
    private Partida partida;
    private TextView misPuntos, misJuegos, misSets,
            susPuntos, susJuegos, susSets;
    private Vibrator vibrador;
    private long[] vibrEntrada = {0l, 500};
    private long[] vibrDeshacer = {0l, 500, 500, 500};
    private DismissOverlayView dismissOverlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contador);
        partida = new Partida();
        vibrador = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
        misPuntos = (TextView) findViewById(R.id.misPuntos);
        susPuntos = (TextView) findViewById(R.id.susPuntos);
        misJuegos = (TextView) findViewById(R.id.misJuegos);
        susJuegos = (TextView) findViewById(R.id.susJuegos);
        misSets = (TextView) findViewById(R.id.misSets);
        susSets = (TextView) findViewById(R.id.susSets);
        actualizaNumeros();
        View fondo = findViewById(R.id.fondo);
        fondo.setOnTouchListener(new View.OnTouchListener() {
            GestureDetector detector = new DireccionesGestureDetector(
                    Contador.this,
                    new DireccionesGestureDetector
                            .SimpleOnDireccionesGestureListener() {
                        @Override
                        public boolean onArriba(MotionEvent e1, MotionEvent e2, float distX, float distY) {
                            partida.rehacerPunto();
                            vibrador.vibrate(vibrDeshacer, -1);
                            actualizaNumeros();
                            return true;
                        }

                        @Override
                        public boolean onAbajo(MotionEvent e1, MotionEvent e2, float distX, float distY) {
                            partida.deshacerPunto();
                            vibrador.vibrate(vibrDeshacer, -1);
                            actualizaNumeros();
                            return true;
                        }
                        @Override
                        public void onLongPress(MotionEvent e) { dismissOverlay.show(); }
                    });

            @Override
            public boolean onTouch(View v, MotionEvent evento) {
                detector.onTouchEvent(evento);
                return true;
            }
        });
        misPuntos.setOnTouchListener(new View.OnTouchListener() {
            GestureDetector detector = new DireccionesGestureDetector(
                    Contador.this,
                    new DireccionesGestureDetector
                            .SimpleOnDireccionesGestureListener() {
                        @Override
                        public boolean onDerecha(MotionEvent e1, MotionEvent e2, float distX, float distY) {
                            partida.puntoPara(true);
                            vibrador.vibrate(vibrEntrada, -1);
                            actualizaNumeros();
                            return true;
                        }
                        @Override
                        public void onLongPress(MotionEvent e) { dismissOverlay.show(); }
                    });

            @Override
            public boolean onTouch(View v, MotionEvent evento) {
                detector.onTouchEvent(evento);
                return true;
            }
        });
        susPuntos.setOnTouchListener(new View.OnTouchListener() {
            GestureDetector detector = new DireccionesGestureDetector(
                    Contador.this,
                    new DireccionesGestureDetector
                            .SimpleOnDireccionesGestureListener() {
                        @Override
                        public boolean onDerecha(MotionEvent e1, MotionEvent
                                e2, float distX, float distY) {
                            partida.puntoPara(false);
                            vibrador.vibrate(vibrEntrada, -1);
                            actualizaNumeros();
                            return true;
                        }
                        @Override
                        public void onLongPress(MotionEvent e) { dismissOverlay.show(); }
                    });

            @Override
            public boolean onTouch(View v, MotionEvent evento) {
                detector.onTouchEvent(evento);
                return true;
            }
        });
        dismissOverlay = (DismissOverlayView) findViewById(R.id.dismiss_overlay);
        dismissOverlay.setIntroText(
                "Para salir de la aplicación, haz una pulsación larga");
        dismissOverlay.showIntroIfNecessary();

    }

    void actualizaNumeros() {
        misPuntos.setText(partida.getMisPuntos());
        susPuntos.setText(partida.getSusPuntos());
        misJuegos.setText(partida.getMisJuegos());
        susJuegos.setText(partida.getSusJuegos());
        misSets.setText(partida.getMisSets());
        susSets.setText(partida.getSusSets());
    }
}
