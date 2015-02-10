package com.datatecsolutions.edutools;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.datatecsolutions.edutools.modelo.EduToolsDb;

import java.util.ArrayList;


public class clases extends ActionBarActivity {
    private ListView list;
    private EduToolsDb baseDatos = new EduToolsDb(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clases);
        list = (ListView) findViewById(R.id.listaclases);

        ArrayList<String> res = consultar("select id_clase,nombre from clase");

        //insertar();

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, res);
        list.setAdapter(adaptador);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.


        getMenuInflater().inflate(R.menu.menu_clases, menu);

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

    public ArrayList<String> consultar(String sqlQuery) {
        ArrayList<String> resultado = new ArrayList<String>();
        SQLiteDatabase db = baseDatos.getReadableDatabase();

        Cursor datos;
        datos = db.rawQuery(sqlQuery, null);
        while (datos.moveToNext()) {
            resultado.add(datos.getString(1));
        }
        return resultado;

    }

    public Cursor consultarColegio(String sqlQuery) {

        SQLiteDatabase db = baseDatos.getReadableDatabase();

        Cursor datos;
        datos = db.rawQuery(sqlQuery, null);

        return datos;

    }


}
