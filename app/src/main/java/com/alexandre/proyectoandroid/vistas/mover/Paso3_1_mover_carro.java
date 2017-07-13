package com.alexandre.proyectoandroid.vistas.mover;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.alexandre.proyectoandroid.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Paso3_1_mover_carro extends Fragment {


    private RadioButton rd_prestamo, rd_venta;
    private Button btn_siguiente_4_mover;
    private Button btn_anterior_4_mover;
    private Spinner spArea;

    private Fragment_mover_3_c fm3c;

    private ArrayAdapter spAdapter;

    public Paso3_1_mover_carro() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_paso3_1_mover_carro, container, false);
        fm3c = (Fragment_mover_3_c) getActivity();
        rd_prestamo = (RadioButton) view.findViewById(R.id.radio_prestamo_c);
        rd_venta = (RadioButton) view.findViewById(R.id.radio_compra_c);
        spArea = (Spinner) view.findViewById(R.id.spArea_c);

        String[] areas = getResources().getStringArray(R.array.Areas);

        spAdapter = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_dropdown_item,areas);
        spArea.setAdapter(spAdapter);

        btn_siguiente_4_mover = (Button) view.findViewById(R.id.btn_continuar_carro_4_mover);
        btn_siguiente_4_mover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tipo = null;
                if (rd_prestamo.isChecked()) {
                    tipo = "Prestamo";
                } else {
                    tipo = "Venta";
                }
                String area = spArea.getItemAtPosition(spArea.getSelectedItemPosition()).toString();

                fm3c.pasar_registrar_movimiento_carro_4(tipo,area);
            }
        });
        btn_anterior_4_mover = (Button) view.findViewById(R.id.btn_anterior_carro_4_mover);
        btn_anterior_4_mover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fm3c.volver_anterior_mover_carro_4();
            }
        });
        return view;
    }

    public interface Fragment_mover_3_c {
        void volver_anterior_mover_carro_4();
        void pasar_registrar_movimiento_carro_4(String tipo,String area);
    }

}
