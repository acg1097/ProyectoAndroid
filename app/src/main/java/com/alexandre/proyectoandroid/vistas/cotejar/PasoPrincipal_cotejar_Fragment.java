package com.alexandre.proyectoandroid.vistas.cotejar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import com.alexandre.proyectoandroid.R;

public class PasoPrincipal_cotejar_Fragment extends Fragment implements View.OnClickListener {

    private RadioButton rd_vehiculo,rd_mobiliario;
    private Button btn_siguiente_1;
    private Fragment_principal fp;

    public PasoPrincipal_cotejar_Fragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_cotejar_paso_principal, container, false);
        fp = (Fragment_principal)getActivity();
        rd_mobiliario = (RadioButton) view.findViewById(R.id.radio_mobiliario);
        rd_vehiculo = (RadioButton) view.findViewById(R.id.radio_vehiculo);
        btn_siguiente_1 = (Button) view.findViewById(R.id.btn_siguiente_1);
        btn_siguiente_1.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        int tipo = -1;

        if(rd_mobiliario.isChecked()){
            tipo = 0;
        }else{
            tipo = 1;
        }
        try {
            fp.pasar_siguiente_1(tipo);
        }catch (Exception e){
            Toast.makeText(getActivity(),"Error:"+e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

    public interface Fragment_principal
    {
        void pasar_siguiente_1(int tipo);
    }




}
