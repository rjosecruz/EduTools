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
    private String valor;
    private String parcial;// los valores que puede tener aqui es I II III IV
    protected String idAcumlativo;

    /**
     * @roseuid 54C2A5A2017C
     */
    public Acumulativo() {

    }

    public Acumulativo(String xnombre, String xvalor, String xIdAcumulativo, String xParcial) {
        this.nombre = xnombre;
        this.valor = xvalor;
        this.idAcumlativo = xIdAcumulativo;
        this.parcial = xParcial;
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

    public String getnombre() {

        return this.nombre;
    }

    public String getId() {

        return this.idAcumlativo;
    }
}
