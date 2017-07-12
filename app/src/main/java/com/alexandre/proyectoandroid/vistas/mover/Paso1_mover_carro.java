package com.alexandre.proyectoandroid.vistas.mover;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alexandre.proyectoandroid.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Paso1_mover_carro extends Fragment {


    public Paso1_mover_carro() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_paso1_mover_carro, container, false);
    }

}
