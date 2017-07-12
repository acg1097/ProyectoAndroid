package com.alexandre.proyectoandroid.vistas.cotejar;

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
import com.alexandre.proyectoandroid.vistas.extras.SimpleScannerFragment;


public class Paso1_cotejar_mobiliario extends Fragment implements View.OnClickListener {

    private Button btn_siguiente_mobiliario_2;
    private Button btn_anterior_mobiliario_2;
    private EditText etCodebar;
    private Fragment_cotejar_1 fc1;

    public Paso1_cotejar_mobiliario() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cotejar_paso1_mobiliario, container, false);
        fc1 = (Fragment_cotejar_1) getActivity();

        etCodebar = (EditText) view.findViewById(R.id.etCodebar);

        btn_anterior_mobiliario_2 = (Button) view.findViewById(R.id.btn_anterior_mobiliario_2);
        btn_anterior_mobiliario_2.setOnClickListener(this);

        btn_siguiente_mobiliario_2 = (Button) view.findViewById(R.id.btn_siguiente_mobiliario_2);
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
                SimpleScannerFragment f1 = new SimpleScannerFragment();
                transaction.replace(R.id.contenedor_cotejar, f1).commit();
            }
        }, 500);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_siguiente_mobiliario_2:
                if (etCodebar.getText().toString().trim().length() > 0) {
                    ActivoDAO dao = new ActivoDAO(getActivity());
                    ActivoDTO mobiliario = dao.getActivoDTO(etCodebar.getText().toString().trim());

                    if (mobiliario.getMarca() == null) {
                        Toast.makeText(getActivity(), "No existe ningún mobiliario con ese codigo", Toast.LENGTH_LONG).show();
                    } else {
                        if (mobiliario.getEstado().toLowerCase().equals("en almacén")) {
                            fc1.pasar_siguiente_cotejar_mobiliario_2(etCodebar.getText().toString().trim());
                        } else {
                            Toast.makeText(getActivity(), "El mobiliario no se encuentra en el almacén", Toast.LENGTH_LONG).show();
                        }
                    }
                } else {
                    Toast.makeText(getActivity(), "Escanee el código de barra o ingreselo en la caja de texto", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.btn_anterior_mobiliario_2:
                fc1.volver_anterior_cotejar_mobiliario_2();
                break;
        }
    }

    public interface Fragment_cotejar_1 {
        void pasar_siguiente_cotejar_mobiliario_2(String codigo);

        void volver_anterior_cotejar_mobiliario_2();
    }

}
