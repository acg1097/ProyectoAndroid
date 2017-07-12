package com.alexandre.proyectoandroid.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.alexandre.proyectoandroid.R;
import com.alexandre.proyectoandroid.beans.MovimientoDTO;

import java.util.ArrayList;

/**
 * Created by ALEXANDRE on 6/07/2017.
 */

public class lvAdapterMovimiento extends ArrayAdapter<MovimientoDTO> {

    public lvAdapterMovimiento(Context context) {
        super(context, 0, new ArrayList<MovimientoDTO>());
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_movimiento, parent, false);

        TextView tvActivo, tvArea, tvFecha, tvHora, tvTipoMovimiento;

        tvActivo = (TextView) convertView.findViewById(R.id.tvActivo_item);
        tvArea = (TextView) convertView.findViewById(R.id.tvArea_item);
        tvFecha = (TextView) convertView.findViewById(R.id.tvFecha_item);
        tvHora = (TextView) convertView.findViewById(R.id.tvHora_item);
        tvTipoMovimiento = (TextView) convertView.findViewById(R.id.tvTipoMov);

        MovimientoDTO m = getItem(position);

        /*MobiliarioDAO mobiliarioDAO = new MobiliarioDAO(getContext());
        VehiculoDAO vehiculoDAO = new VehiculoDAO(getContext());
/*
        ActivoDTO a = mobiliarioDAO.getMobiliarioDTOById(m.getActivoDTO().getCodigo());

        if(a.getTipo().equals("vehiculo")){
            VehiculoDTO v = vehiculoDAO.getVehiculoDTOById(m.getActivoDTO().getCodigo());
            tvActivo.setText(v.getMarca()+", "+v.getModelo());
        }else{

        }
/*
        if (m.getTipoActivo().toLowerCase().equals("vehiculo")) {
            vehiculoDTO = vehiculoDAO.getVehiculoDTOById(m.getIdObjeto());
            tvActivo.setText(vehiculoDTO.getMarcaVehiculo()+", "+vehiculoDTO.getModeloVehiculo());
        } else {
            tvActivo.setText(mobiliarioDTO.getDescripcionMobilario());
        }
*/
        tvArea.setText(m.getAreaMovimiento());
        tvFecha.setText(m.getFechaMovimiento());
        tvHora.setText(m.getHoraMovimiento());
        tvTipoMovimiento.setText(m.getTipoMovimiento());

        return convertView;
    }
}
