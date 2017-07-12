package com.alexandre.proyectoandroid.vistas.cotejar;


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
public class Paso2_cotejar_carro extends Fragment implements View.OnClickListener {

    private TextView tvNumero,tvMarca,tvModelo,tvValor,tvUbicacion;

    private Button btn_editar;
    private Button btn_finalizar;
    private Button btn_anterior_carro_3;
    private Fragment_cotejar_2 fc2;

    public Paso2_cotejar_carro() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cotejar_paso2_carro, container, false);

        String numeroPlaca = CotejarActivity.NROPLACA;

        Toast.makeText(getActivity(),numeroPlaca,Toast.LENGTH_SHORT).show();

        fc2 = (Fragment_cotejar_2) getActivity();
        tvNumero = (TextView) view.findViewById(R.id.tv_numeroPlaca_cotejar);
        tvNumero.setText(numeroPlaca);

        ActivoDAO dao = new ActivoDAO(getActivity());
        ActivoDTO v = dao.getActivoDTO(numeroPlaca);

        tvMarca = (TextView) view.findViewById(R.id.tvMarca_cotejar_carro);
        tvMarca.setText(v.getMarca());

        tvModelo = (TextView) view.findViewById(R.id.tvModelo_cotejar_carro);
        tvModelo.setText(v.getModelo());

        tvValor = (TextView) view.findViewById(R.id.tvValor_cotejar_carro);
        tvValor.setText(v.getValor().toString());

        tvUbicacion = (TextView) view.findViewById(R.id.tvUbicacion_cotejar_carro);
        tvUbicacion.setText(v.getUbicacion());

        btn_anterior_carro_3 = (Button) view.findViewById(R.id.btn_anterior_carro_3);
        btn_anterior_carro_3.setOnClickListener(this);

        btn_editar = (Button) view.findViewById(R.id.btn_editar_carro);
        btn_editar.setOnClickListener(this);

        btn_finalizar = (Button) view.findViewById(R.id.btn_finalizar_carro);
        btn_finalizar.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_finalizar_carro:
                getActivity().finish();
                break;
            case R.id.btn_anterior_carro_3:
                fc2.volver_anterior_cotejar_carro_3();
                break;
            case R.id.btn_editar_carro:
                Intent i = new Intent(getActivity(),Editar_carro.class);
                i.putExtra("codigo",tvNumero.getText().toString().trim());
                startActivityForResult(i,1);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == getActivity().RESULT_OK){
            ActivoDAO dao = new ActivoDAO(getActivity());
            ActivoDTO a = dao.getActivoDTO(data.getStringExtra("codigo"));
            tvMarca.setText(a.getMarca().toString());
            tvModelo.setText(a.getModelo().toString());
            tvValor.setText(a.getValor().toString()+"");
            tvUbicacion.setText(a.getUbicacion().toString());
        }
    }

    public interface Fragment_cotejar_2
    {
        void volver_anterior_cotejar_carro_3();
    }

}
