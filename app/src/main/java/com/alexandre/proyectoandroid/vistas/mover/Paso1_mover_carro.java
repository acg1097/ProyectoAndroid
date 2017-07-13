package com.alexandre.proyectoandroid.vistas.mover;


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

public class Paso1_mover_carro extends Fragment {

    private Fragment_mover_1_v fmv;
    private Button btn_siguiente_carro_2;
    private Button btn_anterior_carro_2;
    private EditText etPlaca;


    public Paso1_mover_carro() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fmv = (Fragment_mover_1_v)getActivity();
        View view = inflater.inflate(R.layout.fragment_paso1_mover_carro, container, false);
        etPlaca = (EditText) view.findViewById(R.id.etPlaca_mover);
        etPlaca.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

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
        btn_anterior_carro_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fmv.volver_anterior_mover_carro_2();
            }
        });

        btn_siguiente_carro_2 = (Button) view.findViewById(R.id.btn_siguiente_carro_2);
        btn_siguiente_carro_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etPlaca.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(), "Ingrese un número de placa", Toast.LENGTH_LONG).show();
                }else{
                    ActivoDAO dao = new ActivoDAO(getActivity());
                    ActivoDTO dto = dao.getActivoDTO(etPlaca.getText().toString());
                    if(dto.getMarca()==null){
                        Toast.makeText(getActivity(), "No existe ningún vehiculo con esa placa", Toast.LENGTH_LONG).show();
                    }else{
                        if (dto.getEstado().toString().equals("En almacén")) {
                            fmv.pasar_siguiente_mover_carro_2(etPlaca.getText().toString().trim());
                        }else{
                            fmv.pasar_siguiente_mover_carro_2(etPlaca.getText().toString().trim());
                        }
                    }
                }
            }
        });

        return view;
    }

    public interface Fragment_mover_1_v {
        void pasar_siguiente_mover_carro_2(String codigo);

        void volver_anterior_mover_carro_2();
    }

}
