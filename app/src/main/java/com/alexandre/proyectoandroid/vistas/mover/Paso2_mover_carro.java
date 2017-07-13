package com.alexandre.proyectoandroid.vistas.mover;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.alexandre.proyectoandroid.R;
import com.alexandre.proyectoandroid.beans.ActivoDTO;
import com.alexandre.proyectoandroid.daos.ActivoDAO;


/**
 * A simple {@link Fragment} subclass.
 */
public class Paso2_mover_carro extends Fragment {

    private TextView tvPlaca, tvMarca, tvModelo, tvValor, tvUbicacion;
    private Button btnSiguiente, btnAnterior;
    private Fragment_mover_2_v fm2;
    ActivoDAO dao = new ActivoDAO(getActivity());
    ActivoDTO a = dao.getActivoDTO(AnadirMovimientoActivity.NROPLACA);

    public Paso2_mover_carro() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fm2 = (Fragment_mover_2_v) getActivity();
        View view = inflater.inflate(R.layout.fragment_paso2_mover_carro, container, false);
        btnSiguiente = (Button) view.findViewById(R.id.btn_continuar_carro_mover_2);
        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (a.getEstado().equals("Vendido")){
                    Toast.makeText(getActivity(),"Vehiculo vendido",Toast.LENGTH_LONG).show();
                }else{
                    fm2.pasar_siguiente_mover_carro_3();
                }

            }
        });
        btnAnterior = (Button) view.findViewById(R.id.btn_anterior_carro_mover_2);
        btnAnterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fm2.volver_anterior_mover_carro_3();
            }
        });

        tvPlaca = (TextView) view.findViewById(R.id.tv_numeroPlaca_mover);
        tvPlaca.setText(a.getId());
        tvMarca = (TextView) view.findViewById(R.id.tvMarca_mover_carro);
        tvMarca.setText(a.getMarca());
        tvModelo = (TextView) view.findViewById(R.id.tvModelo_mover_carro);
        tvModelo.setText(a.getModelo());
        tvValor = (TextView) view.findViewById(R.id.tvValor_mover_carro);
        tvValor.setText(a.getValor().toString());
        tvUbicacion = (TextView) view.findViewById(R.id.tvUbicacion_mover_carro);
        tvUbicacion.setText(a.getUbicacion());
        // Inflate the layout for this fragment
        return view;
    }

    public interface Fragment_mover_2_v {
        void pasar_siguiente_mover_carro_3();

        void volver_anterior_mover_carro_3();
    }

}
