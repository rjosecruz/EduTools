package com.datatecsolutions.edutools;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.datatecsolutions.edutools.modelo.Persona;
import com.datatecsolutions.edutools.modelo.adaptadorListaAlumnos;

import java.nio.BufferUnderflowException;
import java.util.ArrayList;
import java.util.regex.Pattern;


public class evaluar extends ActionBarActivity {
    String codigo, nombre;
    Bundle extras;
    private ListView list;
    Persona alumno;
    private adaptadorListaAlumnos adaptador;
    Pattern patron;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluar);
        // patron=Pattern.compile("([1-9]{1}[0-9]{0,2}([0-9]{3})*(\\.[0-9]{0,2})?|[1-9]{1}[0-9]{0,}(\\.[0-9]{0,2})?|0(\\.[0-9]{0,2})?|(\\.[0-9]{1,2})?)");
        extras = getIntent().getExtras();
        codigo = extras.getString("codigo");
        alumno = new Persona(getApplicationContext(), Integer.parseInt(codigo));
        ArrayList<Persona> datos = alumno.listarPersona(codigo);
        adaptador = new adaptadorListaAlumnos(this, datos);
        EditText val = (EditText) findViewById(R.id.eTcalificacion);

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
