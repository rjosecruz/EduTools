package com.datatecsolutions.edutools;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.datatecsolutions.edutools.modelo.Persona;
import com.datatecsolutions.edutools.modelo.adaptadorListaAlumnos;

import java.nio.BufferUnderflowException;
import java.util.ArrayList;


public class evaluar extends ActionBarActivity {
    String codigo, nombre;
    Bundle extras;
    private ListView list;
    Persona alumno;
    private adaptadorListaAlumnos adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluar);
        alumno = new Persona(getApplicationContext());
        extras = getIntent().getExtras();
        codigo = extras.getString("codigo");
        ArrayList<Persona> datos = alumno.listarPersona(codigo);
        adaptador = new adaptadorListaAlumnos(this, datos);
        list = (ListView) findViewById(R.id.listEvaluarAcum);
        list.setAdapter(adaptador);
        //extras = getIntent().getExtras();

        //nombre = extras.getString("Asignatura");
        //Toast.makeText(getApplicationContext(), codigo, Toast.LENGTH_LONG).show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_evaluar, menu);
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
}
