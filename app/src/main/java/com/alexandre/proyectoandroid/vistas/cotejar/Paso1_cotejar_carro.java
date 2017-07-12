package com.alexandre.proyectoandroid.vistas.cotejar;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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


/**
 * A simple {@link Fragment} subclass.
 */
public class Paso1_cotejar_carro extends Fragment implements View.OnClickListener {

    private EditText etPlaca;
    private Button btn_siguiente_carro_2;
    private Button btn_anterior_carro_2;
    private Fragment_cotejar_1 fc1;

    public Paso1_cotejar_carro() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fc1 = (Fragment_cotejar_1) getActivity();
        View view = inflater.inflate(R.layout.fragment_cotejar_paso1_carro, container, false);
        etPlaca = (EditText) view.findViewById(R.id.etPlaca_cotejar);
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

        btn_anterior_carro_2 = (Button) view.findViewById(R.id.btn_anterior_carro_2);
        btn_anterior_carro_2.setOnClickListener(this);

        btn_siguiente_carro_2 = (Button) view.findViewById(R.id.btn_siguiente_carro_2);
        btn_siguiente_carro_2.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_siguiente_carro_2:
                if (etPlaca.getText().toString().length() == 6) {
                    ActivoDAO dao = new ActivoDAO(getActivity());
                    ActivoDTO c = dao.getActivoDTO(etPlaca.getText().toString().trim());

                    if (c.getMarca() == null) {
                        Toast.makeText(getActivity(), "No existe ningún vehiculo con esa placa", Toast.LENGTH_LONG).show();
                    } else {
                        if (c.getEstado().toLowerCase().equals("en almacén")) {
                            fc1.pasar_siguiente_cotejar_carro_2(etPlaca.getText().toString().trim());
                        } else {
                            Toast.makeText(getActivity(), "El vehiculo no se encuentra en el almacén", Toast.LENGTH_LONG).show();
                        }
                    }
                } else {
                    Toast.makeText(getActivity(), "Ingrese un número de placa correcto", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.btn_anterior_carro_2:
                fc1.volver_anterior_cotejar_carro_2();
                break;
        }
    }

    public interface Fragment_cotejar_1 {
        void pasar_siguiente_cotejar_carro_2(String nroPlaca);

        void volver_anterior_cotejar_carro_2();
    }
}
