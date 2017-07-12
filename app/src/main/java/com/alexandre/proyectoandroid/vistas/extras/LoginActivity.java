package com.alexandre.proyectoandroid.vistas.extras;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alexandre.proyectoandroid.DataBaseHelper;
import com.alexandre.proyectoandroid.R;
import com.alexandre.proyectoandroid.beans.UsuarioDTO;
import com.alexandre.proyectoandroid.daos.UsuarioDAO;
import com.alexandre.proyectoandroid.utils.utils;
import com.alexandre.proyectoandroid.vistas.MainActivity;
import com.alexandre.proyectoandroid.vistas.MainActivity_jefe;

public class LoginActivity extends AppCompatActivity {

    private EditText etDni, etContrasena;
    private Button btnIniciar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        try {
            DataBaseHelper dataBaseHelper = new DataBaseHelper(LoginActivity.this);
            dataBaseHelper.createDataBase();
        } catch (Exception ex) {
            ex.printStackTrace();
            Toast.makeText(getBaseContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
        }


        etDni = (EditText) findViewById(R.id.etDNI_login);
        etContrasena = (EditText) findViewById(R.id.etContrasena_login);
        btnIniciar = (Button) findViewById(R.id.btn_login);
        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String dni = etDni.getText().toString().trim();
                String contrasena = etContrasena.getText().toString().trim();

                if (dni.isEmpty() || contrasena.isEmpty()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                    builder.setCancelable(false);
                    builder.setMessage("Complete todos los campos");
                    builder.setPositiveButton("Aceptar", null);
                    builder.show();
                } else {
                    ProgressDialog pd = new ProgressDialog(LoginActivity.this);
                    pd.setMessage("Validando credenciales");
                    pd.setCancelable(false);
                    pd.show();

                    UsuarioDAO dao = new UsuarioDAO(LoginActivity.this);
                    UsuarioDTO u = dao.getUsuario(dni);

                    if(u != null){
                        if (contrasena.equals(u.getClave())) {

                            SharedPreferences sp = getApplication().getSharedPreferences(utils.ARCHIVO_SESION, Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sp.edit();
                            editor.putString("usuario", dni);
                            editor.commit();

                            if (u.getCargo().equals("Almacenista")) {
                                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(i);
                                finish();
                            } else {
                                Intent i = new Intent(LoginActivity.this, MainActivity_jefe.class);
                                startActivity(i);
                                finish();
                            }

                        } else {
                            pd.dismiss();

                            AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                            builder.setCancelable(false);
                            builder.setMessage("Contraseña incorrecta");
                            builder.setPositiveButton("Aceptar", null);
                            builder.show();
                        }
                    }else{
                        pd.dismiss();

                        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                        builder.setCancelable(false);
                        builder.setMessage("Usuario no existente");
                        builder.setPositiveButton("Aceptar", null);
                        builder.show();
                    }
                }
            }
        });
    }
}
