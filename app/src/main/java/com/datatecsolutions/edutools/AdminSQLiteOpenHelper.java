package com.datatecsolutions.edutools;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by DAVID on 07/02/2015.
 */
public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {

    public AdminSQLiteOpenHelper(Context context, String nombre, CursorFactory factory, int version) {
        super(context, nombre, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table alumnos(rne integer primary key, nombre text, apellido text, correo text,telefono text,sexo text,fechaNac text)");
        db.execSQL("create table acumulativos(id_acumulativo integer primary key, id_seccion integer, descripcion text, id_tipo_acumulativo text,fecha text,valor real,id_clase text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnte, int versionNue) {
        db.execSQL("drop table if exists alumnos");
        db.execSQL("drop table if exists acumulativos");
        db.execSQL("create table alumnos(dni integer primary key, nombre text, colegio text, nromesa integer)");
        db.execSQL("create table acumulativos(id_acumulativo integer primary key, id_seccion integer, descripcion text, id_tipo_acumulativo text,fecha text,valor real,id_clase text)");
    }
}
