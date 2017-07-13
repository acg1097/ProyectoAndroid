package com.alexandre.proyectoandroid.daos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.alexandre.proyectoandroid.DataBaseHelper;
import com.alexandre.proyectoandroid.beans.ActivoDTO;
import com.alexandre.proyectoandroid.vistas.registrar.RegistrarActivo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Italo on 09/07/2017.
 */

public class ActivoDAO {

    private final String TABLA = "tb_activo";

    private final String COL_ID = "idActivo";
    private final String COL_DESC = "descripcionActivo";
    private final String COL_MARCA = "marcaActivo";
    private final String COL_MODELO = "modeloActivo";
    private final String COL_VALOR = "valorActivo";
    private final String COL_UBICACION = "ubicacionActivo";
    private final String COL_TIPO = "tipoActivo";
    private final String COL_ESTADO = "estadoActivo";

    private Context mContext;

    public ActivoDAO(Context context) {
        mContext = context;
    }

    private ActivoDTO cursorToActivoDTO(Cursor cursor) {
        ActivoDTO a = new ActivoDTO();

        a.setId(cursor.isNull(cursor.getColumnIndex(COL_ID)) ? "" : cursor.getString(cursor.getColumnIndex(COL_ID)));
        a.setDescripcion(cursor.isNull(cursor.getColumnIndex(COL_DESC)) ? "" : cursor.getString(cursor.getColumnIndex(COL_DESC)));
        a.setMarca(cursor.isNull(cursor.getColumnIndex(COL_MARCA)) ? "" : cursor.getString(cursor.getColumnIndex(COL_MARCA)));
        a.setModelo(cursor.isNull(cursor.getColumnIndex(COL_MODELO)) ? "" : cursor.getString(cursor.getColumnIndex(COL_MODELO)));
        a.setValor(cursor.isNull(cursor.getColumnIndex(COL_VALOR)) ? 0.0 : cursor.getDouble(cursor.getColumnIndex(COL_VALOR)));
        a.setUbicacion(cursor.isNull(cursor.getColumnIndex(COL_UBICACION)) ? "" : cursor.getString(cursor.getColumnIndex(COL_UBICACION)));
        a.setTipo(cursor.isNull(cursor.getColumnIndex(COL_TIPO)) ? "" : cursor.getString(cursor.getColumnIndex(COL_TIPO)));
        a.setEstado(cursor.isNull(cursor.getColumnIndex(COL_ESTADO)) ? "" : cursor.getString(cursor.getColumnIndex(COL_ESTADO)));

        return a;
    }

    private String obtenerCodigo() {
        String codigo;

        Date fecha = new Date();

        SimpleDateFormat formato = new SimpleDateFormat("yyyyMMddHHmmss");

        codigo = formato.format(fecha);

        return codigo;
    }

    public List<ActivoDTO> listaGeneral(String tipo) {
        List<ActivoDTO> list = new ArrayList<ActivoDTO>();
        try {
            DataBaseHelper db = new DataBaseHelper(mContext);
            SQLiteDatabase sql = db.openDataBase();
            Cursor c;
            if (tipo == null) {
                c = sql.query(TABLA, null, null, null, null, null,null);
            } else {
                c = sql.query(TABLA, null, COL_TIPO + "=?", new String[]{String.valueOf(tipo)}, null, null,null);
            }
            if (c.moveToFirst()) {
                do {
                    list.add(cursorToActivoDTO(c));
                }
                while (c.moveToNext());
            }
        } catch (Exception ex) {

        }
        return list;
    }

    public ActivoDTO getActivoDTO(String id) {
        ActivoDTO a = new ActivoDTO();
        try {
            DataBaseHelper db = new DataBaseHelper(mContext);
            SQLiteDatabase sql = db.openDataBase();
            Cursor c = sql.query(TABLA, null, COL_ID + "= ?", new String[]{String.valueOf(id)}, null, null, null);
            if (c.moveToFirst()) {
                a = cursorToActivoDTO(c);
            }
        } catch (Exception ex) {
        }
        return a;

    }


    public ActivoDTO agregarActivo(ActivoDTO a) {

        ContentValues contentValues = new ContentValues();

        String codigo;

        if (a.getTipo().toString().trim().toLowerCase().equals("vehiculo")) {
            contentValues.put(COL_ID, a.getId());
        } else {
            codigo = obtenerCodigo();
            contentValues.put(COL_ID, codigo);
            a.setId(codigo);
        }

        contentValues.put(COL_DESC, a.getDescripcion());
        contentValues.put(COL_MARCA, a.getMarca());
        contentValues.put(COL_MODELO, a.getModelo());
        contentValues.put(COL_VALOR, a.getValor());
        contentValues.put(COL_UBICACION, a.getUbicacion());
        contentValues.put(COL_TIPO, a.getTipo());

        contentValues.put(COL_ESTADO, "En almacén");

        DataBaseHelper bd = new DataBaseHelper(mContext);

        long ok = -1;

        ok = bd.openDataBase().insert(TABLA,null,contentValues);

        if(ok==-1){
            return null;
        }else{
            return  a;
        }
    }

    public int actualizarActivo(ActivoDTO a) {

        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_DESC, a.getDescripcion());
        contentValues.put(COL_MARCA, a.getMarca());
        contentValues.put(COL_MODELO, a.getModelo());
        contentValues.put(COL_VALOR, a.getValor());
        contentValues.put(COL_UBICACION, a.getUbicacion());

        return new DataBaseHelper(mContext)
                .openDataBase()
                .update(TABLA, contentValues, COL_ID + "= ?", new String[]{String.valueOf(a.getId())});
    }

    public int actualizarEstadoActivo(ActivoDTO a) {

        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_UBICACION, a.getUbicacion());
        contentValues.put(COL_ESTADO, a.getEstado());

        return new DataBaseHelper(mContext)
                .openDataBase()
                .update(TABLA, contentValues, COL_ID + "= ?", new String[]{String.valueOf(a.getId())});
    }

}


