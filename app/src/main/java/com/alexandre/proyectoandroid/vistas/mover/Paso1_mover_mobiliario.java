package com.alexandre.proyectoandroid.vistas.mover;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alexandre.proyectoandroid.R;
import com.alexandre.proyectoandroid.beans.ActivoDTO;
import com.alexandre.proyectoandroid.daos.ActivoDAO;
import com.alexandre.proyectoandroid.vistas.extras.SimpleScannerFragment_mover;


public class Paso1_mover_mobiliario extends Fragment implements View.OnClickListener {

    private Button btn_siguiente_mobiliario_2;
    private Button btn_anterior_mobiliario_2;
    private EditText etCodebar;
    private Fragment_mover_1 fm1;

    public Paso1_mover_mobiliario() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_paso1_mover_mobiliario, container, false);
        fm1 = (Fragment_mover_1) getActivity();

        etCodebar = (EditText) view.findViewById(R.id.etCodebar_mover);

        btn_anterior_mobiliario_2 = (Button) view.findViewById(R.id.btn_anterior_mobiliario_2_mover);
        btn_anterior_mobiliario_2.setOnClickListener(this);

        btn_siguiente_mobiliario_2 = (Button) view.findViewById(R.id.btn_siguiente_mobiliario_2_mover);
        btn_siguiente_mobiliario_2.setOnClickListener(this);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                // acciones que se ejecutan tras los milisegundos
                android.support.v4.app.FragmentManager fragmentomanager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentomanager.beginTransaction();
                SimpleScannerFragment_mover f1 = new SimpleScannerFragment_mover();
                transaction.replace(R.id.contenedor_mover, f1).commit();
            }
        }, 500);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_siguiente_mobiliario_2_mover:
                if (etCodebar.getText().toString().trim().length() > 0) {
                    ActivoDAO dao = new ActivoDAO(getActivity());
                    ActivoDTO mobiliario = dao.getActivoDTO(etCodebar.getText().toString().trim());

                    if (mobiliario.getMarca()==null) {
                        Toast.makeText(getActivity(), "No existe ningún mobiliario con ese codigo", Toast.LENGTH_LONG).show();
                    } else {
                        if (mobiliario.getEstado().toString().equals("En almacén")) {
                            fm1.pasar_siguiente_mover_mobiliario_2(etCodebar.getText().toString().trim(),"salida");
                        }else{
                            fm1.pasar_siguiente_mover_mobiliario_2(etCodebar.getText().toString().trim(),"entrada");
                        }
                    }
                } else {
                    Toast.makeText(getActivity(), "Escanee el código de barra o ingréselo en la caja de texto", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.btn_anterior_mobiliario_2_mover:
                fm1.volver_anterior_mover_mobiliario_2();
                break;
        }
    }

    public interface Fragment_mover_1 {
        void pasar_siguiente_mover_mobiliario_2(String codigo,String tipo);

        void volver_anterior_mover_mobiliario_2();
    }

}
