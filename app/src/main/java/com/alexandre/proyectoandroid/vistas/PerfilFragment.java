package com.alexandre.proyectoandroid.vistas;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import android.widget.TextView;

import com.alexandre.proyectoandroid.R;
import com.alexandre.proyectoandroid.beans.UsuarioDTO;
import com.alexandre.proyectoandroid.daos.UsuarioDAO;
import com.alexandre.proyectoandroid.utils.utils;
import com.alexandre.proyectoandroid.vistas.extras.LoginActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class PerfilFragment extends Fragment {

    private TextView tvDni,tvContrasena,tvNombres,tvApellidos,tvCargo;

    public PerfilFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_perfil, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.ic_cerrar_sesion:
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("¿Realmente desea cerrar sesión?");
                builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences sp = getActivity().getApplication().getSharedPreferences(utils.ARCHIVO_SESION, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString("usuario",null);
                        editor.commit();

                        Intent i = new Intent(getActivity(),LoginActivity.class);
                        startActivity(i);
                        getActivity().finish();
                    }
                });
                builder.setNegativeButton("Cancelar", null);
                builder.show();
                return true;
        }
        return false;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_perfil, container, false);

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar_perfil);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.setTitle("Perfil");

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);
        //activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String usuario = null;

        SharedPreferences sp = getActivity().getApplication().getSharedPreferences(utils.ARCHIVO_SESION, Context.MODE_PRIVATE);
        usuario = sp.getString("usuario", null);

        UsuarioDAO dao = new UsuarioDAO(getActivity().getBaseContext());
        UsuarioDTO u = dao.getUsuario(usuario);

        tvDni = (TextView) view.findViewById(R.id.tvDNI_perfil);
        tvContrasena = (TextView) view.findViewById(R.id.tvContrasena_perfil);
        tvNombres = (TextView) view.findViewById(R.id.tvNombres_perfil);
        tvApellidos = (TextView) view.findViewById(R.id.tvApellidos_perfil);
        tvCargo = (TextView) view.findViewById(R.id.tvCargo_perfil);

        tvDni.setText(u.getDni());
        tvContrasena.setText(u.getClave());
        tvNombres.setText(u.getNombre());
        tvApellidos.setText(u.getApellido());
        tvCargo.setText(u.getCargo());

        return view;
    }

}
