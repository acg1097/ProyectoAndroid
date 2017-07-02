package com.alexandre.proyectoandroid;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AnadirMovimientoActivity extends AppCompatActivity {

    int paginaActual = 0;
    ViewPager pager = null;
    private Toolbar toolbar;

    MiFragmentPagerAdapter adapter = new MiFragmentPagerAdapter(
            getSupportFragmentManager());

    Button btnAnterior,btnSiguiente;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
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
        toolbar.setTitle("Movimiento");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_white_24dp);

        //Botones
        btnAnterior = (Button) findViewById(R.id.btnAnterior);
        btnSiguiente = (Button) findViewById(R.id.btnSiguiente);

        //Ocular boton volver
        btnAnterior.setVisibility(View.INVISIBLE);
        // Instantiate a ViewPager
        pager = (ViewPager) this.findViewById(R.id.pager);
        //bloquear swipe
        pager.beginFakeDrag();
        // Create an adapter with the fragments we show on the ViewPager
        adapter.addFragment(new PasoPrincipal_cotejar_Fragment());

        pager.setAdapter(adapter);

        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                avanzar();
            }
        });

        btnAnterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retroceder();
            }
        });

    }

    @Override
    public void onBackPressed() {
        retroceder();
    }

    private void retroceder() {
        if (pager.getCurrentItem() == 0) {
            finish();
        } else if (pager.getCurrentItem() == 1) {
            //Desactivar boton
            btnAnterior.setVisibility(View.GONE);
            //activar boton home
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            //Retroceder una pagina
            pager.setCurrentItem(pager.getCurrentItem() - 1);

        }else if(pager.getCurrentItem() == 2){
            btnSiguiente.setText("Continuar");
            pager.setCurrentItem(pager.getCurrentItem()-1);
        }else{
            pager.setCurrentItem(pager.getCurrentItem()-1);
        }
    }

    private void avanzar(){
        if(pager.getCurrentItem()==0) {
            if(adapter.getFragments().size()==1)
                definirFragments();
            else{
                pager.setCurrentItem(pager.getCurrentItem()+1);
            }
        }else if(pager.getCurrentItem()==1){
            btnSiguiente.setText("Guardar");
            pager.setCurrentItem(pager.getCurrentItem()+1);
        }
        else if(pager.getCurrentItem()==2){
            Toast.makeText(getBaseContext(),"Muy bien",Toast.LENGTH_SHORT).show();
        }
    }


    int eleccion = 99;
    private void definirFragments(){
        if(eleccion==99){

        }else if(eleccion==1){

        }else{

        }
        if(adapter.getFragments().size()>1){
            adapter.addFragment(new Paso1_cotejar_carro());
            adapter.addFragment(new Paso2_cotejar_carro());
            pager.setAdapter(adapter);
            Toast.makeText(getBaseContext(),"Listo",Toast.LENGTH_SHORT).show();
        }

        if(true) {
            adapter.addFragment(new Paso1_cotejar_carro());
            adapter.addFragment(new Paso2_cotejar_carro());
        }
        else{
            adapter.addFragment(new MovimientosFragment());
        }

        adapter.notifyDataSetChanged();

        btnAnterior.setVisibility(View.VISIBLE);
        pager.setCurrentItem(pager.getCurrentItem()+1);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

}
