package com.datatecsolutions.edutools.modelo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.Contacts;

import java.util.Vector;

/**
 * Created by rj on 09/02/2015.
 */


public class EduToolsDb extends SQLiteOpenHelper {

    public EduToolsDb(Context context) {
        super(context, "EduToolsDb", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table alumnos(rne integer primary key, nombre text, apellido text, correo text,telefono text,sexo text,fechaNac text)");
        db.execSQL("create table acumulativos(id_acumulativo integer autoincrement primary key, id_seccion integer, descripcion text, id_tipo_acumulativo text,fecha text,valor real,id_clase text)");
        db.execSQL("create table notas_acumulativas(id_acumulativo integer, rne text, nota real)");
        db.execSQL("create table tipo_acumulativos(id_tipo_acumulativo integer primary key, descripcion text)");
        db.execSQL("create table clase(id_clase integer primary key, nombre text)");
        db.execSQL("create table seccion(id_seccion integer primary key,id_modalidad text,curso text,seccion text,jornada text,id_cur text)");
        db.execSQL("create table modalidad(id_modalidad integer primary key,nombre text)");
        db.execSQL("create table asistenciaalumno(id_asistencia integer primary key,rne,asistencia integer)");
        db.execSQL("create table asistencia(id_asistencia integer primary key,id_seccion integer,id_clase integer,fecha text)");
        db.execSQL("create table matricula(rne integer,id_seccion integer,annio integer)");
        db.execSQL("create table nota(rne integer,id_clase integer,nota casa numeric,nota_clase numeric,examen numeric,parcial integer,annio integer,id_seccion integer)");
        db.execSQL("create table clase_secciones(id_seccion integer,id_clase integer)");
        db.execSQL("create table encargado(id_encargado integer primary key,nombre text,apellido text,telefono text,direccion text,identidad integer)");
        db.execSQL("create table encargado_alumno(id_encargado integer,rne integer)");
        db.execSQL("create table docente(id_prof integer primary key,nombre text,apellido text,direccion text,telefono text,email text,id_sace text, pass_sace text)");
        db.execSQL("create table instituto(id_instituto integer primary key,nombre_instituto text)");
        preCargar();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnte, int versionNue) {
        db.execSQL("drop table if exists alumnos");
        db.execSQL("drop table if exists acumulativos");
        db.execSQL("drop table if exists notas_acumulativas");
        db.execSQL("drop table if exists tipo_acumulativos");
        db.execSQL("drop table if exists clase");
        db.execSQL("drop table if exists seccion");
        db.execSQL("drop table if exists modalidad");
        db.execSQL("drop table if exists asistenciaalumno");
        db.execSQL("drop table if exists asistencia");
        db.execSQL("drop table if exists matricula");
        db.execSQL("drop table if exists nota");
        db.execSQL("drop table if exists clase_secciones");
        db.execSQL("drop table if exists encargado");
        db.execSQL("drop table if exists encargado_alumno");
        db.execSQL("drop table if exists docente");
        db.execSQL("drop table if exists instituto");

        db.execSQL("create table alumnos(rne integer primary key, nombre text, apellido text, correo text,telefono text,sexo text,fechaNac text)");
        db.execSQL("create table acumulativos(id_acumulativo integer autoincrement primary key, id_seccion integer, descripcion text, id_tipo_acumulativo text,fecha text,valor real,id_clase text)");
        db.execSQL("create table notas_acumulativas(id_acumulativo integer, rne text, nota real)");
        db.execSQL("create table tipo_acumulativos(id_tipo_acumulativo integer primary key, descripcion text)");
        db.execSQL("create table clase(id_clase integer primary key, nombre text)");
        db.execSQL("create table seccion(id_seccion integer primary key,id_modalidad text,curso text,seccion text,jornada text,id_cur text)");
        db.execSQL("create table modalidad(id_modalidad integer primary key,nombre text)");
        db.execSQL("create table asistenciaalumno(id_asistencia integer primary key,rne,asistencia integer)");
        db.execSQL("create table asistencia(id_asistencia integer primary key,id_seccion integer,id_clase integer,fecha text)");
        db.execSQL("create table matricula(rne integer,id_seccion integer,annio integer)");
        db.execSQL("create table nota(rne integer,id_clase integer,nota casa numeric,nota_clase numeric,examen numeric,parcial integer,annio integer,id_seccion integer)");
        db.execSQL("create table clase_secciones(id_seccion integer,id_clase integer)");
        db.execSQL("create table encargado(id_encargado integer primary key,nombre text,apellido text,telefono text,direccion text,identidad integer)");
        db.execSQL("create table encargado_alumno(id_encargado integer,rne integer)");
        db.execSQL("create table docente(id_prof integer primary key,nombre text,apellido text,direccion text,telefono text,email text,id_sace text, pass_sace text)");
        db.execSQL("create table instituto(id_instituto integer primary key,nombre_instituto text)");
        preCargar();
    }

    public void preCargar() {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("insert into tipo_acumulativos values(1,'Clase')");
        db.execSQL("insert into tipo_acumulativos values(2,'Casa')");
        db.execSQL("insert into tipo_acumulativos values(3,'Examen')");
    }


}
