package com.datatecsolutions.edutools.modelo;//Source file: C:\\Users\\DAVID\\Google Drive\\proyecto maestros\\Clases Java\\Persona.java


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

public class Persona {


    private String primerNombre;
    private String segundoNombre;
    private String primerApellido;
    private String segundoApellido;
    private String fechaNacimiento;
    private int edad;
    private String nacionalidad;
    String codigo;
    private int sexo;
    private double nota;
    private int id_acumulativo;
    Context contexto;
    EduToolsDb baseDatos;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getEdad() {
        return edad;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getSexo() {
        return sexo;
    }

    public void setSexo(int sexo) {
        this.sexo = sexo;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    /**
     * @roseuid 54C2A658005A
     */
    public Persona(Context context, int id_acumulativo) {
        this.contexto = context;
        baseDatos = new EduToolsDb(context);
        this.id_acumulativo = id_acumulativo;
    }

    public ArrayList<Persona> listarPersona(String xcodigo) {
        ArrayList<Persona> datos = new ArrayList<Persona>();
        SQLiteDatabase db = baseDatos.getReadableDatabase();
        String sql = "SELECT alumnos.rne, alumnos.nombre, alumnos.apellido, alumnos.correo, alumnos.telefono, alumnos.sexo, alumnos.fechaNac, matricula.id_seccion, matricula.annio FROM alumnos, matricula WHERE alumnos.rne = matricula.rne and id_seccion=" + xcodigo;
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                Persona mipersona = new Persona(contexto, this.id_acumulativo);
                mipersona.setCodigo(cursor.getString(0));
                mipersona.setPrimerNombre(cursor.getString(1));
                mipersona.setNota(getNotaAcumulativo(cursor.getString(0), this.id_acumulativo));

                datos.add(mipersona);
            }
        }
        return datos;

    }

    public double getNotaAcumulativo(String rne, int xidacumulativo) {
        SQLiteDatabase db = baseDatos.getReadableDatabase();
        double nota = 0;
        String sql = "select nota from notas_acumulativas where rne='" + rne + "' and id_acumulativo=" + xidacumulativo;
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.getCount() == 0) {
            guardarAcumulativo(rne, id_acumulativo);

        } else {
            while (cursor.moveToNext()) {
                nota = cursor.getDouble(0);
                Log.i("la nota de _" + rne + "es ", String.valueOf(nota));
            }
        }

        Log.i("depurar_", String.valueOf(nota));
        return nota;

    }

    public void guardarAcumulativo(String rne, int xidacumulativo) {
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        ContentValues nuevoRegistro = new ContentValues();
        try {
            nuevoRegistro.put("id_acumulativo", xidacumulativo);
            nuevoRegistro.put("rne", rne);
            nuevoRegistro.put("nota", 0.0);
            db.insert("notas_acumulativas", null, nuevoRegistro);
        } catch (android.database.sqlite.SQLiteException ex) {
            Log.i("dataPersona_", ex.getMessage().toString());
        }

    }

    public void actualizarNotaAcumulativa(String rne, int xidacumulativo, double nota) {
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        ContentValues actualizarRegistro = new ContentValues();

        try {
            actualizarRegistro.put("id_acumulativo", xidacumulativo);
            actualizarRegistro.put("rne", rne);
            actualizarRegistro.put("nota", nota);
            db.update("notas_acumulativas", actualizarRegistro, "id_acumulativo=" + xidacumulativo + " and rne='" + rne + "'", null);

        } catch (android.database.sqlite.SQLiteException ex) {
            Log.i("dataPersona_", ex.getMessage().toString());
        }

    }


}
