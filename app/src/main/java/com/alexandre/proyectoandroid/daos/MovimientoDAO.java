package com.alexandre.proyectoandroid.daos;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.alexandre.proyectoandroid.beans.MovimientoDTO;
import com.alexandre.proyectoandroid.DataBaseHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Italo on 06/07/2017.
 */

public class MovimientoDAO {
    Context myContext;


    private final String TABLE_MOV = "tb_movimiento";
    private final String COL_NRO_MOV = "nroMovimiento";
    private final String COL_FECHA = "FechaMovimiento";
    private final String COL_HORA = "horaMovimiento";
    private final String COL_TIPO_MOV = "tipoMovimiento";
    private final String COL_AREA_SOL = "areaSolicitante";

    // ----------------------ENTRADA VEHICULO ---------------------//
    private final String TABLE_ENT_VEH = "tb_entrada_vehiculo";
    private final String COL_ID_VEH = "idVehiculo";

    // ----------------------SALIDA VEHICULO ---------------------//
    private final String TABLE_SAL_VEH = "tb_salida_vehiculo";
    // ----------------------ENTRADA MOBILIARIO ---------------------//
    private final String TABLE_ENT_MOB = "tb_entrada_mobiliario";

    // ----------------------SALIDA MOBILIARIO ---------------------//
    private final String TABLE_SAL_MOB = "tb_salida_mobiliario";
    private final String COL_ID_MOB = "idMobiliario";

    private Context mContext;

    public MovimientoDAO(Context context) {
        mContext = context;
    }

   /* private MovimientoDTO cursorToMovimientoDTO(Cursor c) {

        MovimientoDTO m = new MovimientoDTO();

        m.setNroMovimiento(c.isNull(c.getColumnIndex(COL_NRO_MOV)) ? 0 : c.getInt(c.getColumnIndex(COL_NRO_MOV)));

        5.setTipoActivo(c.isNull(c.getColumnIndex(COL_TIPO_MOV)) ? "" : c.getString(c.getColumnIndex(COL_TIPO_MOV)));
        m.setTipoMovimiento(c.isNull(c.getColumnIndex(COL_TIPO_MOV)) ? "" : c.getString(c.getColumnIndex(COL_TIPO_MOV)));
        m.setFechaMovimiento(c.isNull(c.getColumnIndex(COL_FECHA)) ? "" : c.getString(c.getColumnIndex(COL_FECHA)));
        m.setAreaMovimiento(c.isNull(c.getColumnIndex(COL_FECHA)) ? "" : c.getString(c.getColumnIndex(COL_FECHA)));
        m.setHoraMovimiento(c.isNull(c.getColumnIndex(COL_FECHA)) ? "" : c.getString(c.getColumnIndex(COL_FECHA)));

        m.setIdObjeto(getId(m.getNroMovimiento(), m.getTipoActivo(), m.getTipoMovimiento()));

        return m;
    }

    public MovimientoDTO movimientoDTOById(int id) {
        MovimientoDTO f = null;
        try {
            DataBaseHelper db = new DataBaseHelper(mContext);
            SQLiteDatabase sql = db.openDataBase();
            Cursor c = sql.query(TABLE_MOV, null, COL_NRO_MOV + " = ?", new String[]{String.valueOf(id)}, null, null, null);

            if (c.moveToFirst()) {
                f = cursorToMovimientoDTO(c);
            }


        } catch (Exception ex) {

        }
        return f;
    }

    public List<MovimientoDTO> movimientoDTOList() {
        List<MovimientoDTO> f = new ArrayList<>();
        try {
            DataBaseHelper db = new DataBaseHelper(mContext);
            SQLiteDatabase sql = db.openDataBase();
            Cursor c = sql.query(TABLE_MOV, null, null, null, null, null, null);

            while (c.moveToNext()) {
                f.add(cursorToMovimientoDTO(c));
            }


        } catch (Exception ex) {

        }
        return f;
    }

    public List<MovimientoDTO> movimientoDTOListByDate(String fecha) {
        List<MovimientoDTO> f = new ArrayList<>();
        try {
            DataBaseHelper db = new DataBaseHelper(mContext);
            SQLiteDatabase sql = db.openDataBase();
            Cursor c = sql.query(TABLE_MOV, null, COL_FECHA + " = ?", new String[]{String.valueOf(fecha)}, null, null, null);

            while (c.moveToNext()) {
                f.add(cursorToMovimientoDTO(c));
            }


        } catch (Exception ex) {

        }
        return f;
    }

    public List<MovimientoDTO> movimientoDTOListByArea(String area) {
        List<MovimientoDTO> f = new ArrayList<>();
        try {
            DataBaseHelper db = new DataBaseHelper(mContext);
            SQLiteDatabase sql = db.openDataBase();
            Cursor c = sql.query(TABLE_MOV, null, COL_AREA_SOL + " = ?", new String[]{String.valueOf(area)}, null, null, null);

            while (c.moveToNext()) {
                f.add(cursorToMovimientoDTO(c));
            }


        } catch (Exception ex) {

        }
        return f;
    }

    private int getId(int mov, String tipoobj, String tipomov) {
        int id = 0;


        try {
            DataBaseHelper db = new DataBaseHelper(myContext);
            SQLiteDatabase sql = db.openDataBase();

            if (tipoobj.toLowerCase() == "vehiculo") {

                if (tipomov.toLowerCase() == "entrada") {

                    Cursor c = sql.query(TABLE_ENT_VEH, new String[]{COL_ID_VEH}, COL_TIPO_MOV + " = ?", new String[]{String.valueOf(mov)}, null, null, null);
                    id = cursorToId(c, tipoobj, tipomov);

                }

                if (tipomov.toLowerCase() == "salida") {
                    Cursor c = sql.query(TABLE_SAL_VEH, new String[]{COL_ID_VEH}, COL_TIPO_MOV + " = ?", new String[]{String.valueOf(mov)}, null, null, null);
                    id = cursorToId(c, tipoobj, tipomov);
                }

            }

            if (tipoobj == "Mobiliario") {

                if (tipomov.toLowerCase() == "entrada") {

                    Cursor c = sql.query(TABLE_ENT_MOB, new String[]{COL_ID_VEH}, COL_TIPO_MOV + " = ?", new String[]{String.valueOf(mov)}, null, null, null);
                    id = cursorToId(c, tipoobj, tipomov);

                }

                if (tipomov.toLowerCase() == "salida") {
                    Cursor c = sql.query(TABLE_SAL_MOB, new String[]{COL_ID_VEH}, COL_TIPO_MOV + " = ?", new String[]{String.valueOf(mov)}, null, null, null);
                    id = cursorToId(c, tipoobj, tipomov);
                }
            }

        } catch (Exception ex) {
        }

        return id;
    }

    private int cursorToId(Cursor c, String tipoobj, String tipomov) {
        int id;

        if (tipomov.toLowerCase() == "entrada") {
            if (tipoobj.toLowerCase() == "mobiliario") {
                id = c.isNull(c.getColumnIndex(COL_ID_MOB)) ? 0 : c.getInt(c.getColumnIndex(COL_ID_MOB));

            } else {
                id = c.isNull(c.getColumnIndex(COL_ID_VEH)) ? 0 : c.getInt(c.getColumnIndex(COL_ID_VEH));
            }

        } else {
            if (tipomov.toLowerCase() == "mobiliario") {
                id = c.isNull(c.getColumnIndex(COL_ID_MOB)) ? 0 : c.getInt(c.getColumnIndex(COL_ID_MOB));
            } else {
                id = c.isNull(c.getColumnIndex(COL_ID_VEH)) ? 0 : c.getInt(c.getColumnIndex(COL_ID_VEH));
            }
        }


        return id;
    }

*/
}



