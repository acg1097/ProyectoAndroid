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
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alexandre.proyectoandroid.R;
import com.alexandre.proyectoandroid.beans.ActivoDTO;
import com.alexandre.proyectoandroid.daos.ActivoDAO;
import com.alexandre.proyectoandroid.utils.lvAdapterActivo;
import com.alexandre.proyectoandroid.vistas.registrar.RegistrarActivo;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AlmacenFragment extends Fragment {

    private CharSequence[] values = {"Todos","Vehiculos","Mobiliarios"};
    private int itemSeleccionado = 0;
    private AlertDialog alertDialog;
    private Toolbar toolbar;
    private ListView lvActivos;
    private lvAdapterActivo lvAdapterActivo;
    private TextView tvNoSeEncontro;

    public AlmacenFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_reporte, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.ic_filtro:

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                builder.setTitle("Mostrar:");
                builder.setNegativeButton("Cancelar",null);
                builder.setSingleChoiceItems(values, itemSeleccionado, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int item) {
                        switch (item) {
                            case 1:
                                toolbar.setTitle("Almacén: Vehiculos");
                                itemSeleccionado=1;
                                lvAdapterActivo.addAll(listar("Vehiculo"));
                                break;
                            case 2:
                                toolbar.setTitle("Almacén: Mobiliarios");
                                itemSeleccionado=2;
                                lvAdapterActivo.addAll(listar("Mobiliario"));
                                break;
                            case 0:
                                toolbar.setTitle("Almacén");
                                itemSeleccionado=0;
                                lvAdapterActivo.addAll(listar(null));
                        }
                        alertDialog.dismiss();
                    }
                });
                alertDialog = builder.create();
                alertDialog.show();
        }
        return false;
    }

    private List<ActivoDTO> listar(String tipo) {
        List<ActivoDTO>lista;

        ActivoDAO dao = new ActivoDAO(getActivity());
        lista = dao.listaGeneral(tipo);
        lvAdapterActivo.clear();

        if(lista.size()>0){
            tvNoSeEncontro.setVisibility(View.GONE);
            lvActivos.setVisibility(View.VISIBLE);
        }else{
            tvNoSeEncontro.setVisibility(View.VISIBLE);
            lvActivos.setVisibility(View.GONE);
        }
        return lista;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_almacen, container, false);
        toolbar = (Toolbar) view.findViewById(R.id.toolbar_almacen);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.setTitle("Almacén");

        tvNoSeEncontro = (TextView) view.findViewById(R.id.tvNoSeEncontro);

        lvActivos = (ListView) view.findViewById(R.id.lvActivos);
        lvAdapterActivo = new lvAdapterActivo(getActivity());
        lvActivos.setAdapter(lvAdapterActivo);

        lvAdapterActivo.addAll(listar(null));

        FloatingActionButton button = (FloatingActionButton) view.findViewById(R.id.fabAnadir_activo);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), RegistrarActivo.class);
                startActivityForResult(i,1);
            }
        });

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == getActivity().RESULT_OK){
            toolbar.setTitle("Almacén");
            itemSeleccionado = 0;
            lvAdapterActivo.addAll(listar(null));
        }
    }
}
