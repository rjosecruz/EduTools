package com.datatecsolutions.edutools.modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * @author datatec solutions
 * @version 1.0
 */
public class Acumulativo {
    private String nombre;
    private String clase;
    private String seccion;
    private String tipo;
    private double valor;
    private int parcial;// los valores que puede tener aqui es I II III IV
    private int idAcumlativo;
    String fecha;
    int idClase;
    Context context;
    private EduToolsDb baseDatos;

    /**
     * @roseuid 54C2A5A2017C
     */
    public Acumulativo(Context context) {
        this.context = context;
        baseDatos = new EduToolsDb(context);
    }

    public Acumulativo(String xnombre, double xvalor, int xIdAcumulativo, int xParcial, Context context) {
        this.nombre = xnombre;
        this.valor = xvalor;
        this.idAcumlativo = xIdAcumulativo;
        this.parcial = xParcial;
        baseDatos = new EduToolsDb(context);
    }

    /**
     * @roseuid 54C1DBAD038D
     */
    public void nuevoAcumulativo() {


    }


    public void guardarAcumulativo() {
        ContentValues nuevoRegistro = new ContentValues();
        try {

            // nuevoRegistro.put("id_acumulativo", default);
            nuevoRegistro.put("id_seccion", getSeccion());
            nuevoRegistro.put("descripcion", getNombre());
            nuevoRegistro.put("id_tipo_acumulativo", getTipo());
            nuevoRegistro.put("fecha", getFecha());
            nuevoRegistro.put("valor", getValor());
            nuevoRegistro.put("id_clase", getIdClase());
            nuevoRegistro.put("parcial", getParcial());
            SQLiteDatabase db = baseDatos.getWritableDatabase();
            db.insert("acumulativos", null, nuevoRegistro);
            Toast.makeText(context, "Datos Guardados", Toast.LENGTH_LONG).show();
        } catch (android.database.sqlite.SQLiteException ex) {

            Log.i("dataops_", ex.getMessage().toString());
        }


    }

    /**
     * @param idSeccion
     * @return Acumulativo
     * @roseuid 54C1DE820231
     */

    /**
     * Obtiene el acumulativo especificado en el parametro
     *
     * @param idSeccion determina el id de la seccion
     * @return
     */
    public Acumulativo obtenerAcumulativo(Integer idSeccion) {
        return null;
    }

    public ArrayList<Acumulativo> listarAcumulativos(int idSeccion) {
        ArrayList<Acumulativo> resultado = new ArrayList<>();
        String query = "select * from acumulativos where id_seccion=" + idSeccion;
        SQLiteDatabase db = baseDatos.getReadableDatabase();
        Cursor datos = db.rawQuery(query, null);
        if (datos.getCount() > 0) {
            while (datos.moveToNext()) {
                Acumulativo acum = new Acumulativo(context);
                acum.setIdAcumlativo(datos.getInt(0));
                acum.setNombre(datos.getString(2));
                acum.setValor(datos.getDouble(5));
                acum.setParcial(datos.getInt(7));
                resultado.add(acum);
            }
        }


        return resultado;
    }


    /**
     * @roseuid 54C29FCA0025
     */
    public void eliminarAcumulativo() {

    }

    public String getNombre(){
        return this.nombre;
    }

    public void setNombre(String xnombre) {
        this.nombre = xnombre;
    }

    public String getClase() {
        return this.clase;
    }

    public void setClase(String xclase) {
        this.clase = xclase;
    }

    public String getSeccion() {
        return this.seccion;
    }


    public void setSeccion(String xseccion) {
        this.seccion = xseccion;
    }

    public String getTipo() {
        return this.tipo;
    }

    public void setTipo(String xtipo) {
        this.tipo = xtipo;

    }

    public double getValor() {
        return this.valor;
    }

    public void setValor(Double xvalor) {
        this.valor = xvalor;
    }

    public int getParcial() {
        return this.parcial;
    }

    public void setParcial(int xparcial) {
        this.parcial = xparcial;
    }

    public int getidAcumlativo(){
        return this.idAcumlativo;
    }

    public void setIdAcumlativo(int xidacumulativo) {
        this.idAcumlativo = xidacumulativo;
    }

    public void setFecha(String xfecha) {
        this.fecha = xfecha;
    }

    public String getFecha() {
        return this.fecha;
    }

    public void setIdClase(int xidClase) {
        this.idClase = xidClase;
    }

    public int getIdClase() {
        return this.idClase;
    }
}
