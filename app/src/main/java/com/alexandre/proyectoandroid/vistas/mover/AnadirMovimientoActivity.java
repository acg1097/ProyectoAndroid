package com.alexandre.proyectoandroid.vistas.mover;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.alexandre.proyectoandroid.R;
import com.alexandre.proyectoandroid.beans.ActivoDTO;
import com.alexandre.proyectoandroid.daos.ActivoDAO;
import com.alexandre.proyectoandroid.daos.MovimientoDAO;
import com.alexandre.proyectoandroid.vistas.extras.SimpleScannerFragment_mover;

public class AnadirMovimientoActivity extends AppCompatActivity implements SimpleScannerFragment_mover.EnviarData2, PasoPrincipal_mover_fragment.Fragment_principal, Paso1_mover_mobiliario.Fragment_mover_1, Paso2_mover_mobiliario.Fragment_mover_2,Paso3_1_mover_mobiliario.Fragment_mover_3{

    private Toolbar toolbar;
    static final int P_PRINCIPAL = 0,
            MOBILIARIO_1 = 1,
            MOBILIARIO_2 = 2,
            MOBILIARIO_3_1 = 3,
            MOBILIARIO_3_2 = 4,
            CARRO_1 = 6,
            CARRO_2 = 7;

    static String tipo = "";

    static int P_ACTUAL = 0;
    public static String CODEBAR = "";
    public static String NROPLACA = "";

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            default:
                finish();
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        this.setContentView(R.layout.activity_anadir_movimiento);
        toolbar = (Toolbar) findViewById(R.id.toolbar_anadirMovimiento);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.setTitle("Mover");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_white_24dp);

        android.support.v4.app.FragmentManager fragmentomanager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentomanager.beginTransaction();
        PasoPrincipal_mover_fragment f1 = new PasoPrincipal_mover_fragment();
        transaction.replace(R.id.fragment_contenedor_mover, f1).commit();

    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public void pasar_siguiente_1(int tipo) {
        Toast.makeText(AnadirMovimientoActivity.this, tipo + "", Toast.LENGTH_SHORT).show();

        android.support.v4.app.FragmentManager fragmentomanager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentomanager.beginTransaction();
        if (tipo == 0) {
            P_ACTUAL = MOBILIARIO_1;
            Paso1_mover_mobiliario f1 = new Paso1_mover_mobiliario();
            transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
            transaction.replace(R.id.fragment_contenedor_mover, f1).commit();
        } else {
            P_ACTUAL = CARRO_1;
            Paso1_mover_carro f1 = new Paso1_mover_carro();
            transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
            transaction.replace(R.id.fragment_contenedor_mover, f1).commit();
        }
    }

    //MOBILIARIOS
    @Override
    public void pasar_siguiente_mover_mobiliario_2(String codigo,String tipo) {
        try {
            P_ACTUAL = MOBILIARIO_2;
            CODEBAR = codigo;
            android.support.v4.app.FragmentManager fragmentomanager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentomanager.beginTransaction();
            transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
            Paso2_mover_mobiliario f2 = new Paso2_mover_mobiliario();
            transaction.replace(R.id.fragment_contenedor_mover, f2).commit();
        } catch (Exception e) {
        }
    }

    @Override
    public void volver_anterior_mover_mobiliario_2() {
        try {
            P_ACTUAL = P_PRINCIPAL;
            android.support.v4.app.FragmentManager fragmentomanager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentomanager.beginTransaction();
            transaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right);
            PasoPrincipal_mover_fragment f1 = new PasoPrincipal_mover_fragment();
            transaction.replace(R.id.fragment_contenedor_mover, f1).commit();
        } catch (Exception e) {
        }
    }

    @Override
    public void enviarCodigo(String codigo, String tipo) {

        if (tipo.equals("salida")) {

            P_ACTUAL = MOBILIARIO_2;
            CODEBAR = codigo;
            android.support.v4.app.FragmentManager fragmentomanager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentomanager.beginTransaction();
            transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
            Paso2_mover_mobiliario f2 = new Paso2_mover_mobiliario();
            transaction.replace(R.id.fragment_contenedor_mover, f2).commit();

        } else {

            P_ACTUAL = MOBILIARIO_2;
            CODEBAR = codigo;
            android.support.v4.app.FragmentManager fragmentomanager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentomanager.beginTransaction();
            transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
            Paso2_mover_mobiliario f2 = new Paso2_mover_mobiliario();
            transaction.replace(R.id.fragment_contenedor_mover, f2).commit();

        }


    }

    @Override
    public void volver_anterior_mover_mobiliario_3() {
        try {
            P_ACTUAL = MOBILIARIO_2;
            android.support.v4.app.FragmentManager fragmentomanager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentomanager.beginTransaction();
            transaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right);
            Paso1_mover_mobiliario f1 = new Paso1_mover_mobiliario();
            transaction.replace(R.id.fragment_contenedor_mover, f1).commit();
        } catch (Exception e) {
        }
    }

    @Override
    public void pasar_siguiente_mover_mobiliario_3() {
        ActivoDAO dao = new ActivoDAO(AnadirMovimientoActivity.this);
        ActivoDTO a = dao.getActivoDTO(CODEBAR);
        Toast.makeText(AnadirMovimientoActivity.this, "ñi", Toast.LENGTH_LONG).show();
        if (a.getEstado().toString().toLowerCase().trim().equals("vendido")) {
            Toast.makeText(AnadirMovimientoActivity.this, "El activo ha sido vendido", Toast.LENGTH_LONG).show();
        } else {
            if(a.getEstado().toString().toLowerCase().trim().equals("en almacén")){
                P_ACTUAL = MOBILIARIO_3_1;
                android.support.v4.app.FragmentManager fragmentomanager = getSupportFragmentManager();
                FragmentTransaction transaction = fragmentomanager.beginTransaction();
                transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
                Paso3_1_mover_mobiliario f1 = new Paso3_1_mover_mobiliario();
                transaction.replace(R.id.fragment_contenedor_mover, f1).commit();
            }else{
                P_ACTUAL = MOBILIARIO_3_2;
                android.support.v4.app.FragmentManager fragmentomanager = getSupportFragmentManager();
                FragmentTransaction transaction = fragmentomanager.beginTransaction();
                transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
                Paso3_2_mover_mobiliario f1 = new Paso3_2_mover_mobiliario();
                transaction.replace(R.id.fragment_contenedor_mover, f1).commit();
            }


        }
    }

    @Override
    public void volver_anterior_mover_mobiliario_4() {

    }

    @Override
    public void pasar_registrar_movimiento_mobiliario_4(int tipo) {
        /*MovimientoDAO dao = new MovimientoDAO(AnadirMovimientoActivity.this);
        dao.registrarMovimiento(m);*/
        Toast.makeText(AnadirMovimientoActivity.this,"Movimiento registrado correctamente",Toast.LENGTH_LONG).show();
        finish();
    }
}
