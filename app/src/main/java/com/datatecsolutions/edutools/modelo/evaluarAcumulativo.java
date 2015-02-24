package com.datatecsolutions.edutools.modelo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by rj on 22/02/2015.
 */
public class evaluarAcumulativo {
    private EduToolsDb baseDatos;
    private Context context;

    public evaluarAcumulativo(Context context) {

        this.context = context;
    }

    public ArrayList<Persona> listarEstudiante() {
        SQLiteDatabase db = baseDatos.getReadableDatabase();
        ArrayList<Persona> resultado = new ArrayList<>();
        String sql = "select * from alumnos";
        Cursor datos = db.rawQuery(sql, null);
        Persona alumno = new Persona(context);
        if (datos.getCount() > 0) {
            while (datos.moveToNext()) {
                alumno.setCodigo(datos.getString(0));
                alumno.setPrimerNombre(datos.getString(1));
                resultado.add(alumno);
            }
        }
        return resultado;
    }


}
