package com.datatecsolutions.edutools;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
        //guardar();
        list = (ListView) findViewById(R.id.listaasignaturas);
        NameValuePair[] datosLista = consultar("select id_clase,nombre from clase");
        miadaptador = new adaptadorListaAsignaturas(this, datosLista);
        list = (ListView) findViewById(R.id.listaasignaturas);
        list.setAdapter(miadaptador);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {
                //Object listItem = list.getItemAtPosition(position);
                String codigo_clase, nombre_asignatura;
                TextView v = (TextView) view.findViewById(R.id.codigoasignatura);
                TextView vasignatura = (TextView) view.findViewById(R.id.nombreasignatura);
                //Toast.makeText(getApplicationContext(),v.getText().toString(),Toast.LENGTH_LONG).show();
                codigo_clase = v.getText().toString();
                nombre_asignatura = vasignatura.getText().toString();
                Intent i = new Intent(getApplicationContext(), acumulativos.class);
                i.putExtra("Codigo", codigo_clase);
                i.putExtra("Asignatura", nombre_asignatura);
                startActivity(i);
            }
        });
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
