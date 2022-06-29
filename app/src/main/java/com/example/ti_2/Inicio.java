package com.example.ti_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Inicio extends AppCompatActivity {
    Button simon, camara, animal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        simon=(Button) findViewById(R.id.orientacion);
        camara=(Button) findViewById(R.id.abecedario);
        animal=(Button) findViewById(R.id.animal);
        simon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Inicio.this, Simon.class);
                startActivity(intent1);
            }
        });
        camara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Inicio.this, MainActivity.class);
                startActivity(intent1);
            }
        });
        animal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Inicio.this, Animales.class);
                startActivity(intent1);
            }
        });
    }
}