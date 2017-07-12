package com.alexandre.proyectoandroid.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.alexandre.proyectoandroid.beans.AreaDTO;

import java.util.ArrayList;

/**
 * Created by ALEXANDRE on 11/07/2017.
 */

public class lvAdapterAreas extends ArrayAdapter<AreaDTO> {

    public lvAdapterAreas(Context context) {
        super(context, 0, new ArrayList<AreaDTO>());
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return super.getView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return super.getDropDownView(position, convertView, parent);
    }
}
