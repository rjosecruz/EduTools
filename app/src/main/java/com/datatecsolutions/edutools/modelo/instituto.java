package com.datatecsolutions.edutools.modelo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by rj on 21/02/2015.
 */
public class instituto {
    private int idinstituto;
    private String nombre;
    private Context context;
    private EduToolsDb baseDatos;

    public instituto(Context context) {
        this.context = context;
        baseDatos = new EduToolsDb(context);
    }

    public instituto(int idinstituto, String nombre, Context context) {
        this.idinstituto = idinstituto;
        this.nombre = nombre;
        this.context = context;
        baseDatos = new EduToolsDb(context);
    }

    public ArrayList<instituto> listarInstituto() {
        ArrayList<instituto> datos = new ArrayList<>();
        String sql = "select * from instituto";
        SQLiteDatabase db = baseDatos.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                instituto inst = new instituto(context);
                inst.setIdinstituto(cursor.getInt(0));
                inst.setNombre(cursor.getString(1));
                datos.add(inst);
            }

        } else {
            instituto inst = new instituto(context);
            inst.setIdinstituto(1);
            inst.setNombre("No hay Datos");
            datos.add(inst);

        }
        return datos;
    }

    public void setIdinstituto(int id) {
        this.idinstituto = id;
    }

    public int getIdinstituto() {
        return this.idinstituto;
    }

    public void setNombre(String xnombre) {
        this.nombre = xnombre;
    }

    public String getNombre() {
        return this.nombre;
    }
}
