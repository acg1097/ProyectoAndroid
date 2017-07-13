package com.alexandre.proyectoandroid.daos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.alexandre.proyectoandroid.beans.MovimientoDTO;
import com.alexandre.proyectoandroid.DataBaseHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Italo on 06/07/2017.
 */

public class MovimientoDAO {

    private final String TABLA = "tb_movimiento";
    private final String COL_NRO = "nroMovimiento";
    private final String COL_TIPO_MOV = "tipoMovimiento";
    private final String COL_TIPO_ACT = "tipoActivo";
    private final String COL_AREASOL = "areaSolicitante";
    private final String COL_IDACTIVO = "idActivo";
    private final String COL_FECHA = "fechaMovimiento";
    private final String COL_HORA = "horaMovimiento";


    private Context mContext;

    public MovimientoDAO(Context context) {
        mContext = context;
    }

    private MovimientoDTO cursorToMovimientoDTO(Cursor c) {

        MovimientoDTO m = new MovimientoDTO();

        m.setNroMovimiento(c.isNull(c.getColumnIndex(COL_NRO)) ? 0 : c.getInt(c.getColumnIndex(COL_NRO)));

        m.setIdActivo(c.isNull(c.getColumnIndex(COL_IDACTIVO)) ? "" : c.getString(c.getColumnIndex(COL_IDACTIVO)));
        m.setTipoActivo(c.isNull(c.getColumnIndex(COL_TIPO_ACT)) ? "" : c.getString(c.getColumnIndex(COL_TIPO_ACT)));

        m.setTipoMovimiento(c.isNull(c.getColumnIndex(COL_TIPO_MOV)) ? "" : c.getString(c.getColumnIndex(COL_TIPO_MOV)));
        m.setFechaMovimiento(c.isNull(c.getColumnIndex(COL_FECHA)) ? "" : c.getString(c.getColumnIndex(COL_FECHA)));
        m.setAreaMovimiento(c.isNull(c.getColumnIndex(COL_AREASOL)) ? "" : c.getString(c.getColumnIndex(COL_AREASOL)));
        m.setHoraMovimiento(c.isNull(c.getColumnIndex(COL_HORA)) ? "" : c.getString(c.getColumnIndex(COL_HORA)));

        return m;
    }


    public List<MovimientoDTO> listaMovimientoDTOsByIdActivo() {
        List<MovimientoDTO> lista = new ArrayList<>();
        try {
            DataBaseHelper db = new DataBaseHelper(mContext);
            SQLiteDatabase sql = db.openDataBase();
            Cursor c = sql.query(TABLA, null, null, null, null, null,null);

            if (c.moveToFirst()) {
                do {
                    lista.add(cursorToMovimientoDTO(c));
                }
                while (c.moveToNext());
            }

        } catch (Exception ex) {
        }

        return lista;
    }

    public MovimientoDTO BuscarMovimientoPorActivo(String id) {
        MovimientoDTO m = null;
        try {
            DataBaseHelper db = new DataBaseHelper(mContext);
            SQLiteDatabase sql = db.openDataBase();
            Cursor c;

            c = sql.query(TABLA, null, COL_IDACTIVO + "= ?", new String[]{String.valueOf(id)}, null, null,null,"1");

            if (c.moveToFirst()) {
                m = cursorToMovimientoDTO(c);
            }

        } catch (Exception ex) {
        }

        return m;
    }

    public long ingresarMovimientoDTO(MovimientoDTO m) {

        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_TIPO_MOV, m.getTipoMovimiento());
        contentValues.put(COL_TIPO_ACT, m.getTipoActivo());
        contentValues.put(COL_AREASOL, m.getAreaMovimiento());
        contentValues.put(COL_IDACTIVO, m.getIdActivo());
        contentValues.put(COL_FECHA, getFecha());
        contentValues.put(COL_HORA, getHora());

        DataBaseHelper bd = new DataBaseHelper(mContext);

        return bd.openDataBase().insert(TABLA, null, contentValues);

    }

    String getHora() {
        Date hora = new Date();
        SimpleDateFormat hf = new SimpleDateFormat("HH:mm:ss");
        return hf.format(hora);
    }

    String getFecha() {
        Date fecha = new Date();
        SimpleDateFormat hf = new SimpleDateFormat("dd/MM/yyyy");
        return hf.format(fecha);
    }
}


