package com.alexandre.proyectoandroid.vistas.mover;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.alexandre.proyectoandroid.R;
import com.alexandre.proyectoandroid.beans.ActivoDTO;
import com.alexandre.proyectoandroid.beans.MovimientoDTO;
import com.alexandre.proyectoandroid.daos.ActivoDAO;
import com.alexandre.proyectoandroid.daos.MovimientoDAO;
import com.alexandre.proyectoandroid.vistas.extras.SimpleScannerFragment_mover;

public class AnadirMovimientoActivity extends AppCompatActivity implements SimpleScannerFragment_mover.EnviarData2, PasoPrincipal_mover_fragment.Fragment_principal, Paso1_mover_mobiliario.Fragment_mover_1, Paso2_mover_mobiliario.Fragment_mover_2, Paso3_1_mover_mobiliario.Fragment_mover_3, Paso3_2_mover_mobiliario.Fragment_mover_3_2 ,Paso1_mover_carro.Fragment_mover_1_v,Paso2_mover_carro.Fragment_mover_2_v,Paso3_1_mover_carro.Fragment_mover_3_c,Paso3_2_mover_carro.Fragment_mover_3_2_c{

    private Toolbar toolbar;
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
    public void pasar_siguiente_1(int tipo) {
        android.support.v4.app.FragmentManager fragmentomanager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentomanager.beginTransaction();
        if (tipo == 0) {
            Paso1_mover_mobiliario f1 = new Paso1_mover_mobiliario();
            transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
            transaction.replace(R.id.fragment_contenedor_mover, f1).commit();
        } else {
            Paso1_mover_carro f1 = new Paso1_mover_carro();
            transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
            transaction.replace(R.id.fragment_contenedor_mover, f1).commit();
        }
    }

    //MOBILIARIOS
    @Override
    public void pasar_siguiente_mover_mobiliario_2(String codigo, String tipo) {
        try {
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
            CODEBAR = codigo;
            android.support.v4.app.FragmentManager fragmentomanager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentomanager.beginTransaction();
            transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
            Paso2_mover_mobiliario f2 = new Paso2_mover_mobiliario();
            transaction.replace(R.id.fragment_contenedor_mover, f2).commit();

        } else {
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
        if (a.getEstado().toString().toLowerCase().trim().equals("vendido")) {
            Toast.makeText(AnadirMovimientoActivity.this, "El activo ha sido vendido", Toast.LENGTH_LONG).show();
        } else {
            if (a.getEstado().toString().toLowerCase().trim().equals("en almacén")) {
                android.support.v4.app.FragmentManager fragmentomanager = getSupportFragmentManager();
                FragmentTransaction transaction = fragmentomanager.beginTransaction();
                transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
                Paso3_1_mover_mobiliario f1 = new Paso3_1_mover_mobiliario();
                transaction.replace(R.id.fragment_contenedor_mover, f1).commit();
            } else {
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
        android.support.v4.app.FragmentManager fragmentomanager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentomanager.beginTransaction();
        transaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right);
        Paso1_mover_mobiliario f1 = new Paso1_mover_mobiliario();
        transaction.replace(R.id.fragment_contenedor_mover, f1).commit();
    }

    @Override
    public void pasar_registrar_movimiento_mobiliario_4(String tipo, String area) {
        MovimientoDTO m = new MovimientoDTO();
        MovimientoDAO dao = new MovimientoDAO(AnadirMovimientoActivity.this);

        ActivoDAO a = new ActivoDAO(AnadirMovimientoActivity.this);

        m.setIdActivo(CODEBAR);
        m.setTipoActivo("Mobiliario");
        m.setTipoMovimiento(tipo);
        m.setAreaMovimiento(area);

        long ok = dao.ingresarMovimientoDTO(m);

        if (ok > 0) {
            ActivoDTO adto = new ActivoDTO();
            if (tipo.equals("Prestamo")) {
                adto.setEstado("Prestado");
            } else {
                adto.setEstado("Vendido");
            }
            adto.setId(m.getIdActivo());
            adto.setUbicacion(area);
            int ok2 = a.actualizarEstadoActivo(adto);
            if (ok2 > 0) {
                Toast.makeText(AnadirMovimientoActivity.this, "Movimiento registrado correctamente", Toast.LENGTH_LONG).show();
                setResult(RESULT_OK);
            }
        } else {
            Toast.makeText(AnadirMovimientoActivity.this, "No se pudo registrar el movimiento", Toast.LENGTH_LONG).show();
            setResult(RESULT_CANCELED);
        }
        finish();
    }

    @Override
    public void volver_anterior_mover_mobiliario_3_2() {
        android.support.v4.app.FragmentManager fragmentomanager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentomanager.beginTransaction();
        transaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right);
        Paso1_mover_mobiliario f1 = new Paso1_mover_mobiliario();
        transaction.replace(R.id.fragment_contenedor_mover, f1).commit();
    }

    @Override
    public void pasar_siguiente_mover_mobiliario_3_2(String ubicacion) {
        MovimientoDAO dao = new MovimientoDAO(AnadirMovimientoActivity.this);
        MovimientoDTO m = dao.BuscarMovimientoPorActivo(CODEBAR);


        m.setTipoActivo(m.getTipoActivo());
        m.setIdActivo(m.getIdActivo());
        m.setTipoMovimiento("Devolución");
        m.setAreaMovimiento(m.getAreaMovimiento());

        long okm = dao.ingresarMovimientoDTO(m);

        if (okm > 0) {

            ActivoDAO dao2 = new ActivoDAO(AnadirMovimientoActivity.this);

            ActivoDTO a = new ActivoDTO();
            a.setId(m.getIdActivo());
            a.setEstado("En almacén");
            a.setUbicacion(ubicacion);

            int ok = dao2.actualizarEstadoActivo(a);

            if (ok > 0) {
                Toast.makeText(AnadirMovimientoActivity.this, "Movimiento registrado correctamente", Toast.LENGTH_LONG).show();
                setResult(RESULT_OK);
            } else {
                Toast.makeText(AnadirMovimientoActivity.this, "No se pudo registrar el movimiento", Toast.LENGTH_LONG).show();
                setResult(RESULT_CANCELED);
            }
            finish();
        }


    }

    @Override
    public void pasar_siguiente_mover_carro_2(String codigo) {
        NROPLACA = codigo;
        android.support.v4.app.FragmentManager fragmentomanager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentomanager.beginTransaction();
        transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
        Paso2_mover_carro f1 = new Paso2_mover_carro();
        transaction.replace(R.id.fragment_contenedor_mover, f1).commit();
    }

    @Override
    public void volver_anterior_mover_carro_2() {
        android.support.v4.app.FragmentManager fragmentomanager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentomanager.beginTransaction();
        transaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right);
        PasoPrincipal_mover_fragment f1 = new PasoPrincipal_mover_fragment();
        transaction.replace(R.id.fragment_contenedor_mover, f1).commit();
    }

    @Override
    public void pasar_siguiente_mover_carro_3() {
        ActivoDAO dao = new ActivoDAO(AnadirMovimientoActivity.this);
        ActivoDTO a = dao.getActivoDTO(NROPLACA);
        if(a.getEstado().equals("En almacén")){
            android.support.v4.app.FragmentManager fragmentomanager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentomanager.beginTransaction();
            transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
            Paso3_1_mover_carro f1 = new Paso3_1_mover_carro();
            transaction.replace(R.id.fragment_contenedor_mover, f1).commit();
        }else{
            android.support.v4.app.FragmentManager fragmentomanager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentomanager.beginTransaction();
            transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
            Paso3_2_mover_carro f2 = new Paso3_2_mover_carro();
            transaction.replace(R.id.fragment_contenedor_mover, f2).commit();
        }
    }

    @Override
    public void volver_anterior_mover_carro_3() {
        android.support.v4.app.FragmentManager fragmentomanager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentomanager.beginTransaction();
        transaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right);
        Paso1_mover_carro f1 = new Paso1_mover_carro();
        transaction.replace(R.id.fragment_contenedor_mover, f1).commit();
    }

    @Override
    public void volver_anterior_mover_carro_4() {
        android.support.v4.app.FragmentManager fragmentomanager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentomanager.beginTransaction();
        transaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right);
        Paso1_mover_carro f1 = new Paso1_mover_carro();
        transaction.replace(R.id.fragment_contenedor_mover, f1).commit();
    }

    @Override
    public void pasar_registrar_movimiento_carro_4(String tipo, String area) {
        MovimientoDTO m = new MovimientoDTO();
        MovimientoDAO dao = new MovimientoDAO(AnadirMovimientoActivity.this);

        ActivoDAO a = new ActivoDAO(AnadirMovimientoActivity.this);

        m.setIdActivo(NROPLACA);
        m.setTipoActivo("Vehiculo");
        m.setTipoMovimiento(tipo);
        m.setAreaMovimiento(area);

        long ok = dao.ingresarMovimientoDTO(m);

        if (ok > 0) {
            ActivoDTO adto = new ActivoDTO();
            if (tipo.equals("Prestamo")) {
                adto.setEstado("Prestado");
            } else {
                adto.setEstado("Vendido");
            }
            adto.setId(m.getIdActivo());
            adto.setUbicacion(area);
            int ok2 = a.actualizarEstadoActivo(adto);
            if (ok2 > 0) {
                Toast.makeText(AnadirMovimientoActivity.this, "Movimiento registrado correctamente", Toast.LENGTH_LONG).show();
                setResult(RESULT_OK);
            }
        } else {
            Toast.makeText(AnadirMovimientoActivity.this, "No se pudo registrar el movimiento", Toast.LENGTH_LONG).show();
            setResult(RESULT_CANCELED);
        }
        finish();
    }

    @Override
    public void volver_anterior_mover_carro_3_2() {
        android.support.v4.app.FragmentManager fragmentomanager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentomanager.beginTransaction();
        transaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right);
        Paso1_mover_carro f1 = new Paso1_mover_carro();
        transaction.replace(R.id.fragment_contenedor_mover, f1).commit();
    }

    @Override
    public void pasar_siguiente_mover_carro_3_2(String ubicacion) {
        MovimientoDAO dao = new MovimientoDAO(AnadirMovimientoActivity.this);
        MovimientoDTO m = dao.BuscarMovimientoPorActivo(NROPLACA);


        m.setTipoActivo(m.getTipoActivo());
        m.setIdActivo(m.getIdActivo());
        m.setTipoMovimiento("Devolución");
        m.setAreaMovimiento(m.getAreaMovimiento());

        long okm = dao.ingresarMovimientoDTO(m);

        if (okm > 0) {

            ActivoDAO dao2 = new ActivoDAO(AnadirMovimientoActivity.this);

            ActivoDTO a = new ActivoDTO();
            a.setId(m.getIdActivo());
            a.setEstado("En almacén");
            a.setUbicacion(ubicacion);

            int ok = dao2.actualizarEstadoActivo(a);

            if (ok > 0) {
                Toast.makeText(AnadirMovimientoActivity.this, "Movimiento registrado correctamente", Toast.LENGTH_LONG).show();
                setResult(RESULT_OK);
            } else {
                Toast.makeText(AnadirMovimientoActivity.this, "No se pudo registrar el movimiento", Toast.LENGTH_LONG).show();
                setResult(RESULT_CANCELED);
            }
            finish();
        }
    }
}
