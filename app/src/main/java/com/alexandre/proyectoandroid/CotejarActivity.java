package com.alexandre.proyectoandroid;

import android.os.Handler;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class CotejarActivity extends AppCompatActivity{

    private Toolbar toolbar;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            default:
                finish();
        }
        return false;
    }

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.activity_cotejar);
        toolbar = (Toolbar) findViewById(R.id.toolbar_cotejar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.setTitle("Cotejar");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_white_24dp);


        android.support.v4.app.FragmentManager fragmentomanager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentomanager.beginTransaction();
        SimpleScannerFragment f1 = new SimpleScannerFragment();
        transaction.replace(R.id.contenedor_cotejar,f1).commit();

/*
        btnAnterior = (Button) findViewById(R.id.btnAnterior);
        btnAnterior.setVisibility(View.INVISIBLE);
        btnAnterior.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                android.support.v4.app.FragmentManager fragmentomanager = getSupportFragmentManager();
                FragmentTransaction transaction = fragmentomanager.beginTransaction();
                pantalla--;
                if(pantalla==0){
                    btnAnterior.setVisibility(View.INVISIBLE);
                }
            }
        });

        btnSiguiente = (Button) findViewById(R.id.btnSiguiente);
        btnSiguiente.setOnClickListener(new View.OnClickListener() {

            android.support.v4.app.FragmentManager fragmentomanager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentomanager.beginTransaction();

            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(),pantalla+"",Toast.LENGTH_SHORT).show();
                pantalla++;
                if(pantalla==1){
                    btnAnterior.setVisibility(View.VISIBLE);
                    SimpleScannerFragment f2 = new SimpleScannerFragment();
                    transaction.replace(R.id.contentedor_cotejar, f2).commit();
                }


            }
        });
*/
    }





}
