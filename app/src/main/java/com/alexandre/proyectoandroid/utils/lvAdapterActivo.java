package com.alexandre.proyectoandroid.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.alexandre.proyectoandroid.R;
import com.alexandre.proyectoandroid.beans.ActivoDTO;

import java.util.ArrayList;

/**
 * Created by ALEXANDRE on 10/07/2017.
 */

public class lvAdapterActivo extends ArrayAdapter<ActivoDTO>{


    public lvAdapterActivo(Context context) {
        super(context, 0, new ArrayList<ActivoDTO>());
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_activo, parent, false);

        TextView tvDescripcion,tvValor,tvUbicacion,tvEstado;
        ImageView ivVehiculo,ivMobiliario;


        tvDescripcion = (TextView) convertView.findViewById(R.id.tvDescripcion_item_activo);
        tvValor =(TextView) convertView.findViewById(R.id.tvValor_item_activo);
        tvUbicacion =(TextView) convertView.findViewById(R.id.tvUbicacion_item_activo);
        tvEstado = (TextView)convertView.findViewById(R.id.tvEstado_item_activo);
        ivVehiculo = (ImageView) convertView.findViewById(R.id.img_vehiculo_item_activo);
        ivMobiliario = (ImageView) convertView.findViewById(R.id.img_mobiliario_item_activo);

        ActivoDTO a = getItem(position);

        if(a.getTipo().toString().trim().toLowerCase().equals("vehiculo")){
            tvDescripcion.setText(a.getMarca()+", "+a.getModelo());
            ivVehiculo.setVisibility(View.VISIBLE);
            ivMobiliario.setVisibility(View.GONE);
        }else{
            tvDescripcion.setText(a.getDescripcion());
            ivMobiliario.setVisibility(View.VISIBLE);
            ivVehiculo.setVisibility(View.GONE);
        }

        tvValor.setText("Valor: S/."+a.getValor());
        tvUbicacion.setText(a.getUbicacion());
        tvEstado.setText(a.getEstado());

        return convertView;
    }
}
