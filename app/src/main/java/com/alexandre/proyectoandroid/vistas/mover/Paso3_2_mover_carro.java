package com.alexandre.proyectoandroid.vistas.mover;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alexandre.proyectoandroid.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Paso3_2_mover_carro extends Fragment {

    private Button btn_continuar_mobiliario_3_2;
    private Button btn_anterior_mobiliario_3_2;
    private Fragment_mover_3_2_c fm3_2;
    private EditText etUbicacion;

    public Paso3_2_mover_carro() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fm3_2 = (Fragment_mover_3_2_c) getActivity();
        View view = inflater.inflate(R.layout.fragment_paso3_2_mover_carro, container, false);
        etUbicacion = (EditText) view.findViewById(R.id.etUbicacion_retorno_c);
        btn_anterior_mobiliario_3_2 = (Button) view.findViewById(R.id.btn_anterior_carro_3_2);
        btn_anterior_mobiliario_3_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fm3_2.volver_anterior_mover_carro_3_2();
            }
        });

        btn_continuar_mobiliario_3_2 = (Button) view.findViewById(R.id.btn_siguiente_carro_3_2);
        btn_continuar_mobiliario_3_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etUbicacion.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(),"Ingrese una ubicación",Toast.LENGTH_LONG).show();
                }else{
                    fm3_2.pasar_siguiente_mover_carro_3_2(etUbicacion.getText().toString());
                }
            }
        });
        return view;
    }

    public interface Fragment_mover_3_2_c
    {
        void volver_anterior_mover_carro_3_2();
        void pasar_siguiente_mover_carro_3_2(String ubicacion);
    }

}
