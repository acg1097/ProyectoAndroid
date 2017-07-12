package com.alexandre.proyectoandroid.vistas.cotejar;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.alexandre.proyectoandroid.R;
import com.alexandre.proyectoandroid.vistas.extras.SimpleScannerFragment;

public class CotejarActivity extends AppCompatActivity implements SimpleScannerFragment.EnviarData, PasoPrincipal_cotejar_Fragment.Fragment_principal, Paso1_cotejar_mobiliario.Fragment_cotejar_1, Paso2_cotejar_mobiliario.Fragment_cotejar_2, Paso1_cotejar_carro.Fragment_cotejar_1, Paso2_cotejar_carro.Fragment_cotejar_2 {

    private Toolbar toolbar;
    static final int P_PRINCIPAL = 0,
            MOBILIARIO_1 = 1,
            MOBILIARIO_2 = 2,
            CARRO_1 = 3,
            CARRO_2 = 4;

    static int P_ACTUAL = 0;
    public static String CODEBAR = "";
    public static String NROPLACA = "";

    @Override
    public void onBackPressed() {
        try {
            android.support.v4.app.FragmentManager fragmentomanager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentomanager.beginTransaction();
            switch (P_ACTUAL) {
                case P_PRINCIPAL:
                    finish();
                    break;
                case MOBILIARIO_1:
                    P_ACTUAL = P_PRINCIPAL;
                    PasoPrincipal_cotejar_Fragment f1 = new PasoPrincipal_cotejar_Fragment();
                    transaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right);
                    transaction.replace(R.id.fragment_contenedor_cotejar, f1).commit();
                    break;
                case MOBILIARIO_2:
                    P_ACTUAL = MOBILIARIO_1;
                    Paso1_cotejar_mobiliario f2 = new Paso1_cotejar_mobiliario();
                    transaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right);
                    transaction.replace(R.id.fragment_contenedor_cotejar, f2).commit();
                    break;
                case CARRO_1:
                    P_ACTUAL = P_PRINCIPAL;
                    PasoPrincipal_cotejar_Fragment f3 = new PasoPrincipal_cotejar_Fragment();
                    transaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right);
                    transaction.replace(R.id.fragment_contenedor_cotejar, f3).commit();
                    break;
                case CARRO_2:
                    P_ACTUAL = CARRO_1;
                    Paso1_cotejar_carro f4 = new Paso1_cotejar_carro();
                    transaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right);
                    transaction.replace(R.id.fragment_contenedor_cotejar, f4).commit();
                    break;
            }
        } catch (Exception e) {
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
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
        PasoPrincipal_cotejar_Fragment f1 = new PasoPrincipal_cotejar_Fragment();
        transaction.replace(R.id.fragment_contenedor_cotejar, f1).commit();
    }


    @Override
    public void enviarCodigo(String codigo) {
        try {
            P_ACTUAL = MOBILIARIO_2;
            CODEBAR = codigo;
            android.support.v4.app.FragmentManager fragmentomanager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentomanager.beginTransaction();
            transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
            Paso2_cotejar_mobiliario f2 = new Paso2_cotejar_mobiliario();
            transaction.replace(R.id.fragment_contenedor_cotejar, f2).commit();
        } catch (Exception e) {
        }
    }

    @Override
    public void pasar_siguiente_1(int tipo) {
        try {
            android.support.v4.app.FragmentManager fragmentomanager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentomanager.beginTransaction();
            if (tipo == 0) {
                P_ACTUAL = MOBILIARIO_1;
                Paso1_cotejar_mobiliario f1 = new Paso1_cotejar_mobiliario();
                transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
                transaction.replace(R.id.fragment_contenedor_cotejar, f1).commit();
            } else {
                P_ACTUAL = CARRO_1;
                Paso1_cotejar_carro f1 = new Paso1_cotejar_carro();
                transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
                transaction.replace(R.id.fragment_contenedor_cotejar, f1).commit();
            }
        } catch (Exception e) {
        }
    }

    //MOBILIARIOS
    @Override
    public void pasar_siguiente_cotejar_mobiliario_2(String codigo) {
        try {
            P_ACTUAL = MOBILIARIO_2;
            CODEBAR = codigo;
            android.support.v4.app.FragmentManager fragmentomanager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentomanager.beginTransaction();
            transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
            Paso2_cotejar_mobiliario f2 = new Paso2_cotejar_mobiliario();
            transaction.replace(R.id.fragment_contenedor_cotejar, f2).commit();
        } catch (Exception e) {
        }
    }

    @Override
    public void volver_anterior_cotejar_mobiliario_2() {
        try {
            P_ACTUAL = P_PRINCIPAL;
            android.support.v4.app.FragmentManager fragmentomanager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentomanager.beginTransaction();
            transaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right);
            PasoPrincipal_cotejar_Fragment f1 = new PasoPrincipal_cotejar_Fragment();
            transaction.replace(R.id.fragment_contenedor_cotejar, f1).commit();
        } catch (Exception e) {
        }
    }

    @Override
    public void volver_anterior_cotejar_mobiliario_3() {
        try {
            P_ACTUAL = MOBILIARIO_1;
            android.support.v4.app.FragmentManager fragmentomanager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentomanager.beginTransaction();
            transaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right);
            Paso1_cotejar_mobiliario f1 = new Paso1_cotejar_mobiliario();
            transaction.replace(R.id.fragment_contenedor_cotejar, f1).commit();
        } catch (Exception e) {
        }
    }

    //CARROS
    @Override
    public void pasar_siguiente_cotejar_carro_2(String nroPlaca) {
        try {
            NROPLACA = nroPlaca;

            P_ACTUAL = CARRO_2;
            android.support.v4.app.FragmentManager fragmentomanager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentomanager.beginTransaction();
            transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
            Paso2_cotejar_carro f2 = new Paso2_cotejar_carro();
            transaction.replace(R.id.fragment_contenedor_cotejar, f2).commit();
        } catch (Exception e) {
        }
    }

    @Override
    public void volver_anterior_cotejar_carro_2() {
        try {
            P_ACTUAL = P_PRINCIPAL;
            android.support.v4.app.FragmentManager fragmentomanager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentomanager.beginTransaction();
            transaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right);
            PasoPrincipal_cotejar_Fragment f1 = new PasoPrincipal_cotejar_Fragment();
            transaction.replace(R.id.fragment_contenedor_cotejar, f1).commit();
        } catch (Exception e) {
        }
    }

    @Override
    public void volver_anterior_cotejar_carro_3() {
        try {
            P_ACTUAL = CARRO_1;
            android.support.v4.app.FragmentManager fragmentomanager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentomanager.beginTransaction();
            transaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right);
            Paso1_cotejar_carro f1 = new Paso1_cotejar_carro();
            transaction.replace(R.id.fragment_contenedor_cotejar, f1).commit();
        } catch (Exception e) {
        }
    }
}
