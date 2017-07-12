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
public class Paso2_cotejar_mobiliario extends Fragment implements View.OnClickListener {

    private TextView tvCodigo,tvDescripcion,tvMarca,tvModelo,tvValor,tvUbicacion;

    private Button btn_editar;
    private Button btn_finalizar;
    private Button btn_anterior_mobiliario_3;
    private Fragment_cotejar_2 fc2;



    public Paso2_cotejar_mobiliario() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cotejar_paso2_mobiliario, container, false);
        fc2 = (Fragment_cotejar_2) getActivity();

        String codebar = CotejarActivity.CODEBAR;
        ActivoDAO dao = new ActivoDAO(getActivity());
        ActivoDTO activo = dao.getActivoDTO(codebar);

        tvCodigo = (TextView) view.findViewById(R.id.tvCodigo_cotejar);
        tvCodigo.setText(activo.getId());
        tvDescripcion = (TextView) view.findViewById(R.id.tvDescripcion_cotejar);
        tvDescripcion.setText(activo.getDescripcion());
        tvMarca = (TextView) view.findViewById(R.id.tvMarca_cotejar);
        tvMarca.setText(activo.getMarca());
        tvModelo = (TextView) view.findViewById(R.id.tvModelo_cotejar);
        tvModelo.setText(activo.getModelo());
        tvUbicacion = (TextView) view.findViewById(R.id.tvUbicacion_cotejar);
        tvUbicacion.setText(activo.getUbicacion());
        tvValor = (TextView) view.findViewById(R.id.tvValor_cotejar);
        tvValor.setText(activo.getValor()+"");

        btn_anterior_mobiliario_3 = (Button) view.findViewById(R.id.btn_anterior_mobiliario_3);
        btn_anterior_mobiliario_3.setOnClickListener(this);

        btn_editar = (Button) view.findViewById(R.id.btn_editar_mobiliario);
        btn_editar.setOnClickListener(this);

        btn_finalizar = (Button) view.findViewById(R.id.btn_finalizar_mobiliario);
        btn_finalizar.setOnClickListener(this);
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == getActivity().RESULT_OK){
            ActivoDAO dao = new ActivoDAO(getActivity());
            ActivoDTO a = dao.getActivoDTO(data.getStringExtra("codigo"));
            tvMarca.setText(a.getMarca().toString());
            tvModelo.setText(a.getModelo().toString());
            tvDescripcion.setText(a.getDescripcion().toString());
            tvValor.setText(a.getValor().toString()+"");
            tvUbicacion.setText(a.getUbicacion().toString());
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_finalizar_mobiliario:
                getActivity().finish();
                break;
            case R.id.btn_anterior_mobiliario_3:
                fc2.volver_anterior_cotejar_mobiliario_3();
                break;
            case R.id.btn_editar_mobiliario:
                Intent i = new Intent(getActivity(),Editar_mobiliario.class);
                i.putExtra("codigo",tvCodigo.getText().toString().trim());
                startActivityForResult(i,1);
                break;
        }
    }

    public interface Fragment_cotejar_2
    {
        void volver_anterior_cotejar_mobiliario_3();
    }
}
