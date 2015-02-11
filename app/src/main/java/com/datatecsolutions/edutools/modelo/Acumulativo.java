package com.datatecsolutions.edutools.modelo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author datatec solutions
 * @version 1.0
 */
public class Acumulativo {
    private String nombre;
    private String clase;
    private String seccion;
    private String tipo;
    private Double valor = 0.0;
    private char parcial;// los valores que puede tener aqui es I II III IV
    protected Integer idAcumlativo;

    /**
     * @roseuid 54C2A5A2017C
     */
    public Acumulativo() {

    }

    /**
     * @roseuid 54C1DBAD038D
     */
    public void nuevoAcumulativo() {

    }

    /**
     * @roseuid 54C1DBF80135
     */
    public void guardarAcumulativo() {

    }

    /**
     * @param idSeccion
     * @return Acumulativo
     * @roseuid 54C1DE820231
     */
    public Acumulativo obtenerAcumulativo(Integer idSeccion) {
        return null;
    }


    /**
     * @roseuid 54C29FCA0025
     */
    public void eliminarAcumulativo() {

    }
}
