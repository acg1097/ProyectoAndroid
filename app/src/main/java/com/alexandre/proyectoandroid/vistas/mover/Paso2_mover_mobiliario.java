package com.alexandre.proyectoandroid.vistas.mover;


import android.content.Intent;
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
public class Paso2_mover_mobiliario extends Fragment implements View.OnClickListener{


    private TextView tvCodigo,tvDescripcion,tvMarca,tvModelo,tvValor,tvUbicacion;

    private Button btn_continuar_mobiliario_3;
    private Button btn_anterior_mobiliario_3;
    private Fragment_mover_2 fm2;



    public Paso2_mover_mobiliario() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_paso2_mover_mobiliario, container, false);

        String codebar = AnadirMovimientoActivity.CODEBAR;

        Toast.makeText(getContext(),codebar,Toast.LENGTH_SHORT).show();

        fm2 = (Fragment_mover_2) getActivity();

        ActivoDAO dao = new ActivoDAO(getActivity());
        ActivoDTO a = dao.getActivoDTO(codebar);

        tvCodigo = (TextView) view.findViewById(R.id.tvCodigo_mover);
        tvCodigo.setText(codebar);

        tvDescripcion = (TextView) view.findViewById(R.id.tvDescripcion_mover);
        tvDescripcion.setText(a.getDescripcion());

        tvMarca = (TextView) view.findViewById(R.id.tvMarca_mover);
        tvMarca.setText(a.getMarca());

        tvModelo = (TextView) view.findViewById(R.id.tvModelo_mover);
        tvModelo.setText(a.getModelo());

        tvValor = (TextView) view.findViewById(R.id.tvValor_mover);
        tvValor.setText(a.getValor()+"");

        tvUbicacion = (TextView) view.findViewById(R.id.tvUbicacion_mover);
        tvUbicacion.setText(a.getUbicacion());

        btn_anterior_mobiliario_3 = (Button) view.findViewById(R.id.btn_anterior_mobiliario_3_mover);
        btn_anterior_mobiliario_3.setOnClickListener(this);

        btn_continuar_mobiliario_3 = (Button) view.findViewById(R.id.btn_continuar_mobiliario_3_mover);
        btn_continuar_mobiliario_3.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_anterior_mobiliario_3_mover:
                fm2.volver_anterior_mover_mobiliario_3();
                break;
            case R.id.btn_continuar_mobiliario_3_mover:
                fm2.pasar_siguiente_mover_mobiliario_3();
                break;
        }
    }

    public interface Fragment_mover_2
    {
        void volver_anterior_mover_mobiliario_3();
        void pasar_siguiente_mover_mobiliario_3();
    }

}
