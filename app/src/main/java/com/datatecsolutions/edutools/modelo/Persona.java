package com.datatecsolutions.edutools.modelo;//Source file: C:\\Users\\DAVID\\Google Drive\\proyecto maestros\\Clases Java\\Persona.java


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

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

    /**
     * @roseuid 54C2A658005A
     */
    public Persona(Context context) {
        this.contexto = context;
        baseDatos = new EduToolsDb(context);
    }

    public ArrayList<Persona> listarPersona(String xcodigo) {
        ArrayList<Persona> datos = new ArrayList<Persona>();
        SQLiteDatabase db = baseDatos.getReadableDatabase();
        String sql = "SELECT alumnos.rne, alumnos.nombre, alumnos.apellido, alumnos.correo, alumnos.telefono, alumnos.sexo, alumnos.fechaNac, matricula.id_seccion, matricula.annio FROM alumnos, matricula WHERE alumnos.rne = matricula.rne and id_seccion=" + xcodigo;
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                Persona mipersona = new Persona(contexto);
                mipersona.setCodigo(String.valueOf(cursor.getInt(0)));
                mipersona.setPrimerNombre(cursor.getString(1));
                datos.add(mipersona);
            }
        }
        return datos;

    }


}
