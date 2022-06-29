package com.example.ti_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Simon extends AppCompatActivity {
    Button facil, medio, dificil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simon);

        facil=(Button)findViewById(R.id.facil);
        medio=(Button)findViewById(R.id.medio);
        //dificil=(Button)findViewById(R.id.dificil);

        facil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent facil = new Intent(Simon.this, FacilSimon.class);
                startActivity(facil);
            }
        });

        medio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent medio = new Intent(Simon.this, MedioSimon.class);
                startActivity(medio);
            }
        });

        /*dificil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dificil = new Intent(Simon.this, DificilSimon.class);
                startActivity(dificil);
            }
        });*/

    }
}