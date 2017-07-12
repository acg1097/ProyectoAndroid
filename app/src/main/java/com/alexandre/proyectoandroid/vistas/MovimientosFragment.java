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
import android.widget.ListView;
import android.widget.Toast;

import com.alexandre.proyectoandroid.R;
import com.alexandre.proyectoandroid.beans.MovimientoDTO;
import com.alexandre.proyectoandroid.daos.MovimientoDAO;
import com.alexandre.proyectoandroid.utils.lvAdapterMovimiento;
import com.alexandre.proyectoandroid.vistas.mover.AnadirMovimientoActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovimientosFragment extends Fragment {

    private CharSequence[] values = {"Todos","Articulo", "Área", "Fecha"};
    private int itemSeleccionado = 0;
    private AlertDialog alertDialog;
    private EditText etFiltro;
    private Toolbar toolbar;
    private ListView lvMovimientos;
    private lvAdapterMovimiento lvAdapterMovimiento;
    private MovimientoDAO movimientoDAO;
    private List<MovimientoDTO> listaMovimientos;

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

                builder.setTitle("Buscar por:");

                builder.setSingleChoiceItems(values, itemSeleccionado, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int item) {
                        switch (item) {
                            case 1:
                                toolbar.setTitle("Movimientos: Por articulo");
                                itemSeleccionado=1;
                                break;
                            case 2:
                                toolbar.setTitle("Movimientos: Por área");
                                itemSeleccionado=2;
                                break;
                            case 3:
                                toolbar.setTitle("Movimientos: Por fecha");
                                itemSeleccionado=3;
                                break;
                            default:
                                toolbar.setTitle("Movimientos");
                                itemSeleccionado=0;
                        }
                        alertDialog.dismiss();
                    }
                });
                alertDialog = builder.create();
                alertDialog.show();
        }
        return false;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movimiento, container, false);

        lvMovimientos = (ListView) view.findViewById(R.id.lvMovimientos);
        lvAdapterMovimiento = new lvAdapterMovimiento(getActivity());
        lvMovimientos.setAdapter(lvAdapterMovimiento);

        movimientoDAO = new MovimientoDAO(getContext());
/*
        listaMovimientos = movimientoDAO.movimientoDTOList();
        lvAdapterMovimiento.addAll(listaMovimientos);
*/
        MovimientoDTO m = new MovimientoDTO();

        m.setAreaMovimiento("Recursos humanos");
        m.setFechaMovimiento("10/10/2016");
        m.setHoraMovimiento("11:30");
        m.setTipoMovimiento("Entrada");

        lvAdapterMovimiento.add(m);

       /* etFiltro =(EditText) view.findViewById(R.id.etFiltro);
        etFiltro.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                List<MovimientoDTO> lista = new ArrayList<MovimientoDTO>();
                switch (itemSeleccionado){
                    case 1://por articulo
                        //lista  = movimientoDAO.; FALTA POR DESCRIPCION
                        lvAdapterMovimiento.clear();
                        lvAdapterMovimiento.addAll(lista);
                        break;
                    case 2://por Área
                        lista = movimientoDAO.movimientoDTOListByArea(etFiltro.getText().toString());// = movimientoDAO.; FALTA POR DESCRIPCION
                        lvAdapterMovimiento.clear();
                        lvAdapterMovimiento.addAll(lista);
                        Toast.makeText(getActivity(),"Buscando en la base de datos por área",Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        lista = movimientoDAO.movimientoDTOList();
                        lvAdapterMovimiento.clear();
                        lvAdapterMovimiento.addAll(lista);
                        Toast.makeText(getActivity(),"Buscando en la base de datos por todo",Toast.LENGTH_SHORT).show();
                        break;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
*/
        FloatingActionButton button = (FloatingActionButton) view.findViewById(R.id.fabAnadir);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), AnadirMovimientoActivity.class);
                startActivity(i);
            }
        });

        toolbar = (Toolbar) view.findViewById(R.id.toolbar_reporte);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.setTitle("Movimientos");

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);
        //activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        return view;
    }


}
