package com.alexandre.proyectoandroid.vistas.registrar;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alexandre.proyectoandroid.R;
import com.alexandre.proyectoandroid.beans.ActivoDTO;

/**
 * A simple {@link Fragment} subclass.
 */
public class Paso1_registrar_mobiliario extends Fragment {

    private EditText etDescripcion, etMarca, etModelo, etValor, etUbicacion;
    private Button btn_registrar, btn_volver;
    private Fragment_mobiliario_registrar fmr;

    public Paso1_registrar_mobiliario() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_paso1_registrar_mobiliario, container, false);
        fmr = (Fragment_mobiliario_registrar) getActivity();

        etDescripcion = (EditText) view.findViewById(R.id.etDescripcion_registrar);
        etMarca = (EditText) view.findViewById(R.id.etMarca_registrar);
        etModelo = (EditText) view.findViewById(R.id.etModelo_registrar);
        etValor = (EditText) view.findViewById(R.id.etValor_registrar);
        etUbicacion = (EditText) view.findViewById(R.id.etUbicacion_registrar);

        btn_volver = (Button) view.findViewById(R.id.btn_volver_registrar_mobiliario);
        btn_volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fmr.volver_anterior_mobiliario();
            }
        });
        btn_registrar = (Button) view.findViewById(R.id.btn_finalizar_registrar_mobiliario);
        btn_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validar()) {

                    ActivoDTO m = new ActivoDTO();
                    m.setDescripcion(etDescripcion.getText().toString());
                    m.setMarca(etMarca.getText().toString());
                    m.setModelo(etModelo.getText().toString());
                    m.setValor(Double.parseDouble(etValor.getText()+""));
                    m.setUbicacion(etUbicacion.getText().toString());
                    m.setTipo("Mobiliario");

                    fmr.pasar_siguiente_registrar(m);
                }
            }
        });
        return view;
    }

    public interface Fragment_mobiliario_registrar {
        void pasar_siguiente_registrar(ActivoDTO m);

        void volver_anterior_mobiliario();
    }

    private boolean validar() {
        if(etDescripcion.getText().toString().trim().isEmpty()){
            Toast.makeText(getActivity(),"Ingrese la descripción",Toast.LENGTH_LONG).show();
            return false;
        }
        if(etMarca.getText().toString().trim().isEmpty()){
            Toast.makeText(getActivity(),"Ingrese la marca",Toast.LENGTH_LONG).show();
            return false;
        }
        if(etModelo.getText().toString().trim().isEmpty()){
            Toast.makeText(getActivity(),"Ingrese el modelo",Toast.LENGTH_LONG).show();
            return false;
        }
        if(etValor.getText().toString().trim().isEmpty()){
            Toast.makeText(getActivity(),"Ingrese el valor",Toast.LENGTH_LONG).show();
            return false;
        }
        if(etUbicacion.getText().toString().trim().isEmpty()){
            Toast.makeText(getActivity(),"Ingrese la ubicación",Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }
}
