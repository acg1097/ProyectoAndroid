package com.alexandre.proyectoandroid.vistas.extras;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.alexandre.proyectoandroid.R;
import com.alexandre.proyectoandroid.beans.UsuarioDTO;
import com.alexandre.proyectoandroid.daos.UsuarioDAO;
import com.alexandre.proyectoandroid.utils.utils;
import com.alexandre.proyectoandroid.vistas.MainActivity;
import com.alexandre.proyectoandroid.vistas.MainActivity_jefe;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread splash = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(2500);

                    String usuario = null;

                    SharedPreferences sp = getApplication().getSharedPreferences(utils.ARCHIVO_SESION, Context.MODE_PRIVATE);
                    usuario = sp.getString("usuario", null);

                    if (usuario == null) {
                        Intent i = new Intent(Splash.this, LoginActivity.class);
                        startActivity(i);
                    } else {

                        UsuarioDAO dao = new UsuarioDAO(Splash.this);
                        UsuarioDTO u = dao.getUsuario(usuario);

                        if (u.getCargo().equals("Almacenista")) {
                            Intent i = new Intent(Splash.this, MainActivity.class);
                            startActivity(i);
                        } else {
                            Intent i = new Intent(Splash.this, MainActivity_jefe.class);
                            startActivity(i);
                        }

                    }

                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        splash.start();
    }
}
