package com.example.ti_2;


import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.ar.core.Anchor;
import com.google.ar.core.HitResult;
import com.google.ar.core.Plane;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.math.Vector3;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.rendering.ViewRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.BaseArFragment;
import com.google.ar.sceneform.ux.TransformableNode;

public class Animales extends AppCompatActivity implements View.OnClickListener {
    ArFragment arFragment;
    private ModelRenderable bearRenderable,
            catRenderable,
            cowRenderable,
            dogRenderable,
            elephantRenderable,
            ferretRenderable,
            hippoRenderable,
            hourseRenderable, koalaRenderable, lionRenderable, reindeerRenderable, wolverineRenderable;

    ImageView bear,cat,cow,dog,elephant,ferret,hippo,hourse,koals,lion,reindeer,wolverine;

    View arrayView[];

    int selected = 1;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animales);
        arFragment = (ArFragment)getSupportFragmentManager().findFragmentById(R.id.fragment);

        bear = (ImageView)findViewById(R.id.bear);
        cat = (ImageView)findViewById(R.id.cat);
        cow = (ImageView)findViewById(R.id.cow);
        dog = (ImageView)findViewById(R.id.dog);
        elephant = (ImageView)findViewById(R.id.elephant);
        ferret = (ImageView)findViewById(R.id.ferret);
        hippo = (ImageView)findViewById(R.id.hippopotamus);
        hourse = (ImageView)findViewById(R.id.horse);
        koals = (ImageView)findViewById(R.id.koala);
        lion = (ImageView)findViewById(R.id.lion);
        reindeer = (ImageView)findViewById(R.id.reindeer);

        setArrayView();
        setClickListener();

        setUpModel();
        arFragment.setOnTapArPlaneListener(new BaseArFragment.OnTapArPlaneListener() {
            @Override
            public void onTapPlane(HitResult hitResult, Plane plane, MotionEvent motionEvent) {

                Anchor anchor = hitResult.createAnchor();
                AnchorNode anchorNode = new AnchorNode(anchor);
                anchorNode.setParent(arFragment.getArSceneView().getScene());

                createModel(anchorNode, selected);

            }
        });


    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void createModel(AnchorNode anchorNode, int selected) {
        if (selected == 1) {
            TransformableNode bear = new TransformableNode(arFragment.getTransformationSystem());
            bear.setParent(anchorNode);
            bear.setRenderable(bearRenderable);
            bear.select();

            addName(anchorNode, bear, "Oso");
        }
        if (selected == 2){
            TransformableNode bear = new TransformableNode(arFragment.getTransformationSystem());
            bear.setParent(anchorNode);
            bear.setRenderable(catRenderable);
            bear.select();

            addName(anchorNode,bear,"Gato");
        }
        if (selected == 3){
            TransformableNode bear = new TransformableNode(arFragment.getTransformationSystem());
            bear.setParent(anchorNode);
            bear.setRenderable(cowRenderable);
            bear.select();

            addName(anchorNode,bear,"Vaca");
        }
        if (selected == 4){
            TransformableNode bear = new TransformableNode(arFragment.getTransformationSystem());
            bear.setParent(anchorNode);
            bear.setRenderable(dogRenderable);
            bear.select();

            addName(anchorNode,bear,"Perro");
        }
        if (selected == 5){
            TransformableNode bear = new TransformableNode(arFragment.getTransformationSystem());
            bear.setParent(anchorNode);
            bear.setRenderable(elephantRenderable);
            bear.select();

            addName(anchorNode,bear,"Elefante");
        }
        if (selected == 6){
            TransformableNode bear = new TransformableNode(arFragment.getTransformationSystem());
            bear.setParent(anchorNode);
            bear.setRenderable(ferretRenderable);
            bear.select();

            addName(anchorNode,bear,"Hurón");
        }
        if (selected == 7){
            TransformableNode bear = new TransformableNode(arFragment.getTransformationSystem());
            bear.setParent(anchorNode);
            bear.setRenderable(hippoRenderable);
            bear.select();

            addName(anchorNode,bear,"Hipopótamo");
        }
        if (selected == 8){
            TransformableNode bear = new TransformableNode(arFragment.getTransformationSystem());
            bear.setParent(anchorNode);
            bear.setRenderable(hourseRenderable);
            bear.select();

            addName(anchorNode,bear,"Caballo");
        }
        if (selected == 9){
            TransformableNode bear = new TransformableNode(arFragment.getTransformationSystem());
            bear.setParent(anchorNode);
            bear.setRenderable(koalaRenderable);
            bear.select();

            addName(anchorNode,bear,"Koala");
        }
        if (selected == 10){
            TransformableNode bear = new TransformableNode(arFragment.getTransformationSystem());
            bear.setParent(anchorNode);
            bear.setRenderable(lionRenderable);
            bear.select();

            addName(anchorNode,bear,"León");
        }
        if (selected == 11){
            TransformableNode bear = new TransformableNode(arFragment.getTransformationSystem());
            bear.setParent(anchorNode);
            bear.setRenderable(reindeerRenderable);
            bear.select();

            addName(anchorNode,bear,"Reno");
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void addName(AnchorNode anchorNode, TransformableNode model, String name) {
        ViewRenderable.builder()
                .setView(this,R.layout.name_animal)
                .build()
                .thenAccept(ViewRenderable-> {
                    TransformableNode nameVIew = new TransformableNode(arFragment.getTransformationSystem());
                    nameVIew.setLocalPosition(new Vector3(0f,model.getLocalPosition().y+0.5f,0));
                    nameVIew.setParent(anchorNode);
                    nameVIew.setRenderable(ViewRenderable);
                    nameVIew.select();

                    TextView txt_name = (TextView)ViewRenderable.getView();
                    txt_name.setText(name);

                    txt_name.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            anchorNode.setParent(null);
                        }
                    });
                });

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void setUpModel() {
        ModelRenderable.builder()
                .setSource(this, R.raw.bear)
                .build().thenAccept(renderable -> bearRenderable = renderable)
                .exceptionally(
                        throwable -> {
                            Toast.makeText(this, "Unnable to load bear model", Toast.LENGTH_SHORT).show();
                            return null;
                        }
                );
        ModelRenderable.builder()
                .setSource(this,R.raw.cat)
                .build().thenAccept(renderable -> catRenderable = renderable)
                .exceptionally(
                        throwable -> {
                            Toast.makeText(this, "Unnable to load bear model", Toast.LENGTH_SHORT).show();
                            return null;
                        }
                );
        ModelRenderable.builder()
                .setSource(this,R.raw.cow)
                .build().thenAccept(renderable -> cowRenderable = renderable)
                .exceptionally(
                        throwable -> {
                            Toast.makeText(this, "Unnable to load bear model", Toast.LENGTH_SHORT).show();
                            return null;
                        }
                );
        ModelRenderable.builder()
                .setSource(this,R.raw.dog)
                .build().thenAccept(renderable -> dogRenderable = renderable)
                .exceptionally(
                        throwable -> {
                            Toast.makeText(this, "Unnable to load bear model", Toast.LENGTH_SHORT).show();
                            return null;
                        }
                );
        ModelRenderable.builder()
                .setSource(this,R.raw.elephant)
                .build().thenAccept(renderable -> elephantRenderable= renderable)
                .exceptionally(
                        throwable -> {
                            Toast.makeText(this, "Unnable to load bear model", Toast.LENGTH_SHORT).show();
                            return null;
                        }
                );
        ModelRenderable.builder()
                .setSource(this,R.raw.ferret)
                .build().thenAccept(renderable -> ferretRenderable= renderable)
                .exceptionally(
                        throwable -> {
                            Toast.makeText(this, "Unnable to load bear model", Toast.LENGTH_SHORT).show();
                            return null;
                        }
                );
        ModelRenderable.builder()
                .setSource(this,R.raw.hippopotamus)
                .build().thenAccept(renderable -> hippoRenderable = renderable)
                .exceptionally(
                        throwable -> {
                            Toast.makeText(this, "Unnable to load bear model", Toast.LENGTH_SHORT).show();
                            return null;
                        }
                );
        ModelRenderable.builder()
                .setSource(this,R.raw.horse)
                .build().thenAccept(renderable -> hourseRenderable= renderable)
                .exceptionally(
                        throwable -> {
                            Toast.makeText(this, "Unnable to load bear model", Toast.LENGTH_SHORT).show();
                            return null;
                        }
                );
        ModelRenderable.builder()
                .setSource(this,R.raw.koala_bear)
                .build().thenAccept(renderable -> koalaRenderable = renderable)
                .exceptionally(
                        throwable -> {
                            Toast.makeText(this, "Unnable to load bear model", Toast.LENGTH_SHORT).show();
                            return null;
                        }
                );

        ModelRenderable.builder()
                .setSource(this,R.raw.lion)
                .build().thenAccept(renderable -> lionRenderable= renderable)
                .exceptionally(
                        throwable -> {
                            Toast.makeText(this, "Unnable to load bear model", Toast.LENGTH_SHORT).show();
                            return null;
                        }
                );
        ModelRenderable.builder()
                .setSource(this,R.raw.reindeer)
                .build().thenAccept(renderable -> reindeerRenderable= renderable)
                .exceptionally(
                        throwable -> {
                            Toast.makeText(this, "Unnable to load bear model", Toast.LENGTH_SHORT).show();
                            return null;
                        }
                );
    }

    private void setClickListener() {
        for (int i = 0; i < arrayView.length;i++){
            arrayView[i].setOnClickListener(this);
        }
    }

    private void setArrayView() {
        arrayView = new View[]{
                bear,cat,cow,dog,elephant,ferret,hippo,hourse,koals,lion,reindeer
        };
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.bear) {
            selected = 1;
            setBackground(v.getId());
        }else if (v.getId() == R.id.cat){
            selected = 2;
            setBackground(v.getId());
        }else if (v.getId() == R.id.cow){
            selected = 3;
            setBackground(v.getId());
        }else if (v.getId() == R.id.dog){
            selected = 4;
            setBackground(v.getId());
        }else if (v.getId() == R.id.elephant){
            selected = 5;
            setBackground(v.getId());
        }else if (v.getId() == R.id.ferret){
            selected = 6;
            setBackground(v.getId());
        }else if (v.getId() == R.id.hippopotamus){
            selected = 7;
            setBackground(v.getId());
        }else if (v.getId() == R.id.horse){
            selected = 8;
            setBackground(v.getId());
        }else if (v.getId() == R.id.koala){
            selected = 9;
            setBackground(v.getId());
        }else if (v.getId() == R.id.lion){
            selected = 10;
            setBackground(v.getId());
        }else if (v.getId() == R.id.reindeer){
            selected = 11;
            setBackground(v.getId());
        }

    }

    private void setBackground(int id) {
        for (int i =0; i < arrayView.length;i++){
            if (arrayView[i].getId() == id)
                arrayView[i].setBackgroundColor(Color.parseColor("#80333639"));
            else
                arrayView[i].setBackgroundColor(Color.TRANSPARENT);
        }
    }
}
