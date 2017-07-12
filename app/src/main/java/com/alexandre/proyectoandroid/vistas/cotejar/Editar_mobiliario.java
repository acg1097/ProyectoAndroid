package com.alexandre.proyectoandroid.vistas.cotejar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alexandre.proyectoandroid.R;
import com.alexandre.proyectoandroid.beans.ActivoDTO;
import com.alexandre.proyectoandroid.daos.ActivoDAO;

public class Editar_mobiliario extends AppCompatActivity {

    private TextView tvCodigo;
    private EditText etDescripcion,etMarca,etModelo,etValor,etUbicacion;
    private Button btnGuardar,btnCancelar;
    private Toolbar toolbar;

    ActivoDAO dao = new ActivoDAO(Editar_mobiliario.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_mobiliario);

        toolbar = (Toolbar) findViewById(R.id.toolbar_editar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.setTitle("Editar mobiliario");
        setSupportActionBar(toolbar);

        Intent i = getIntent();
        String codigo = i.getStringExtra("codigo");

        ActivoDTO a = dao.getActivoDTO(codigo);

        tvCodigo = (TextView) findViewById(R.id.tvCodigo_editar_mobiliario);
        tvCodigo.setText(a.getId());

        etDescripcion = (EditText) findViewById(R.id.etDescripcion_editar_mobiliario);
        etDescripcion.setText(a.getDescripcion());

        etMarca = (EditText) findViewById(R.id.etMarca_editar_mobiliario);
        etMarca.setText(a.getMarca());

        etModelo = (EditText) findViewById(R.id.etModelo_editar_mobiliario);
        etModelo.setText(a.getModelo());

        etValor = (EditText) findViewById(R.id.etValor_editar_mobiliario);
        etValor.setText(a.getValor()+"");

        etUbicacion = (EditText) findViewById(R.id.etUbicacion_editar_mobiliario);
        etUbicacion.setText(a.getUbicacion());

        btnGuardar = (Button) findViewById(R.id.btn_guardar_editar_mobiliario);
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(validar()) {

                    ActivoDTO activo = new ActivoDTO();
                    activo.setId(tvCodigo.getText().toString());
                    activo.setDescripcion(etDescripcion.getText().toString());
                    activo.setMarca(etMarca.getText().toString());
                    activo.setModelo(etModelo.getText().toString());
                    activo.setValor(Double.parseDouble(etValor.getText().toString()));
                    activo.setUbicacion(etUbicacion.getText().toString());

                    Intent i = new Intent();
                    i.putExtra("codigo", activo.getId());

                    int ok = dao.actualizarActivo(activo);

                    if (ok > 0) {
                        Toast.makeText(Editar_mobiliario.this, "Guardado correctamente", Toast.LENGTH_LONG).show();
                        setResult(RESULT_OK, i);
                        finish();
                    } else {
                        finish();
                    }
                }
            }
        });

        btnCancelar = (Button)findViewById(R.id.btn_cancelar_editar_mobiliario);
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private boolean validar(){
        if(etDescripcion.getText().toString().isEmpty()){
            Toast.makeText(Editar_mobiliario.this, "Ingrese una descripción", Toast.LENGTH_LONG).show();
            return false;
        }
        if(etMarca.getText().toString().isEmpty()){
            Toast.makeText(Editar_mobiliario.this, "Ingrese una marca", Toast.LENGTH_LONG).show();
            return false;
        }
        if(etModelo.getText().toString().isEmpty()){
            Toast.makeText(Editar_mobiliario.this, "Ingrese un modelo", Toast.LENGTH_LONG).show();
            return false;
        }
        if(etValor.getText().toString().isEmpty()){
            Toast.makeText(Editar_mobiliario.this, "Ingrese un valor", Toast.LENGTH_LONG).show();
            return false;
        }
        if(etUbicacion.getText().toString().isEmpty()){
            Toast.makeText(Editar_mobiliario.this, "Ingrese una ubicación", Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }

}
