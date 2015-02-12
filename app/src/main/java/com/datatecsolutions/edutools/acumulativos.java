package com.datatecsolutions.edutools;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.datatecsolutions.edutools.modelo.EduToolsDb;
import com.datatecsolutions.edutools.modelo.adaptadorListaAcumulativos;


import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.sql.SQLException;
import java.util.ArrayList;


public class acumulativos extends ActionBarActivity {
    private ListView list;
    private adaptadorListaAcumulativos miadaptador;
    private EduToolsDb baseDatos = new EduToolsDb(this);
    Bundle extras;
    String codigo, nombre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acumulativos);
        //guardar();
        extras = getIntent().getExtras();
        codigo = extras.getString("Codigo");
        nombre = extras.getString("Asignatura");
        list = (ListView) findViewById(R.id.listaAcumulativos);
        NameValuePair[] datosLista = consultar("select id_acumulativo,descripcion from acumulativos where id_seccion=" + codigo);
        miadaptador = new adaptadorListaAcumulativos(this, datosLista);
        list = (ListView) findViewById(R.id.listaAcumulativos);
        list.setAdapter(miadaptador);
        registerForContextMenu(list);

    }

    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.contextmenuacum, menu);
    }

    public boolean onContextItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.crear_acumulativo:

                return true;
            case R.id.editar:

                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_acumulativos, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.crear_acumulativo) {
            Intent i = new Intent(getApplicationContext(), crear_acumulativo.class);
            i.putExtra("codigo_clase", codigo);
            i.putExtra("nombre_clase", nombre);
            startActivity(i);
            //return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public NameValuePair[] consultar(String sqlQuery) {
        ArrayList<String> resultado = new ArrayList<String>();
        SQLiteDatabase db = baseDatos.getReadableDatabase();

        Cursor datos;
        datos = db.rawQuery(sqlQuery, null);
        NameValuePair[] datosLista = new NameValuePair[datos.getCount()];
        int y = 0;

        while (datos.moveToNext()) {
            resultado.add(datos.getString(1));

            datosLista[y] = new BasicNameValuePair(Integer.toString(datos.getInt(0)), datos.getString(1));
            y++;
        }

        return datosLista;

    }

    public void guardar() {
        try {
            SQLiteDatabase db = baseDatos.getWritableDatabase();
            db.execSQL("Insert into clase(id_clase,nombre) values(1,'Informatica I')");
            db.execSQL("Insert into clase(id_clase,nombre) values(2,'Informatica II')");
            db.execSQL("Insert into clase(id_clase,nombre) values(3,'Progamacion Estructura II')");
            db.execSQL("Insert into clase(id_clase,nombre) values(4,'Logica Formal')");
            db.execSQL("Insert into clase(id_clase,nombre) values(4,'Base de Datos')");
            db.execSQL("Insert into clase(id_clase,nombre) values(5,'Programacion de IA')");
        } catch (android.database.sqlite.SQLiteException ex) {
            Log.i("dataops_", ex.getMessage().toString());
        }
    }


}
