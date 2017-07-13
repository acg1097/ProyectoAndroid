package com.alexandre.proyectoandroid.vistas;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alexandre.proyectoandroid.R;
import com.alexandre.proyectoandroid.beans.MovimientoDTO;
import com.alexandre.proyectoandroid.daos.MovimientoDAO;
import com.alexandre.proyectoandroid.utils.lvAdapterMovimiento;
import com.alexandre.proyectoandroid.vistas.mover.AnadirMovimientoActivity;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovimientosFragment extends Fragment {

    private Toolbar toolbar;
    private ListView lvMovimientos;
    private lvAdapterMovimiento lvAdapterMovimiento;
    private TextView tvNoSeEncontro;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movimiento, container, false);
        tvNoSeEncontro = (TextView) view.findViewById(R.id.tvNoSeEncontro_m);
        lvMovimientos = (ListView) view.findViewById(R.id.lvMovimientos);
        lvAdapterMovimiento = new lvAdapterMovimiento(getActivity());
        lvMovimientos.setAdapter(lvAdapterMovimiento);

        listarMovimientos();

        FloatingActionButton button = (FloatingActionButton) view.findViewById(R.id.fabAnadir);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), AnadirMovimientoActivity.class);
                startActivityForResult(i,1);
            }
        });

        toolbar = (Toolbar) view.findViewById(R.id.toolbar_reporte);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.setTitle("Movimientos");

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == getActivity().RESULT_OK) {
            listarMovimientos();
        }
    }

    private void listarMovimientos(){
        MovimientoDAO dao = new MovimientoDAO(getContext());

        List<MovimientoDTO>lista = dao.listaMovimientoDTOsByIdActivo();

        if(lista.size()>0){
            lvMovimientos.setVisibility(View.VISIBLE);
            tvNoSeEncontro.setVisibility(View.GONE);
            lvAdapterMovimiento.clear();
            lvAdapterMovimiento.addAll(lista);
        }else{
            tvNoSeEncontro.setVisibility(View.VISIBLE);
            lvMovimientos.setVisibility(View.GONE);
        }
    }


}
