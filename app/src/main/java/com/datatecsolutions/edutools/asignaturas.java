package com.datatecsolutions.edutools;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.datatecsolutions.edutools.modelo.EduToolsDb;
import com.datatecsolutions.edutools.modelo.adaptadorListaAsignaturas;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.sql.SQLException;
import java.util.ArrayList;


public class asignaturas extends ActionBarActivity {
    private ListView list;
    adaptadorListaAsignaturas miadaptador;
    private EduToolsDb baseDatos = new EduToolsDb(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asignaturas);

        list = (ListView) findViewById(R.id.listaasignaturas);
        NameValuePair[] datosLista = consultar("select id_clase,nombre from clase");
        miadaptador = new adaptadorListaAsignaturas(this, datosLista);
        list = (ListView) findViewById(R.id.listaasignaturas);
        list.setAdapter(miadaptador);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_asignaturas, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
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


}
