package com.alexandre.proyectoandroid.vistas.mover;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import com.alexandre.proyectoandroid.R;

public class Paso3_1_mover_mobiliario extends Fragment implements View.OnClickListener {


    private RadioButton rd_prestamo, rd_venta;
    private Button btn_siguiente_4_mover;
    private Button btn_anterior_4_mover;
    private Spinner spArea;

    private Fragment_mover_3 fm3;

    public Paso3_1_mover_mobiliario() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_paso3_1_mover_mobiliario, container, false);
        fm3 = (Fragment_mover_3) getActivity();
        rd_prestamo = (RadioButton) view.findViewById(R.id.radio_prestamo);
        rd_venta = (RadioButton) view.findViewById(R.id.radio_compra);
        spArea = (Spinner) view.findViewById(R.id.spArea);

        String [] areas = {"Administración","Contabilidad","Finanzas","Recursos humanos","Sistemas"};

        ArrayAdapter adapter = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_dropdown_item,areas);
        spArea.setAdapter(adapter);

        btn_siguiente_4_mover = (Button) view.findViewById(R.id.btn_continuar_mobiliario_4_mover);
        btn_siguiente_4_mover.setOnClickListener(this);
        btn_anterior_4_mover = (Button) view.findViewById(R.id.btn_anterior_mobiliario_4_mover);
        btn_anterior_4_mover.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_anterior_mobiliario_4_mover:
                fm3.volver_anterior_mover_mobiliario_4();
                break;
            case R.id.btn_continuar_mobiliario_4_mover:
                int tipo = -1;
                if (rd_prestamo.isChecked()) {
                    tipo = 0;
                } else {
                    tipo = 1;
                }
                String area;
                fm3.pasar_registrar_movimiento_mobiliario_4(tipo);
                break;
        }

    }

    public interface Fragment_mover_3 {
        void volver_anterior_mover_mobiliario_4();
        void pasar_registrar_movimiento_mobiliario_4(int tipo);
    }

}
