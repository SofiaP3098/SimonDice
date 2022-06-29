package com.example.ti_2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MedioSimon extends AppCompatActivity {
    SensorManager sensorManagerP;
    SensorManager sensorManagerA;
    Sensor sensorP;
    Sensor sensorA;
    SensorEventListener proximidad;
    SensorEventListener acelerometro;
    TextView puntos;
    TextView instruccion;
    TextView cara;
    TextView respT;
    TextView respC;
    ArrayList<String> movimientos = new ArrayList<>();

    public static final int DERECHA = 0;
    public static final int IZQUIERDA = 1;
    public static final int MEDIO = 2;
    public static final int ADELANTE = 3;
    public static final int ATRAS = 4;

    public static final int CERCA = 0;
    public static final int LEJOS = 1;

    public static final int ACELEROMETRO = 0;
    public static final int PROXIMIDAD = 1;

    int puntaje =0;
    int operacion = 0;
    int respuesta;
    int orden = 0;
    int captado = NO;
    int anterior = -1;
    int anteriorA = -1;

    public static final int SI = 0;
    public static final int NO = 1;
    public static String [] opAcelerometro = {"derecha" , "izquierda", "medio", "adelante", "atras"};
    public static String [] opProximidad = {"cerca"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medio_simon);
        instruccion = (TextView) findViewById(R.id.instruccion);
        puntos = (TextView) findViewById(R.id.puntos);
        cara = (TextView) findViewById(R.id.cara);
        respT = (TextView) findViewById(R.id.resultadoT);
        respC = (TextView) findViewById(R.id.resultadoC);

        sensorManagerP= (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorP = sensorManagerP.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        proximidad = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                if (operacion == PROXIMIDAD){
                    String text = String.valueOf(event.values[0]);
                    float valor = Float.parseFloat(text);
                    if (valor == 0) {
                        if (captado == NO){
                            if (orden == CERCA){
                                puntaje = puntaje +1;
                                cara.setText("D");
                                getWindow().getDecorView().setBackgroundColor(Color.parseColor("#39c0e5"));
                            }
                            else{
                                cara.setText("V");
                            }
                        }
                    } else {
                        if (valor == 1){
                            if (captado == NO){
                                if (orden == LEJOS){
                                    puntaje = puntaje +1;
                                    cara.setText("D");
                                    getWindow().getDecorView().setBackgroundColor(Color.parseColor("#e5396a"));
                                }
                                else{
                                    cara.setText("V");
                                }
                            }
                        }
                    }
                }

            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
            }
        };

        sensorManagerA = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorA = sensorManagerA.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (sensorA == null) {
            finish();
        }
        acelerometro = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                float x = event.values[0];
                float y = event.values[1];
                if (operacion == ACELEROMETRO) {
                    if (x > -1 && x < 1 || y > -1 && y < 1) {
                        if (captado == NO) {
                            if(orden == MEDIO){
                                puntaje = puntaje +1;
                                cara.setText("D");
                                getWindow().getDecorView().setBackgroundColor(Color.parseColor("#b439e5"));
                                captado = SI;
                            }
                            else{
                                cara.setText("V");
                            }
                        }
                    } else if (x < -3.5) {
                        if (captado == NO) {
                            if(orden == DERECHA){
                                puntaje = puntaje +1;
                                cara.setText("D");
                                getWindow().getDecorView().setBackgroundColor(Color.parseColor("#5e39e5"));
                                captado = SI;
                            }
                            else{
                                cara.setText("V");
                            }
                        }
                    } else  if (x > 3.5) {
                        if (captado == NO) {
                            if(orden == IZQUIERDA){
                                puntaje = puntaje +1;
                                cara.setText("D");
                                getWindow().getDecorView().setBackgroundColor(Color.parseColor("#4be539"));
                                captado = SI;
                            }
                            else{
                                cara.setText("V");
                            }
                        }
                    }

                    else if (y < -3.5){
                        if (captado == NO){
                            if (orden == ATRAS){
                                puntaje = puntaje +1;
                                cara.setText("D");
                                getWindow().getDecorView().setBackgroundColor(Color.parseColor("#e57d39"));
                                captado = SI;
                            }
                            else{
                                cara.setText("V");
                            }
                        }
                    }

                    else if (y > 3.5){
                        if (captado == NO){
                            if (orden == ADELANTE){
                                puntaje = puntaje +1;
                                cara.setText("D");
                                getWindow().getDecorView().setBackgroundColor(Color.parseColor("#a1e539"));
                                captado = SI;
                            }
                            else{
                                cara.setText("V");
                            }
                        }
                    }
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };

        new MedioSimon.Iterador().execute();

    }

    public class Iterador extends AsyncTask<Void, Integer, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            for (int i =0; i<10; i++){
                if(anterior == -1){
                    int op;
                    int or;
                    op = new Random().nextInt(2);
                    if(op == ACELEROMETRO){
                        anterior = 5;
                        or = new Random().nextInt(5);
                        sensorManagerA.registerListener(acelerometro, sensorA, SensorManager.SENSOR_DELAY_NORMAL);
                    }else{
                        anterior = 1;
                        or = 0;
                        sensorManagerP.registerListener(proximidad, sensorP, SensorManager.SENSOR_DELAY_NORMAL);
                    }

                    publishProgress(op, or, NO);
                    try{
                        Thread.sleep(2000);
                    }catch(Exception e){

                    }
                    anteriorA = or;
                }else{
                    int op;
                    int or;
                    op = new Random().nextInt(2);

                    if(op == PROXIMIDAD && anterior == 1){
                        i--;
                    }else if(op == PROXIMIDAD){
                        anterior = 1;
                        or = 0;
                        anteriorA = or;
                        sensorManagerP.registerListener(proximidad, sensorP, SensorManager.SENSOR_DELAY_NORMAL);
                        publishProgress(op, or, NO);
                        try{
                            Thread.sleep(2000);
                        }catch(Exception e){

                        }
                    }else{
                        //anterior = 5;
                        or = new Random().nextInt(5);
                        if (or == anteriorA){
                            i--;
                        }else{
                            anterior = 5;
                            sensorManagerA.registerListener(acelerometro, sensorA, SensorManager.SENSOR_DELAY_NORMAL);
                            anteriorA = or;
                            publishProgress(op, or, NO);
                            try{
                                Thread.sleep(2000);
                            }catch(Exception e){

                            }
                        }

                    }
                }

            }
            return  null;
        }
        @Override
        protected void onProgressUpdate(Integer ... values) {
            super.onProgressUpdate(values);
            operacion = values[0];
            orden = values[1];
            captado = values[2];
            if (operacion == ACELEROMETRO){
                instruccion.setText(opAcelerometro[orden]);
                String text = String.valueOf(puntaje);
                puntos.setText(text);
            }
            else{
                instruccion.setText(opProximidad[orden]);
                String text = String.valueOf(puntaje);
                puntos.setText(text);
            }
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            getWindow().getDecorView().setBackgroundColor(Color.parseColor("#39c0e5"));
            instruccion.setText("Fin");
            String auxP = (String) puntos.getText();
            int aux = Integer.parseInt(auxP);
            if (aux==0 || aux < 5){
                respT.setText("Practica más");
                respC.setText("N");
            } else if (aux == 5 || aux < 8) {
                respT.setText("Bien");
                respC.setText("S");
            }else {
                respT.setText("¡Excelente!");
                respC.setText("O");
            }
        }
    }
}