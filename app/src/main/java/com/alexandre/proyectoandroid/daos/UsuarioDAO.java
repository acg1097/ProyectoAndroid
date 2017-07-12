package com.alexandre.proyectoandroid.daos;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.widget.Toast;

import com.alexandre.proyectoandroid.DataBaseHelper;
import com.alexandre.proyectoandroid.beans.UsuarioDTO;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Italo on 09/07/2017.
 */

public class UsuarioDAO {

    private final String TABLA = "tb_usuario";
    private final String COL_ID = "idUsuario";
    private final String COL_DNI = "dniUsuario";
    private final String COL_CLAVE = "claveUsuario";
    private final String COL_NOMBRE = "nombreUsuario";
    private final String COL_APELLIDO = "apellidoUsuario";
    private final String COL_CARGO = "cargoUsuario";
    private Context myContext;

    public UsuarioDAO(Context context) {
        myContext = context;
    }

    private UsuarioDTO cursorToUsuarioDTO(Cursor cursor) {
        UsuarioDTO m = new UsuarioDTO();

        m.setId(cursor.isNull(cursor.getColumnIndex(COL_ID)) ? 0 : cursor.getInt(cursor.getColumnIndex(COL_ID)));
        m.setDni(cursor.isNull(cursor.getColumnIndex(COL_DNI)) ? "" : cursor.getString(cursor.getColumnIndex(COL_DNI)));
        m.setClave(cursor.isNull(cursor.getColumnIndex(COL_CLAVE)) ? "" : cursor.getString(cursor.getColumnIndex(COL_CLAVE)));
        m.setNombre(cursor.isNull(cursor.getColumnIndex(COL_NOMBRE)) ? "" : cursor.getString(cursor.getColumnIndex(COL_NOMBRE)));
        m.setApellido(cursor.isNull(cursor.getColumnIndex(COL_APELLIDO)) ? "" : cursor.getString(cursor.getColumnIndex(COL_APELLIDO)));
        m.setCargo(cursor.isNull(cursor.getColumnIndex(COL_CARGO)) ? "" : cursor.getString(cursor.getColumnIndex(COL_CARGO)));

        return m;
    }

    public UsuarioDTO getUsuario(String dni) {

        UsuarioDTO d = null;
        try {
            DataBaseHelper helper = new DataBaseHelper(myContext);
            SQLiteDatabase sql = helper.openDataBase();
            Cursor c = sql.query(TABLA, null, COL_DNI + "= ?", new String[]{String.valueOf(dni)}, null, null, null);

            if (c.moveToFirst()) {

                d = cursorToUsuarioDTO(c);

            }
        } catch (Exception ex) {
            Toast.makeText(myContext, ex.getMessage(), Toast.LENGTH_LONG).show();
        }
        return d;
    }

    public List<UsuarioDTO> listaUsuarios() {
        List<UsuarioDTO> lista = new ArrayList<UsuarioDTO>();

        try {
            DataBaseHelper helper = new DataBaseHelper(myContext);
            SQLiteDatabase sql = helper.openDataBase();
            Cursor c = sql.query(TABLA, null, null, null, null, null, null);

            do {

                UsuarioDTO u = cursorToUsuarioDTO(c);
                lista.add(u);

            } while (c.moveToNext());
        } catch (Exception ex) {
            Toast.makeText(myContext, ex.getMessage(), Toast.LENGTH_LONG).show();
        }
        return lista;

    }
}
