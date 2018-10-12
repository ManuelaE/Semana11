package com.example.a1005705286.semana11;

import android.support.annotation.UiThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements Comunicacion.OnMessage{

    TextView tvJugador1;
    TextView tvJugador2;
    TextView tvJugador3;
    Button btnComenzar;
    Button btnParar;

    Comunicacion c;

    int cont=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        c = new Comunicacion();
        c.start();

        tvJugador1 = findViewById(R.id.tv_jugador1);
        tvJugador2 = findViewById(R.id.tv_jugador1);
        tvJugador3 = findViewById(R.id.tv_jugador1);

        btnComenzar = findViewById(R.id.btn_comenzar);
        btnParar = findViewById(R.id.btn_parar);

        btnComenzar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    c.enviar("1");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        btnParar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    c.enviar("2");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    public void onReceived(final String mensaje) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
               switch(cont){
                   case 0:
                       tvJugador1.setText(mensaje);
                       cont++;
                       break;

                   case 1:

                       tvJugador2.setText(mensaje);
                       cont++;
                       break;


                   case 2:

                       tvJugador3.setText(mensaje);
                       cont++;
                       break;

               }
            }
        });
    }
}
