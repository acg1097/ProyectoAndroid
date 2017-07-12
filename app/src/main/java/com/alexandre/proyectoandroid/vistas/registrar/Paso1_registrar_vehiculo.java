package com.alexandre.proyectoandroid.vistas.registrar;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alexandre.proyectoandroid.R;
import com.alexandre.proyectoandroid.beans.ActivoDTO;
import com.alexandre.proyectoandroid.daos.ActivoDAO;
import com.alexandre.proyectoandroid.vistas.cotejar.Editar_carro;
import com.itextpdf.text.ExceptionConverter;

/**
 * A simple {@link Fragment} subclass.
 */
public class Paso1_registrar_vehiculo extends Fragment {

    private EditText etPlaca, etMarca, etModelo, etValor, etUbicacion;
    private Button btn_registrar, btn_volver;
    private Fragment_vehiculo_registrar fmr;

    public Paso1_registrar_vehiculo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_paso1_registrar_vehiculo, container, false);
        fmr = (Fragment_vehiculo_registrar) getActivity();

        etPlaca = (EditText) view.findViewById(R.id.etPlaca_registrar_v);
        etPlaca.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable et) {
                String placa = et.toString();

                if(!placa.equals(placa.toString().toUpperCase())){
                    etPlaca.setText(placa.toUpperCase());
                    etPlaca.setSelection(etPlaca.getText().length());
                }

            }
        });
        etMarca = (EditText) view.findViewById(R.id.etMarca_registrar_v);
        etModelo = (EditText) view.findViewById(R.id.etModelo_registrar_v);
        etValor = (EditText) view.findViewById(R.id.etValor_registrar_v);
        etUbicacion = (EditText) view.findViewById(R.id.etUbicacion_registrar_v);

        btn_volver = (Button) view.findViewById(R.id.btn_volver_registrar_vehiculo);
        btn_volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fmr.volver_anterior_vehiculo();
            }
        });
        btn_registrar = (Button) view.findViewById(R.id.btn_finalizar_registrar_vehiculo);
        btn_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validar()) {

                    ActivoDAO dao = new ActivoDAO(getActivity());
                    ActivoDTO a = dao.getActivoDTO(etPlaca.getText().toString());
                    if(a.getMarca()==null) {
                        ActivoDTO v = new ActivoDTO();
                        v.setId(etPlaca.getText().toString());
                        v.setMarca(etMarca.getText().toString());
                        v.setModelo(etModelo.getText().toString());
                        v.setValor(Double.parseDouble(etValor.getText() + ""));
                        v.setUbicacion(etUbicacion.getText().toString());
                        v.setTipo("Vehiculo");

                        fmr.pasar_siguiente_registrar_v(v);
                    }else{
                        Toast.makeText(getActivity(),"Ya existe un vehiculo con esa placa",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        return view;
    }

    public interface Fragment_vehiculo_registrar {
        void pasar_siguiente_registrar_v(ActivoDTO m);

        void volver_anterior_vehiculo();
    }

    private boolean validar() {
        if(etPlaca.getText().toString().trim().isEmpty() || etPlaca.getText().toString().trim().length()!=6){
            Toast.makeText(getActivity(),"Ingrese la placa correctamente",Toast.LENGTH_LONG).show();
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
