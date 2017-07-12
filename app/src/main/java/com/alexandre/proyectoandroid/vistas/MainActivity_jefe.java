package com.alexandre.proyectoandroid.vistas;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.alexandre.proyectoandroid.R;

public class MainActivity_jefe extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private int pantallaActual = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_jefe);

        AlmacenFragment f1 = new AlmacenFragment();
        establecerFragment(f1);



        bottomNavigationView = (BottomNavigationView) findViewById(R.id.btmNavBar_jefe);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.ic_almacen:
                        if(pantallaActual!=1) {
                            AlmacenFragment f1 = new AlmacenFragment();
                            establecerFragment(f1);
                            pantallaActual=1;
                            return true;
                        }else{
                            return false;
                        }
                    case R.id.ic_perfil:
                        if(pantallaActual!=2) {
                            PerfilFragment f2 = new PerfilFragment();
                            establecerFragment(f2);
                            pantallaActual=2;
                            return true;
                        }else{
                            return false;
                        }
                }
                return false;
            }
        });
    }

    private void establecerFragment(android.support.v4.app.Fragment fragment) {
        android.support.v4.app.FragmentManager fragmentomanager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentomanager.beginTransaction();
        transaction.replace(R.id.fragment_contenedor_jefe,fragment).commit();
    }
}
