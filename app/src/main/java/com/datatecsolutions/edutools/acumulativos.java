package com.datatecsolutions.edutools;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.datatecsolutions.edutools.modelo.EduToolsDb;
import com.datatecsolutions.edutools.modelo.adaptadorListaAcumulativos;
import com.datatecsolutions.edutools.modelo.adaptadorListaAsignaturas;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;


public class acumulativos extends ActionBarActivity {
    Bundle extras;
    String codigo, nombre;
    private EduToolsDb baseDatos;
    private ListView list;
    NameValuePair[] datosLista;
    adaptadorListaAcumulativos miadaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acumulativos);
        extras = getIntent().getExtras();
        codigo = extras.getString("Codigo");
        nombre = extras.getString("Asignatura");
        baseDatos = new EduToolsDb(this);
        datosLista = consultar("select id_acumulativo,descripcion from acumulativos");
        miadaptador = new adaptadorListaAcumulativos(this, datosLista);
        list = (ListView) findViewById(R.id.listaasignaturas);
        //list.setAdapter(miadaptador);

        Toast.makeText(getApplicationContext(), datosLista.length, Toast.LENGTH_LONG).show();

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

        }

        return super.onOptionsItemSelected(item);
    }

    public NameValuePair[] consultar(String sqlQuery) {
        // Toast.makeText(getApplicationContext(),sqlQuery,Toast.LENGTH_LONG).show();
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
}
